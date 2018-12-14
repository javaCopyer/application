package com.muteng.dgjs.service.impl;

import com.muteng.dgjs.dao.UserMapper;
import org.omg.CORBA.OBJECT_NOT_EXIST;
import org.springframework.stereotype.Service;

import com.muteng.dgjs.dao.RelationMapper;
import com.muteng.dgjs.DTO.Relation;
import com.muteng.dgjs.service.RelationService;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RelationServiceImpl implements RelationService {
	@Resource
	private RelationMapper relationMapper;
	@Resource
	private UserMapper userMapper;

	//邀请的客户
	@Override
	public Map<String,Object> getRelationKehu(Long userid) {
		String yaoqingma = relationMapper.yaoqingma(userid);
		List<Map<String,Object>> KehuDeatails =  relationMapper.queryUserByInviter(yaoqingma);
		List<Map<String,Object>> invitedEntry = relationMapper.invitedEntry(userid);
		Map<String,Object> rentrnMap = new HashMap<>();
//		rentrnMap.put("邀请注册详情",KehuDeatails);
		for(Map<String,Object> map :invitedEntry){
		try {
			String account =  String.valueOf(relationMapper.querypaylog(map).get("account"));
				if(!account.equals(null)&&!account.equals("null")){
				map.put("isArrival","已到账");
				map.put("account",account);

				}
				else{
					map.put("isArrival","未到账");
					map.put("account",String.valueOf(relationMapper.querypaylog(map).get("price")));
				}
		}catch (Exception e){
			System.out.println("没有付款单");
			map.put("isArrival","未到账");
			map.put("account",0);
		}
		}
//		rentrnMap.put("邀请入职详情",invitedEntry);
		rentrnMap.put("注册",KehuDeatails.size());
		rentrnMap.put("入职",invitedEntry.size());

		List<Map<String,Object>> allList = new ArrayList<Map<String,Object>>();
		SimpleDateFormat secondFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		for(Map<String,Object> map : invitedEntry){
				map.put("createtime",String.valueOf(map.get("createtime"))+" 00:00:00");
		}
		allList.addAll(KehuDeatails);
		allList.addAll(invitedEntry);
		for(int i = 0;i<allList.size()-1;i++){
			Map<String,Object > midMap = new HashMap<>();
			midMap = allList.get(i);
			int midNum = i;
			for(int j = i+1;j<=allList.size()-1;j++){
				try {
					long thisTime = secondFormat.parse(String.valueOf(allList.get(i).get("createtime"))).getTime();
					long frontTime = secondFormat.parse(String.valueOf(allList.get(j).get("createtime"))).getTime();
					if(thisTime<frontTime){
						midMap = allList.get(j);
						allList.set(j,allList.get(i));
						allList.set(i,midMap);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

		}
		for(Map map : allList){
			if(map.get("nickname")==null){
				map.put("nickname","   ");
			}else
			{
				try {
					map.put("nickname", URLDecoder.decode(String.valueOf(map.get("nickname")),"utf-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					System.out.println("转码错误！！！！！！！！！！！！！！");
				}
			}

		}

		rentrnMap.put("test",allList);
		return rentrnMap;
	}

	//月潜在客户，日潜在客户，邀请的客户
	@Override
	public List<Map<String,Object>> customDetail(Long userid) {
		Date date = new Date();
		//时间精确为日数
		SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
		//时间精确为月数
		SimpleDateFormat mounth = new SimpleDateFormat("yyyy-MM");
		//日潜在客户
		Calendar ThisMounth = Calendar.getInstance();
		ThisMounth.setTime(date);
		ThisMounth.add(Calendar.MONTH,0);
		Calendar LastMounth = Calendar.getInstance();
		LastMounth.setTime(date);
		LastMounth.add(Calendar.MONTH,-1);
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.setTime(date);
		tomorrow.add(Calendar.DAY_OF_MONTH,1);
		Calendar yestoday = Calendar.getInstance();
		yestoday.setTime(date);
		yestoday.add(Calendar.DAY_OF_MONTH,-1);
		Calendar today = Calendar.getInstance();
		today.setTime(date);
		today.add(Calendar.DAY_OF_MONTH,0);

		List<Map<String,Object>> countList =
				relationMapper.countTodayAndYester(day.format(yestoday.getTime()),day.format(tomorrow.getTime()),userid);
		if(countList.size()>0) {
			try {
			long theday = day.parse(String.valueOf(countList.get(0).get("createtime"))).getTime();
			long current = System.currentTimeMillis();
			long zero = current/(1000*3600*24)*(1000*3600*24) - TimeZone.getDefault().getRawOffset();
			if(theday>=zero){
				countList.get(0).put("今日人数", countList.get(0).get("count"));
				countList.get(0).put("昨日人数",0);
			}
			else {
				countList.get(0).put("昨日人数", countList.get(0).get("count"));

				if (countList.size() == 2) {
					countList.get(0).put("今日人数", countList.get(1).get("count"));
					countList.remove(1);
				} else {
					countList.get(0).put("今日人数", 0);
				}
				countList.get(0).remove("count");
			}
			} catch (ParseException e) {
				e.printStackTrace();
			}

			}
			else{
				Map<String,Object> map = new HashMap<>();
				map.put("昨日人数",0);
				map.put("今日人数",0);
			countList.add(0,map);
		}

		//本月访问详情
		List<Map<String,Object>> MounthDatilList =
				relationMapper.DetailToday(mounth.format(ThisMounth.getTime()),day.format(tomorrow.getTime()),userid);

		//将nickname为null的转变为“”,urlencode转译decode
		for(Map<String,Object> map : MounthDatilList){
			if(map.get("nickname")==null){
				map.put("nickname"," ");
			}else
			{
				try {
					map.put("nickname", URLDecoder.decode(String.valueOf(map.get("nickname")),"utf-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					System.out.println("转码错误！！！！！！！！！！！！！！");
				}
			}
		}
		//月访问量
		if (MounthDatilList.size()>0){

		int mounthcount = MounthDatilList.size();
		countList.get(0).put("本月访问数",mounthcount);
		try {
			int lastMountCount =
					relationMapper.lastMount(mounth.format(LastMounth.getTime()),mounth.format(ThisMounth.getTime()),userid);
			countList.get(0).put("上月访问人数",lastMountCount);
		}catch (Exception e){
			countList.get(0).put("上月访问人数",0);
		}
		}else{
			countList.get(0).put("上月访问人数",0);
			countList.get(0).put("本月访问人数",0);
		}

		//取今日访问详情
		List<Map<String,Object>> DayDatilList = new ArrayList<>();
		Map<String,Object> DetailMap = new HashMap<>();
		for(Map<String,Object> map : MounthDatilList){
			try {
				long theday =day.parse(String.valueOf(map.get("createtime"))).getTime();
				long current = System.currentTimeMillis();
				long zero = current/(1000*3600*24)*(1000*3600*24) - TimeZone.getDefault().getRawOffset();
				if(theday>=zero){
				DayDatilList.add(map);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		DetailMap.put("今日访问详情",DayDatilList);
		DetailMap.put("本月访问详情",MounthDatilList);
		countList.add(DetailMap);
		return countList;
	}
	//，邀请的会员
	@Override
	public List<Map<String,Object>> memberDetail(Long userid) {
		List<Map<String,Object>> membersList = relationMapper.querymembers(userid);
		int  i =  0;
		for (Map<String,Object> map:
		membersList) {
			if(Integer.parseInt(String.valueOf(map.get("inviterId"))) == userid){
				map.put("inviterName","我");
				map.put("price","邀请奖励"+String.valueOf(map.get("price")).replace(".0000","")+"元");

				i++;
				System.out.println("============================================================"+i);
			}else {
				map.put("price"," ");
			}
			if(map.get("inviterName") == null){
				map.put("inviterName","您的好友");
			}
			if(map.get("nickname")==null){
				map.put("nickname"," ");
			}

				try {
					map.put("nickname", URLDecoder.decode(String.valueOf(map.get("nickname")),"utf-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					System.out.println("转码错误！！！！！！！！！！！！！！");
				}


			try {
				map.put("content","通过"+URLDecoder.decode(String.valueOf(map.get("inviterName")),"utf-8")+"成为会员");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		List<Map<String,Object>> addlist = new ArrayList<>();
		Map<String,Object> map = new HashMap<>();
		if(membersList.size()>0) {
			map.put("allcount", membersList.size());
			map.put("othercount", membersList.size() - i);

		}
		else {
			map.put("allcount",0);
			map.put("othercount",0);
		}
		map.put("mycount",i);

		addlist.add(map);
		addlist.addAll(membersList);

		return   addlist;
	}
//
//	//    递归向下
//	//传入层数，累计用集合，上层id集合
//	List<Map<String,Object>> recursive(List<Map<String,Object>> list,List<Map<String,Object>> frontyqmlist)
//	{
//		//new yqmlist为本层id集合
//		List<Map<String,Object>> thisidList = new ArrayList<>();
//		//遍历上层id集合
//		for (Map<String,Object> map :frontyqmlist){
//			//根据 forlsit中id获得子节点的id
//			thisidList = relationMapper.useridlist(map);
//			list.get(list.size()-1).put("acount",thisidList.size());
//			//在此forthislist，加入map中name即上层的名字
//			list.addAll(thisidList);
//			//判断yqmlist长度是否小于等于零。
//			if (thisidList.size()<=0)
//			{//最外层循环结束
//				return list;}
//			else
//			{//将层数减一，累计用list传入，本层邀请码list传入,所得结果加入累计用list
//				recursive(list,thisidList);}
//		}
//		return list;
//	}


		//今日访问数、当月访问数、邀请客户数、邀请会员数量method040
	@Override
	public Map<String,Object> getRelation(Long userid,Long phone) {
		Map<String,Object> map = new HashMap<>();
		map.put("id",userid);
		map.put("phone",phone);
		//时间精确为日数
		SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
		//时间精确为月数
		SimpleDateFormat mounth = new SimpleDateFormat("yyyy-MM");
		//查询月关注数
		map.put("mounthcount",relationMapper.getRelationMonth(mounth.format(new Date()),userid));
		//查询日关注数
		map.put("daycount",relationMapper.getRelationDay(day.format(new Date()),userid));
		//获取我邀请的会员数量
		map.put("memberacount",userMapper.querymembercount(map));
		//获取我邀请的注册数量+入职数量
		map.put("registeracount",userMapper.querinvitedByid(userid)+relationMapper.invitedEntry(userid).size());
		//查询我的邀请码
		map.put("yaoqingma",relationMapper.yaoqingma(userid));

		return map;
	}

	@Override
	public int insert(Relation relation) {
		return relationMapper.insertRelation(relation);
	}

	@Override
	public List<Map<String,Object>> invitedNum(){
		List<Map<String,Object>> invitedList = relationMapper.invitedNum();
		for(Map<String,Object> map: invitedList){
			String num = String.valueOf(map.get("num"));
			map.put("thisIsString",String.valueOf(map.get("name")).substring(0,1)+"**"+"邀请了"+num+"人注册，获得赏金"+Integer.parseInt(num)*2+"元");
		}
		List<Map<String,Object>> rekrutenList = relationMapper.rekruten();
		for(Map<String,Object> map: rekrutenList){
			String num = String.valueOf(map.get("num"));
			map.put("thisIsString",String.valueOf(map.get("name")).substring(0,1)+"**"+"邀请了"+num+"人入职，获得赏金"+Integer.parseInt(num)*100+"元");
		}
		List<Map<String,Object>> resultList = new ArrayList<>();
		resultList.addAll(invitedList);
		resultList.addAll(rekrutenList);
		return resultList;
	}

	@Override
	public Relation getRelationByopenid(String openid) {
		return relationMapper.getRelationByopenid(openid);
	}

	@Override
	public void insertRelation(Relation relation) { relationMapper.insertrelation(relation);
	}

}
