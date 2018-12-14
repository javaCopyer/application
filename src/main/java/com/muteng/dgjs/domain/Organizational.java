package com.muteng.dgjs.domain;

import java.sql.Timestamp;
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
@Table(name = "organizational")
public class Organizational {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

	@Column(name = "code")
    private String code;

	@Column(name = "orgname")
    private String orgname;

	@Column(name = "parentid")
    private Long parentid;

	@Column(name = "level")
    private Long level;

	@Column(name = "hot")
    private String hot;

	@Column(name = "initial")
    private String initial;

	@Column(name = "status")
    private String status;

	@Column(name = "territory")
    private Integer territory;

	@Column(name = "addname")
    private String addname;

	@Column(name = "updatename")
    private String updatename;

	@Column(name = "createtime")
	//@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp createtime;

	@Column(name = "updatetime")
	//@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp updatetime;
	
	@Transient
	private List<Organizational> children = new ArrayList<Organizational>();
}