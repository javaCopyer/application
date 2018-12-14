package com.muteng.dgjs.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "relation")
public class Relation {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

	@Column(name = "fromUserid")
    private Long fromUserid;

	@Column(name = "toUserid")
    private Long toUserid;
	
	@Column(name = "type")
	private Integer type;
	
	@Column(name = "createtime")
	private Date createtime;
}