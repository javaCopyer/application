package com.muteng.dgjs.service;


import com.muteng.dgjs.DTO.Relation;
import com.muteng.dgjs.DTO.Share;

public interface ShareService {

    //查询分享所需信息
    Share selectShare(Integer sharetype);

    //插入点击分享的数据
    void insertrelation(Relation relation);

    //根据openid查询relation
    Relation selectrelation(String openid);
}
