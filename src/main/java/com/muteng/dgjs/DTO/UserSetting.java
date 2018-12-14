package com.muteng.dgjs.DTO;

import lombok.Data;

/**
 * Author:JJH
 * Date : 2018-10-27 16:21
 * Description: <进入设置个人信息实体类>
 */
@Data
public class UserSetting {
    private Long userid;   //用户ID
    private String name;   //真实姓名
    private String phone;  //用户手机
    private String qq;
    private String weixin; //微信号
    private String nickname; //微信昵称
    private String headimgurl;  //微信头像
    private Integer authenticate;  //判断是否认证  1为已认证，0为未认证
    private Integer ismemb;   //判断是否是会员  1为会员，0为非会员
    private String weixinurl; //微信名片
    private String idcard;   //身份证号
}
