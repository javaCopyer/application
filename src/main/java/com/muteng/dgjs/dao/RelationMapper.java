package com.muteng.dgjs.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.dao.helper.DefaultMapper;
import com.muteng.dgjs.DTO.Relation;

import java.util.List;
import java.util.Map;

public interface RelationMapper extends DefaultMapper<Relation>{
 
	@Select("SELECT COUNT(*) FROM USER u LEFT OUTER JOIN relation re ON u.id = re.toUserid WHERE fromUserid = #{fromUserid} AND TYPE = 2")
	Integer getRelationHuiYuan(Long fromUserid);

	//计算本月上月关注人数
	@Select("SELECT COUNT(*) FROM relation WHERE DATE_FORMAT(createtime, '%Y-%m') >= #{0} and inviteruserid = #{1}")
	Integer getRelationMonth(String month,Long userid);

	//查询当日和昨日的关注人数
	@Select("SELECT count(createtime) count,createtime FROM relation where createtime>=#{0} and  createtime<#{1} and inviteruserid=#{2} group by DATE_FORMAT(createtime,'%m-%d-%Y') ")
	List<Map<String,Object>> countTodayAndYester(String yesterday,String today,Long userid);

	@Select("SELECT COUNT(*) FROM relation WHERE DATE_FORMAT(createtime, '%Y-%m-%d') >= #{0}  and inviteruserid = #{1}")
	Integer getRelationDay(String date,Long userid);

	@Update("INSERT INTO relation(fromUserid, toUserid, TYPE, createtime) VALUES(#{fromUserid}, #{toUserid}, #{type}, #{createtime})")
	int insertRelation(Relation relation);

	//查询我的邀请码
	@Select("SELECT yaoqingma from user where id=#{id}")
	String yaoqingma(Long id);

	//根据我 的邀请码查询我的用户
	@Select("Select u.id,nickname,headimgurl,u.createtime from userinfo uf\n" +
			"\t\t\tRIGHT JOIN user u on u.id  = uf.userid\n" +
			"\t\t\twhere u.inviter = #{yaoqingma}")
	List<Map<String,Object>> queryUserByInviter(String yaoqingma);

	//查询本月关注人详情
	@Select("SELECT ui.userid,r.nickname,r.headimgurl,r.createtime FROM relation r\n" +
			" left join userinfo ui on ui.userid=r.inviteruserid\n" +
			" where r.createtime>=#{0} and  r.createtime<#{1} and r.inviteruserid=#{2}" +
			" order by r.createtime desc")
	List<Map<String,Object>> DetailToday(String today,String yesterday,Long userid);

	@Select("SELECT count(createtime) count FROM relation where createtime>=#{0} and  createtime<#{1} group by DATE_FORMAT(createtime,'%m-%Y') and inviteruserid=#{2}")
	int lastMount(String lastMounth,String thisMounth,Long userid);

	@Select("SELECT ol.productname,ui.userid,oam.orderid,nickname,headimgurl,ol.status,hiredate createtime FROM  userinfo ui\n" +
			"LEFT join orderagencymoney oam on oam.userid = ui.userid\n" +
			"left JOIN orderlist ol on ol.orderid = oam.orderid\n" +
			"where oam.userid=#{userid} and ol.status in ('入职-返费确认中','收到返费-结束','离职','供应商拖欠','申请离职')\n" +
			" Order by hiredate desc\n ")
	List<Map<String,Object>> invitedEntry(Long userid);

	//
	@Select("select  account/100 account,price/100 price  from orders_paylog op\n" +
			"right join paymentvoucher pv on pv.code=op.paycode\n" +
			"where pv.code=( select code from paymentvoucher where orderuserid=#{userid} and orderid=#{orderid})")
	Map<String,Object> querypaylog(Map<String,Object> map);

	//查询前两名 邀请人数最多的;
	@Select("SELECT count(u.id) num,name,headimgurl FROM `user` u\n" +
			"left join userinfo ui on ui.userid=u.id\n" +
			"where name!='' group by inviter order by num desc limit 1,2 ;")
	List<Map<String,Object>> invitedNum();
	//查询前三名，邀请入职最多的；
	@Select("SELECT COUNT(inviteduserid) num,name,headimgurl from orderlist2 olt\n" +
			"    \tleft join userinfo ui on ui.userid=olt.inviteduserid\n" +
			"    \tleft join user u on u.id= olt.inviteduserid\n" +
			"\t    where olt.inviteduserid>0\n" +
			"\t    GROUP BY olt.inviteduserid\n" +
			"\t    order by COUNT(olt.inviteduserid) desc limit 0,3")
	List<Map<String,Object>> rekruten();

	//openID查询relation信息
	@Select("select * from relation where openid =#{openid} order by createtime desc limit 1")
	Relation getRelationByopenid(String openid);

	@Insert("INSERT INTO relation(headimgurl,openid,inviteruserid, nickname, createtime,productid) VALUES (#{headimgurl},#{openid},#{inviteruserid}, #{nickname},  #{createtime},#{productid})")
	void insertrelation(Relation relation);

	//查询邀请的所有会员
	@Select("Select oam.createtime,ui2.nickname,ui2.headimgurl,ui1.nickname inviterName,oam.userid inviterId,pv.price/100 price\n" +
			"from orderagencymoney oam\n" +
			"LEFT JOIN (Select nickname,userid from userinfo ) ui1 on ui1.userid = oam.userid\n" +
			"LEFT JOIN ordersmember om on om.id = oam.orderid\n" +
			"LEFT JOIN (Select nickname,headimgurl,userid from userinfo ) ui2 on ui2.userid = om.userid\n" +
			"LEFT JOIN paymentvoucher pv on pv.orderid = oam.orderid and pv.orderuserid = oam.userid\n" +
			"where oam.orderid in(Select orderid from orderagencymoney where userid = #{userid} and ordertype = '会员') and `level` = 0\n" +
			"order by oam.createtime desc")
	List<Map<String,Object>> querymembers(Long userid);
}
