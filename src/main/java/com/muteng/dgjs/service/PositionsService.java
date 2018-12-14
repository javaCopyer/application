package com.muteng.dgjs.service;

import java.util.List;
import java.util.Map;

import com.muteng.dgjs.domain.Positions;

public interface PositionsService {

	List<Positions> getPositions();
	//进去职位选择
	List<Map<String,Object>> queryPositions();
}
