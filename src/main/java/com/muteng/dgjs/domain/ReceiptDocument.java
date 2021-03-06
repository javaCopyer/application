package com.muteng.dgjs.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "receipt_document")
public class ReceiptDocument {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

	@Column(name = "orderid")
    private Long orderid;

	@Column(name = "ordertype")
    private String ordertype;

	@Column(name = "price")
	private Integer price;

	@Column(name = "userid")
    private Long userid;

	@Column(name = "name")
    private String name;

	@Column(name = "method")
	private String method;

	@Column(name = "comment")
	private String comment;

	@Column(name = "createtime")
    private Date createtime;

	@Column(name = "code")
    private String code;

	@Column(name = "trade_no")
	private String trade_no;

	@Column(name = "returnfeecode")
	private String returnfeecode;

}