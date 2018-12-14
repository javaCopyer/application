package com.muteng.dgjs.DTO;

import lombok.Data;

import java.util.Date;

/**
 * Author:JJH
 * Date : 2018-10-31 16:05
 * Description: <描述>
 */
@Data
public class Bank {
    private Integer id;
    private String name;   //银行名称
    private String leftcolor; //左渐变颜色
    private String rightcolor;  //右渐变颜色
    private Date createtime;
    private Integer status; //  1:启用  0：禁用
    private String logourl;
}
