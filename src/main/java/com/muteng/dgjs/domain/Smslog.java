package com.muteng.dgjs.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "smslog")
public class Smslog {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	@Column(name = "mobile")
    private Long mobile;
	@Column(name = "accessuserid")
    private Long accessuserid;
	@Column(name = "content")
    private String content;
	@Column(name = "status")
    private Integer status;
	@Column(name = "taskid")
    private String taskid;
	@Column(name = "returnmsg")
    private String returnmsg;
	@Column(name = "createtime")
    private Date createtime;
}