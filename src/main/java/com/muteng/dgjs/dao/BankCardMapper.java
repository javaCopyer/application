package com.muteng.dgjs.dao;

import java.util.List;
import java.util.Map;

import com.muteng.dgjs.DTO.Bank;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.dao.helper.DefaultMapper;
import com.muteng.dgjs.domain.BankCard;
public interface BankCardMapper extends DefaultMapper<BankCard>{
	//根据用户ID查询所有银行卡号
	@Select("SELECT b.cardnum,b.name,bs.name as bankname,bs.logourl,bs.leftcolor,bs.rightcolor FROM bankcard b" +
			" left join bankstandard bs on b.tel = bs.tel" +
			" WHERE b.userid = #{userid} " +
			" and b.status = 1")
	List<BankCard> queryByUserid(Long userid);
	
	@Select("SELECT * FROM bankcard WHERE userid = #{userid} AND cardnum = #{cardnum} AND status = 1")
	BankCard queryOne(Map<String,Object> map);

	//根据用户ID和卡号修改银行卡为自动付款卡
	@Update("update bankcard set  ismain = 1 where userid = #{param1} and cardnum =#{param2}")
    void updateBankcardByUserid(Long userid,String cardnum);

	//根据用户ID添加绑定银行卡
	@Insert("insert into bankcard (userid,name,cardnum,cardType,area,brand,status,ismain,url,tel,bankname,createtime) " +
			"values(#{userid},#{name},#{cardNum},#{cardType},#{area},#{brand},1,1,#{url},#{tel},#{bankName},now())")
	void addBankcardByUserid(Map<String,Object> map);

	//根据ID设置所有银行卡为不是自动付款卡
	@Update("update bankcard set  ismain = 2 where userid =#{userid} ")
	void updateOtherBankcardByUserid(Long userid);

	//查询所有支持的银行
	@Select("select * from bankstandard where status = 1")
	List<Bank> selectbank();

	//根据ID查询历史绑定的银行卡信息
	@Select("select bk.name,bk.userid,bk.bankname,bk.cardnum,bk.ismain,b.leftcolor,b.rightcolor,b.logourl from bankcard bk" +
			" left join bankstandard b on b.tel = bk.tel " +
			" where bk.userid = #{id} and bk.status = 1")
	List<BankCard> getgetBankcardInfoByUserid(Long id);

	//查询所有支持的银行的电话
    @Select("select tel from bankstandard where tel = #{tel}")
    List<Integer> selectBankTel(Object tel);

    //根据银行名查询key
    @Select("select keyword from bankstandard ")
    List<String> selectBankByFuzzy(String bname);

    //根据银行卡号查询银行卡信息
	@Select("select bs.logourl,b.cardnum,bs.name as bankname from bankcard b" +
			" left join bankstandard bs on b.tel =bs.tel" +
			" where b.cardnum = #{cardnum}")
	BankCard selectBankBynum(String cardnum);


	//根据银行卡查询是否已经存在此银行卡
	@Select("select * from bankcard where cardnum = #{bankcard}")
	String getBankcardBybankCard(String bankcard);

	//根据银行名称查询银行信息
	@Select("SELECT bs.name as bankname,bs.logourl,bs.leftcolor,bs.rightcolor " +
			" FROM bankstandard bs " +
			" WHERE bs.tel = #{tel} " )
	BankCard queryBankBycard(String tel);
}
