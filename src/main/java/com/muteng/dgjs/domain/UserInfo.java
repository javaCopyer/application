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
@Table(name = "userinfo")
public class UserInfo {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "userid")
    private Long userid;

	@Column(name = "weixin_openid")
    private String weixin_openid;

	@Column(name = "nickname")
    private String nickname;
    
	@Column(name = "sex")
    private int sex;
    
	@Column(name = "province")
    private String province;
    
	@Column(name = "city")
    private String city;
    
	@Column(name = "headimgurl")
    private String headimgurl;
    
	@Column(name = "createtime")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createtime;
}