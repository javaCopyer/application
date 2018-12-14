package com.muteng.dgjs.service.impl;

import com.muteng.dgjs.DTO.FinancePaymentDocument;
import com.muteng.dgjs.DTO.FinancePaymentVoucher;
import com.muteng.dgjs.common.utils.Constants;
import com.muteng.dgjs.common.utils.DateUtils;
import com.muteng.dgjs.dao.AccountMapper;
import com.muteng.dgjs.domain.FinanceReceiptDocument;
import com.muteng.dgjs.domain.FinanceReceiptVoucher;
import com.muteng.dgjs.service.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:JJH
 * Date : 2018-11-22 16:30
 * Description: <描述>
 * Version: V1.0
 */
@Service
public class CommonImpl implements Common {

    @Autowired
    private AccountMapper accountMapper;


    /**
     * 获取付款凭证号
     */
    public synchronized String getPaymentVoucher(String type,String financeType) {
        Map<String,String> paramMap = new HashMap<String,String>();
        String nowdate = "";
        try {
            nowdate = DateUtils.GetNowDateStr(DateUtils.FORMAT_2);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        paramMap.put("begintime", nowdate + " 00:00:00");
        paramMap.put("endtime", nowdate + " 23:59:59");
        FinancePaymentVoucher frv = this.accountMapper.PaymentVoucher(paramMap);
        String date="";
        try {
            date = DateUtils.GetNowDateStr(DateUtils.FORMAT_7);
        } catch (Exception e) {
            e.printStackTrace();
        }
        FinancePaymentVoucher newfrv = new FinancePaymentVoucher();
        String finance = Constants.mapping_document_type.get(type+"-"+financeType);
        if(frv == null) {
            newfrv.setNumber(finance+date+"0001");
            newfrv.setNum(1);
        }else {
            int num = frv.getNum()+1;
            int len = String.valueOf(num).length();
            String newnum="";
            for(int i=0;i<(4-len);i++) {
                newnum+="0";
            }
            newfrv.setNumber(finance+date+newnum+num);
            newfrv.setNum(num);
        }
        newfrv.setCreatetime(new Timestamp(System.currentTimeMillis()));
        newfrv.setType(type);
        newfrv.setVouchertype("使用");
        this.accountMapper.addnewpv(newfrv);
        return newfrv.getNumber();
    }

    /**
     * 获取付款单据
     */
    public synchronized String getPaymentDocument(String type,String financeType) {
        Map<String,String> paramMap = new HashMap<String,String>();
        String nowdate = "";
        try {
            nowdate = DateUtils.GetNowDateStr(DateUtils.FORMAT_2);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        paramMap.put("begintime", nowdate + " 00:00:00");
        paramMap.put("endtime", nowdate + " 23:59:59");
        FinancePaymentDocument frv = this.accountMapper.paymentdocument(paramMap);
        String date="";
        try {
            date = DateUtils.GetNowDateStr(DateUtils.FORMAT_7);
        } catch (Exception e) {
            e.printStackTrace();
        }
        FinancePaymentDocument newfrv = new FinancePaymentDocument();
        String finance = Constants.mapping_document_type.get(type+"-"+financeType);
        if(frv == null) {
            newfrv.setNumber(finance+date+"0001");
            newfrv.setNum(1);
        }else {
            int num = frv.getNum()+1;
            int len = String.valueOf(num).length();
            String newnum="";
            for(int i=0;i<(4-len);i++) {
                newnum+="0";
            }
            newfrv.setNumber(finance+date+newnum+num);
            newfrv.setNum(num);
        }
        newfrv.setCreatetime(new Timestamp(System.currentTimeMillis()));
        newfrv.setType(type);
        newfrv.setDocumenttype("使用");
        this.accountMapper.addnewop(newfrv);
        return newfrv.getNumber();
    }


    /**
     * 获取收款凭证号
     */
    public synchronized String getReceiptVoucher(String type,String financeType) {
        Map<String,String> paramMap = new HashMap<String,String>();
        String nowdate = "";
        try {
            nowdate = DateUtils.GetNowDateStr(DateUtils.FORMAT_2);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        paramMap.put("begintime", nowdate + " 00:00:00");
        paramMap.put("endtime", nowdate + " 23:59:59");
        FinanceReceiptVoucher frv = this.accountMapper.receiptvoucher(paramMap);
        String date="";
        try {
            date = DateUtils.GetNowDateStr(DateUtils.FORMAT_7);
        } catch (Exception e) {
            e.printStackTrace();
        }
        FinanceReceiptVoucher newfrv = new FinanceReceiptVoucher();
        String finance = Constants.mapping_document_type.get(type+"-"+financeType);
        if(frv == null) {
            newfrv.setNumber(finance+date+"0001");
            newfrv.setNum(1);
        }else {
            int num = frv.getNum()+1;
            int len = String.valueOf(num).length();
            String newnum="";
            for(int i=0;i<(4-len);i++) {
                newnum+="0";
            }
            newfrv.setNumber(finance+date+newnum+num);
            newfrv.setNum(num);
        }
        newfrv.setCreatetime(new Timestamp(System.currentTimeMillis()));
        newfrv.setType(type);
        newfrv.setVouchertype("使用");
        this.accountMapper.addnewrv(newfrv);
        return newfrv.getNumber();
    }

    /**
     * 获取收款单据
     */
    public synchronized String getReceiptDocument(String type,String financeType) {
        Map<String,String> paramMap = new HashMap<String,String>();
        String nowdate = "";
        try {
            nowdate = DateUtils.GetNowDateStr(DateUtils.FORMAT_2);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        paramMap.put("begintime", nowdate + " 00:00:00");
        paramMap.put("endtime", nowdate + " 23:59:59");
        FinanceReceiptDocument frv = this.accountMapper.receiptDocument(paramMap);
        String date="";
        try {
            date = DateUtils.GetNowDateStr(DateUtils.FORMAT_7);
        } catch (Exception e) {
            e.printStackTrace();
        }
        FinanceReceiptDocument newfrv = new FinanceReceiptDocument();
        String finance = Constants.mapping_document_type.get(type+"-"+financeType);
        if(frv == null) {
            newfrv.setNumber(finance+date+"0001");
            newfrv.setNum(1);
        }else {
            int num = frv.getNum()+1;
            int len = String.valueOf(num).length();
            String newnum="";
            for(int i=0;i<(4-len);i++) {
                newnum+="0";
            }
            newfrv.setNumber(finance+date+newnum+num);
            newfrv.setNum(num);
        }
        newfrv.setCreatetime(new Timestamp(System.currentTimeMillis()));
        newfrv.setType(type);
        newfrv.setDocumenttype("使用");
        this.accountMapper.addnewrd(newfrv);
        return newfrv.getNumber();
    }
}
