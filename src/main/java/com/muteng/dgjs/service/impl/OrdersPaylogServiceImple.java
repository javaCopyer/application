package com.muteng.dgjs.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.muteng.dgjs.DTO.DzRevenue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muteng.dgjs.dao.OrdersPaylogMapper;
import com.muteng.dgjs.domain.OrdersPaylog;
import com.muteng.dgjs.service.OrdersPaylogService;

@Service
public class OrdersPaylogServiceImple implements OrdersPaylogService{

	@Autowired
	private OrdersPaylogMapper ordersPaylogMapper;
	
	@Override
	public List<DzRevenue> getOrderPaylogs(Map<String, Object> map) {
		List<DzRevenue> list = this.ordersPaylogMapper.queryList(map);

		return list;
	}
}