package com.muteng.dgjs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muteng.dgjs.DTO.ProductMarketingDTO;
import com.muteng.dgjs.dao.ProductMarketingMapper;
import com.muteng.dgjs.service.ProductMarketingService;
@Service
public class ProductMarketingServiceImpl implements ProductMarketingService{
	@Autowired
	private ProductMarketingMapper productMarketingMapper;
	@Override
	public List<ProductMarketingDTO> getjlForIndex() {
		return productMarketingMapper.getjlForIndex();
	}

}
