package com.muteng.dgjs.DTO;

import lombok.Data;

import java.util.Date;

/**
 * Author:JJH
 * Date : 2018-10-31 16:05
 * Description: <分享实体类>
 */
@Data
public class Share {
    private Long userid;
    private String title; //标题
    private String desc; //分享内容
    private String url;  //分享地址
    private String imgurl;  //图片地址
    private Integer version; //  版本号
    private Integer type;      //分享类型1 红包，2 详情页分享
    private String backurl;
}
