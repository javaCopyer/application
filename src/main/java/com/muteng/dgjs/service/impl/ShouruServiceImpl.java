package com.muteng.dgjs.service.impl;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import com.muteng.dgjs.DTO.InviteRevenue;
import com.muteng.dgjs.DTO.LzRevenue;
import org.springframework.stereotype.Service;

import com.muteng.dgjs.DTO.LzsrDTO;
import com.muteng.dgjs.dao.ShouruMapper;
import com.muteng.dgjs.service.ShouruService;
@Service
public class ShouruServiceImpl implements ShouruService {
	@Resource
	private ShouruMapper shouruMapper;
	@Override
	public List<LzRevenue> getLizhiShouru(Long userid) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<LzRevenue> r = shouruMapper.getLizhiShouru(userid);
		try {
			for(LzRevenue i : r){
				if(i.getHiredate()==null){
					i.setHiredate("");
				}
				if(i.getResignationtime()==null){
					i.setResignationtime("");
				}
			}

		}catch (Exception e){
			e.printStackTrace();
		}


		return r;
	}
	@Override
	public List<InviteRevenue> getInviteShouru(Long userid) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<InviteRevenue> r = shouruMapper.getInviteiShouru(userid);
		try {
			for(InviteRevenue i : r){
				if(i.getCollectiontime()!=null){
					i.setCollectiontime("预计确认时间："+i.getCollectiontime()+"个工作日");
				}else{
					i.setCollectiontime("  ");
				}

				if(i.getHiredate()==null){
					i.setTimeStr("报名时间："+sdf.format(i.getCreatetime()));
					i.setHiredate(new Date());
				}
				else{
					i.setTimeStr("入职时间："+sdf.format(i.getHiredate()));
				}
			}

		}catch (Exception e){
			e.printStackTrace();
		}


		return r;
	}

	@Override
	public Map<String,Integer> getMyShouru(Long userid) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		Integer lz = shouruMapper.getlz(userid);//离职作废额
		if(lz == null){
			lz = 0;
		}
		Integer wqr = shouruMapper.getwqr(userid);//未确认金额
		if(wqr == null){
			wqr = 0;
		}
		Integer dz = shouruMapper.getdz(userid);//到账金额
		if(dz == null){
			dz = 0;
		}
		Integer account = shouruMapper.getaccount(userid);//获取可用余额
		if(account == null){
			account = 0;
		}
		map.put("lzmoney",lz);
		map.put("wqrmoney",wqr);
		map.put("dzmoney",dz);
		map.put("account",account);
		return map;
	}
}
