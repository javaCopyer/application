package com.muteng.dgjs.service;

import java.util.List;

import com.muteng.dgjs.DTO.OrderuserPaymentvoucherDTO;
import com.muteng.dgjs.domain.OrderUser;

public interface OrderUserService {
	//
	List<OrderUser> queryByOrderListId(Long orderlistid);

	//
	List<OrderuserPaymentvoucherDTO> queryPaymentvoucherAndOrderUser(Long orderlistid);
}
