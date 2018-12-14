package com.muteng.dgjs.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Table(name = "finance_shopkeeper")
public class FinanceShopkeeper {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

	@Column(name = "marketingprogramid")
    private Long marketingprogramid;

	@Column(name = "amount")
    private Integer amount;

	@Column(name = "addname")
    private String addname;

	@Column(name = "createtime")
    private Date createtime;
}