package com.muteng.dgjs.domain;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="device")
public class Device {
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="userid")
	private Long userid;
	
	@Column(name="channelid")
	private String channelid;
	
	@Column(name="device")
	private String device;
	
	@Column(name="factory")
	private String factory;
	
	@Column(name="uuid")
	private String uuid;
	
	@Column(name="os")
	private String os;
	
	@Column(name="os_version")
	private String osVersion;
	
	@Column(name="imei")
	private String imei;
	
	@Column(name="createtime")
	private Timestamp createtime;
}
