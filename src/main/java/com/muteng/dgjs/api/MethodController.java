package com.muteng.dgjs.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.muteng.dgjs.DTO.*;
import com.muteng.dgjs.DTO.Relation;
import com.muteng.dgjs.common.system.BaseResponse;
import com.muteng.dgjs.common.system.ResponseEntity;
import com.muteng.dgjs.common.utils.*;
import com.muteng.dgjs.condition.LoginCondition;
import com.muteng.dgjs.dao.BankCardMapper;
import com.muteng.dgjs.dao.UserMapper;
import com.muteng.dgjs.domain.*;
import com.muteng.dgjs.service.*;
import com.muteng.dgjs.vo.UpdateUserBaseInfoRequest;
import com.tianwu.dubbo.service.intf.OrderIntf;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.apache.commons.lang.ArrayUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import java.text.ParseException;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.SSLSocketFactory;
import static org.apache.commons.codec.binary.Base64.encodeBase64;

@RestController
@RequestMapping(value="/api",produces = {"application/json;charset=utf-8"})
@Api(value="method001到method043",description="method001到method010")
public class MethodController {
	@Resource
	private BankCardMapper bankCardMapper;
    @Resource
	private GetPictureCapService getPictureCapService;
	@Resource
	private SendMessageService sendMessageService;
	@Resource
	private Loginservice loginService;
	@Resource
	private UserService userService;
	@Resource
	private OrdersPaylogService ordersPaylogService;
	@Resource
	private AccountService accountService;
	@Resource
	private BankCardService bankCardService;
	@Resource
	private PositionsService positionsService;
	@Resource
	private OrganizationalService organizationalService;
	@Resource
	private ProductMarketingService productMarketingService;
	@Resource
	private UserinfoService userinfoService;
	@Resource
	private UserBaseInfoService userBaseInfoService;
	@Resource
	private ShouruService shouruService;
	@Resource
	private OrderListService orderListService;
	@Resource
	private ProductlistService productlistService;
	@Resource
	private RelationService relationService;
	@Resource
	private AddressBookService addressBookService;
	@Resource
	private UtilService utilService;
	@Resource
	private UserMapper userMapper;
	@Resource
	private OrderIntf orderIntf;
	@Resource
    private AuditlogService auditlogService;
	@Resource
    private OrdersMemberService orderMemberService;
	@Resource
    private ShareService shareService;
	@Resource
	private PicturesService picturesService;
	@Resource
	private PlatformTransactionManager transactionManager;
	final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	private static Map<Integer,String> msg = new HashMap<Integer,String>();
	static {
		msg.put(0, "失败");
		msg.put(1, "成功");
	}
	//登录验证/method001
	@RequestMapping(value = { "/method001" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST,
			org.springframework.web.bind.annotation.RequestMethod.GET}, produces = {
	"application/json;charset=UTF-8" })
	public ResponseEntity method001(@RequestParam("loginname") String loginname,@RequestParam("smsMessage") String smsMessage,String weixinopenid,String  subsource,String  source,@ApiIgnore HttpServletRequest request){

		Map<String,Object> paramMap = new HashMap<String,Object>();
		this.copyProperties(request.getParameterMap(), paramMap);

		LoginCondition condition = new LoginCondition();
		condition.setLoginname(loginname);
		condition.setSmsMessage(smsMessage);
		condition.setSource(source);
		condition.setSubsource(subsource);
		if(weixinopenid != null){
			condition.setWeixinopenid(weixinopenid);
		}
		Object[] obj = this.loginService.doLogin(condition, paramMap);
		Integer code = (Integer)obj[0];
        System.out.println("obj==============="+(Map)obj[1]);
		return BaseResponse.buildSuccess(obj[1],code, msg.get(code));
	}

	//获取图片验证码/method002
	@GetMapping("/method002")
	@ApiOperation(value="获取图片验证码",httpMethod="GET")
	public ResponseEntity method002(@RequestParam("phone") String phone,@ApiIgnore HttpServletRequest request) throws IOException{
		String picture = this.getPictureCapService.getPicture();
		request.getSession().setAttribute(phone, picture);
		VerifiedCodeGenerator codeGenerator = new VerifiedCodeGenerator();
		codeGenerator.setImgWidth(60);
		codeGenerator.setImgHeight(22);
		BufferedImage image =  codeGenerator.createImage(picture);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", baos);
		String key = phone + "_" + picture + ".jpg";
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		String url = OssUtil.uploadInputstreamToOSS(bais, key);
		Map<String, String> map = new HashMap<String, String>();
		map.put("value", picture);
		map.put("url", url);
		System.out.println();
		return BaseResponse.buildSuccess(map, 1, "成功");
	}

	//获取短信验证码method003
	@GetMapping("/method003")
	@ApiOperation(value="获取短信验证码",httpMethod="GET")
	public ResponseEntity method003(@RequestParam("phone") String phone,
									@RequestParam("piccaptcha") String piccaptcha,
									@ApiIgnore HttpServletRequest request){
		//获取图片验证码
		String mycaptcha = (String)request.getSession().getAttribute(phone);
		if(!mycaptcha.equals(piccaptcha)) {
			return BaseResponse.buildSuccess("验证码错误",0, "失败");
		}
		String ret = "成功";
		String vo = this.sendMessageService.sendMessage(phone);
		if(vo.equals("0")){
			ret = "成功";
		}else{
			ret = "失败";
		}
		return BaseResponse.buildSuccess(ret,1, ret);
	}

	//首页获取推荐奖励method004
	@GetMapping("/method004")
	@ApiOperation(value="首页获取推荐奖励",httpMethod="GET")
	public ResponseEntity method004(){
		List<ProductMarketingDTO> list = this.productMarketingService.getjlForIndex();
		return BaseResponse.buildSuccess(list,1, "成功");
	}

	//首页获取会员信息method005
	@GetMapping("/method005")
	@ApiOperation(value="首页获取会员信息",httpMethod="GET")
	public ResponseEntity method005(@RequestParam("phone") String phone,@ApiIgnore HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("phone", phone);
		Map<String,Object> map1 = this.userService.getUserInfo(map);
		map1.put("amount",this.userService.getMemberInfo(map));

		return BaseResponse.buildSuccess(map1,1, "成功");
	}

	//首页获取我的入职信息method006
	@GetMapping("/method006")
	@ApiOperation(value="首页获取入职信息",httpMethod="GET")
	public ResponseEntity method006(@RequestParam("userid") Long userid,@ApiIgnore HttpServletRequest request){
		Long userRz = this.userService.getUserRz(userid);
		return BaseResponse.buildSuccess(userRz,1, "成功");
	}

	//7首页获取客户信息method007
	@GetMapping("/method007")
	@ApiOperation(value="首页获取客户信息",httpMethod="GET")
	public ResponseEntity method007(@RequestParam("phone") String phone,@ApiIgnore HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phone", phone);

		ClientsDTO clientsDTO = this.userService.getInviter(map);
		return BaseResponse.buildSuccess(clientsDTO, 1, "成功");
	}

	//短信邀请注册method008
	@PostMapping("/method008")
	@ApiOperation(value="短信邀请注册",httpMethod="POST")
	public ResponseEntity method008(@ApiIgnore HttpServletRequest request) throws UnsupportedEncodingException {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		this.copyProperties(request.getParameterMap(), paramMap);
		Object[] obj = this.addressBookService.invitingfriends(paramMap);
		Integer code = (Integer)obj[0];
		return BaseResponse.buildSuccess(obj[1],code, msg.get(code));
	}

    @PostMapping("/method009")
    @ApiOperation(value="上传通讯录",httpMethod="POST")
    public ResponseEntity method009(@ApiIgnore HttpServletRequest request) throws IOException{
    	Map<String,Object> paramMap = new HashMap<String,Object>();
    	this.copyProperties(request.getParameterMap(), paramMap);
    	Object[] obj = this.addressBookService.synchronizeRelationship(paramMap);
    	Integer code = (Integer)obj[0];
		return BaseResponse.buildSuccess(obj[1],code, msg.get(code));
    }

	//产品列表method010
	@RequestMapping(value="/method010",method = { org.springframework.web.bind.annotation.RequestMethod.POST,
			org.springframework.web.bind.annotation.RequestMethod.GET}, produces = {
			"application/json;charset=UTF-8" })
	@ApiOperation(value="产品列表",httpMethod="GET")
	public ResponseEntity method010(@RequestParam("page") int page,@RequestParam("rows") int rows,@ApiIgnore HttpServletRequest request){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		this.copyProperties(request.getParameterMap(), paramMap);
		System.out.println("=================="+paramMap+"===============");
		productlistService.cityAndCriteria(paramMap);

		Map<String,Object> productLists = productlistService.getProductLists(page*rows-rows,rows,paramMap);

		return BaseResponse.buildSuccess(productLists,1, "成功");
	}

	//获取分享信息method013
	@GetMapping("/method013")
	@ApiOperation(value="获取分享信息",httpMethod="GET")
	public ResponseEntity method013(String subsource,String source,String sharephone,String yaoqingma,String  productid,@RequestParam("sharetype") Integer  sharetype,Long userid,@ApiIgnore HttpServletRequest request) throws UnsupportedEncodingException {
		if(subsource ==null){
			subsource = "APP分享";
		}
		Share share =this.shareService.selectShare(sharetype);
		Productlist p = this.productlistService.queryProductByProductid(productid);
		if(sharetype==3){
            share.setTitle("工作随手发，福利等你拿。");
			share.setImgurl(p.getLogo());
        }else if(sharetype==4){
			share.setTitle("您的好友发现了一份高薪工作。");
			share.setImgurl("https://lanzhipei.oss-cn-hangzhou.aliyuncs.com/advertisement/%E6%89%93%E5%B7%A5%E9%9B%86%E5%B8%82logo.jpg");
		}else if(sharetype==2){
			share.setTitle("好友开了个店，快来看看。");
			share.setImgurl("https://lanzhipei.oss-cn-hangzhou.aliyuncs.com/advertisement/%E6%89%93%E5%B7%A5%E9%9B%86%E5%B8%82logo.jpg");
		}
		else{
		    share.setTitle(p.getTitle());
			share.setImgurl(p.getLogo());
		}
		PasswordED passwordED =new PasswordED();
		String pass ="productid="+productid+",sharetype="+sharetype+",userid="+userid+",sharephone="+sharephone+",subsource="+"\""+subsource+"\""+",yaoqingma="+"\""+yaoqingma+"\""+",source="+"\""+source+"\"";
		String enpass= passwordED.encPassword(pass);
		share.setBackurl("http://workbazaar.lanlingzhifu.cn/weixin/weixin.jsp?pass="+URLEncoder.encode(enpass,"UTF-8"));
		share.setUserid(userid);
		return BaseResponse.buildSuccess(share,1, "分享成功");
	}

	//method014根据条件搜索产品详情
	@RequestMapping(value="/method014",method = { org.springframework.web.bind.annotation.RequestMethod.POST,
			org.springframework.web.bind.annotation.RequestMethod.GET}, produces = {
			"application/json;charset=UTF-8" })
	@ApiOperation(value="产品详情",httpMethod="GET")
	public ResponseEntity method014(@RequestParam("productid") Long productid,@ApiIgnore HttpServletRequest request){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		this.copyProperties(request.getParameterMap(), paramMap);
		return BaseResponse.buildSuccess(productlistService.queryByProductid(productid,paramMap),1, "成功");
	}

	//method015点击分享页面统计和分析
	@GetMapping("/method015")
	@ApiOperation(value="点击分享页面统计和分析",httpMethod="GET")
	public ResponseEntity method015(Relation relation ,String openid, @ApiIgnore HttpServletRequest request) throws IOException {
		Relation relation1 = this.shareService.selectrelation(relation.getOpenid());
		if(relation1 !=null ){
			if(!DateUtils.isSameDay(relation1.getCreatetime(),new Date())){
				relation.setHeadimgurl(relation1.getHeadimgurl());
				relation.setNickname(relation1.getNickname());
				relation.setOpenid(openid);
				relation.setCreatetime(new Date());
				this.shareService.insertrelation(relation);
				return BaseResponse.buildSuccess("成功",1, "成功");
			}
		}
		return BaseResponse.buildSuccess("失败",1, "失败");
	}

	//我的入职method016
	//方法在serviceimpt实现
	@GetMapping("/method016")
	@ApiOperation(value="我的入职",httpMethod="GET")
	public ResponseEntity method016(long userid){
		return BaseResponse.buildSuccess(this.orderListService.queryMyEntry(userid),1, "成功");
	}

	//入职详情method017
	@GetMapping("/method017")
	@ApiOperation(value="入职详情",httpMethod="GET")
	public ResponseEntity method017(String orderid,String userid,String userId,String orderId){
		System.out.println(orderid+"======================================\n"+userid+"======================================\n"
				+userId+"======================================\n"+orderId);
		Long orderd = Long.valueOf(orderid);
        List<Map<String,Object>> list = orderListService.queryById(orderd);
		return BaseResponse.buildSuccess(list,1, "成功");
	}

	//编辑个人信息method018
	@RequestMapping(value = { "/method018" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST}, produces = {
			"application/json;charset=UTF-8" })
	@ApiOperation(value="编辑个人信息",httpMethod="POST")
	public ResponseEntity method018(@ApiIgnore HttpServletRequest request){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		this.copyProperties(request.getParameterMap(), paramMap);
		Object[] updateUser = this.userService.updateUser(paramMap);
		Integer code = (Integer)updateUser[0];
		return BaseResponse.buildSuccess(updateUser[1],code, msg.get(code));
	}

	//我邀请的入职method019
	@GetMapping("/method019")
	@ApiOperation(value="我邀请的入职",httpMethod="GET")
	public ResponseEntity method019(Long userid){
		List<Map<String,Object>> list = orderListService.queryInvite(userid);
		return BaseResponse.buildSuccess(list,1, "成功");
	}

	//收入列表(邀请收入)method020
	@GetMapping("/method020")
	@ApiOperation(value="收入列表(邀请收入)",httpMethod="GET")
	public ResponseEntity method020(@RequestParam("userid") Long userid){
		List<InviteRevenue> list = this.shouruService.getInviteShouru(userid);
		for(InviteRevenue in : list){
			if(in.getOrderid() != null){
				in.setStatus(1);
			}else{
				in.setStatus(0);
			}
		}
		return BaseResponse.buildSuccess(list,1, "成功");
	}

	//收入列表(离职作废)method021
	@GetMapping("/method021")
	@ApiOperation(value="收入列表(离职作废)",httpMethod="GET")
	public ResponseEntity method021(@RequestParam("userid") Long userid){
		List<LzRevenue> list = this.shouruService.getLizhiShouru(userid);
		for(LzRevenue in : list){
			if(in.getOrderid() != null){
				in.setStatus(1);
			}else{
				in.setStatus(0);
			}
		}
		return BaseResponse.buildSuccess(list,1, "成功");
	}

    //收入列表(到账)method022
	@GetMapping("/method022")
	@ApiOperation(value="收入列表(到账)",httpMethod="GET")
	public ResponseEntity method022(@RequestParam("userid") Long userid,@ApiIgnore HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userid", userid);
		List<DzRevenue> list = this.ordersPaylogService.getOrderPaylogs(map);
		for(DzRevenue in : list){
			if(in.getOrderid() != null&&!in.getPaytype().equals("邀请人")){
				in.setStatus(1);
			}else{
				in.setStatus(0);
			}
		}
		return BaseResponse.buildSuccess(list,1, "成功");
	}

    //账单列表method023
	@GetMapping("/method023")
	@ApiOperation(value="账单列表",httpMethod="GET")
	public ResponseEntity method023(@RequestParam("userid") Long userid,@ApiIgnore HttpServletRequest request){
	//1 "注册成功",2 "邀请@注册成功",3 "签到成功",4 "@分享成功",5 "@报名进厂成功",6 "@报名培训成功",7 "每日理财收益",
	// 8 "提现",11 "工资发放"12 "新人补贴"，13 "报销",14 '购买会员'
		if(userid != null){
			List<Account2> map = this.accountService.getAccounts(userid);
			for(Account2 c:map){
				c.setAmount(c.getAmount()/100);
			}
			Map<String,Object> map1 =new HashMap<>();
			map1.put("1","注册成功");map1.put("2","邀请注册成功");map1.put("3","签到成功");map1.put("4","分享成功");
			map1.put("5","报名进厂成功");map1.put("6","报名培训成功");map1.put("7","每日理财收益");map1.put("8","提现");
			map1.put("11","工资发放");map1.put("12","新人补贴");map1.put("13","报销");map1.put("14","购买会员");map1.put("15","充值");map1.put("16","邀请购买会员");
			Iterator keys = map1.keySet().iterator();
			while(keys.hasNext()){
				String key = (String) keys.next();
				for(Account2 a :map){
					if(a.getReason().toString().equals(key)){
						a.setReason( map1.get(key));
					}
				}
			}
			return BaseResponse.buildSuccess(map,1, "成功");
		}

		return BaseResponse.buildSuccess("系统错误",0, "失败");
	}

    //申请提现method024
	@GetMapping("/method024")
	@ApiOperation(value="申请提现",httpMethod="GET")
	public ResponseEntity method024(User user) throws Exception {
		//根据ID查询用户
        User userSelect = this.userService.getUserById(user.getId());
        if (userSelect != null) {
            //查询出用户绑定身份
            String idCard = userSelect.getIdcard();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userid", user.getId());
            //设置提现页面所需信息
            UserM u = this.userService.getInfoById(user.getId());
            if (idCard != null) {
                u.setIsauth(1);
            } else {
                u.setIsauth(0);
            }
            if(u.getAccount() == null){u.setAccount(0.0);}
            u.setAccount(u.getAccount()/100);
            //设置返回日期
            List<Date> inHoliday = this.utilService.selectHoliday();
            List<Date> wHoliday = this.utilService.selectworkHoliday();
            SimpleDateFormat simple = new SimpleDateFormat("yy-MM-dd");
            // new一个新的集合，来存放格式化的时间数据
            List<String> in = new ArrayList<String>();
            List<String> w = new ArrayList<String>();
            // 将List中的Date集合全部格式化类型为"yy-MM-dd HH:mm"
            for (Date time : inHoliday) {
                in.add(simple.format(time).toString());
            }
            for (Date time : wHoliday) {
                w.add(simple.format(time).toString());
            }
            //设置预计审核日期
            Date back = DateUtils.workDay(DateUtils.convert(new Date()), 2, in, w);
            u.setPayday(simple.format(back));
            //对象转换为map 返回页面
            Map<String, Object> mp = new HashMap<>();
            Class clazz = u.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                mp.put(field.getName(), field.get(u));
            }
            return BaseResponse.buildSuccess(mp, 1, "成功");
        } else {
            return BaseResponse.buildSuccess("系统错误", 0, "失败");
        }
	}

    //历史绑定银行卡method025
	@GetMapping("/method025")
	@ApiOperation(value="历史绑定银行卡",httpMethod="GET")
	public ResponseEntity method025(@RequestParam("userid") Long userid,@ApiIgnore HttpServletRequest request){
			List<BankCard>list = this.bankCardService.getgetBankcardInfoByUserid(userid);
		return BaseResponse.buildSuccess(list,1, "成功");
	}

    //身份认证照片识别method026
	@PostMapping("/method026")
	@ApiOperation(value="身份认证照片识别",httpMethod="POST")
	public ResponseEntity method026( @RequestParam(value="file") CommonsMultipartFile file,@RequestParam("userid")Long userid,@ApiIgnore HttpServletRequest request){
		String host = "http://dm-51.data.aliyun.com";
		String path = "/rest/160601/ocr/ocr_idcard.json";
		String appcode = "963c93cef90c410b8cdd46e32308961d";
		//String imgFile = image;
		Boolean is_old_format = false;//如果文档的输入中含有inputs字段，设置为True， 否则设置为False
		//请根据线上文档修改configure字段
		JSONObject configObj = new JSONObject();
		configObj.put("side", "face");
		String config_str = configObj.toString();
		String method = "POST";
		Map<String, String> headers = new HashMap<String, String>();
		//最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + appcode);

		Map<String, String> querys = new HashMap<String, String>();
		// 对图像进行base64编码
		String imgBase64 = "";
		imgBase64 = new String(encodeBase64(file.getBytes()));
		// 拼装请求body的json字符串
		JSONObject requestObj = new JSONObject();
		try {
			if(is_old_format) {
				JSONObject obj = new JSONObject();
				obj.put("image", IdcardValidator.getParam(50, imgBase64));
				if(config_str.length() > 0) {
					obj.put("configure", IdcardValidator.getParam(50, config_str));
				}
				JSONArray inputArray = new JSONArray();
				inputArray.add(obj);
				requestObj.put("inputs", inputArray);
			}else{
				requestObj.put("image", imgBase64);
				if(config_str.length() > 0) {
					requestObj.put("configure", config_str);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String bodys = requestObj.toString();
		try {
			/**
			 * 重要提示如下:
			 * HttpUtils请从
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
			 * 下载
			 *
			 * 相应的依赖请参照
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
			 */
			HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
			int stat = response.getStatusLine().getStatusCode();
			if(stat != 200){
				System.out.println("Http code: " + stat);
				System.out.println("http header error msg: "+ response.getFirstHeader("X-Ca-Error-Message"));
				System.out.println("Http body error msg:" + EntityUtils.toString(response.getEntity()));
				return BaseResponse.buildSuccess("图片解析失败",0, "失败");
			}

			String res = EntityUtils.toString(response.getEntity());
			com.alibaba.fastjson.JSONObject res_obj = JSON.parseObject(res);
			if(is_old_format) {
				JSONArray outputArray = res_obj.getJSONArray("outputs");
				String output = outputArray.getJSONObject(0).getJSONObject("outputValue").getString("dataValue");
				com.alibaba.fastjson.JSONObject out = JSON.parseObject(output);
				System.out.println(out.toJSONString()+"if");
			}else{
				Map<String, Object> map = new HashMap<>();
				if(!res_obj.getString("name").equals("") && !res_obj.getString("num").equals("")){
					map.put("name",res_obj.getString("name"));
					map.put("num",res_obj.getString("num"));
					User up =new User();
					up.setId(userid);
					up.setIdcardimg(imgBase64);
					this.userService.updateByPrimaryKeySelective(up);
					return BaseResponse.buildSuccess(map,1, "成功");
				}else{
					return BaseResponse.buildSuccess("图片解析失败",0, "失败");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return BaseResponse.buildSuccess("内部错误",0, "失败");
		}
		return BaseResponse.buildSuccess("系统错误",0, "失败");
	}

	//身份认证提交method027
	@GetMapping("/method027")
	@ApiOperation(value="身份认证提交",httpMethod="GET")
	public ResponseEntity method027(User user) throws ParseException {
		IdcardValidator iv =new IdcardValidator();
		if(IdcardValidator2.IDCardValidate(user.getIdcard())){
			userService.updateByPrimaryKeySelective(user);
			return BaseResponse.buildSuccess("身份认证成功",1, "成功");
		}
		return BaseResponse.buildSuccess("身份证信息有误",0, "失败");
	}

    //查询用户是否认证method028
	@GetMapping("/method028")
	@ApiOperation(value="查询用户是否认证",httpMethod="GET")
	public ResponseEntity method028(@RequestParam("userid") Long userid,@ApiIgnore HttpServletRequest request){
		Integer isCertification = 0;
		if(userid != null){
			User user = this.userService.getUserById(userid);
			if(StringUtil.isNotEmpty(user.getIdcard())){
				isCertification = 1;
			}
		}
		return BaseResponse.buildSuccess(isCertification,1, "成功");
	}

    //查询是否绑定银行卡method029
	@GetMapping("/method029")
	@ApiOperation(value="查询是否绑定银行卡",httpMethod="GET")
	public ResponseEntity method029(@RequestParam("userid") Long userid,@ApiIgnore HttpServletRequest request){
		if(userid != null){
			List<BankCard> list = this.bankCardService.getBankcardByUserid(userid);
				if(list !=null && !list.isEmpty()){
					return BaseResponse.buildSuccess("已绑定银行卡",1, "成功");
				}else{
					return BaseResponse.buildSuccess("未绑定银行卡",0, "失败");
				}
		}

		return BaseResponse.buildSuccess("系统错误",0, "失败");
	}

    //银行卡解绑method030
	@GetMapping("/method030")
	@ApiOperation(value="银行卡解绑",httpMethod="GET")
	public ResponseEntity method030(@RequestParam("userid") Long userid,@RequestParam("cardnum") String cardnum,@ApiIgnore HttpServletRequest request){

		String updateBackcard;
		if(userid != null){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userid", userid);
			map.put("cardnum", cardnum);
			updateBackcard = this.bankCardService.updateBackcard(map);
		}else{
			return BaseResponse.buildSuccess("系统错误",0, "失败");
		}

		return BaseResponse.buildSuccess(updateBackcard,1, "成功");
	}


	@PostMapping("/method031")
	@ApiOperation(value="下单接口",httpMethod="POST")
	public ResponseEntity method031(@ApiIgnore HttpServletRequest request)throws Exception{
		Map<String,String> paramMap = new HashMap<String,String>();
		this.copyProperties(request.getParameterMap(),paramMap);
		String loginname = paramMap.get("loginname");
		String name = paramMap.get("name");
		if(name == null || name.length()<=0) {
			//查询user表里的name
			Map<String,Object> m = new HashMap<String,Object>();
			m.put("phone", loginname);
			User user = userMapper.queryByloginname(m);
			if(user == null || user.getName()==null || user.getName().length()<=0) {
				paramMap.put("name", "未知");
			}else {
				paramMap.put("name", user.getName());
			}
		}
		System.out.println("paramMap===="+paramMap);
		int value = this.orderIntf.saveOrders(paramMap);
		System.out.println("value===="+value);
		if(value >0) {
			sendMessageService.sendToInviters(paramMap);
			return BaseResponse.buildSuccess("成功",1, "报名成功");
		} else if(value == -2){
			return BaseResponse.buildSuccess("失败",0, "参数不完整");
		} else if(value == -3){
			return BaseResponse.buildSuccess("失败",0, "重复报名");
		} else if(value == -4){
			return BaseResponse.buildSuccess("失败",0, "当日报名不能超过5个产品");
		} else if(value == -5){
			return BaseResponse.buildSuccess("失败",0, "重复报名");
		} else {
			return BaseResponse.buildSuccess("失败",0, "报名失败");
		}
	}

    //进入设置method032
	@GetMapping("/method032")
	@ApiOperation(value="进入设置",httpMethod="GET")
	public ResponseEntity method032(Long userid){
		//查询个人设置
		UserSetting userTemp = this.userService.getUserInfoById(userid);
		//查询是否实名认证
		User user = this.userService.getUserById(userid);
		if(StringUtil.isNotEmpty(user.getIdcard())){
				userTemp.setAuthenticate(1);
		}else {userTemp.setAuthenticate(0);}
		//查询是否是会员
		if(user.getUsertype() == 8||user.getUsertype() ==9){
			userTemp.setIsmemb(1);
		}else {userTemp.setIsmemb(0);}
		return BaseResponse.buildSuccess(userTemp,1, "成功");
	}

    //更新User数据method033
	@PostMapping("/method033")
	@ApiOperation(value="更新User数据",httpMethod="POST")
	public ResponseEntity method033(User user, @RequestParam(value="file", required=false) CommonsMultipartFile file) throws IOException{
		this.userService.updateByPrimaryKeySelective(user);
		return BaseResponse.buildSuccess("更新数据成功",1, "成功");
	}

    //更新Userbaseinfo数据method034
    @RequestMapping(value = { "/method034" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST,
            org.springframework.web.bind.annotation.RequestMethod.GET}, produces = {
            "application/json;charset=UTF-8" })
	public ResponseEntity method034(@RequestParam("infoJson") String baseInfoJson, Long userid,String type){
		List<UpdateUserBaseInfoRequest> jsonList = JSON.parseArray(baseInfoJson, UpdateUserBaseInfoRequest.class);
		userBaseInfoService.deleteByUserid(userid,type);
		for (UpdateUserBaseInfoRequest request : jsonList) {
			String name = request.getName();
			Long[] value = request.getValue();
			if (ArrayUtils.isNotEmpty(value)) {
				for (Long v : value) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("userid", userid);
					map.put("name", name);
					map.put("createtime", new Date());
					map.put("value", v);
					this.userBaseInfoService.insert(map);
				}
			}
		}
		return BaseResponse.buildSuccess("更新数据成功",1, "成功");
	}

    //个人信息里面初始数据method035
	@GetMapping("/method035")
	@ApiOperation(value="个人信息里面初始数据",httpMethod="GET")
	public ResponseEntity method035(@RequestParam("userid") Long userid) {
		List<BasicinFormation> list =new ArrayList<>();
		 list = this.userBaseInfoService.getBasicInfoMation(userid);
		return BaseResponse.buildSuccess(list, 1, "成功");
	}

    //进入意向工作选择method036
	@GetMapping("/method036")
	@ApiOperation(value="进入意向工作选择",httpMethod="GET")
	public ResponseEntity method036(@ApiIgnore HttpServletRequest request){
		List<Map<String,Object>> list = this.positionsService.queryPositions();
		return BaseResponse.buildSuccess(list,1, "成功");
	}
    //进入意向城市选择method037
	@GetMapping("/method037")
	@ApiOperation(value="进入意向城市选择",httpMethod="GET")
	public ResponseEntity method037(@ApiIgnore HttpServletRequest request){
        List<Map<String,Object>> list = this.organizationalService.queryLive2();
        return BaseResponse.buildSuccess(list,1, "成功");
	}

	//成功用户行为跟踪method039
    @GetMapping("/method039")
    @ApiOperation(value="用户行为跟踪",httpMethod="GET")
    public ResponseEntity method039(Auditlog auditlog, @ApiIgnore HttpServletRequest request){
	    auditlog.setCreatetime(new Date());
        this.auditlogService.addlog(auditlog);
        return BaseResponse.buildSuccess("成功",1, "成功");
    }

	 //客户管理method040
	@GetMapping("/method040")
	@ApiOperation(value="客户管理",httpMethod="GET")
	public ResponseEntity method040(Long userid,Long phone){
		Map<String,Object> map = relationService.getRelation(userid,phone);
		return BaseResponse.buildSuccess(map,1, "成功");
	}

	//41 访问人数，method041
	// 访问人详情
	@GetMapping("/method041")
	@ApiOperation(value="访问人数及详情",httpMethod="GET")
	public ResponseEntity method041(Long userId){//customtype
		List<Map<String,Object>> customDetail = relationService.customDetail(userId);
		return BaseResponse.buildSuccess(customDetail,1, "成功");
	}

	//邀请的注册人数和入职人数method042
	@GetMapping("/method042")
	@ApiOperation(value="邀请注册人数和入职人数",httpMethod="GET")
	public ResponseEntity method042(Long userId){
		return BaseResponse.buildSuccess(relationService.getRelationKehu(userId),1, "成功");
	}

	//邀请的会员method043
	@GetMapping("/method043")
	@ApiOperation(value="邀请的会员",httpMethod="GET")
	public ResponseEntity method043(Long userId){
		return BaseResponse.buildSuccess(relationService.memberDetail(userId),1, "成功");
	}

	//进入收入页面method044
	@GetMapping("/method044")
	@ApiOperation(value="进入收入页面",httpMethod="GET")
	public ResponseEntity method044(@RequestParam("userid") Long userid,@ApiIgnore HttpServletRequest request){
		if(userid != null &&userid !=0){
			Map<String, Integer> map = this.shouruService.getMyShouru(userid);
			if(map == null || map.size()<1){
				map.put("account",0);
				map.put("lzmoney",0);
				map.put("wqrmoney",0);
				map.put("dzmoney",0);
				return BaseResponse.buildSuccess(map,1, "成功");
			}
            Map<String, Float> remap= new HashMap<>();
            remap.put("wqrmoney",CommonUtil.convertYuan( map.get("wqrmoney")));
            remap.put("lzmoney",CommonUtil.convertYuan( map.get("lzmoney")));
            remap.put("dzmoney",CommonUtil.convertYuan( map.get("dzmoney")));
            remap.put("account",CommonUtil.convertYuan( map.get("account")));
			return BaseResponse.buildSuccess(remap,1, "成功");
		}else {
			return BaseResponse.buildSuccess("系统错误",0, "失败");
		}
	}

	/**
	 * 拷贝属性
	 *
	 * @param source
	 * @param target
	 */
	@SuppressWarnings("all")
	private void copyProperties(Map<String, String[]> source, Map target) {
		Set<String> set = source.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String key = it.next();
			target.put(key, source.get(key)[0]);
		}
	}

    //修改微信名片method045
    @PostMapping("/method045")
    @ApiOperation(value="修改微信名片",httpMethod="POST")
    public ResponseEntity method045(User user, @RequestParam(value="file") CommonsMultipartFile file) throws IOException{
        if (null != file) {
            //获取源文件名称
            String fileName = file.getOriginalFilename();
            //获取文件后缀
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
            fileExt = fileExt.toLowerCase();
            //进行文件格式判断
            if (!"jpg".equals(fileExt) && !"jpeg".equals(fileExt) && !"png".equals(fileExt) && !"bmp".equals(fileExt)
                    && !"gif".equals(fileExt)) {
                return BaseResponse.buildSuccess("上传文件格式错误",0, "失败");
            }
            //文件大小判断
            long fileSize = file.getSize();
             if (fileSize > (2*1048576)) {
                 return BaseResponse.buildSuccess("上传文件不得大于2M",0, "失败");
            }

            String suffix = fileName.substring(fileName.lastIndexOf("."));
            String key = String.valueOf(System.currentTimeMillis()) + "_" +UUID.randomUUID().toString()+ suffix;
            InputStream is = file.getInputStream();
            String wxurl = OssUtil.uploadInputstreamToOSS(is, key);
            user.setWeixinurl(wxurl);
        }
        this.userService.updateByPrimaryKeySelective(user);
        return BaseResponse.buildSuccess("更新数据成功",1, "成功");
    }

	//method046
	@GetMapping("/method046")
	@ApiOperation(value="个人信息默认选项查询",httpMethod="GET")
	public ResponseEntity method046(@RequestParam("code") String code) {
		Map map =new HashMap();
		List<BasicinFormation> listTemp = this.userBaseInfoService.getBasicInfoMationByValue(code);
		return BaseResponse.buildSuccess(listTemp, 1, "成功");
	}

	//method047
    @GetMapping("/method047")
    @ApiOperation(value="验证是否绑定微信",httpMethod="GET")
    public ResponseEntity method047(@ApiIgnore HttpServletRequest request) throws IOException{
    	Map<String,Object> paramMap = new HashMap<String,Object>();
    	this.copyProperties(request.getParameterMap(), paramMap);
    	Object[] obj = this.loginService.isBandingWeixin(paramMap);
    	Integer code = (Integer)obj[0];
		return BaseResponse.buildSuccess(obj[1],code, msg.get(code));
    }

	//修改默认付款银行卡method048
	@GetMapping("/method048")
	@ApiOperation(value="修改默认付款银行卡",httpMethod="GET")
	public ResponseEntity method048(@RequestParam("userid") Long userid,@RequestParam("bankcard") String bankcard,@ApiIgnore HttpServletRequest request) {
		this.bankCardService.updateOtherBankcardByUserid(userid);
		this.bankCardService.updateBankcardByUserid(userid, bankcard);
		return BaseResponse.buildSuccess("成功", 1, "成功");
	}

    //method049
    @GetMapping("/method049")
	@ApiOperation(value="查询搜索用职位信息",httpMethod="GET")
	public ResponseEntity method049(@ApiIgnore HttpServletRequest request){
			return  BaseResponse.buildSuccess(productlistService.searchCriteria(),1,"成功");
	}

	//method050  提现功能
    @GetMapping("/method050")
    @ApiOperation(value="提现功能",httpMethod="GET")
    public synchronized ResponseEntity method050(Long userid,String bankCard,String amount,@ApiIgnore HttpServletRequest request) throws Exception {
        Pattern pattern= Pattern.compile("(^[1-9](\\d+)?(\\.\\d{1,2})?$)|(^0$)|(^\\d\\.\\d{1,2}$)"); // 判断小数点后2位的数字的正则表达式
        Matcher match=pattern.matcher(amount);

        if (match.matches()) {
            Double a = Double.parseDouble(amount);
            if (a >= 0.01 && amount != null) {

                User userSelect = this.userService.getUserById(userid);
                //用户取款金额转换为分
                int userAmount = CommonUtil.convertCent(a);
                //判断余额
                if (userSelect.getAccount() == null || userSelect.getAccount() < userAmount) {
                    return BaseResponse.buildSuccess("可用余额不足", 0, "提现失败");
                }
                //判断银行卡信息
                if (bankCard.isEmpty()) {
                    return BaseResponse.buildSuccess("银行卡信息不存在", 0, "提现失败");
                }
                //验证银行卡信息
                String host = "http://ali-bankcard.showapi.com";
                String path = "/bankcard";
                String method = "GET";
                String appcode = "963c93cef90c410b8cdd46e32308961d";
                Map<String, String> headers = new HashMap<String, String>();
                //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
                headers.put("Authorization", "APPCODE " + appcode);
                //根据API的要求，定义相对应的Content-Type
                headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                Map<String, String> querys = new HashMap<String, String>();
                querys.put("kahao", bankCard);
                HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
                //获取response的body
                String re = EntityUtils.toString(response.getEntity());
                //存放请求返回信息
                JSONObject jb = JSONObject.fromObject(re);
                Map<String, Object> map2 = (Map<String, Object>) jb;
                Map returnm = (Map) map2.get("showapi_res_body");
                Map<String, Object> s = new HashMap<>(returnm);
                s.put("userid", userid);
                s.put("name", userSelect.getName());
                Object bname = s.get("bankName");
                Object tel = s.get("tel");
                Object remark = s.get("remark");
                if (remark != null) {
                    return BaseResponse.buildSuccess("找不到此卡号信息", 0, "失败");
                }
                List<Integer> selectBankTel = new ArrayList<>();
                List<String> selectBankKey = new ArrayList<>();
                if (tel != null) {
                    selectBankTel = this.bankCardService.selectBankTel(tel);
                }
                if (bname != null) {
                    selectBankKey = this.bankCardService.selectBankByFuzzy(bname.toString());
                }
                if (!selectBankTel.isEmpty() || !selectBankKey.isEmpty()) {
                    /**事务处理**/
                    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
                    TransactionStatus status = transactionManager.getTransaction(def);
                    try {
                        //根据用户查询bankcard表是否存在此次提现卡，不存在则添加绑定，如存在，修改status=1
                        String list2 = this.bankCardService.getBankcardBybankCard(bankCard);
                        this.bankCardService.updateOtherBankcardByUserid(userid);
                        if (list2 != null) {
                            this.bankCardService.updateBankcardByUserid(userid, bankCard);
                        } else {
                            this.bankCardService.addBankcardByUserid(s);
                        }
                        //插入账单信息
                        Account account = new Account();
                        account.setAmount(-CommonUtil.convertCent(a));
                        account.setCreatetime(new Date());
                        account.setReason(8);
                        account.setUserid(userid);
                        account.setType(3);
                        account.setUpdatetime(new Date());
                        account.setStatus(2);
                        account.setComment("APP提现"+bname+bankCard);
                        accountService.insertAccount(account);
                        //修改用户账户余额
                        this.userService.getMoney(userid, (long) userAmount);

                        BankCard returnB = this.bankCardService.selectbankcardBycardnum(bankCard);
                        Map<String, Object> returnmap = new HashMap<String, Object>();
                        returnmap.put("bankname", returnB.getBankname());
                        returnmap.put("logourl", returnB.getLogourl());
                        returnmap.put("amount", amount);
                        returnmap.put("cardnum", bankCard);
                        transactionManager.commit(status);
                        return BaseResponse.buildSuccess(returnmap, 1, "成功");
                    } catch (Exception e) {
                        transactionManager.rollback(status); //事务回滚
                        return BaseResponse.buildSuccess("失败", 0, "提现失败");
                    }
                } else {
                    return BaseResponse.buildSuccess("所选的银行不支持", 0, "失败");
                }
            } else {
                return BaseResponse.buildSuccess("取现金额最小为0.01元", 0, "提现失败");
            }
        } else {
            return BaseResponse.buildSuccess("取现金额错误", 0, "提现失败");
        }
	}

	//method051
	@GetMapping("/method051")
	@ApiOperation(value="查询所有支持的银行",httpMethod="GET")
	public ResponseEntity method051(@ApiIgnore HttpServletRequest request) {
		List<Bank> bank = this.bankCardService.selectbank();
		return BaseResponse.buildSuccess(bank,1, "成功");
	}

	@PostMapping("/method052")
	@ApiOperation(value="购买会员",httpMethod="POST")
	public ResponseEntity method052(@ApiIgnore HttpServletRequest request) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		this.copyProperties(request.getParameterMap(), paramMap);
		Object[] obj = orderMemberService.addOrderMember(paramMap);
		Integer code = (Integer)obj[0];
		return BaseResponse.buildSuccess(obj[1],code,msg.get(code));
	}

	//投诉发送邮件
	@GetMapping("/method053")
	@ApiOperation(value="投诉发送邮件",httpMethod="GET")
	public ResponseEntity method053(Long userid,String word) {

		this.orderListService.addcomplaint(userid,word);
		return BaseResponse.buildSuccess("发送成功",1, "成功");
	}

	//method054查询用户openid
	@GetMapping("/method054")
	@ApiOperation(value="查询用户openid",httpMethod="GET")
	public ModelAndView method054(String backpass,String code,@ApiIgnore HttpServletRequest request, @ApiIgnore HttpServletResponse response) throws UnsupportedEncodingException {
        PasswordED passwordED = new PasswordED();
	    String pass =passwordED.decPassword(backpass);
		JSONObject  jasonObject = JSONObject.fromObject(pass);
		Map <String,Object> mapR = (Map)jasonObject;

		String yaoqingma="";
		if(mapR.get("yaoqingma")!=null){
			yaoqingma = mapR.get("yaoqingma").toString();
		}
		Object subsource=mapR.get("subsource");
		Object sharetype=mapR.get("sharetype");
		Object productid=mapR.get("productid");
		Object userid =mapR.get("userid");
		Object sharephone =mapR.get("sharephone");

		System.out.println("subsource"+subsource);
		System.out.println("sharephone="+sharephone);
		System.out.println("sharetype="+sharetype);
		System.out.println("productid="+productid);
		System.out.println("userid="+userid);
		//根据分享类型判断需要跳转的页面
		String view = "";
		if(sharetype.equals("1")){
			view="/share/workdetail";
		}else if(sharetype.equals("2"))
		{
			view="/share/member";
		}else if(sharetype.equals("3")){
			view="/share/payman";
		}else {
			view="/share/message";
		}
		 //小程序唯一标识   (在微信小程序管理后台获取)
		String wxspAppid = "wx4797099c5b4642a1";
		//小程序的 app secret (在微信小程序管理后台获取)
		String wxspSecret = "b3d55e0624ce44dd5a0d83b1fde19793";
		//授权（必填）
		String grant_type = "authorization_code";

		//////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid ////////////////
		//请求参数
		String params = "APPID=" + wxspAppid + "&SECRET=" + wxspSecret + "&code=" + code + "&grant_type=" + grant_type;
		//发送请求
		String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/oauth2/access_token?"+params);
        //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.fromObject(sr);
		//用户的唯一标识（openid）
		String openid = (String) json.optString("openid");
		//网页授权接口调用凭证（access_token）
		String access_token = (String) json.optString("access_token");
		String sex = json.optString("sex");
		String province =json.optString("province");
		String city =json.optString("city");

		String params2 = "access_token=" + access_token + "&openid=" + openid + "&lang=zh_CN";
		String sr2 = HttpRequest.sendGet("https://api.weixin.qq.com/sns/userinfo?"+params2);
		//解析相应内容（转换成json对象）
		JSONObject json2 = JSONObject.fromObject(sr2);
		String nickname =json2.optString("nickname");
		String headimgurl=json2.optString("headimgurl");
		Map<String,Object> map = new HashMap<>();
		map.put("openid",openid);
		map.put("yaoqingma",yaoqingma);
		map.put("headimgurl",headimgurl);
		map.put("nickname",nickname);
		map.put("productid",productid);
		map.put("userid",userid);
		map.put("sharetype",sharetype);
		map.put("sharephone",sharephone);
		map.put("subsource",subsource);

		Relation re = this.relationService.getRelationByopenid(openid);
		if(re ==null){
			Relation relation =new Relation();
			relation.setCreatetime(new Date());
			relation.setInviteruserid(Long.parseLong(String.valueOf(userid)));
			relation.setNickname(URLEncoder.encode(nickname,"UTF-8"));
			relation.setOpenid(openid);
			relation.setHeadimgurl(headimgurl);
			relation.setProductid(Integer.parseInt(String.valueOf(productid)));
			this.relationService.insertRelation(relation);
		}
		if(headimgurl != null){
			map.put("headimgurl",headimgurl);
		}
		Cookie cookie = new Cookie("openid", openid);
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24*90);
		response.addCookie(cookie);
		if(sharetype.equals("2")){
			Map<String,Object> map2 = new HashMap<>();
				UserSetting user=this.userService.getUserInfoById(Long.parseLong(String.valueOf(userid)));
				map2.put("headimgurl",user.getHeadimgurl());
				map2.put("openid",openid);
				map2.put("sharephone",user.getPhone());
				map2.put("userid",userid);
				map2.put("subsource",subsource);
				if(user.getName()==null ){
					if(user.getNickname()==null){
						map2.put("nickname","好友"+"的店铺");
					}else{
						map2.put("nickname",user.getNickname()+"的店铺");
					}
				}else{
					map2.put("nickname",user.getName()+"的店铺");
				}


            ModelAndView mav2 = new ModelAndView(view);
            mav2.addObject("user",map2);
			System.out.println("======================================="+mav2);
			return mav2;
		}
		ModelAndView mav = new ModelAndView(view);
		mav.addObject("user",map);
		return mav;
    }

	//method055进入店铺
	@GetMapping("/method055")
	@ApiOperation(value="进入店铺",httpMethod="GET")
	public ResponseEntity method055(Long userid,String subsource,String openid,@ApiIgnore HttpServletRequest request){
		Map<String,Object> map = new HashMap<>();
		String view ="/share/member";
		UserSetting user=this.userService.getUserInfoById(userid);
		map.put("headimgurl",user.getHeadimgurl());
		map.put("sharephone",user.getPhone());
		map.put("userid",userid.toString());
		map.put("subsource",subsource);
		map.put("openid",openid);
		if(user.getName() == null ){
			if(user.getNickname() == null ){
				map.put("nickname","我"+"的店铺");
			}else{
				map.put("nickname",user.getNickname()+"的店铺");
			}
		}else{
			map.put("nickname",user.getName()+"的店铺");
		}
		return BaseResponse.buildSuccess(map,1,"成功");
	}

	//method056取广告图片
	@GetMapping("/method056")
	@ApiOperation(value="取广告图片",httpMethod="GET")
	public ResponseEntity method056(@RequestParam("workType") String workType){
		return BaseResponse.buildSuccess(picturesService.pictures(workType),1,"成功");
	}

	//method057取邀请和入职人数金额和宣传图片
	@GetMapping("/method057")
	@ApiOperation(value="取邀请和入职人数金额",httpMethod="GET")
	public ResponseEntity method057(){
		List<Map<String,Object>> resultList =  relationService.invitedNum();
		Map<String,Object> map = new HashMap<>();
		map.put("picture","https://lanzhipei.oss-cn-hangzhou.aliyuncs.com/advertisement/%E9%82%80%E8%AF%B7%E5%A5%BD%E5%8F%8B%402x.png");
		resultList.add(map);
		return BaseResponse.buildSuccess(resultList,1,"成功");
	}

    //method058静默授权查询用户openid
    @GetMapping("/method058")
    @ApiOperation(value="查询用户openid",httpMethod="GET")
    public ModelAndView method058(String backpass,@ApiIgnore HttpServletRequest request)  {
        PasswordED passwordED = new PasswordED();
        String pass =passwordED.decPassword(backpass);
        JSONObject  jasonObject = JSONObject.fromObject(pass);
        Map <String,Object>mapR = (Map)jasonObject;

		String yaoqingma="";
		if(mapR.get("yaoqingma")!=null){
			yaoqingma = mapR.get("yaoqingma").toString();
		}
		Object sharetype=mapR.get("sharetype");
		Object productid=mapR.get("productid");
		Object userid =mapR.get("userid");
		Object openid =mapR.get("openid");
		Object sharephone =mapR.get("sharephone");
		Object subsource =mapR.get("subsource");
        String view = "";
        if(sharetype.equals("1")){
            view="/share/workdetail";
        }else if(sharetype.equals("2")){
            view="/share/member";
        }else if(sharetype.equals("3")){
            view="/share/payman";
        }else {
			view="/share/message";
		}
        Map<String,Object> map = new HashMap<>();
        map.put("openid",openid);
        map.put("yaoqingma",yaoqingma);
        map.put("productid",productid);
        map.put("userid",userid);
        map.put("sharetype",sharetype);
        map.put("sharephone",sharephone);
		map.put("subsource",subsource);
        ModelAndView mav = new ModelAndView(view);
        System.out.println(map);
		System.out.println("subsource"+subsource);
        mav.addObject("user",map);
		if(sharetype.equals("2")){
			Map<String,Object> map2 = new HashMap<>();
				UserSetting user=this.userService.getUserInfoById(Long.parseLong(String.valueOf(userid)));
				map2.put("headimgurl",user.getHeadimgurl());
				map2.put("openid",openid);
				map2.put("sharephone",user.getPhone());
				map2.put("userid",userid);
				map2.put("subsource",subsource);
				if(user.getName()==null ){
					if(user.getNickname()==null){
						map2.put("nickname","您的好友"+"的店铺");
					}else{
						map2.put("nickname",user.getNickname()+"的店铺");
					}
				}else{
					map2.put("nickname",user.getName()+"的店铺");
				}

			ModelAndView mav2 = new ModelAndView(view);
			mav2.addObject("user",map2);
			return mav2;
		}
        return mav;
    }

	//短信邀请注册method059
	@RequestMapping(value = { "/method059" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST,
			org.springframework.web.bind.annotation.RequestMethod.GET}, produces = {
			"application/json;charset=UTF-8" })
	public ResponseEntity method059(@RequestParam("yaoqingma") String yaoqingma,@RequestParam("loginname") String loginname,@RequestParam("smsMessage") String smsMessage,String  subsource,String  source,@ApiIgnore HttpServletRequest request){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		this.copyProperties(request.getParameterMap(), paramMap);

		LoginCondition condition = new LoginCondition();
		condition.setLoginname(loginname);
		condition.setSmsMessage(smsMessage);
		condition.setInvitation(yaoqingma);
		condition.setSubsource(subsource);
		condition.setSource(source);
		Object[] obj = this.loginService.doMessageLogin(condition, paramMap);
		Integer code = (Integer)obj[0];
		return BaseResponse.buildSuccess(obj[1],code, msg.get(code));
	}

	//审核状态判断method60
	@GetMapping("/method060")
	@ApiOperation(value="审核状态判断",httpMethod="GET")
	public ResponseEntity method060(){

		return BaseResponse.buildSuccess("fail",0,"fail");
	}

	//method061店铺购买会员
	@GetMapping("/method061")
	@ApiOperation(value="店铺购买会员",httpMethod="GET")
	public ModelAndView method061(Long userid,String subsource,String openid,String sharephone,@ApiIgnore HttpServletRequest request){
		Map<String,Object> map = new HashMap<>();
		String view ="/share/payman";
		map.put("userid",userid.toString());
		map.put("subsource",subsource);
		map.put("openid",openid);
		map.put("sharephone",sharephone);
		ModelAndView mav = new ModelAndView(view);
		mav.addObject("user",map);
		return mav;
	}

	//method062
	@GetMapping("/method062")
	@ApiOperation(value="前端跳转用",httpMethod="GET")
	public ModelAndView method062(){
		Map<String,Object> map = new HashMap<>();
		String view ="/share/member-search";
		ModelAndView mav = new ModelAndView(view);
		mav.addObject("user",map);
		return mav;
	}

	//method063进入店铺
	@GetMapping("/method063")
	@ApiOperation(value="进入店铺",httpMethod="GET")
	public ModelAndView method063(Long userid,String subsource,String sharephone,String openid,@ApiIgnore HttpServletRequest request){
		Map<String,Object> map = new HashMap<>();
		String view ="/share/member";
		UserSetting user=this.userService.getUserInfoById(userid);
		System.out.println("======================================="+user);
		map.put("headimgurl",user.getHeadimgurl());
		map.put("sharephone",user.getPhone());
		map.put("userid",userid.toString());
		map.put("subsource",subsource);
		map.put("openid",openid);
		if(user.getName() == null ){
			if(user.getNickname() == null ){
				map.put("nickname","好友的"+"店铺");
			}else{
				map.put("nickname",user.getNickname()+"的店铺");
			}
		}else{
			map.put("nickname",user.getName()+"的店铺");
		}
		ModelAndView mav = new ModelAndView(view);
		mav.addObject("user",map);
		return mav;
	}

	//method064
	@GetMapping("/method064")
	@ApiOperation(value="银行卡信息动态验证",httpMethod="GET")
	public ResponseEntity method064(String bankCard){
		BankCard bk =new BankCard();
		try {
			//验证银行卡信息
			String host = "http://ali-bankcard.showapi.com";
			String path = "/bankcard";
			String method = "GET";
			String appcode = "963c93cef90c410b8cdd46e32308961d";
			Map<String, String> headers = new HashMap<String, String>();
			//最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
			headers.put("Authorization", "APPCODE " + appcode);
			//根据API的要求，定义相对应的Content-Type
			headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			Map<String, String> querys = new HashMap<String, String>();
			querys.put("kahao", bankCard);
			HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
			//获取response的body
			String re = EntityUtils.toString(response.getEntity());
			//存放请求返回信息
			JSONObject jb = JSONObject.fromObject(re);
			Map<String, Object> map2 = (Map<String, Object>) jb;
			Map returnm = (Map) map2.get("showapi_res_body");
			Map<String, Object> s = new HashMap<>(returnm);
			Object bname = s.get("bankName");
			Object tel = s.get("tel");
			Object remark = s.get("remark");
			if (remark != null) {
				return BaseResponse.buildSuccess("找不到此卡号信息", 0, "失败");
			}
			bk = this.bankCardMapper.queryBankBycard(tel.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return BaseResponse.buildSuccess(bk, 0, "失败");
	}
}