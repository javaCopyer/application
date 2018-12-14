package com.muteng.dgjs.dao;

import java.util.List;
import java.util.Map;

import com.muteng.dgjs.DTO.DzRevenue;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.dao.helper.DefaultMapper;
import com.muteng.dgjs.domain.OrdersPaylog;
public interface OrdersPaylogMapper extends DefaultMapper<OrdersPaylog>{

	@Select("SELECT p.orderid,op.amount as price,p.paytype,op.paycode,op.createtime,p.orderuserid as userid" +
			" FROM paymentvoucher p " +
			" left join orders_paylog op on op.orderid = p.orderid" +
			" WHERE op.userid = #{userid}" +
			" and op.paycode = p.code" +
			" order by createtime desc")
	List<DzRevenue> queryList(Map<String,Object> map);
}
