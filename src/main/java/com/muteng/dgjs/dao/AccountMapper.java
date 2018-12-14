package com.muteng.dgjs.dao;

import java.util.List;
import java.util.Map;

import com.muteng.dgjs.DTO.*;
import com.muteng.dgjs.domain.FinanceReceiptDocument;
import com.muteng.dgjs.domain.FinanceReceiptVoucher;
import com.muteng.dgjs.domain.OrdersPaylog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import com.dao.helper.DefaultMapper;
import com.muteng.dgjs.domain.Account;
public interface AccountMapper extends DefaultMapper<Account>{

	//获取账单列表
	@Select("select * from account where userid = #{userid} AND type !=1 order by createtime desc")
	List<Account2> queryAccount(Long userid);


	//添加付款凭证
	@Insert("insert into Paymentvoucher (orderid,orderuserid,ordertype,paytype,price,name,orderusername,content," +
			"createtime,code,peopletype) values" +
			"(#{orderid},#{orderuserid},#{ordertype},#{paytype},#{price},#{name},#{orderusername},#{content}," +
			"#{createtime},#{code},#{peopletype})")
    void addpaym(Paymentvoucher pm);


	//添加付款单
	@Insert("insert into orders_paylog (orderid,ordertype,amounttype,userid,method,amount,createtime," +
			" comment,paycode,code) values" +
			"(#{orderid},#{ordertype},#{amounttype},#{userid},#{method},#{amount},#{createtime}," +
			"#{comment},#{paycode},#{code})")
	void addop(OrdersPaylog op);

	//查询付款凭证
	@Select("select * from finance_payment_voucher where createtime >= #{begintime} and createtime <=#{endtime} order by num desc limit 1")
	FinancePaymentVoucher PaymentVoucher(Map<String, String> paramMap);

	//查询付款单
	@Select("select * from finance_payment_document where createtime >= #{begintime} and createtime <=#{endtime} order by num desc limit 1")
	FinancePaymentDocument paymentdocument(Map<String, String> paramMap);

	//添加付款凭证
	@Insert("insert into finance_payment_voucher (number,num,type,vouchertype,createtime) values(#{number},#{num},#{type},#{vouchertype},#{createtime})")
	void addnewpv(FinancePaymentVoucher newfrv);

	//添加付款单
	@Insert("insert into finance_payment_document (number,num,type,documenttype,createtime) values(#{number},#{num},#{type},#{documenttype},#{createtime})")
	void addnewop(FinancePaymentDocument newfrv);

	//查询收款凭证
	@Select("select * from finance_receipt_voucher where " +
			" createtime>=#{begintime} and createtime<=#{endtime} order by num desc limit 1")
    FinanceReceiptVoucher receiptvoucher(Map<String, String> paramMap);

	//添加收款凭证
	@Select("insert into finance_receipt_voucher (number,num,type,vouchertype,createtime) " +
			" values(#{number},#{num},#{type},#{vouchertype},#{createtime}) ")
	void addnewrv(FinanceReceiptVoucher newfrv);

	//查询收款单
	@Select("select * from finance_receipt_document where " +
			" createtime>=#{begintime} and createtime<=#{endtime} order by num desc limit 1")
	FinanceReceiptDocument receiptDocument(Map<String, String> paramMap);

	//添加收款凭证
	@Select("insert into finance_receipt_document (number,num,type,vouchertype,createtime) " +
			" values(#{number},#{num},#{type},#{vouchertype},#{createtime}) ")
	void addnewrd(FinanceReceiptDocument newfrv);

}
