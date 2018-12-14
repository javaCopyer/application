package com.muteng.dgjs.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Table(name = "ordersmember")
public class OrdersMember {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "orderNo")
	private Long orderNo;
	
	@Column(name = "userid")
	private Long userid;
	
	@Column(name = "productid")
	private Long productid;
	
	@Column(name = "accessuserid")
	private Long accessuserid;
	
	@Column(name = "supplierid")
	private Long supplierid;
	
	@Column(name = "canceltemplateid")
	private Long canceltemplateid;
	
	@Column(name = "shouldpayprice")
	private Integer shouldpayprice;
	
	@Column(name = "finalpayprice")
	private Integer finalpayprice;
	
	@Column(name = "finalcost")
	private Integer finalcost;
	
	@Column(name = "grossprofit")
	private Integer grossprofit;
	
	@Column(name = "payfinishtime")
	private Timestamp payfinishtime;
	
	@Column(name = "createtime")
	private Timestamp createtime;
	
	@Column(name = "canceltime")
	private Timestamp canceltime;
	
	@Column(name = "isinvoice")
	private Integer isinvoice;
	
	@Column(name = "taxid")
	private Long taxid;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "source")
	private String source;
	
	@Column(name = "subsource")
	private String subsource;
	
	@Column(name = "numberorder")
	private Integer numberorder;
	
	@Column(name = "orderplacer")
	private String orderplacer;

	@Column(name = "openid")
	private String openid;

	@Column(name = "paydeadlinetime")
	private String paydeadlinetime;

	@Column(name = "finishtime")
	private Timestamp finishtime;
}
