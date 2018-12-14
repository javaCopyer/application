package com.muteng.dgjs.DTO;

import com.muteng.dgjs.common.utils.CommonUtil;
import lombok.Data;

import java.util.Date;

/**
 * Author:JJH
 * Date : 2018-10-27 10:18
 * Description: <邀请收入返回实体类>
 */

public class LzRevenue {

    private Long userid;
    private String resignationtime;  //离职时间
    private String paytype;
    private Integer orderid;
    private Double price;
    private String hiredate;  //入职时间
    private Integer Status ;//1  为可查看详情  0为不可查看

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getResignationtime() {
        return resignationtime;
    }

    public void setResignationtime(String resignationtime) {
        this.resignationtime = resignationtime;
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

    public Double getPrice() {return price/100; }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getHiredate() {
        return hiredate;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "LzRevenue{" +
                "userid=" + userid +
                ", resignationtime=" + resignationtime +
                ", paytype='" + paytype + '\'' +
                ", orderid=" + orderid +
                ", price=" + price +
                ", hiredate=" + hiredate +
                ", Status=" + Status +
                '}';
    }
}
