package com.muteng.dgjs.service;

import java.util.Date;
import java.util.Map;

import com.muteng.dgjs.domain.Account;
import com.muteng.dgjs.domain.OrdersMember;

public interface PayService {
	OrdersMember getOrder(Long orderNo);
	int updateOrderListStatus(Long orderNo, String status);
	int updateOrdersMemberStatus(Long orderNo, Integer status,String amount);
	Map<String, Object> getOrderAndProductInfo(Long orderid);
	int insertOrdersIncomelog(Long orderid, Long productmanagerid, String ordertype, Long userid, int amount, String incometype, String method, Date createtime, String comment, Long orderNo, Long productid, String productname, String accessusername, String productmanagername, String code, Date collectiontime, String returnfeecode);
	int insertAccount(Account account);

	void deleteorderByoderNo(String orderNo);

	void deleteorderListByoderNo(String orderNo);

	void updateOrdersMember(String orderNo);
}
