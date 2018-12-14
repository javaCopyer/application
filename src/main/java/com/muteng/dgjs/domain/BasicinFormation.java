package com.muteng.dgjs.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Table(name = "basicinformation")
public class BasicinFormation {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

	@Column(name = "name")
    private String name;

	@Column(name = "code")
    private String code;

	@Column(name = "value")
    private String value;

	@Column(name = "createtime")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createtime;
}