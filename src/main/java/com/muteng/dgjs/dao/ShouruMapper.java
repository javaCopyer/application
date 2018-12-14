package com.muteng.dgjs.dao;

import java.util.List;

import com.muteng.dgjs.DTO.InviteRevenue;
import com.muteng.dgjs.DTO.LzRevenue;
import org.apache.ibatis.annotations.Select;

import com.dao.helper.DefaultMapper;
import com.muteng.dgjs.domain.UserInfo;
public interface ShouruMapper extends DefaultMapper<UserInfo>{
	//获取离职作废详情
	@Select("select ow.resignationtime,ow.hiredate,pv.price,pv.paytype,ow.createtime"+
			" from paymentvoucher pv"+
			" left join orderswork ow on ow.id=pv.orderid"+
			" where  ow.status in (0,9,11) and (ow.userid = #{userid}  or pv.orderuserid = #{userid})"+
			" and pv.paytype !='邀请人'")
	List<LzRevenue> getLizhiShouru(Long userid);


	//获取邀请收入详情
	@Select("Select osw.userid,pv.paytype,pv.code,pv.id,pv.price,osw.hiredate,osw.createtime,pv.orderid,COALESCE(pv.day,15) as collectiontime " +
			" from paymentvoucher pv" +
			" Left join orders_paylog op on pv.code = op.paycode " +
			" Left join orderswork osw on osw.id = pv.orderid" +
			" where op.paycode IS NULL and osw.userid=#{userid} and osw.status = 4 or pv.orderuserid = #{userid} " +
			"and paytype!='邀请人' and osw.status not in(0,9,11)")
	List<InviteRevenue> getInviteiShouru(Long userid);


	//获取到账金额
	@Select("SELECT sum(op.amount)" +
			" FROM paymentvoucher p " +
			" left join orders_paylog op on op.orderid = p.orderid" +
			" WHERE op.userid = #{userid}" +
			" and op.paycode = p.code")
	Integer getdz(Long userid);


	//获取未确认金额
	@Select("select sum(price) " +
			" from paymentvoucher pv" +
			" Left join orders_paylog op on pv.code = op.paycode " +
			" Left join orderswork osw on osw.id = pv.orderid" +
			" where op.paycode IS NULL and osw.userid=#{userid} and osw.status = 4 or pv.orderuserid = #{userid} " +
			"and paytype!='邀请人' and osw.status not in(0,9,11)" )
	Integer getwqr(Long userid);

	//获取离职作废金额
	@Select("select sum(price) " +
			" from paymentvoucher pv"+
			" left join orderswork ow on ow.id=pv.orderid"+
			" where  ow.status in (0,9,11) and (ow.userid = #{userid}  or pv.orderuserid = #{userid})"+
			" and pv.paytype !='邀请人'"+
			" order by resignationtime desc" )
	Integer getlz(Long userid);

	//获取可用余额
	@Select("select account from user where id = #{userid}")
	Integer getaccount(Long userid);

}
