package com.muteng.dgjs.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "finance_returnfeeway")
public class FinanceReturnfeeway {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "financelongrangeprogrammeid")
    private Long financelongrangeprogrammeid;
	
	@Column(name = "month")
    private Integer month;
	
	@Column(name = "amount")
    private Integer amount;
	
	@Column(name = "addname")
    private String addname;
	
	@Column(name = "createtime")
    private Date createtime;

}