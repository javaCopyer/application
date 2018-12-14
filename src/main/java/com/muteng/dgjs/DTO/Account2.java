package com.muteng.dgjs.DTO;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "account")
public class Account2 {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

	@Column(name = "userid")
    private Long userid;

	@Column(name = "amount")
    private Double amount;

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