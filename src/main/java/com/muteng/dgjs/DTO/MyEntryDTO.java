package com.muteng.dgjs.DTO;


import lombok.Data;

@Data
public class MyEntryDTO {

    //productlist
    private int productid;
    private String address;
    private int  minsalary;
    private  int maxsalary;
    private int minage;
    private int maxage;
    private int money;

    private String logo;
    //orderlist
    private int id;
    private String accessid;
    //产品名称
    private  String productname;

    //finance_invitescheme
    private int amount;
}
