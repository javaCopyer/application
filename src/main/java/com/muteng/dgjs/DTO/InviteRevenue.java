package com.muteng.dgjs.DTO;

import com.muteng.dgjs.common.utils.CommonUtil;
import lombok.Data;

import java.util.Date;

/**
 * Author:JJH
 * Date : 2018-10-27 10:18
 * Description: <邀请收入返回实体类>
 */

public class InviteRevenue {
    private Long userid;
    private String collectiontime;
    private String paytype;
    private Integer orderid;
    private Double price;
    private Date hiredate;  //入职时间
    private Integer Status ;//1  为可查看详情  0为不可查看
    private Date createtime;
    private  String timeStr;


    @Override
    public String toString() {
        return "InviteRevenue{" +
                "userid=" + userid +
                ", collectiontime='" + collectiontime + '\'' +
                ", paytype='" + paytype + '\'' +
                ", orderid=" + orderid +
                ", price=" + price +
                ", hiredate=" + hiredate +
                ", Status=" + Status +
                ", createtime=" + createtime +
                ", timeStr='" + timeStr + '\'' +
                '}';
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getCollectiontime() {
        return collectiontime;
    }

    public void setCollectiontime(String collectiontime) {
        this.collectiontime = collectiontime;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Double getPrice() { return  price/100; }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
