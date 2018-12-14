package com.muteng.dgjs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.dao.helper.DefaultMapper;
import com.muteng.dgjs.DTO.ProductMarketingDTO;
import com.muteng.dgjs.domain.ProductMarketing;
public interface ProductMarketingMapper extends DefaultMapper<ProductMarketing>{
	@Select("SELECT  t2.title, t2.id productlistid,amount/100 amount \n" +
			"FROM product_marketing t1 \n" +
			"LEFT JOIN productlist t2 ON t2.id = t1.productlistid \n" +
			"RIGHT JOIN finance_invitescheme t3 ON t3.id = t1.schemeid \n" +
			"WHERE t2.status = '上架' AND t1.invalidation = '未失效'\n" +
			"ORDER BY t3.amount DESC LIMIT 10")
	List<ProductMarketingDTO> getjlForIndex();
}
