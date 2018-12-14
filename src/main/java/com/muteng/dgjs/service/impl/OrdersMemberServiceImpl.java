package com.muteng.dgjs.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.muteng.dgjs.domain.*;
import org.springframework.stereotype.Service;

import com.muteng.dgjs.common.utils.CommonUtil;
import com.muteng.dgjs.dao.OrderListMapper;
import com.muteng.dgjs.dao.OrdersMemberMapper;
import com.muteng.dgjs.dao.ProductMapper;
import com.muteng.dgjs.dao.UserMapper;
import com.muteng.dgjs.service.OrdersMemberService;
@Service
public class OrdersMemberServiceImpl implements OrdersMemberService{
	
	@Resource
	OrdersMemberMapper ordersMemberMappper;
	@Resource
	UserMapper userMapper;
	@Resource
	ProductMapper productMapper;
	@Resource
	OrderListMapper orderlistMapper;
	
	public Object[] addOrderMember(Map<String,Object> paramMap) {

		Object[] obj = new Object[2];
		Map<String,Object> res = new HashMap<String,Object>();
		User u = this.userMapper.queryByUserId(paramMap);
		if(paramMap.get("productid")==null) {
			paramMap.put("productid", 1088);
		}
		Productlist p = this.productMapper.queryProductByid(paramMap);
		
		if(u == null) {
			//返回用户不存在
			obj[0] = 0;
			res.put("msg", "用户不存在");
			obj[1] = res;
		}
		
		Orderlist ol = new Orderlist();
		ol.setLoginname(Long.parseLong(u.getLoginname()));
		ol.setName(u.getName());
		ol.setSupplierid(p.getSupplierid());
		ol.setProductid(p.getProductid());
		ol.setOrdertype("会员");
		ol.setProductname(p.getTitle());
		ol.setStatus("已报名");
		ol.setAccessid(0L);
		ol.setCreatetime(new Timestamp(System.currentTimeMillis()));
		ol.setSource(paramMap.get("source").toString());
		ol.setSubsource(paramMap.get("subsource").toString());
		int id = orderlistMapper.insert(ol);
		long orderNo = CommonUtil.getOrderNO(id);
		ol.setOrderno(orderNo);
		orderlistMapper.updateByPrimaryKey(ol);
		
		OrdersMember member = new OrdersMember();
		if(paramMap.get("userid") ==null){
			member.setOpenid(paramMap.get("openid").toString());
		}
		member.setUserid(Long.parseLong(paramMap.get("userid").toString()));
		member.setCreatetime(new Timestamp(System.currentTimeMillis()));
		member.setIsinvoice(0);
		member.setNumberorder(1);
		member.setStatus(1);
		member.setFinalcost(0);
		member.setAccessuserid(0L);
		member.setShouldpayprice(1);
		Calendar now=Calendar.getInstance();
		now.add(Calendar.MINUTE,15);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr=sdf.format(now.getTimeInMillis());
		member.setPaydeadlinetime(dateStr);
		member.setSupplierid(p.getSupplierid());
		member.setProductid(p.getId());
		member.setOrderNo(orderNo);
		member.setSource(paramMap.get("source").toString());
		member.setSubsource(paramMap.get("subsource").toString());
		member.setOrderplacer(paramMap.get("userid").toString());
		ordersMemberMappper.insert(member);
		OrdersMember om = ordersMemberMappper.queryorderById(Long.parseLong(paramMap.get("userid").toString()));
		ol.setOrderid(member.getId());
		orderlistMapper.updateByPrimaryKey(ol);
		
		obj[0] = 1;
		res.put("orderNo", orderNo);
		res.put("orderid",om.getId());
		res.put("productid", p.getId());
		obj[1] = res;
		return obj;
	}

	@Override
	public Object[] addOrderMember2(Map<String, Object> paramMap) {
		Object[] obj = new Object[2];
		Map<String,Object> res = new HashMap<String,Object>();
		if(paramMap.get("productid")==null) {
			paramMap.put("productid", 1088);
		}
		Productlist p = this.productMapper.queryProductByid(paramMap);
		String loginname= (String) paramMap.get("loginname");

		Orderlist ol = new Orderlist();
		ol.setLoginname(Long.parseLong(loginname));
		ol.setSupplierid(p.getSupplierid());
		ol.setProductid(p.getProductid());
		ol.setOrdertype("会员");
		ol.setProductname(p.getTitle());
		ol.setStatus("已报名");
		ol.setAccessid(0L);
		ol.setCreatetime(new Timestamp(System.currentTimeMillis()));
		ol.setSource(paramMap.get("source").toString());
		ol.setSubsource(paramMap.get("subsource").toString());
		int id = orderlistMapper.insert(ol);
		long orderNo = CommonUtil.getOrderNO(id);
		ol.setOrderno(orderNo);
		orderlistMapper.updateByPrimaryKey(ol);

		OrdersMember member = new OrdersMember();
		if(paramMap.get("userid") ==null){
			member.setOpenid(paramMap.get("openid").toString());
		}

		member.setUserid(Long.parseLong(paramMap.get("userid").toString()));
		member.setCreatetime(new Timestamp(System.currentTimeMillis()));
		member.setIsinvoice(0);
		member.setNumberorder(1);
		member.setStatus(1);
		member.setFinalcost(0);
		member.setAccessuserid(0L);
		member.setShouldpayprice(1);
		Calendar now=Calendar.getInstance();
		now.add(Calendar.MINUTE,15);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr=sdf.format(now.getTimeInMillis());
		member.setPaydeadlinetime(dateStr);
		member.setSupplierid(p.getSupplierid());
		member.setProductid(p.getId());
		member.setOrderNo(orderNo);
		member.setSource(paramMap.get("source").toString());
		member.setSubsource(paramMap.get("subsource").toString());
		member.setOrderplacer(paramMap.get("userid").toString());
		ordersMemberMappper.insert(member);
		OrdersMember om = ordersMemberMappper.queryorderById(Long.parseLong(paramMap.get("userid").toString()));
		ol.setOrderid(member.getId());
		orderlistMapper.updateByPrimaryKey(ol);

		obj[0] = 1;
		res.put("orderNo", orderNo);
		res.put("orderid",om.getId());
		res.put("productid", p.getId());
		obj[1] = res;
		return obj;
	}

	@Override
	public OrdersMember queryorderById(Long id) {
		return ordersMemberMappper.queryorderById(id);
	}


	@Override
	public String queryorderByNo(String orderNo) {
		return ordersMemberMappper.queryorderByNo(orderNo);
	}

}
	
