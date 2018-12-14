package com.muteng.dgjs.DTO;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ProductDTO {
    private Date createtime;
    private String sex;
    private Long minage;
    private Long maxage;
    private String picture;
    private Long productlistid;
    private String title;
    private Double shopkeeperAmount;
    private Long minsalary;
    private Long maxsalary;
    private Double livingallowancesAmount;
    private Double memberamount;
    private Double inviterAmount;
    private String logo;
    private String address;
    private int count;
    private int productid;

}
