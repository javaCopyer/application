package com.muteng.dgjs.DTO;

import com.muteng.dgjs.common.utils.CommonUtil;
import lombok.Data;

import java.util.Date;

/**
 * Author:JJH
 * Date : 2018-10-27 10:18
 * Description: <邀请收入返回实体类>
 */

public class DzRevenue {
    private Long userid;
    private String paytype;
    private String paycode;   //付款单号
    private Integer orderid;
    private Double price;
    private Date createtime;  //入职时间
    private Integer Status ;//1  为可查看详情  0为不可查看

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public String getPaycode() {
        return paycode;
    }

    public void setPaycode(String paycode) {
        this.paycode = paycode;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Double getPrice() {return price/100; }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "DzRevenue{" +
                "userid=" + userid +
                ", paytype='" + paytype + '\'' +
                ", paycode='" + paycode + '\'' +
                ", orderid=" + orderid +
                ", price=" + price +
                ", createtime=" + createtime +
                ", Status=" + Status +
                '}';
    }
}
