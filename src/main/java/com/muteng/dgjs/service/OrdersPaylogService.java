package com.muteng.dgjs.service;

import java.util.List;
import java.util.Map;

import com.muteng.dgjs.DTO.DzRevenue;
import com.muteng.dgjs.domain.OrdersPaylog;

public interface OrdersPaylogService {

	List<DzRevenue> getOrderPaylogs(Map<String,Object> map);
}
