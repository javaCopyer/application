package com.muteng.dgjs.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muteng.dgjs.dao.PositionsMapper;
import com.muteng.dgjs.domain.Positions;
import com.muteng.dgjs.service.PositionsService;

@Service
public class PositionsServiceImpl implements PositionsService{

	@Autowired
	private PositionsMapper positionsMapper;
	
	@Override
	public List<Positions> getPositions() {
		
		List<Positions> dtos = new ArrayList<Positions>();
		
		List<Positions> list = this.positionsMapper.selectAll();
		for (Positions positions : list) {
			if(positions.getParentid() == 0){
				dtos.add(positions);
			}
		}
		
		for (Positions positions : dtos) {
			List<Positions> list2 = new ArrayList<Positions>();
			for (int i = 0; i < list.size(); i++) {
				Positions positions_ = list.get(i);
				if(positions.getId() == positions_.getParentid()){
					list2.add(positions_);
				}
			}
			positions.setChildren(list2);
		}
		
		return dtos;
	}

	@Override
	public List<Map<String, Object>> queryPositions() {
		return positionsMapper.queryPositions();
	}

	//进入职位选择


}
