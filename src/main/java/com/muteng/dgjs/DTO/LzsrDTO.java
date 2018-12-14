package com.muteng.dgjs.DTO;

import java.util.Date;

import lombok.Data;

@Data
public class LzsrDTO {
	private Long orderuserid;
	private Long userid;
	private String dimission;
	private String sex;
	private Integer processstatus;
	
	private Long orderlistid;
	private Long orderNo;
	private String ordertype;
	private Long loginname;
	private String name;
	private String idcard;
	private String productname;
	private String orderliststatus;
	private String orderplacer;
	private String mystatus;
	private String appstatus;
	
	private String amounttype;
	private String method;
	private Integer amount;
	private String code;
	private String paycode;
	private Date collectiontime;
	
	private String paytype;
	private Integer isrecharge;
	private Integer day;
	private Integer price;
	private String orderusername;
	private String paymentvouchername;
	private String content;
	private String daystatus;
	private String stagename;
	private String property;
	private Integer computeday;
	private String paymentvouchercode;
	private String peopletype;
	 
}
