package com.muteng.dgjs.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "orderpool")
public class Orderpool {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	@Column(name="telephoneaccessid")
    private Long telephoneaccessid;
	@Column(name="clientgrade")
    private String clientgrade;
	@Column(name="loginname")
    private String loginname;
	@Column(name="name")
    private String name;
	@Column(name="year")
    private Integer year;
	@Column(name="month")
    private Integer month;
	@Column(name="day")
    private Integer day;
	@Column(name="sex")
    private String sex;
	@Column(name="education")
    private String education;
	@Column(name="idcard")
    private String idcard;
	@Column(name="address")
    private String address;
	@Column(name="productname")
    private String productname;
	@Column(name="encoder")
    private String encoder;
	@Column(name="province")
    private String province;
	@Column(name="dialnumber")
    private Integer dialnumber;
	@Column(name="throughnumber")
    private Integer throughnumber;
	@Column(name="phonestate")
    private String phonestate;
	@Column(name="source")
    private String source;
	@Column(name="subsource")
    private String subsource;
	@Column(name="expected_work_city")
    private String expectedWorkCity;
	@Column(name="belong_city")
    private String belongCity;
	@Column(name="remarks")
    private String remarks;
	@Column(name="ordertime")
    private Date ordertime;
	@Column(name="remarkstime")
    private Date remarkstime;
	@Column(name="createtime")
    private Date createtime;
}