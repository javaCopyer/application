package com.muteng.dgjs.dao;

import java.util.Date;
import java.util.Map;

import org.apache.ibatis.annotations.*;

import com.dao.helper.DefaultMapper;
import com.muteng.dgjs.domain.Account;
import com.muteng.dgjs.domain.OrdersMember;
public interface PayMapper extends DefaultMapper<Account>{

	@Select("select * from ordersmember where orderNo=#{orderNo}")
	OrdersMember getOrder(@Param("orderNo") Long orderNo);
	
	//更改订单状态
	@Update("UPDATE orderlist SET STATUS = #{status},updatetime = now()  WHERE orderNo = #{orderNo}")
	int updateOrderListStatus(@Param("orderNo") Long orderNo, @Param("status") String status);
	//更改订单状态
	@Update("UPDATE ordersmember SET STATUS = #{param2},payfinishtime = now(),finalpayprice = #{param3},finishtime=DATE_ADD(now(), INTERVAL 1 YEAR) WHERE orderNo = #{param1}")
	int updateOrdersMemberStatus(Long orderNo,Integer status,String amount);
	//添加账单
	@Insert("INSERT INTO account(userid, amount, TYPE, reason, createtime, updatetime, STATUS, COMMENT) VALUES(#{userid}, #{amount}, #{type}, #{reason}, #{createtime}, #{updatetime}, #{status}, #{comment})")
	int insertAccount(Account account);
	
	//查询订单和产品信息
	@Select("SELECT t2.accessuserid, t2.productid, t2.title,  t1.ordertype, t1.orderid, t3.name " + 
			"FROM orderlist t1 LEFT OUTER JOIN productlist t2  " + 
			"ON t1.productid = t2.productid AND t2.producttype = t1.ordertype  " + 
			"LEFT OUTER JOIN accessuser t3 " + 
			"ON t2.accessuserid = t3.id " + 
			"WHERE t1.orderid = #{orderid}")
	Map<String, Object> getOrderAndProductInfo(@Param("orderid") Long orderid);
	
	//天剑付款单
	@Update("INSERT INTO " + 
			"orders_incomelog(orderid, productmanagerid, ordertype, userid, amount, incometype, method, createtime, COMMENT, orderNo, productid, productname, accessusername, productmanagername, CODE, collectiontime, returnfeecode)"
			+ ""
			+ "values(#{orderid}, #{productmanagerid}, #{ordertype}, #{userid}, #{amount}, #{incometype}, #{method}, #{createtime}, #{comment}, #{orderNo}, #{productid}, #{productname}, #{accessusername}, #{productmanagername}, #{code}, #{collectiontime}, #{returnfeecode})")
	int insertOrdersIncomelog(@Param("orderid") Long orderid, @Param("productmanagerid") Long productmanagerid, @Param("ordertype") String ordertype, @Param("userid") Long userid, @Param("amount") int amount, @Param("incometype") String incometype, @Param("method") String method, @Param("createtime") Date createtime, @Param("comment") String comment, @Param("orderNo") Long orderNo, @Param("productid") Long productid, @Param("productname") String productname, @Param("accessusername") String accessusername, @Param("productmanagername") String productmanagername, @Param("code") String code, @Param("collectiontime") Date collectiontime, @Param("returnfeecode") String returnfeecode);


	@Delete("delete from ordersmember where orderNo = #{orderNo}")
	void deleteorderByoderNo(String orderNo);

	@Delete("delete from orderlist where orderNo = #{orderNo}")
	void deleteorderListByoderNo(String orderNo);

	//（取消）更改订单状态
	@Update("UPDATE ordersmember SET canceltime = now() WHERE orderNo = #{orderNo}")
	void updateOrdersMember(@Param("orderNo") String orderNo);
}
