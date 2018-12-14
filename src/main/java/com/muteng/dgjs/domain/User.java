package com.muteng.dgjs.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="user")
public class User {
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	@Column(name="name")
    private String name;
	@Column(name="loginname")
    private String loginname;
	@Column(name="birthday")
    private Date birthday;
	@Column(name="idcard")
    private String idcard;
	@Column(name="pwd")
    private String pwd;
	@Column(name="nick")
    private String nick;
	@Column(name="usertype")
    private Integer usertype;
	@Column(name="orderpoolstatus")
    private String orderpoolstatus;
	@Column(name="sex")
    private Integer sex;
	@Column(name="education")
    private String education;
	@Column(name="job")
    private String job;
	@Column(name="salary")
    private Double salary;
	@Column(name="phone")
    private String phone;
	@Column(name="qq")
    private String qq;
	@Column(name="weixin")
    private String weixin;
	@Column(name="introduce")
    private String introduce;
	@Column(name="createtime")
    private Date createtime;
	@Column(name="updatetime")
    private Date updatetime;
	@Column(name="minzu")
    private String minzu;
	@Column(name="address")
    private String address;
	@Column(name = "touxiang")
	private String touxiang;
	@Column(name="accessuserid")
    private Long accessuserid;
	@Column(name="islocal")
    private String islocal;
	@Column(name="acccompanyid")
    private Long acccompanyid;
//	@Column(name="isdeformity")
//    private String isdeformity;
//	@Column(name="isstudent")
//    private String isstudent;
	@Column(name = "idcardimg")
	private String idcardimg;
	@Column(name="yaoqingma")
    private String yaoqingma;
	@Column(name="inviter")
    private String inviter;
	@Column(name="channelid")
    private Long channelid;
	@Column(name="account")
    private Long account;
	@Column(name="fund")
    private Long fund;
	@Column(name="loginstatus")
    private Integer loginstatus;
	@Column(name="belongcity")
    private String belongcity;
	@Column(name="expectedworkcity")
    private String expectedworkcity;
	@Column(name="isvalidate")
    private Boolean isvalidate;
	@Column(name="os")
    private String os;
	@Column(name="source")
    private String source;
	@Column(name="subsource")
    private String subsource;
	@Column(name="ip")
    private String ip;
	@Column(name="isinviter")
    private String isinviter;
	@Column(name="invitednumber")
    private Integer invitednumber;
	@Column(name="clientid")
    private String clientid;
	@Column(name="tattoo")
    private String tattoo;
	@Column(name="disability")
    private String disability;
	@Column(name="certificate")
    private String certificate;
	@Column(name="parentid")
    private Long parentid;
	@Column(name="provinceid")
    private String provinceid;
	@Column(name="cityid")
    private String cityid;
	@Column(name="districtid")
    private String districtid;
	@Column(name="townid")
    private String townid;
	@Column(name="weixinurl")
    private String weixinurl;
	@Column(name="alipay")
	private String alipay;
	@Column(name="appversion")
	private String appversion;
}