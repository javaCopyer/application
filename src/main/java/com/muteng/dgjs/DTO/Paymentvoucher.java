package com.muteng.dgjs.DTO;

import lombok.Data;

import java.util.Date;

/**
 * Author:JJH cuiyi@itany.com
 * Date : 2018-11-22 15:18
 * Description: <描述>
 * Version: V1.0
 */
@Data
public class Paymentvoucher {
    private Long id ;
    private Long orderid;
    private Long orderuserid;
    private Integer accountid;
    private String ordertype;
    private String paytype;
    private Integer price;
    private String name;
    private String orderusername;
    private String content;
    private Date createtime;
    private String code;
    private String peopletype;


}
