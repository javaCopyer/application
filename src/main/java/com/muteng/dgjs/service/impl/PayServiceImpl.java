package com.muteng.dgjs.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muteng.dgjs.dao.PayMapper;
import com.muteng.dgjs.domain.Account;
import com.muteng.dgjs.domain.OrdersMember;
import com.muteng.dgjs.service.PayService;
@Service
public class PayServiceImpl implements PayService {
	@Autowired
	private PayMapper payMapper;
	@Override
	public OrdersMember getOrder(Long orderNo) {
		return payMapper.getOrder(orderNo);
	}
	@Override
	public int insertAccount(Account account) {
		return payMapper.insertAccount(account);
	}

	@Override
	public void deleteorderByoderNo(String orderNo) {
		payMapper.deleteorderByoderNo(orderNo);
	}

	@Override
	public void deleteorderListByoderNo(String orderNo) {
		payMapper.deleteorderListByoderNo(orderNo);
	}

	@Override
	public int updateOrderListStatus(Long orderNo, String status) {
		return payMapper.updateOrderListStatus(orderNo, status);
	}
	@Override
	public int updateOrdersMemberStatus(Long orderNo, Integer status,String amount) {
		return payMapper.updateOrdersMemberStatus(orderNo, status,amount);
	}
	@Override
	public Map<String, Object> getOrderAndProductInfo(Long orderid) {
		return payMapper.getOrderAndProductInfo(orderid);
	}
	@Override
	public int insertOrdersIncomelog(Long orderid, Long productmanagerid, String ordertype,
			Long userid, int amount, String incometype, String method, Date createtime, String comment, Long orderNo,
			Long productid, String productname, String accessusername, String productmanagername, String code,
			Date collectiontime, String returnfeecode) {
		return payMapper.insertOrdersIncomelog(orderid, productmanagerid, ordertype, userid, amount, incometype, method, createtime, comment, orderNo, productid, productname, accessusername, productmanagername, code, collectiontime, returnfeecode);
	}


	@Override
	public void updateOrdersMember(String orderNo) {
		payMapper.updateOrdersMember(orderNo);
	}
}
