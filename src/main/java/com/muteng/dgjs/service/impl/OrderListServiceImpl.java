package com.muteng.dgjs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.muteng.dgjs.dao.OrderListMapper;
import com.muteng.dgjs.domain.Orderlist;
import com.muteng.dgjs.service.OrderListService;
@Service
public class OrderListServiceImpl implements OrderListService {

	private static Map<String,String> mappingStatus = new HashMap<String,String>();
	private static Map<String, String>  statusFlowMap = new HashMap<String, String>();

	static {
		//数据库状态映射app显示状态
		mappingStatus.put("已取消",			"结束");
		mappingStatus.put("系统回滚",			"结束");
		mappingStatus.put("已报名",			"报名");
		mappingStatus.put("邀约成功",			"邀约面试");
		mappingStatus.put("面试",			"邀约面试");
		mappingStatus.put("面试通过",			"面试通过");
		mappingStatus.put("放弃面试",			"未到场");
		mappingStatus.put("拒绝面试",			"未到场");
		mappingStatus.put("放弃入职",			"面试通过");
		mappingStatus.put("面试未通过",		"面试未通过");
		mappingStatus.put("供应商拖欠",		"入职");
		mappingStatus.put("收到返费-结束",	"入职");
		mappingStatus.put("入职-返费确认中",	"入职");
		mappingStatus.put("离职",			"离职");
		mappingStatus.put("申请离职",			"离职");

		statusFlowMap.put("结束",		"结束");
		statusFlowMap.put("报名",		"报名");
		statusFlowMap.put("邀约面试",		"邀约面试-报名");
		statusFlowMap.put("面试通过",		"面试通过-邀约面试-报名");
		statusFlowMap.put("未到场",		"未到场-邀约面试-报名");
		statusFlowMap.put("面试未通过",	"面试未通过-邀约面试-报名");
		statusFlowMap.put("入职",		"入职-面试通过-邀约面试-报名");
		statusFlowMap.put("离职",		"离职-入职-面试通过-邀约面试-报名");
	}
	@Resource
	OrderListMapper orderListMapper;


	@Override
	public List<Orderlist> queryByLoginname(String phone) {

		return orderListMapper.queryByLoginname(phone);
	}

	//查询我的入职列表
	@Override
	public List<Map<String, Object>> queryMyEntry(long userid) {
		List<Map<String, Object>> list = this.orderListMapper.queryMyEntry(userid);
		//循环映射app状态
		for(Map<String,Object> map :list) {
			if (map.get("appstatus")!=null) {
				//根据当前状态映射app显示当前状态
				map.put("appstatus", mappingStatus.get(map.get("appstatus")));
			}
		}

		return list;
	}

	//查询邀请入职
	@Override
	public List<Map<String,Object>> queryInvite(Long userid) {
		List<Map<String, Object>> list = this.orderListMapper.queryInvitedEntry(userid);
		for (Map map : list) {
			if (map.get("appstatus")!=null) {
				//根据当前状态映射app显示当前状态
				map.put("appstatus", mappingStatus.get(map.get("appstatus")));
			}
		}

		return list;
	}
	//查询入职详情
	@Override
	public List<Map<String, Object>> queryById(Long orderid) {
		List<Map<String, Object>> list = orderListMapper.queryMyEntryDatils(orderid);
		for (Map<String, Object> map :list
			 ) {
			if(map.get("olname")==null){
				map.put("olname"," ");
			}
		}

		String status = String.valueOf(list.get(0).get("status"));
		//取出该订单所有用户
		List<Map<String, Object>> Alluserlist = orderListMapper.queryEntryDatils(orderid);
		//存放订单用户信息和相关凭证;
        List<Map<String, Object>> userList  = new ArrayList<>();
		//遍历订单内用户，查询凭证号，再查询付款单号
		for (Map<String, Object> map : Alluserlist) {
			//取付款凭证号
				List<Map<String,Object>> voucherList = orderListMapper.queryvoucher(Integer.parseInt(String.valueOf(map.get("orderuserid"))));
				double sum = 0;
				for(Map<String,Object> voucherMap : voucherList){
                    voucherMap.put("price",(Integer.parseInt(String.valueOf(voucherMap.get("price")))/100));
                    voucherMap.put("pay","未发放");
                    String daystatus = String.valueOf(voucherMap.get("daystatus"));
                    String day = String.valueOf(voucherMap.get("day"));
                    String price = String.valueOf(voucherMap.get("price"));
                    String paytype = String.valueOf(voucherMap.get("paytype"));

                    day = day.replace("null","1");
                    daystatus = daystatus.replace("null","自然日");
                    paytype = paytype.replace("会员","会员奖励");
                    paytype = paytype.replace("店主","店主奖励");
					voucherMap.put("content","入职"+day+"个"+daystatus+"，"+paytype+price+"元");
                    Map<String,Object>  paylogMap= orderListMapper.queryPaylog(voucherMap);
                     if(paylogMap!=null){
                       sum = sum+Double.valueOf(String.valueOf(paylogMap.get("price")));
                      paylogMap.put("pay","已发放");
						 paylogMap.put("content","入职"+day+"个"+daystatus+"，"+paytype+paylogMap.get("price")+"元");
                      userList.add(paylogMap);
                     }else {
                         sum = sum+Integer.parseInt(String.valueOf(voucherMap.get("price")));
						userList.add(voucherMap);
                     }
				}
				map.put("allprice",sum);
				map.put("uservoucher",userList);

		}

		for(Map<String,Object> map :Alluserlist){
			Object dimission  = map.get("dimission");
			if(dimission!=null && (String.valueOf(dimission).equals("离职") )){
				map.put("pay","离职作废");
			}
		}
		list.get(0).put("orderuser",Alluserlist);

		Map<String, String>  statusTimeMap =orderListMapper.queryTime(orderid);
		statusTimeMap.put("报名",statusTimeMap.get("createtime"));
		statusTimeMap.put("邀约面试",statusTimeMap.get("offerinteviewtime"));
		statusTimeMap.put("面试通过",statusTimeMap.get("interviewpasstime"));
		statusTimeMap.put("面试未通过",statusTimeMap.get("interviewpasstime"));
		statusTimeMap.put("未到场",statusTimeMap.get("interviewpasstime"));
		statusTimeMap.put("入职",statusTimeMap.get("hiredate"));
		statusTimeMap.put("离职",statusTimeMap.get("resignationtime"));
		statusTimeMap.put("结束","");

		//根据当前状态映射app显示当前状态
		String mappingstatus = mappingStatus.get(status);
		//根据当前状态取出状态跟踪
		String flow = statusFlowMap.get(mappingstatus);
		//将状态跟踪以‘-’分割存入flows数组
		String[] flows = flow.split("-");
		List<Map<String,Object>> statusList = new ArrayList<Map<String,Object>>();

		for(int i =0 ;i<flows.length;i++){
			//根据单个状态取时间
			Map<String,Object> resMap = new HashMap<String,Object>();
			resMap.put("status",flows[i]);
			resMap.put("time",statusTimeMap.get(flows[i]));
			statusList.add(resMap);
		}
		Map<String,Object> statusMap= new HashMap<String,Object>();
		statusMap.put("statuslist",statusList);
		list.add(statusMap);
		//取订单的邀请人
				List<Map<String, Object>> orderInvitedslist = orderListMapper.heighLevelMap(orderid);
		for (Map<String,Object> map :orderInvitedslist) {
			if(map.get("price")==null){
				map.put("status","未到账");
			}
			if(mappingstatus.equals("结束")){
				map.put("status","离职作废");
			}

			if(map.get("price")!=null){
				map.put("status","已到账");
			}
			if(map.get("name")==null){
				map.put("name"," &nbsp");
			}
			if(String.valueOf(map.get("amount")).equals("0.0000")){
				map.remove("amount");
			}
		}

			Map<String,Object> orderListMap = new HashMap<>();
			if(orderInvitedslist.size()>0){
				orderListMap.put("orderinvites",orderInvitedslist);
				list.add(orderListMap);
			}
		return list;

	}

    @Override
    public String queryByorderNo(String orderNo) {
		return this.orderListMapper.queryByorderNo(orderNo);
    }

	@Override
	public void addcomplaint(Long userid, String word) {
		this.orderListMapper.addcomplaint(userid,word);
	}


}