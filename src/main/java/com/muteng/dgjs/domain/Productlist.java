package com.muteng.dgjs.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "productlist")
public class Productlist {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	@Column(name="productid")
    private Long productid;
	@Column(name="accessuserid")
    private Long accessuserid;
	@Column(name="factoryid")
    private Long factoryid;
	@Column(name="supplierid")
    private Long supplierid;
	@Column(name="positionsid")
    private Long positionsid;
	@Column(name="productno")
    private Long productno;
	@Column(name="title")
    private String title;
	@Column(name="longtitle")
    private String longtitle;
	@Column(name="address")
    private String address;
	@Column(name="minprice")
    private Long minprice;
	@Column(name="marketprice")
    private Long marketprice;
	@Column(name="status")
    private String status;
	@Column(name="registration")
    private Integer registration;
	@Column(name="producttype")
    private String producttype;
	@Column(name="salary")
    private String salary;
	@Column(name="minsalary")
    private Long minsalary;
	@Column(name="maxsalary")
    private Long maxsalary;
	@Column(name="sex")
    private String sex;
	@Column(name="minage")
    private Integer minage;
	@Column(name="maxage")
    private Integer maxage;
	@Column(name="requirements")
    private String requirements;
	@Column(name="education")
    private String education;
	@Column(name="annualsalary")
    private Integer annualsalary;
	@Column(name="picture")
    private String picture;
	@Column(name="createtime")
    private Date createtime;
	@Column(name="recommend")
    private String recommend;
	@Column(name="day")
    private Integer day;
	@Column(name="money")
    private Long money;
	@Column(name="sort")
    private Long sort;
	@Column(name="accessusername")
    private String accessusername;
	@Column(name="interviewtime")
    private String interviewtime;
	@Column(name="interviewlocation")
    private String interviewlocation;
	@Column(name="updatename")
    private String updatename;
	@Column(name="updatetime")
    private Date updatetime;
	@Column(name="businessid")
    private Long businessid;
	@Column(name="jobrequirements")
    private String jobrequirements;
	@Column(name="basicinformation")
    private String basicinformation;
	@Column(name="interviewsituation")
    private String interviewsituation;
	@Column(name="workingcondition")
    private String workingcondition;
	@Column(name="compensationwelfare")
    private String compensationwelfare;
	@Column(name="brand")
    private String brand;
	@Column(name="price")
    private Long price;
	@Column(name="logo")
    private String logo;

}