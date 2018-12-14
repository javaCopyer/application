package com.muteng.dgjs.service;

import java.util.List;
import java.util.Map;

import com.muteng.dgjs.DTO.ProductDTO;
import com.muteng.dgjs.domain.Product;
import com.muteng.dgjs.domain.Productlist;

public interface ProductlistService {
	//根据id获取产品详情
	Map<String,Object> queryByProductid(Long productid,Map<String,Object> phoneMap);
	//获取产品列表
	Map<String,Object> getProductLists(int page,int rows,Map<String,Object> map);

	//根据城市搜索产品列表
	List<ProductDTO> getProductListByCityId(Long cityid);

	//查询搜索基本信息所用条件：民族，用工性质，年龄范围，纹身烟疤
	Map<String,List<String>> searchCriteria();

	//记录城市或者查询条件
	void cityAndCriteria(Map<String,Object> map);

	//根据产品id查询产品信息
	Productlist queryProductByProductid(String productid);
}
