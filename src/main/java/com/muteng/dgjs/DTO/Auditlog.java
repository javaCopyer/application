package com.muteng.dgjs.DTO;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Author:JJH
 * Date : 2018-11-01 20:41
 * Description: <行为记录实体类>
 */
@Data
@Table(name = "workbazaarauditlog")
public class Auditlog {
    @Column(name = "userid")
    private Long userid;
    @Column(name = "ip")
    private String ip;
    @Column(name = "action")
    private String action;
    @Column(name = "actionnum")
    private String actionnum;
    @Column(name = "page")
    private String page;
    @Column(name = "pagenum")
    private String pagenum;
    @Column(name = "createtime")
    private Date createtime;
    @Column(name = "content")
    private String content;

}
