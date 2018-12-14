package com.muteng.dgjs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muteng.dgjs.dao.OrganizationalMapper;
import com.muteng.dgjs.domain.Organizational;
import com.muteng.dgjs.service.OrganizationalService;

@Service
public class OrganizationalServiceImpl implements OrganizationalService{

	@Autowired
	private OrganizationalMapper organizationalMapper;
	
	@Override
	public List<Organizational> getList() {
		List<Organizational> list = this.organizationalMapper.queryListOrder();
		return list;
	}

	//查询城市，orderby 热门，首字母
	@Override
	public List<Map<String,Object>> queryOrganzation(){
		return organizationalMapper.queryOrganzation();
	}

	@Override
	public List<Map<String,Object>> queryLive2() {
		List<Map<String,Object>> list= organizationalMapper.queryOrganzation();
		String word[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		List<Map<String,Object>> listCity =new ArrayList<>();
		for(String initial : word) {
			Map<String,Object> cityM = new HashMap<String,Object>();
			cityM.put("title",initial);
			List<String> cityL = new ArrayList<String>();
			for (Map<String, Object> map : list) {
				String center = String.valueOf(map.get("initial"));
				if (center.equals(initial)) {
					cityL.add(map.get("id")+"-"+String.valueOf(map.get("orgname")));
				}
			}
			if(cityL.size()>=1){
			cityM.put("content",cityL);
			listCity.add(cityM);}
		}
		Map<String,Object> hotM = new HashMap<String,Object>();
		List<String> hotL = new ArrayList<String>();
		for (Map<String, Object> map : list) {
			String center = String.valueOf(map.get("initial"));
			if (String.valueOf(map.get("hot")).equals("热门")) {
				hotL.add(map.get("id")+"-"+String.valueOf(map.get("orgname")));
			}
		}
		hotM.put("热门",hotL);
		listCity.add(hotM);

		return listCity;
	}

	@Override
	public List<Organizational> queryByInitial(String initial) {

		return organizationalMapper.queryByInitial(initial);
	}


}
