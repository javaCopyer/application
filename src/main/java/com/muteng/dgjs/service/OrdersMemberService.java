package com.muteng.dgjs.service;

import com.muteng.dgjs.domain.OrdersMember;

import java.util.Map;

public interface OrdersMemberService {
	public Object[] addOrderMember(Map<String,Object> paramMap);

	Object[] addOrderMember2(Map<String, Object> paramMap);

    OrdersMember queryorderById(Long id);

	String queryorderByNo(String orderNo);
}
