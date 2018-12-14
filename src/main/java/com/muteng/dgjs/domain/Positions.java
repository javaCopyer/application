package com.muteng.dgjs.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Table(name = "positions")
public class Positions {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

	@Column(name = "code")
    private Long code;

	@Column(name = "producttype")
    private String producttype;

	@Column(name = "jobtitle")
    private String jobtitle;

	@Column(name = "parentid")
    private Long parentid;

	@Column(name = "addname")
    private String addname;

	@Column(name = "updatename")
    private String updatename;

	@Column(name = "status")
    private String status;

	@Column(name = "hot")
    private String hot;

	@Column(name = "updatetime")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updatetime;

	@Column(name = "createtime")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createtime;
	
	@Transient
	private List<Positions> children = new ArrayList<Positions>();
	
}