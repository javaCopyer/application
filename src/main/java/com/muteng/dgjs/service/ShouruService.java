package com.muteng.dgjs.service;

import java.util.List;
import java.util.Map;

import com.muteng.dgjs.DTO.InviteRevenue;
import com.muteng.dgjs.DTO.LzRevenue;
import com.muteng.dgjs.DTO.LzsrDTO;
import com.muteng.dgjs.DTO.MyRevenue;

public interface ShouruService {
	List<LzRevenue> getLizhiShouru(Long userid);
	
	List<InviteRevenue> getInviteShouru(Long userid);

	Map<String,Integer> getMyShouru(Long userid);

}
