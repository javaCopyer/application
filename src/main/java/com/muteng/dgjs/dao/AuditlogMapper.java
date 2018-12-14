package com.muteng.dgjs.dao;

import com.dao.helper.DefaultMapper;
import com.muteng.dgjs.DTO.Auditlog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface AuditlogMapper extends DefaultMapper<Auditlog>{

    @Select("select action,page from action " +
            " where pagenum = #{0} and actionnum = #{1}")
    Auditlog selectauditlog(String pagenum,String actionnum);

    @Select("select action,page from action " +
            " where pagenum = #{0} and actionnum = #{1}")
    Auditlog selectauditLog(String pagenum,String actionnum);


    @Insert("INSERT INTO workbazaarauditlog  ( userid,`action`,actionnum,page,pagenum,`condition`,createtime ) " +
            "VALUES(#{userid},#{action},#{actionnum},#{page},#{pagenum},#{condition},now() )")
    int insertAction(Auditlog auditlog);

}
