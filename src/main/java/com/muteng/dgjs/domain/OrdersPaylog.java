package com.muteng.dgjs.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Table(name = "orders_paylog")
public class OrdersPaylog {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

	@Column(name = "orderid")
    private Long orderid;

	@Column(name = "ordertype")
    private String ordertype;

	@Column(name = "amounttype")
    private String amounttype;

	@Column(name = "userid")
    private Long userid;

	@Column(name = "method")
    private String method;

	@Column(name = "account")
    private String account;

	@Column(name = "name")
    private String name;

	@Column(name = "bank")
    private String bank;

	@Column(name = "amount")
    private Integer amount;

	@Column(name = "createtime")
    private Date createtime;

	@Column(name = "comment")
    private String comment;

	@Column(name = "accessusername")
    private String accessusername;

	@Column(name = "code")
    private String code;

	@Column(name = "paycode")
    private String paycode;

	@Column(name = "collectiontime")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date collectiontime;
}