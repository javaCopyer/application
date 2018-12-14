package com.muteng.dgjs.DTO;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * Author:JJH
 * Date : 2018-11-02 9:17
 * Description: <点击分享统计实体类>
 */
@Data
@Table(name = "relation")
public class Relation {
    @Column(name = "nickname")
    private String nickname;  //微信昵称
    @Column(name = "openid")
    private String openid;  //微信openid
    @Column(name = "inviteruserid")
    private Long inviteruserid; //邀请人id
    @Column(name = "type")
    private String type;   //邀请类型
    @Column(name = "productid")
    private Integer productid; //产品id
    @Column(name = "headimgurl")
    private String headimgurl; //微信头像
    @Column(name = "createtime")
    private Date createtime;
    @Column(name = "fromUserid")
    private Long fromUserid;
    @Column(name = "toUserid")
    private Long toUserid;

}
