package com.muteng.dgjs.dao;

import com.dao.helper.DefaultMapper;
import com.muteng.dgjs.DTO.Auditlog;
import com.muteng.dgjs.DTO.Relation;
import com.muteng.dgjs.DTO.Share;
import org.apache.ibatis.annotations.Select;

/**
 * Author:JJH
 * Date : 2018-11-01 8:34
 * Description: <分享>
 */
public interface ShareMapper extends DefaultMapper<Relation> {

    @Select("select * FROM sharesetting   where sharetype = #{sharetype} ORDER BY version desc limit 1")
    Share selectShare(Integer sharetype);

    @Select("select * from relation where openid =#{openid} order by createtime desc limit 1")
    Relation selectrelation(String openid);
}
