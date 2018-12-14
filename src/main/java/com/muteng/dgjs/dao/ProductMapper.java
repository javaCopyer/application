package com.muteng.dgjs.dao;

import java.util.Map;

import com.muteng.dgjs.domain.Productlist;
import org.apache.ibatis.annotations.Select;

import com.dao.helper.DefaultMapper;

public interface ProductMapper extends DefaultMapper<Productlist>{
	
	@Select("select * from productlist where productid=#{productid}")
	Productlist queryProductByid(Map<String,Object> paramMap);
}
