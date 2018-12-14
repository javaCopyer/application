package com.muteng.dgjs.service;

import com.muteng.dgjs.DTO.Relation;

import java.util.List;
import java.util.Map;

public interface RelationService {

	Map<String,Object> getRelationKehu(Long fromUserid);
	
	//月潜在客户，日潜在客户，邀请的客户，邀请的会员
	List<Map<String,Object>> customDetail(Long userid);

	//获取关注的人数（按天数,按月数,邀请客户数、邀请会员数量）
	Map<String,Object> getRelation(Long userid,Long phone);
	
	int insert(Relation relation);

	List<Map<String,Object>> memberDetail(Long userid);

	//前两名邀请人数最多的,和前三名邀请入职最多的的
	List<Map<String,Object>> invitedNum();

	//根据openid 查询relation信息
	Relation getRelationByopenid(String openid);

	void insertRelation(Relation relation);
}
