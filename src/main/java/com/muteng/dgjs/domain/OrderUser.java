package com.muteng.dgjs.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="orderuser")
public class OrderUser {
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="userid")
    private Long userid;
    
	@Column(name="orderid")
    private Long orderid;
	@Column(name="orderlistid")
    private Long orderlistid;
	@Column(name="name")
    private String name;
	@Column(name="loginname")
    private String loginname;
	@Column(name="ordertype")
    private String ordertype;
	@Column(name="accessusername")
    private String accessusername;
	@Column(name="dimission")
    private String dimission;
	@Column(name="dimissiontime")
    private Date dimissiontime;
	@Column(name="sex")
    private String sex;
	@Column(name="createtime")
    private Date createtime;
	@Column(name="processstatus")
    private Integer processstatus;
    
   
}