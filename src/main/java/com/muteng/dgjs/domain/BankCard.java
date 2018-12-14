package com.muteng.dgjs.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "bankcard")
public class BankCard {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

	@Column(name = "userid")
    private Long userid;

	@Column(name = "name")
    private String name;

	@Column(name = "cardnum")
    private String cardnum;

	@Column(name = "ismain")
    private Integer ismain;

	@Column(name = "status")
    private Integer status;

	@Column(name = "area")
    private String area;

	@Column(name = "tel")
    private String tel;

	@Column(name = "cardtype")
    private String cardtype;

	@Column(name = "url")
    private String url;

	@Column(name = "brand")
    private String brand;

	@Column(name = "bankname")
    private String bankname;

	@Column(name = "createtime")
    private Date createtime;

	@Column(name = "leftcolor")
	private String leftcolor;

	@Column(name = "rightcolor")
	private String rightcolor;

	@Column(name = "logourl")
	private String  logourl;

	@Column(name = "amount")
	private Long  amount;
}