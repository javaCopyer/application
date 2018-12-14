package com.muteng.dgjs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.dao.helper.DefaultMapper;
import com.muteng.dgjs.domain.Orderlist;
public interface OrderListMapper extends DefaultMapper<Orderlist>{

	@Select("SELECT * FROM orderlist WHERE loginname = #{phone}")
	List<Orderlist> queryByLoginname(String phone);

	//查询我的入职基本信息,和我的type---8：会员，9：店主
		@Select("select p.productid,org.orgname address,p.minsalary,p.maxsalary,p.minage,p.maxage,COALESCE(sum(pv.price),0)/100 money\n" +
				"\t\t\t,p.logo,o.orderid,o.accessid,o.ordertype,o.productname,user.usertype,o.numberorder,o.status appstatus,ac.workmobile,\n" +
				"\t\t\to.createtime hiredate,o.idcard\n" +
				"\t\t\tfrom  orderlist o\n" +
				"\t\t\tleft join productlist p on o.productid = p.productid\n" +
				"\t\t\tleft join orderuser ou on o.orderid = ou.orderid\n" +
				"\t\t\tLEFT JOIN user on ou.userid = user.id\n" +
				"\t\t\tLEFT JOIN accessuser ac on ac.id = o.accessid \n" +
				"      left join factory f on f.id=p.factoryid  " +
				"      left join organizational  org on org.id=f.cityid  " +
				"\t	   LEFT JOIN paymentvoucher pv on pv.orderuserid = ou.id" +
				"\t\t\twhere ou.userid=#{userid}  " +
				"      AND o.ordertype='招工'   " +
				"    GROUP BY o.orderid  order by o.createtime desc")
	List<Map<String,Object>> queryMyEntry(Long userid);

	//查询我邀请的入职基本信息列表,和我的type---8：会员，9：店主
	@Select("select p.productid,org.orgname address,p.minsalary,p.maxsalary,p.minage,p.maxage,COALESCE(pv.price)/100 money\n" +
			"\t\t\t,p.logo,o.orderid,o.accessid,o.ordertype,o.productname,user.usertype,o.numberorder,o.status appstatus,ac.workmobile,\n" +
			"\t\t\to.createtime hiredate,o.idcard\n" +
			"\t\t\tfrom  orderlist o\n" +
			"\t\t\tleft join productlist p on o.productid = p.productid\n" +
			"\t\t\tleft join orderuser ou on o.orderid = ou.orderid\n" +
			"\t\t\tLEFT JOIN user on ou.userid = user.id\n" +
			"\t\t\tLEFT JOIN accessuser ac on ac.id = o.accessid \n" +
			"      left join factory f on f.id=p.factoryid\n" +
			"      left join organizational  org on org.id=f.cityid\n" +
			"		LEFT JOIN orderagencymoney oam on oam.orderid = o.orderid and oam.ordertype='招工'" +
			"\t\t\tLEFT JOIN paymentvoucher pv on pv.orderid = o.orderid and pv.orderuserid  = oam.userid\n" +
			"\t\t\twhere oam.userid=#{userid}\n" +
			"      AND o.ordertype='招工' \n" +
			"      order by o.createtime desc")
	List<Map<String,Object>> queryInvitedEntry(Long userid);



	//根据orderid和查询对应的个人入职详情和订单用户姓名，号码
	@Select("SELECT ol.name olname,ol.loginname phone,ol.idcard olidcard,ol.numberorder,ol.appstatus,ol.productid,ol.orderNo,ol.hiredate," +
			"ol.status,pl.logo picture,pl.title,pl.minsalary,pl.maxsalary,pl.address,\n" +
			"au.name csname,au.workmobile\n" +
			"FROM orderlist ol\n" +
			"left join productlist pl on ol.productid=pl.productid\n" +
			"left join accessuser au on au.id=ol.accessid\n" +
			"where ol.orderid=#{orderid}")
	List<Map<String,Object>> queryMyEntryDatils(Long orderid);

	//订单多个用户，用户名。银行卡，状态，
	@Select("SELECT  ou.loginname,u.name,COALESCE(u.idcard,'没有卡号') idcard,ou.dimission,ou.id as orderuserid,u.id as userid  from orderuser ou\n" +
			"LEFT JOIN user u on u.id =ou.userid\n" +
			"where ou.orderid =#{orderid}")
	List<Map<String,Object>> queryEntryDatils(Long orderid);

	@Select("select amount/100 price,comment content,amounttype from orders_paylog where paycode = #{code} order by id desc limit 1")
	Map<String,Object> queryPaylog(Map map);

	//根据订单用户id查询补贴凭证
	@Select("select price,content,name,code,daystatus,day,paytype from paymentvoucher pv\n" +
			"where orderuserid=#{orderuserid}")
	List<Map<String,Object>> queryvoucher(Integer orderuserid);

	//根据订单id查询订单的创建时间，邀请面试，面试通过，入职时间,离职时间
	@Select("select DATE_FORMAT(createtime,'%Y-%m-%d')createtime/*报名时间*/,DATE_FORMAT(offerinteviewtime,'%Y-%m-%d')offerinteviewtime/*邀约面试时间*/,DATE_FORMAT(interviewpasstime,'%Y-%m-%d')interviewpasstime/*面试通过*/,\n" +
			"DATE_FORMAT(hiredate,'%Y-%m-%d')hiredate/*入职时间*/,DATE_FORMAT(resignationtime,'%Y-%m-%d')resignationtime   from orderlist where orderid=#{orderid} order by id desc limit 1")
	Map<String,String> queryTime(Long orderid);


	@Select("select shouldpayprice from ordersmember where orderNo = #{oderNo}")
    String queryByorderNo(String orderNo);


	//取该订单，所有邀请人的姓名，手机号，奖励金，orderid
	@Select("SELECT  op.amount price,u.name,loginname,COALESCE(pv.price)/100 amount\n" +
			"FROM user u\n" +
			"LEFT JOIN orderagencymoney oam on oam.userid = u.id\n" +
			"LEFT JOIN paymentvoucher pv on pv.orderid = oam.orderid and pv.orderuserid = oam.userid\n" +
			"LEFT JOIN orders_paylog op on pv.code = op.paycode\n" +
			"where oam.orderid =  #{orderid}" +
			" ORDER BY oam.id")
	List<Map<String,Object>> heighLevelMap(Long orderid);

	//添加投诉建议
	@Insert("insert into complaint (userid,content,createtime) values(#{param1},#{param2},now())")
    void addcomplaint(Long userid, String word);

}
