package com.muteng.dgjs.domain;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Table(name="userpool")
public class UserPool {
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="userid")
	private Long userid;
	@Column(name="loginname")
	private String loginname;
	@Column(name="is_entry")
	private Integer is_entry;
	@Column(name="registrationtime")
	private Date registrationtime;
	@Column(name="createtime")
	private Date createtime;
}
