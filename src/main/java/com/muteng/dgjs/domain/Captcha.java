package com.muteng.dgjs.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "captcha")
public class Captcha {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

	@Column(name = "mobile")
    private Long mobile;

	@Column(name = "captcha")
    private Integer captcha;

	@Column(name = "createtime")
    private Date createtime;

	@Column(name = "effectivetime")
    private Integer effectivetime;
}