package com.muteng.dgjs.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Table(name = "orderlist")
public class Orderlist {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

	@Column(name = "orderid")
    private Long orderid;

	@Column(name = "supplierid")
    private Long supplierid;

	@Column(name = "canceltemplateid")
    private Long canceltemplateid;

	@Column(name = "orderno")
    private Long orderno;

	@Column(name = "ordertype")
    private String ordertype;

	@Column(name = "loginname")
    private Long loginname;

	@Column(name = "name")
    private String name;

	@Column(name = "idcard")
    private String idcard;

	@Column(name = "accessid")
    private Long accessid;

	@Column(name = "accessname")
    private String accessname;

	@Column(name = "productid")
    private Long productid;

	@Column(name = "productname")
    private String productname;

	@Column(name = "createtime")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createtime;

	@Column(name = "status")
    private String status;

	@Column(name = "remarks")
    private String remarks;

	@Column(name = "remarkstime")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date remarkstime;

	@Column(name = "source")
    private String source;

	@Column(name = "subsource")
    private String subsource;

	@Column(name = "hiredate")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date hiredate;

	@Column(name = "numberorder")
    private Integer numberorder;

	@Column(name = "orderplacerid")
    private Long orderplacerid;

	@Column(name = "orderplacer")
    private String orderplacer;

	@Column(name = "feedays")
    private Integer feedays;

	@Column(name = "amountcashed")
    private Integer amountcashed;

	@Column(name = "businesscontact")
    private String businesscontact;

	@Column(name = "financecontact")
    private String financecontact;

	@Column(name = "drivercontact")
    private String drivercontact;

	@Column(name = "interviewtime")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date interviewtime;

	@Column(name = "interviewpasstime")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date interviewpasstime;

	@Column(name = "returntime")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date returntime;

	@Column(name = "resignationtime")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date resignationtime;

	@Column(name = "prepaymenttime")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date prepaymenttime;

	@Column(name = "actualtime")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date actualtime;

	@Column(name = "actualamount")
    private Integer actualamount;

	@Column(name = "monthaccounts")
    private Integer monthaccounts;

	@Column(name = "balance")
    private Integer balance;

	@Column(name = "mystatus")
    private String mystatus;

	@Column(name = "updatetime")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updatetime;

	@Column(name = "appstatus")
    private String appstatus;

	@Column(name = "processstatus")
    private Integer processstatus;

	@Column(name = "businessid")
    private Long businessid;

	@Column(name = "offerinteviewtime")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date offerinteviewtime;

	@Column(name = "schemeid")
    private Long schemeid;
	@Transient
    private Productlist productlist;
    @Transient
    private List<OrderUser> orderuser;
   
    
}