package com.muteng.dgjs.service;

import java.util.List;
import java.util.Map;

import com.muteng.dgjs.domain.Orderlist;

public interface OrderListService {
	List<Orderlist> queryByLoginname(String phone);

	//查询我的入职
	List<Map<String,Object>> queryMyEntry(long userid);


	//查询邀请入职
	List<Map<String,Object>> queryInvite(Long userid);

	//根据userid和orderid查询
	// 个人入职详情和同行人，邀请奖励
	List<Map<String,Object>> queryById(Long orderid);

	//根据oderNo查询订单金额
	String queryByorderNo(String  orderNo);

    void addcomplaint(Long userid, String word);

}
