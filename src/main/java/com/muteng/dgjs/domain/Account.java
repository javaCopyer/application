package com.muteng.dgjs.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "account")
public class Account {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

	@Column(name = "userid")
    private Long userid;

	@Column(name = "amount")
    private Integer amount;

	@Column(name = "type")
    private Integer type;

	@Column(name = "reason")
    private Object reason;

	@Column(name = "profittime")
    private Date profittime;

	@Column(name = "createtime")
    private Date createtime;

	@Column(name = "updatetime")
    private Date updatetime;

	@Column(name = "status")
    private Integer status;

	@Column(name = "comment")
    private String comment;
}