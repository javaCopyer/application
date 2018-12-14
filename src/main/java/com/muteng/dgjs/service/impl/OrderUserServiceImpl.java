package com.muteng.dgjs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.muteng.dgjs.DTO.OrderuserPaymentvoucherDTO;
import com.muteng.dgjs.dao.OrderUserMapper;
import com.muteng.dgjs.domain.OrderUser;
import com.muteng.dgjs.service.OrderUserService;
@Service
public class OrderUserServiceImpl implements OrderUserService {
	@Resource
	private OrderUserMapper orderUserMapper;
	@Override
	public List<OrderUser> queryByOrderListId(Long orderlistid) {

		return orderUserMapper.queryByOrderListId(orderlistid);
	}
	@Override
	public List<OrderuserPaymentvoucherDTO> queryPaymentvoucherAndOrderUser(Long orderlistid) {
		return orderUserMapper.queryPaymentvoucherAndOrderUser(orderlistid);
	}
	
}
