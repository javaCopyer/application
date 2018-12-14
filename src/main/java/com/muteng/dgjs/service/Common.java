package com.muteng.dgjs.service;

/**
 * Author:JJH
 * Date : 2018-11-22 16:30
 * Description: <描述>
 * Version: V1.0
 */
public interface Common {
    //获取付款凭证号
    String getPaymentVoucher(String type,String financeType);

    //获取付款单号
    String getPaymentDocument(String type,String financeType);

    //获取收款凭证号
    String getReceiptVoucher(String type,String financeType);

    //获取收款单号
    String getReceiptDocument(String type,String financeType);
}
