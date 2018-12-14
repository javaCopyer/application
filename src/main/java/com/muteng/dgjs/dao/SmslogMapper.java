package com.muteng.dgjs.dao;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

import com.dao.helper.DefaultMapper;
import com.muteng.dgjs.domain.Smslog;
public interface SmslogMapper extends DefaultMapper<Smslog>{

    //插入短信。。
    @Insert("INSERT into  smslog (mobile,content,taskid,createtime) VALUES(#{0},#{1},#{2},NOW())")
    int insertMessage(Long mobile,String content,Long taskid);
}
