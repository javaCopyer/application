package com.muteng.dgjs.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "product_marketing")
public class ProductMarketing {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	@Column(name="productlistid")
    private Long productlistid;
	@Column(name="schemeid")
    private Long schemeid;
	@Column(name="productid")
    private Long productid;
	@Column(name="producttype")
    private String producttype;
	@Column(name="invalidation")
    private String invalidation;
	@Column(name="begintime")
    private Date begintime;
	@Column(name="endtime")
    private Date endtime;
	@Column(name="addname")
    private String addname;
	@Column(name="createtime")
    private Date createtime;

  
}