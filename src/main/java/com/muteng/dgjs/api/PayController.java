package com.muteng.dgjs.api;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.muteng.dgjs.DTO.Relation;
import com.muteng.dgjs.common.utils.Staffvalidator;
import com.muteng.dgjs.dao.UserMapper;
import com.muteng.dgjs.domain.OrdersPaylog;
import com.alibaba.fastjson.JSON;
import com.muteng.dgjs.DTO.Paymentvoucher;
import com.muteng.dgjs.dao.CaptchaMapper;
import com.muteng.dgjs.domain.*;
import com.muteng.dgjs.service.*;
import com.tianwu.dubbo.service.intf.OrderIntf;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.muteng.dgjs.common.utils.AliAppPayUtil;
import com.muteng.dgjs.common.utils.CommonUtil;
import com.muteng.dgjs.common.utils.WxpayUtil;
import com.tianwu.dubbo.service.intf.OrderIntf;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value="/api",produces = {"application/json;charset=utf-8"})
@ResponseBody
@Api(value="支付接口",description="支付接口")
public class PayController {
	private static final Logger logger = LoggerFactory.getLogger(PayController.class);
	@Resource
	private PayService payService;
	@Resource
	private UserMapper userMapper;
	@Resource
	private PlatformTransactionManager transactionManager;
	@Resource
    private OrdersMemberService orderMemberService;
	@Resource
	private UserService userService;
	@Resource
	private UserinfoService userinfoService;
	@Resource
	private CaptchaMapper captchaMapper;
	@Resource
	private AccountService accountService;
	@Resource
	private Common common;
	@Resource
	private RelationService relationService;
	@Resource
	private OrderIntf orderIntf;

	@PostMapping("/alipay")
	@ApiOperation(value="支付宝APP",httpMethod="POST")
	public String aliPay(@RequestParam("userId") Long userId,@RequestParam("source") String source,
			@RequestParam("subsource") String subsource) throws UnsupportedEncodingException {
		User us = userService.getUserById(userId);
		if (us != null) {
			OrdersMember om =orderMemberService.queryorderById(us.getId());
			if(om != null && om.getStatus()== 2 && om.getFinishtime().after(new Date())){
				return "您已成为会员，请勿重复购买";
			}
		}
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("source", source);
		paramMap.put("subsource", subsource);
		paramMap.put("userid", userId);
		Object[] obj = this.orderMemberService.addOrderMember(paramMap);
		Map<String,Object> res = (Map<String,Object>)obj[1];
		Long orderNo = (Long)res.get("orderNo");
		Long orderid = (Long)res.get("orderNo");
		OrdersMember om = payService.getOrder(orderNo);
		if (null == om) {
			return "订单不存在";
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("body", String.valueOf(orderNo) + "," + userId+","+orderid);
		map.put("subject", new String("购买会员"));
		map.put("out_trade_no", new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis()));
		map.put("total_amount", String.valueOf(CommonUtil.convertYuan(om.getShouldpayprice())));
		try {
			String response = AliAppPayUtil.order(map);
			System.out.println("response==="+response);
			if (StringUtils.isEmpty(response)) {
				throw new RuntimeException("支付返回值为空");
			}
			return response;

		} catch (Exception e) {
			e.printStackTrace();
			payService.updateOrdersMember(String.valueOf(orderNo));
			payService.deleteorderByoderNo(String.valueOf(orderNo));
			payService.deleteorderListByoderNo(String.valueOf(orderNo));
			return "订单支付异常";
		}

	}

	@PostMapping("/wxpay")
	@ResponseBody
	@ApiOperation(value="微信APP， 公众号",httpMethod="POST")
	public String wxapp(@ApiParam(value="openid", required=true) @RequestParam(name="openid", required=false)String openid,
						@ApiParam(value = "会员id", required = true) @RequestParam("userId") Long userId,
						@ApiParam(value = "终端", required = true) @RequestParam("source") String source,
						@ApiParam(value = "渠道来源", required = true) @RequestParam("subsource") String subsource,
						@ApiParam(value = "1-微信APP, 2-微信公众号", required = true) @RequestParam("payType") Integer payType,
						@ApiParam(value = "手机号", required = false) @RequestParam(name = "loginname", required = false) String loginname,
						@ApiParam(value = "短信验证码", required = false) @RequestParam(name = "smsMessage", required = false) String smsMessage,
						@ApiParam(value = "邀请人手机号", required = false) @RequestParam(name = "sharephone", required = false) String sharephone) {
		Map<String, Object> pmap = new HashMap<String, Object>();
		JSONObject jsonObj = new JSONObject();
		pmap.put("phone", loginname);
		Captcha captcha = this.captchaMapper.queryByPhone(pmap);
		String id = "";
		String is = "0";
		String st = this.userMapper.queryStaffByPhone(sharephone);
		if(st != null){
			is= "1";
		}
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);
		UserInfo info = userinfoService.getInfoByopenid(openid);
		if (null != payType && payType.intValue() == 2) { //微信公众号
			try {
				if (captcha != null) {
					long effecttime = captcha.getEffectivetime();
					long catptchatime = captcha.getCreatetime().getTime();
					long nowtime = new Date().getTime();

					if (nowtime > (catptchatime + effecttime)) {
						Map<String, Object> map = new HashMap<>();
						map.put("code", 0);
						map.put("msg", "短信验证码过期");
						map.put("obj", "失败");
						transactionManager.rollback(status);//事务回滚
						return jsonObj.toJSONString(map);
					} else {
						if (captcha.getCaptcha().intValue() == Integer.parseInt(smsMessage)) {
							User us = userService.getUserByPhone(loginname);
							if(us != null){
								OrdersMember om =orderMemberService.queryorderById(us.getId());
								if(om != null && om.getStatus()== 2 && om.getFinishtime().after(new Date())){
									Map<String, Object> map = new HashMap<>();
									map.put("code", 0);
									map.put("msg", "您已成为会员，请勿重复购买 ");
									map.put("obj", "失败");
									transactionManager.rollback(status);//事务回滚
									return jsonObj.toJSONString(map);
								}
								id = String.valueOf(us.getId());
								if(info == null){
									Relation re = this.relationService.getRelationByopenid(openid);
									UserInfo ui = new UserInfo();
									ui.setUserid(userId);
									ui.setHeadimgurl(re.getHeadimgurl());
									ui.setNickname(re.getNickname());
									ui.setWeixin_openid(openid);
									ui.setCreatetime(new Date());
									userinfoService.addUserinfo(ui);
								}
							}else{
								User u = new User();
								u.setParentid(userId);
								u.setIsinviter("是");
								u.setCreatetime(new Date());
								u.setLoginname(loginname);
								u.setLoginstatus(1);
								id = String.valueOf(userService.insertuser(u));
								if(info == null){
									Relation re = this.relationService.getRelationByopenid(openid);
									UserInfo ui = new UserInfo();
									ui.setUserid(Long.parseLong(id));
									ui.setHeadimgurl(re.getHeadimgurl());
									ui.setNickname(re.getNickname());
									ui.setWeixin_openid(openid);
									ui.setCreatetime(new Date());
									userinfoService.addUserinfo(ui);
								}
							}
							Object[] obj;
							Map<String, Object> paramMap = new HashMap<String, Object>();
							paramMap.put("userid", id);
							paramMap.put("source", source);
							paramMap.put("subsource", subsource);
							paramMap.put("phone", loginname);
							paramMap.put("loginname", loginname);
							paramMap.put("openid", openid);
							obj = this.orderMemberService.addOrderMember2(paramMap);

							Map<String, Object> res = (Map<String, Object>) obj[1];
							Long orderNo = (Long) res.get("orderNo");
							OrdersMember member = payService.getOrder(orderNo);
							if (null == orderNo) {
								Map<String, Object> map = new HashMap<>();
								map.put("code", 0);
								map.put("msg", "订单不存在 ");
								map.put("obj", "失败");
								transactionManager.rollback(status);//事务回滚
								return jsonObj.toJSONString(map);
							}
							String out_trade_no = String.valueOf(orderNo) + "-" + userId + "-" + payType;
							String total_amount = String.valueOf(member.getShouldpayprice());
							List<Map<String, String>> payResultList;
							String attach =is;
							try {
								payResultList = WxpayUtil.jsPay(openid, out_trade_no + "-" + id, total_amount,attach);
								if (payResultList != null) {
									Map<String, Object> map = new HashMap<>();
									map.put("code", "1");
									map.put("msg", "成功");
									map.put("obj", JSON.toJSON(payResultList.get(0)));
									System.out.println(jsonObj.toJSONString(map));
									transactionManager.commit(status);
									return jsonObj.toJSONString(map);
								} else {
									Map<String, Object> map = new HashMap<>();
									map.put("code", 0);
									map.put("msg", "支付异常 ");
									map.put("obj", "失败");
									transactionManager.rollback(status);//事务回滚
									return jsonObj.toJSONString(map);
								}
							} catch (Exception e) {
								e.printStackTrace();
								userinfoService.deleteUserInfo(openid);
								userService.deleteUserByPhone(loginname);
								payService.deleteorderListByoderNo(String.valueOf(orderNo));
								Map<String, Object> map = new HashMap<>();
								map.put("code", 0);
								map.put("msg", "订单异常");
								map.put("obj", "失败");
								transactionManager.rollback(status);//事务回滚
								return jsonObj.toJSONString(map);
							}
						} else {
							Map<String, Object> map = new HashMap<>();
							map.put("code", 0);
							map.put("msg", "短信验证码错误");
							map.put("obj", "失败");
							transactionManager.rollback(status);//事务回滚
							return jsonObj.toJSONString(map);
						}
					}
				} else {
					Map<String, Object> map = new HashMap<>();
					map.put("code", 0);
					map.put("msg", "输入短信验证码");
					map.put("obj", "失败");
					transactionManager.rollback(status);//事务回滚
					return jsonObj.toJSONString(map);
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e);
				transactionManager.rollback(status);//事务回滚
				Map<String, Object> map = new HashMap<>();
				map.put("code", 0);
				map.put("msg", "支付异常");
				map.put("obj", "失败");
				return jsonObj.toJSONString(map);
			}
		} else {//微信APP
			User us = userService.getUserById(userId);
			if (us != null) {
				OrdersMember om =orderMemberService.queryorderById(us.getId());
				if(om != null && om.getStatus()== 2 && om.getFinishtime().after(new Date())){
					Map<String, Object> map = new HashMap<>();
					map.put("code", 0);
					map.put("msg", "您已成为会员，请勿重复购买");
					map.put("obj", "失败");
					transactionManager.rollback(status);//事务回滚
					return jsonObj.toJSONString(map);
				}
			}
			Object[] obj;
			User user = userService.getUserById(userId);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("source", source);
			paramMap.put("subsource", subsource);
			paramMap.put("userid", userId);
			paramMap.put("openid", openid);
			obj = this.orderMemberService.addOrderMember(paramMap);

			Map<String, Object> res = (Map<String, Object>) obj[1];
			Long orderNo = (Long) res.get("orderNo");
			OrdersMember member = payService.getOrder(orderNo);
			if (null == orderNo) {
				Map<String, Object> map = new HashMap<>();
				map.put("code", 0);
				map.put("msg", "订单不存在");
				map.put("obj", "失败");
				transactionManager.rollback(status);//事务回滚
				return jsonObj.toJSONString(map);
			}
			String out_trade_no = String.valueOf(orderNo) + "-" + userId + "-" + payType;
			String total_amount = String.valueOf(member.getShouldpayprice());
			List<Map<String, String>> payResultList;
			try {
				payResultList = WxpayUtil.appPay(out_trade_no, total_amount);
				System.out.println("payResultListpayResultList" + payResultList);
				if (payResultList != null) {
					transactionManager.commit(status);
					return jsonObj.toJSONString(payResultList.get(0));
				} else {
					Map<String, Object> map = new HashMap<>();
					map.put("code", 0);
					map.put("msg", "支付异常");
					map.put("obj", "失败");
					transactionManager.rollback(status);//事务回滚
					return jsonObj.toJSONString(map);
				}
			} catch (Exception e) {
				e.printStackTrace();
				Map<String, Object> map = new HashMap<>();
				map.put("code", 0);
				map.put("msg", "订单异常");
				map.put("obj", "失败");
				transactionManager.rollback(status);//事务回滚
				return jsonObj.toJSONString(map);
			}
		}
	}


	@PostMapping(value="/alipay/notify", produces="text/plain;charset=utf-8")
	@ResponseBody
	@ApiOperation(value="支付宝回调",httpMethod="POST")
	public String alipayNotify(HttpServletRequest request) throws UnsupportedEncodingException{
		String tradeStatus = request.getParameter("trade_status");
		if (tradeStatus != null && (tradeStatus.equals("WAIT_SELLER_SEND_GOODS") || tradeStatus.equals("WAIT_BUYER_CONFIRM_GOODS") || tradeStatus.equals("TRADE_FINISHED") || tradeStatus.equals("TRADE_SUCCESS"))) {
			Map<String, String> params = generatorMap(request);
			boolean flag = false;
			try {
				flag = AlipaySignature.rsaCheckV1(params, AliAppPayUtil.aliPublicKey, "UTF-8", "RSA2");
			} catch (AlipayApiException e1) {
				logger.error("支付宝验证签名异常");
				e1.printStackTrace();
				return "fail";
			}
			if (flag) {
				String[] array = request.getParameter("body").split(",");
				String amount = request.getParameter("total_amount"); //单位元
				String buyer_id = request.getParameter("buyer_id");//支付宝账号
				String trade_no = request.getParameter("trade_no");//支付宝流水号
				String orderNo = array[0];
				String userId = array[1];
				String orderid = array[2];
				/**事务处理**/
				DefaultTransactionDefinition def = new DefaultTransactionDefinition();
				def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
				TransactionStatus status = transactionManager.getTransaction(def);
				try {
					/**业务处理部分**/
					//修改订单表订单状态
					payService.updateOrderListStatus(Long.parseLong(orderNo), "已付款");
					//修改会员订单表状态
					payService.updateOrdersMemberStatus(Long.parseLong(orderNo), 2, amount);
					//修改用户状态
					User u = new User();
					u.setId(Long.parseLong(userId));
					u.setUsertype(8);
					u.setAlipay(buyer_id);
					u.setUpdatetime(new Date());
					u.setLoginstatus(1);
					userService.updateByPrimaryKeySelective(u);
					//添加收款凭证
					Receiptvoucher rv = new Receiptvoucher();
					rv.setOrderid(Long.parseLong(orderid));
					rv.setOrdertype("会员");
                    rv.setPrice(CommonUtil.convertCent(Double.parseDouble(amount)));
					rv.setCreatetime(new Date());
					rv.setUserid(Long.parseLong(userId));
					String code = common.getReceiptVoucher("购买会员订单","收款凭证");
					rv.setCode(code);
					rv.setTrade_no(trade_no);
					rv.setName("系统");
					rv.setComment("购买会员充值");
					userMapper.addreceipt(rv);
					//添加收款单
					ReceiptDocument rd = new ReceiptDocument();
					rd.setOrderid(Long.parseLong(orderid));
					rd.setOrdertype("会员");
					rd.setUserid(Long.parseLong(userId));
                    rd.setPrice(CommonUtil.convertCent(Double.parseDouble(amount)));
					rd.setName("系统");
					rd.setMethod("支付宝");
					rd.setComment("购买会员充值");
					String vcode = common.getReceiptDocument("购买会员订单","收款单据");
					rd.setCreatetime(new Date());
					rd.setCode(vcode);
					rd.setTrade_no(trade_no);
					rd.setReturnfeecode(code);
					userMapper.addIncomelog(rd);
					ReceiptDocument oi = new ReceiptDocument();
					userMapper.addIncomelog(oi);
					//插入账单
					insertAccount(userId, amount, 3, "购买会员",14);
					insertAccount(userId, amount, 2, "支付宝充值",15);
					//insertOrdersIncomelog(orderid, userId, amount, orderNo);
					transactionManager.commit(status);
					return "success";
				} catch (Exception e) {
					e.printStackTrace();
					transactionManager.rollback(status); //事务回滚
					payService.updateOrdersMember(orderNo);
					payService.deleteorderByoderNo(orderNo);
					payService.deleteorderListByoderNo(orderNo);
					logger.error("业务处理异常");
					return "fail";
				}
			} else {
				logger.error("回调签名验证失败");
				return "fail";
			}
		} else {
			return "fail";
		}
	}

	
	@PostMapping(value="/wxpay/notify", produces="text/plain;charset=utf-8")
	@ResponseBody
	@ApiOperation(value="微信回调",httpMethod="POST")
	public String wxpayNotify(HttpServletRequest request){
		  try {
		    String notifyXml = "";
		    String inputLine = "";
		    while ((inputLine = request.getReader().readLine()) != null)
		      notifyXml += inputLine;
		    request.getReader().close();
		    if (WxpayUtil.checkSign(notifyXml)) {
		      String trade = notifyXml.replaceAll("^.+<out_trade_no><!\\[CDATA\\[(.+?)\\]\\]></out_trade_no>.+$", "$1");
		      String[] array = trade.split("-");
				System.out.println(array.toString());
				String orderNo = array[0];
				String userId = array[1];
				String paytype = array[2];
				String attach=notifyXml.replaceAll("^.+<attach><!\\[CDATA\\[(.+?)\\]\\]></attach>.+$", "$1");
				String transaction_id=notifyXml.replaceAll("^.+<transaction_id><!\\[CDATA\\[(.+?)\\]\\]></transaction_id>.+$", "$1");
				String amount = notifyXml.replaceAll("^.+<total_fee><!\\[CDATA\\[(.+?)\\]\\]></total_fee>.+$", "$1");
				System.out.println("orderNo===="+orderNo);
				System.out.println("transaction_id===="+transaction_id);
				System.out.println("userId===="+userId);
				System.out.println("amount===="+amount);
				System.out.println("paytype===="+paytype);
				System.out.println("attach===="+attach);
				String orderid=orderMemberService.queryorderByNo(orderNo);
				System.out.println("orderid===="+orderid);
		      /**事务处理**/
		      DefaultTransactionDefinition def = new DefaultTransactionDefinition();
			  def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			  TransactionStatus status = transactionManager.getTransaction(def);
		      try {
		    	  /**业务处理部分**/
                  if(Integer.parseInt(paytype) == 2){//公众号
					  String id = array[3];
					  User u = new User();
					  User bu= userService.getUserById(Long.parseLong(id));
					  //判断邀请人不是员工且和付款人是否是同一人，不是则添加邀请人奖励
					  if (attach.equals("0") && userId != id) {
					  	  //添加订单关系
						  Map<String,String> paramMap = new HashMap<String,String>();
						  paramMap.put("orderid",orderid);
						  paramMap.put("ordertype","会员");
						  paramMap.put("inviteduserid",userId);
						  int value = this.orderIntf.handlerMembership(paramMap);
						  //邀请人增加付款凭证
						  Paymentvoucher pm = new Paymentvoucher();
						  pm.setOrderid(Long.parseLong(orderid));
						  pm.setOrderuserid(Long.parseLong(userId));
						  pm.setOrdertype("会员");
						  pm.setPaytype("邀请人");
						  pm.setPrice(14000);
						  pm.setName("app");
						  pm.setOrderusername(userId);
						  pm.setContent("介绍"+bu.getLoginname()+"购买会员");
						  pm.setCreatetime(new Date());
						  String code = common.getPaymentVoucher("购买会员订单","付款凭证");
						  pm.setCode(code);
						  pm.setPeopletype("介绍人");
						  accountService.addpaym(pm);
						  //邀请人增加付款单
						  OrdersPaylog op = new OrdersPaylog();
						  op.setOrderid(Long.parseLong(orderid));
						  op.setOrdertype("会员");
						  op.setAmounttype("介绍费");
						  op.setUserid(Long.parseLong(userId));
						  op.setMethod("app充值");
						  op.setAmount(14000);
						  op.setCreatetime(new Date());
						  op.setComment("介绍"+bu.getLoginname()+"购买会员");
						  op.setPaycode(code);
						  String pcode = common.getPaymentDocument("购买会员订单","付款单据");
						  op.setCode(pcode);
						  accountService.addop(op);
					  	  //邀请人增加账单
						  insertAccount(userId, "140", 2, "邀请"+bu.getLoginname()+"购买会员",16);
						  //修改邀请人账户余额
						  User u1 = userService.getUserById(Long.parseLong(userId));
						  u1.setId(Long.parseLong(userId));
						  System.out.println(u1);
						  System.out.println(u1.getAccount());
						  u1.setAccount(u1.getAccount() + 14000);
						  userService.updateByPrimaryKeySelective(u1);
						  System.out.println(u);
						  u.setParentid(Long.parseLong(userId));
						  u.setIsinviter("是");
					  }
					  u.setId(Long.parseLong(id));
					  u.setUsertype(8);
					  u.setUpdatetime(new Date());
					  u.setLoginstatus(1);
					  userService.updateByPrimaryKeySelective(u);
					  //添加购买人账单
					  insertAccount(id, amount, 3, "购买会员",14);
					  insertAccount(id, amount, 2, "微信支付充值",15);
					  //添加收款凭证
					  Receiptvoucher rv = new Receiptvoucher();
					  rv.setOrderid(Long.parseLong(orderid));
					  rv.setOrdertype("会员");
                      rv.setPrice(CommonUtil.convertCent(Double.parseDouble(amount)));
					  rv.setCreatetime(new Date());
					  rv.setUserid(Long.parseLong(userId));
					  String code = common.getReceiptVoucher("购买会员订单","收款凭证");
					  rv.setCode(code);
					  rv.setTrade_no(transaction_id);
					  rv.setName("系统");
					  rv.setComment("购买会员充值");
					  userMapper.addreceipt(rv);
					  //添加收款单
					  ReceiptDocument rd = new ReceiptDocument();
					  rd.setOrderid(Long.parseLong(orderid));
					  rd.setOrdertype("会员");
					  rd.setUserid(Long.parseLong(userId));
					  rd.setPrice(CommonUtil.convertCent(Double.parseDouble(amount)));
					  rd.setName("系统");
					  rd.setMethod("微信");
					  rd.setComment("购买会员充值");
					  String vcode = common.getReceiptDocument("购买会员订单","收款单据");
					  rd.setCreatetime(new Date());
					  rd.setCode(vcode);
					  rd.setTrade_no(transaction_id);
					  rd.setReturnfeecode(code);
					  userMapper.addIncomelog(rd);
					  //修改会员订单状态
					  payService.updateOrderListStatus(Long.parseLong(orderNo), "已付款");
					  payService.updateOrdersMemberStatus(Long.parseLong(orderNo), 2,amount);
				  }else{//微信APP
		    	  payService.updateOrderListStatus(Long.parseLong(orderNo), "已付款");
		    	  payService.updateOrdersMemberStatus(Long.parseLong(orderNo), 2,amount);
		    	  User u = new User();
				  u.setId(Long.parseLong(userId));
				  u.setUsertype(8);
				  u.setUpdatetime(new Date());
				  u.setLoginstatus(1);
				  userService.updateByPrimaryKeySelective(u);
		    	  insertAccount(userId, amount,2,"微信支付充值",15);
				  insertAccount(userId, amount,3, "购买会员",14);
		    	  //  insertOrdersIncomelog( userId, amount, orderNo);
				  }
		    	  transactionManager.commit(status);
		    	  return "success";
		      }
		      catch (Exception e) {
		      	  e.printStackTrace();
				  transactionManager.rollback(status);//事务回滚
				  payService.updateOrdersMember(orderNo);
				  payService.deleteorderByoderNo(orderNo);
				  payService.deleteorderListByoderNo(orderNo);
		    	return "fail";
		      }
		    } else {

		    	logger.error("回调验证签名失败");
		    	return "fail";
		    }
		  } catch (Exception e) {

			logger.error("微信支付回调异常");
			e.printStackTrace();
		    return "fail";
		  }
	}

	private int insertAccount(String userId, String amount, Integer type,String payType,Integer reason) {
		Long userIdLong = Long.parseLong(userId);
		Integer amountLong= CommonUtil.convertCent(Double.parseDouble(amount));
		Account account = new Account();
		account.setUserid(userIdLong);
		account.setAmount( amountLong);
		account.setType(type);
		account.setReason(reason);
		account.setCreatetime(new Date());
		account.setUpdatetime(new Date());
		account.setStatus(3);
		account.setComment(payType);
		return payService.insertAccount(account);
	}
	
	private int insertOrdersIncomelog(String orderid, String userid, String amount, String orderNo) {
		Map<String, Object> map = payService.getOrderAndProductInfo(Long.parseLong(orderid));
		Long accessuserid = (Long) map.get("accessuserid");
		Long productid = (Long) map.get("productid");
		String title = (String) map.get("title");
		String ordertype = (String) map.get("ordertype");
		String name = (String) map.get("name");
		
		return payService.insertOrdersIncomelog(Long.parseLong(orderid), accessuserid, ordertype, 
										 Long.parseLong(userid), ((int)(Double.parseDouble(amount) * 100)), "全款", "支付宝", 
										new Date(), "支付宝APP支付", Long.parseLong(orderNo), productid, title, 
										"", name, "", new Date(), "");
		
	}
	private Map<String, String> generatorMap(HttpServletRequest request) throws UnsupportedEncodingException {
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		Set<String> keySet = requestParams.keySet();
		Iterator<String> iterator = keySet.iterator();
		while (iterator.hasNext()) {
		  String key = iterator.next();
		  String[] values = requestParams.get(key);
		  String allValue = "";
		  for (int i = 0, len = values.length; i < len; i++) {
		    allValue += values[i];
		    if (i != len - 1)
		      allValue += ",";
		  }
		  allValue = new String(allValue);
		  params.put(key, allValue);
		}
		return params;
	}
	
}
