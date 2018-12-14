package com.muteng.dgjs.dao;

import java.util.List;
import java.util.Map;

import com.muteng.dgjs.DTO.ProductDetailDTO;
import com.muteng.dgjs.domain.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dao.helper.DefaultMapper;
import com.muteng.dgjs.DTO.ProductDTO;
import com.muteng.dgjs.domain.Productlist;
public interface ProductlistMapper extends DefaultMapper<Productlist>{
	@Select("Select pl.`title` longtitle,pw2.address,pl.minsalary,pl.maxsalary,pw2.logo,f.factoryname,pw2.picture pictures,pc.restaurant,pc.accommodation,pc.roomid,pc.airconditioner,pc.restroom,\n" +
			"pc.boardwages,pc.paidwayid,extrawork,fullfrequentlyaward,performance,pc.positivetime,nightgrant,positivebasesalary,payday,beginday,endday,bank,transactor,\n" +
			"pj.sex,pj.minage,pj.maxage,womanminage,womanmaxage,nationid,englishcharactersid,simplearithmeticid,surdomute,tattoosmokeid,criminalrecord,returnfactory,\n" +
			"pw.production,pw.jobcontent,pw.pipelineid,pw.dutyid,\n" +
			"pi.idcard,pi.diploma,pi.copies,pi.picture photo,pi.examination,\n" +
			"pc.compact,insurance,fiveinsurance,gold,f.introduce factorycontent,\n" +
			"pl.money,fsc.amount/100 shopamount,fin.amount/100 lifecontent,fma.amount/100 memberamount,fint.amount/100 inviterAmount," +
			"fin.day\n" +
			"FROM productlist pl\n" +
			"LEFT JOIN product_compensationwelfare pc ON pl.id=pc.productlistid\n" +
			"LEFT JOIN product_jobrequirements PJ ON pl.id=pj.productlistid\n" +
			"LEFT JOIN product_workingcondition pw ON pl.id=pw.productlistid\n" +
			"LEFT JOIN product_interviewsituation pi ON pl.id=pi.productlistid\n" +
			"LEFT JOIN (Select productlistid,amount from product_marketing pm\n" +//查询邀请奖励
			"\t\t\t\t\t RIGHT JOIN finance_invitescheme  fi on  pm.schemeid = fi.marketingprogramid and pm.invalidation='未失效') fint on fint.productlistid = pl.id\n" +
			"LEFT JOIN (Select productlistid,amount from product_marketing pm\n" +//查询店主奖励
			"\t\t\t\t\t RIGHT JOIN finance_shopkeeper  fi on  pm.schemeid = fi.marketingprogramid and pm.invalidation='未失效') fsc on fsc.productlistid = pl.id\n" +
			"LEFT JOIN (Select productlistid,amount from product_marketing pm\n" +//查询会员奖励
			"\t\t\t\t\t RIGHT JOIN finance_member  fi on  pm.schemeid = fi.marketingprogramid and pm.invalidation='未失效') fma on fma.productlistid = pl.id\n" +
			" left join productwork2 pw2 on pw2.productNo=pl.productNo " +
			"LEFT JOIN product_spendingplan ps ON ps.productlistid=pl.id and ps.invalidation='未失效'\n" +
			"LEFT JOIN finance_livingallowances fin ON fin.id=ps.schemeid\n" +
			"LEFT JOIN factory f ON f.id = pl.factoryid " +
			"WHERE pl.productid=#{productid} and pl.producttype='招工'")
    List<ProductDetailDTO> queryByProductid(Long productid);

	@Select("select name from tag where id in(select tagid from producttag where productid=#{productid})")
	List<Map<String,Object>> queryroductag(Long productid);

	@Select("Select  value,name from basicinformation where id in " +
			"(#{0},#{1},#{2},#{3},#{4},#{5},#{6},#{7})")
	List<Map<String,Object>>  querynformation(Long a,Long b,Long c,Long d,Long e ,Long f,Long g,Long h);


	@Select("select * from productlist " +
			"" +
			"where producttype = #{producttype} and productid = #{productid} limit 1")
	Productlist queryProductlistByProductid(@Param("productid") Long productid,
			@Param("producttype") String producttype);

	//查询产品列表,根据补贴倒序排列
		@Select("<script>SELECT  " +
			   	" pl.`logo`, pl.`createtime`, pl.`sex`,pj.`minage`, pj.`maxage`,pl.`picture`, pl.`id` AS productlistid, pl.productid , pl.`title`, " +
				" fsc.`amount`/100 AS shopkeeperAmount,org.orgname address,fma.amount/100 memberamount," +
				" pl.`minsalary`, pl.`maxsalary`, fin.`amount`/100 AS livingallowancesAmount,fint.`amount`/100 AS inviterAmount " +
				" FROM productlist pl  " +
				" LEFT JOIN product_compensationwelfare com ON pl.id=com.productlistid  " +
				" LEFT JOIN product_spendingplan spend ON spend.productlistid=pl.id  and spend.invalidation='未失效'  " +
				" LEFT JOIN finance_livingallowances fin ON fin.id=spend.schemeid   " +
				"LEFT JOIN (Select productlistid,amount from product_marketing pm\n" +
				"\t\t\t\t\t RIGHT JOIN finance_invitescheme  fi on  pm.schemeid = fi.marketingprogramid and pm.invalidation='未失效') fint on fint.productlistid = pl.id\n" +
				"LEFT JOIN (Select productlistid,amount from product_marketing pm\n" +
				"\t\t\t\t\t RIGHT JOIN finance_shopkeeper  fi on  pm.schemeid = fi.marketingprogramid and pm.invalidation='未失效') fsc on fsc.productlistid = pl.id\n" +
				"LEFT JOIN (Select productlistid,amount from product_marketing pm\n" +
				"\t\t\t\t\t RIGHT JOIN finance_member  fi on  pm.schemeid = fi.marketingprogramid and pm.invalidation='未失效') fma on fma.productlistid = pl.id\n" +
				" left join product_jobrequirements pj on pl.id = pj.productlistid " +
				" left join productwork2 pw on pw.productNo=pl.productNo " +
				" left join factory f on f.id=pl.factoryid " +
				" left join organizational  org on org.id=f.cityid "
				+" WHERE pl.`status` = '上架' and pl.producttype='招工' " +
				"<if test=\"minage != null\">" +
				"	AND pj.maxage >= ${minage}" +
				"</if>"+
				"<if test=\"maxage != null\">" +
				"	AND ${maxage} >= pj.minage" +
				"</if>"+
				"<if test=\"Sex != null \">" +
				"	AND pj.sex = '${Sex}'  OR pj.sex='不限'" +
				"</if>"+
				"<if test=\"nation != null\">" +
				"	AND pl.productid NOT in (SELECT DISTINCT(productid) from product_nation where basicinformationid  in(${nation}))" +
				"</if>"+
				"<if test=\"tattoo != null\">" +
				"	AND pj.tattoosmokeid  in (${tattoo})" +
				"</if>"+
				"<if test=\"WorkNature != null\">" +
				"	AND pw.interviewsituationid in (${WorkNature})" +
				"</if>" +
				"<if test=\"cityid != null\">" +
				"	AND f.cityid in (${cityid})" +
				"</if>" +
				"order by  null" +
				"<if test=\" salary != null\">" +
				"  ,pl.`maxsalary` desc" +
				"</if>" +
				"<if test=\" life != null\">" +
				"  ,fin.`amount` desc" +
				"</if>" +
				"<if test=\" Shop != null\">" +
				"  ,fsc.`amount` desc" +
				"</if>" +
				"   ,pl.sort desc" +
				"	limit ${page},${rows}" +
				"</script>")
		List<ProductDTO>  getProductLists(Map<String,Object> map);

	@Select("<script> SELECT  " +
			" COALESCE(count(*),0) " +
			" FROM productlist pl  " +
			" LEFT JOIN product_compensationwelfare com ON pl.id=com.productlistid  " +
			" LEFT JOIN product_spendingplan spend ON spend.productlistid=pl.id  and spend.invalidation='未失效'  " +
			" LEFT JOIN finance_livingallowances fin ON fin.id=spend.schemeid   " +
			"LEFT JOIN (Select productlistid,amount from product_marketing pm\n" +
			"\t\t\t\t\t RIGHT JOIN finance_invitescheme  fi on  pm.schemeid = fi.marketingprogramid and pm.invalidation='未失效') fint on fint.productlistid = pl.id\n" +
			"LEFT JOIN (Select productlistid,amount from product_marketing pm\n" +
			"\t\t\t\t\t RIGHT JOIN finance_shopkeeper  fi on  pm.schemeid = fi.marketingprogramid and pm.invalidation='未失效') fsc on fsc.productlistid = pl.id\n" +
			"LEFT JOIN (Select productlistid,amount from product_marketing pm\n" +
			"\t\t\t\t\t RIGHT JOIN finance_member  fi on  pm.schemeid = fi.marketingprogramid and pm.invalidation='未失效') fma on fma.productlistid = pl.id\n" +
			" left join product_jobrequirements pj on pl.id = pj.productlistid " +
			" left join productwork2 pw on pw.productNo=pl.productNo " +
			" left join factory f on f.id=pl.factoryid "
			+" WHERE pl.`status` = '上架' and pl.producttype='招工' " +
			"<if test=\"minage != null\">" +
			"	AND pj.maxage >= ${minage}" +
			"</if>"+
			"<if test=\"maxage != null\">" +
			"	AND ${maxage} >= pj.minage" +
			"</if>"+
			"<if test=\"Sex != null \">" +
			"	AND pj.sex = '${Sex}'   OR pj.sex='不限'" +
			"</if>"+
			"<if test=\"nation != null\">" +
			"	AND pl.productid NOT in (SELECT DISTINCT(productid) from product_nation where basicinformationid  in(${nation}))" +
			"</if>"+
			"<if test=\"tattoo != null\">" +
			"	AND pj.tattoosmokeid  in (${tattoo})" +
			"</if>"+
			"<if test=\"WorkNature != null\">" +
			"	AND pw.interviewsituationid in (${WorkNature})" +
			"</if>" +
			"<if test=\"cityid != null\">" +
			"	AND f.cityid in (${cityid})" +
			"</if>" +
			"order by  null" +
			"<if test=\" salary != null\">" +
			"  ,pl.`maxsalary` desc" +
			"</if>" +
			"<if test=\" life != null\">" +
			"  ,fin.`amount` desc" +
			"</if>" +
			"<if test=\" Shop != null\">" +
			"  ,fsc.`amount` desc" +
			"</if>" +
			"</script>")
	int getProductListsCount(Map<String,Object> map);


	//查询产品列表，根据
	//查询搜索基本信息所用条件：民族，用工性质，年龄范围，纹身烟疤
	@Select("SELECT id,name,value FROM basicinformation " +
			"where id in(96,97,98,99,100) or value in('用工性质','纹身烟疤','年龄范围') AND id not in (20)")
	List<Map<String,Object>> searchriteria();

	@Select("Select orgname from organizational where id in(#{cityid})")
	List<Map<String,Object>> city(String cityid);

	@Select("Select  value,name from basicinformation where id in \n" +
			"(#{nation},#{tattoo},#{WorkNature})")
	List<Map<String,Object>> Criteria(Map<String,Object> map);

	//根据产品id查询产品信息
	@Select("select * from productlist where productid = #{productid}")
    Productlist queryProductByProductid(String productid);


}
