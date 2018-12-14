package com.muteng.dgjs.service.impl;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.muteng.dgjs.DTO.Auditlog;
import com.muteng.dgjs.DTO.ProductDetailDTO;
import com.muteng.dgjs.dao.AuditlogMapper;
import com.muteng.dgjs.dao.UserMapper;
import com.muteng.dgjs.domain.Product;
import com.muteng.dgjs.domain.Productlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muteng.dgjs.DTO.ProductDTO;
import com.muteng.dgjs.dao.ProductlistMapper;
import com.muteng.dgjs.service. ProductlistService;

@Service
public class ProdictlistsServiceImpl implements ProductlistService {
	@Autowired
	private ProductlistMapper productlistMapper;
	@Resource
	private UserMapper userService;
	@Resource
	private AuditlogMapper auditlogMapper;
	@Override
	//根据产品id获取产品详情
	public Map<String,Object> queryByProductid(Long productid,Map<String,Object> phoneMap) {
		Map<String,Object> renturnMap = new HashMap<>();

		List<ProductDetailDTO> pdList=  productlistMapper.queryByProductid(productid);
		for(ProductDetailDTO dto : pdList){
			String pic = dto.getPictures();
			String[] picArray = pic.split(",");
			String newpic = "";
			for(String pics : picArray){
				if(pics.trim().length()>0){
					newpic += pics + ",";
				}
			}
			dto.setPictures(newpic.substring(0,newpic.length()-1));
		}

		if(pdList.size()==0){
			renturnMap.put("标签","暂无");

			renturnMap.put("基本信息","暂无");

			renturnMap.put("其他信息","暂无");
			return renturnMap;
		}
		ProductDetailDTO productDetailDTO =  pdList.get(0);

		List<Map<String,Object>> basicInformationL = productlistMapper.querynformation(productDetailDTO.getPaidwayid(),
				productDetailDTO.getDutyid(),
				productDetailDTO.getEnglishcharactersid(),
				productDetailDTO.getNationid(),
				productDetailDTO.getPipelineid(),
				productDetailDTO.getTattoosmokeid(),
				productDetailDTO.getSimplearithmeticid(),productDetailDTO.getRoomid());
		Map<String,String> basemap = new HashMap<>();
		if(basicInformationL.size()>0) {
			for (Map<String, Object> map : basicInformationL) {
				basemap.put(String.valueOf(map.get("value")),map.get("value")+"："+map.get("name"));
			}
		}

		renturnMap.put("标签",productlistMapper.queryroductag(productid));

		//职位要求
		String jobrequirements = productDetailDTO.getJobrequirements();
		if( basemap.get("少数民族") != null && !basemap.get("少数民族").equals("null")  ){
			jobrequirements = jobrequirements + basemap.get("少数民族") + "</br>";
			System.out.println(jobrequirements);
		}
		if( basemap.get("英文汉字") != null && !basemap.get("英文汉字").equals("null") ){
			jobrequirements = jobrequirements + basemap.get("英文汉字") + "</br>";
		}
		if( basemap.get("简单算术") != null && !basemap.get("简单算术").equals("null") ){
			jobrequirements = jobrequirements + basemap.get("简单算术")+"</br>";
		}
		if(basemap.get("纹身烟疤") != null && !basemap.get("纹身烟疤").equals("null")){
			jobrequirements = jobrequirements + basemap.get("纹身烟疤");
			jobrequirements = jobrequirements.replace("无","不接受");
			jobrequirements = jobrequirements.replace("明显","接受明显纹身烟疤");
			jobrequirements = jobrequirements.replace("不明显","接受不明显纹身烟疤");
		}

		//工作环境
		String workingcondition = productDetailDTO.getWorkingcondition();
		if( basemap.get("站班做班") != null && !basemap.get("站班做班").equals("null") ){
			workingcondition = workingcondition + basemap.get("站班做班")+"</br>";
		}
		if( basemap.get("流水线") != null && !basemap.get("流水线").equals("null") ){
			workingcondition = workingcondition + basemap.get("流水线")+"</br>";
		}
		//吃住条件
		String eatAndLive = productDetailDTO.getEatAndLive();
		if(basemap.get("房间类型") != null &&  !basemap.get("房间类型").equals("null") ){
			eatAndLive = eatAndLive + basemap.get("房间")+"</br>";
		}
		//compensationwelfare
		 String compensationwelfare =  productDetailDTO.getCompensationwelfare();
		//interviewsituation
		String interviewsituation = productDetailDTO.getInterviewsituation();
		//compactions
		String compactions = productDetailDTO.getCompactions();

		renturnMap.put("基本信息",productDetailDTO);
		renturnMap.put("吃住条件",eatAndLive);
		renturnMap.put("职位要求",jobrequirements);
		renturnMap.put("工作条件",workingcondition);
		renturnMap.put("其他信息",basicInformationL);
		renturnMap.put("compensationwelfare",compensationwelfare);
		renturnMap.put("interviewsituation",interviewsituation);
		renturnMap.put("compactions",compactions);
		try {
			Map<String,Object> memberMap =userService.querymember(phoneMap);
			if(memberMap.get("nickname") != null ){
				memberMap.put("name", URLDecoder.decode(String.valueOf(memberMap.get("nickname")),"utf-8")+"的店铺");
			}
			if(memberMap.get("name") == null){
				memberMap.put("name","好友的店铺");
			}
			renturnMap.put("用户信息", memberMap);
		}catch (Exception e){
			e.printStackTrace();
		}
		renturnMap.put("openid",phoneMap.get("openid"));
		renturnMap.put("nickname",phoneMap.get("nickname"));
		renturnMap.put("headimgurl",phoneMap.get("headimgurl"));
		renturnMap.put("subsource",phoneMap.get("subsource"));
		return renturnMap;
	}


	//获取产品列表
	@Override
	public Map<String,Object> getProductLists(int page,int rows,Map<String,Object> map) {
		for (Object obj :map.values()){
			String str = String.valueOf(obj);
			if (str.indexOf("delete")>-1||str.indexOf("update")>-1){
				return null;
			}
		}
		map.put("page",Integer.valueOf(page));
		map.put("rows",Integer.valueOf(rows));
		String age  =String.valueOf(map.get("age"));
		if(age != null && !age.equals("") && !age.equals("null")){
			if(age.indexOf('-')>-1){
				map.put("minage",age.split("-")[0]);
				map.put("maxage",age.split("-")[1]);
			}
			else{
				map.put("minage",age.replace("以上", ""));
			}
		}
		String nation = String.valueOf(map.get("nation"));
		if(nation != null && !nation.equals("") && !nation.equals("null")){
			nation =nation.replace("104","12");
			nation =nation.replace("105","13");
			nation =nation.replace("106","15");
			nation =nation.replace("107","14");
			map.put("nation",nation);

		}
		Map<String,Object> resMap = new HashMap<String,Object>();
		StringBuffer cityBuffer = new StringBuffer();
		if(map.get("cityid") != null) {
			String cityid = (String)map.get("cityid");
			String[] cityArray = cityid.split(",");
			for(String city : cityArray) {
				city = city.trim();
				if(city.length()>0) {
					cityBuffer.append("'" + city + "'").append(",");
				}
			}
			String resCity = cityBuffer.toString().substring(0, cityBuffer.length()-1);
			map.put("cityid", resCity);
		}
		
		resMap.put("count", productlistMapper.getProductListsCount(map));
		
		List<ProductDTO> list =productlistMapper.getProductLists(map);
		resMap.put("List", list);
		
		return resMap;

	}
	//根据城市获取产品列表
	@Override
	public List<ProductDTO> getProductListByCityId(Long cityid) {
		System.out.println(cityid);
		return null;
	}


	//查询搜索基本信息所用条件：民族，用工性质，年龄范围，纹身烟疤
	@Override
	public Map<String,List<String>> searchCriteria() {
		List<Map<String, Object>> forlist = productlistMapper.searchriteria();
		Map<String,List<String>> resultMap = new HashMap<String,List<String>>();
		List<String> resultlist = new ArrayList<String>();
		List<String> resultlist1 = new ArrayList<String>();
		List<String> resultlist2 = new ArrayList<String>();
		List<String> resultlist3 = new ArrayList<String>();

		for(Map<String, Object> map :forlist){
			switch (String.valueOf(map.get("value"))) {
				case "民族":
					resultlist.add(String.valueOf(map.get("name")));
					resultlist.add(String.valueOf(map.get("id")));
					break;
				case "用工性质":
					resultlist1.add(String.valueOf(map.get("name")));
					resultlist1.add(String.valueOf(map.get("id")));
					break;
				case "年龄范围":
					resultlist2.add(String.valueOf(map.get("name")));
					resultlist2.add(String.valueOf(map.get("id")));
					break;
				case "纹身烟疤":
					resultlist3.add(String.valueOf(map.get("name")));
					resultlist3.add(String.valueOf(map.get("id")));
					break;
			}
		}
		resultlist.add("其他");
		resultlist.add("9999");
		resultMap.put("民族",resultlist);
		resultMap.put("用工性质",resultlist1);
		resultMap.put("年龄范围",resultlist2);
		resultMap.put("纹身烟疤",resultlist3);

		return resultMap;
	}



	//记录城市和搜索对应条件
	@Override
	public void cityAndCriteria(Map<String,Object> map){
		try {
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			String resultString = "";
			Auditlog auditlog = new Auditlog();
			if (map.get("cityid") != null) {
				resultList = productlistMapper.city(String.valueOf(map.get("cityid")));
				for (Map<String, Object> formap : resultList) {
					resultString = resultString + formap.get("orgname") + ".";
				}
				resultString = "城市：" + resultString;
				auditlog.setActionnum("Work_10");
				auditlog.setPagenum("work");
			}
			if (map.get("cityid") == null) {

				List<Map<String, Object>> basicInformationL = productlistMapper.Criteria(map);
				if (basicInformationL.size() > 0) {
					for (Map<String, Object> formap : basicInformationL) {
						resultString = resultString + String.valueOf(formap.get("value")) + formap.get("value") + ":" + formap.get("name");

					}
				}
				auditlog.setActionnum("Work_12");
				auditlog.setPagenum("work");
				resultString = resultString + String.valueOf(map.get("sex")) + String.valueOf(map.get("age"));
				resultString = resultString.replaceAll("null", "");
			}


			Auditlog a = auditlogMapper.selectauditLog(auditlog.getPagenum(), auditlog.getActionnum());
			auditlog.setAction(a.getAction());
			auditlog.setPage(a.getPage());
			auditlog.setContent(a.getPage() + "执行了" + a.getAction() + "条件为" + resultString);
			if (map.get("userid") == null) {
				long num = -1;
				map.put("userid", num);
			}
			;
			auditlog.setUserid(Long.valueOf(String.valueOf(map.get("userid"))));
			auditlogMapper.insertAction(auditlog);
		}
		catch (Exception e){
			System.out.println(e);
			return;
		}
	}

	@Override
	public Productlist queryProductByProductid(String productid) {
		return productlistMapper.queryProductByProductid(productid);
	}

}
