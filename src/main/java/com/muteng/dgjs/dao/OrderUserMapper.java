package com.muteng.dgjs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.dao.helper.DefaultMapper;
import com.muteng.dgjs.DTO.OrderuserPaymentvoucherDTO;
import com.muteng.dgjs.domain.OrderUser;

public interface OrderUserMapper extends DefaultMapper<OrderUser>{
	@Select("select * from orderuser where orderlistid = #{orderlistid}")
	List<OrderUser> queryByOrderListId(Long orderlistid);
	
	@Select("SELECT  " + 
			"t5.*, " + 
			"t4.paytype, t4.isrecharge, t4.day, t4.price, t4.orderusername, t4.name paymentvouchername, t4.content,  " + 
			"t4.thiswayday, " + 
			"t4.daystatus, t4.stagename, t4.property, t4.computeday, t4.code paymentvouchercode, t4.peopletype " + 
			"FROM paymentvoucher t4  " + 
			"LEFT OUTER JOIN orderuser t5 " + 
			"ON t4.orderuserid = t5.id " + 
			"WHERE t5.orderlistid = #{orderlistid}")
	List<OrderuserPaymentvoucherDTO> queryPaymentvoucherAndOrderUser(Long orderlistid);
}
