package com.muteng.dgjs.dao;

import com.muteng.dgjs.domain.User;
import org.apache.ibatis.annotations.Select;

import com.dao.helper.DefaultMapper;
import com.muteng.dgjs.domain.Yaoqingma;
public interface YaoqingmaMapper extends DefaultMapper<Yaoqingma>{

	@Select("SELECT * FROM yaoqingma WHERE id = #{id}")
	Yaoqingma queryById(Long id);

	//根据邀请码查询邀请人
	@Select("select * from user where yaoqingma = #{yaoqingma} ")
	User queryByyaoqingma(String yaoqingma);
}
