package com.muteng.dgjs.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.swing.plaf.synth.SynthOptionPaneUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.icu.text.Collator;
import com.muteng.dgjs.common.utils.CommonUtil;
import com.muteng.dgjs.common.utils.OssUtil;
import com.muteng.dgjs.dao.UserMapper;
import com.muteng.dgjs.domain.User;
import com.muteng.dgjs.service.AddressBookService;
import com.muteng.dgjs.service.SendMessageService;

/**
 * 同步通讯录
 * 
 * @author 杨阳
 *
 */
@Service
public class AddressBookServiceImpl implements AddressBookService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Resource
	private SendMessageService sendMessageService;
	/**
	 * 同步通讯录
	 * 
	 * @version V3.0
	 * @return
	 */
	@SuppressWarnings("unused")
	public Object[] synchronizeRelationship(Map<String, Object> paramMap) {

		System.out.println(paramMap);
		Object[] obj = new Object[2];
		
		Map<String,Object> res = new HashMap<String,Object>();
		if (paramMap == null) {
			return null;
		}
		if (paramMap.get("userid") == null) {
			return null;
		}
		if (paramMap.get("relation") == null) {
			return null;
		}
		List<String> nameList = new ArrayList<String>();

		String relationship = paramMap.get("relation").toString();
		System.out.println("+++++++++++++++++++"+relationship);
		String[] relation = relationship.split(",");
		String filename = paramMap.get("userid").toString();
		StringBuffer keytemp = new StringBuffer("maillist/");
		for (int i = 0; i < filename.length(); i++) {
			keytemp.append(filename.charAt(i)).append("/");
		}
		String key = keytemp.append(filename).append(".txt").toString();
		// 判断oss是否有该文件
		String REGEX_MOBILE = "^1[0-9]{10}$";
		boolean flag = OssUtil.doesFileExistToOSS(key);
		// 通讯录存在 下载通讯录

		if (flag) {
			String result = OssUtil.downloadFileToOSS(key);
			if(result.length()<=0) {
				flag = false;
			}
			if(flag){
				String[] resulttemp = result.trim().split(",");
				// 客户端提交的通讯录
				List<Map<String, String>> paramList = new ArrayList<Map<String, String>>();
				// oss中已有的通讯录
				List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
				for (int j = 0; j < relation.length; j++) {
					Map<String, String> ralationMap = new HashMap<String, String>();
					if (relation[j].indexOf("@") == relation[j].length() - 1) {
						// 没有获取姓名
						continue;
					}
					String[] data = relation[j].split("@");
					if (!CommonUtil.isMatching(data[0], REGEX_MOBILE)) {
						continue;
					}
					ralationMap.put("friendmobile", data[0]);
					ralationMap.put("friendname", data[1]);
					//0 为注册 1 已邀请 2 邀请成功 3 已注册
					ralationMap.put("friendstate", "0");
					paramList.add(ralationMap);
				}


				for (int k = 0; k < resulttemp.length; k++) {
					String resultone = resulttemp[k];
					String[] resulto = resultone.split("\\|");
					String friendmobile = resulto[1];
					for (int i = 0; i < paramList.size(); i++) {
						Map<String, String> ralationMap = new HashMap<String, String>();
						String mobile = paramList.get(i).get("friendmobile");
						if (mobile.equals(friendmobile)) {
							// 比较相同的手机号，把新读取的姓名覆盖oss中通讯录中的姓名
							Map<String, String> resultMap = new HashMap<String, String>();
							resultMap.put("friendname", paramList.get(i).get("friendname"));
							resultMap.put("friendmobile", resulto[1]);
							resultMap.put("friendstate", resulto[2]);
							resultList.add(resultMap);
							// 相同手机号的删除，剩余不相同的手机号
							paramList.remove(i);
						}
					}
				}
				if (paramList.size() != 0) {
					// 剩余不同手机号 全部同步到通讯录列表中
					resultList.addAll(paramList);
				}

				Collections.sort(resultList, new Comparator<Map<String, String>>() {
					public int compare(Map<String, String> m1, Map<String, String> m2) {
						String friendname1 = m1.get("friendname");
						String friendname2 = m2.get("friendname");
						Comparator<Object> com = Collator.getInstance(java.util.Locale.CHINA);
						return com.compare(friendname1, friendname2);
					};
				});
				// 上传内容列表
				List<String> contentList = new ArrayList<String>();

				for (int i = 0; i < resultList.size(); i++) {
					Map<String, String> resultMap = new HashMap<String, String>();
					StringBuffer content = new StringBuffer();
					String name = resultList.get(i).get("friendname");
					String mobile = resultList.get(i).get("friendmobile");
					String state = resultList.get(i).get("friendstate");
					content.append(name).append("|").append(mobile).append("|").append(state).append(",");
					contentList.add(content.toString());
				}
				OssUtil.uploadInputstream(contentList, key);
				obj[0] = 1;
				res.put("msg", contentList);
				obj[1] = res;
				return obj;
			}
		}

		// 首次读取通讯录
		List<String> contentList = new ArrayList<String>();
		// 将传过来的字符串拼接成上传需要的list
		for (int i = 0; i < relation.length; i++) {
			StringBuffer content = new StringBuffer();
			if (relation[i].indexOf("@") == relation[i].length() - 1) {
				continue;
			}
			String[] data = relation[i].split("@");
			String mobile = data[0];
			if (!CommonUtil.isMatching(data[0], REGEX_MOBILE)) {
				continue;
			}

			String name = data[1];
			String state = "0";

			content.append(name).append("|").append(mobile).append("|").append(state).append(",");
			contentList.add(content.toString());
		}
		Collections.sort(contentList, new Comparator<String>() {
			public int compare(String m1, String m2) {
				String friendname1 = m1.substring(0, m1.indexOf("|"));
				String friendname2 = m2.substring(0, m1.indexOf("|"));
				Comparator<Object> com = Collator.getInstance(java.util.Locale.CHINA);
				return com.compare(friendname1, friendname2);
			};
		});
		if(contentList.size()>0) {
			// 将文件上传到oss
			OssUtil.uploadInputstream(contentList, key);
		}
		obj[0] = 1;
		res.put("msg", contentList);
		obj[1] = res;
		return obj;


	}
	
	/**
	 * 邀请好友入职
	 */
	public Object[] invitingfriends(Map<String, Object> paramMap) throws UnsupportedEncodingException {
		Object[] obj = new Object[2];
		Map<String,Object> res = new HashMap<String,Object>();
		String relationship = paramMap.get("relation").toString();
		String[] relation = relationship.split(",");
		String filename = paramMap.get("userid").toString();
		Map<String,Object> mp = new HashMap<String,Object>();
		mp.put("userid",filename);
		User u= this.userMapper.queryByUserId(mp);
		String yaoqingma = u.getYaoqingma();
		String invitername=u.getName();
		if(invitername ==null){
			invitername ="";
		}
		StringBuffer keytemp = new StringBuffer("maillist/");
		for (int i = 0; i < filename.length(); i++) {
			keytemp.append(filename.charAt(i)).append("/");
		}
		String key = keytemp.append(filename).append(".txt").toString();
		
		String REGEX_MOBILE = "^1[0-9]{10}$";
		
		// 客户端提交的通讯录
		List<Map<String, String>> paramList = new ArrayList<Map<String, String>>();
		for (int j = 0; j < relation.length; j++) {
			Map<String, String> ralationMap = new HashMap<String, String>();
			if (relation[j].indexOf("@") == relation[j].length() - 1) {
				// 没有获取姓名
				continue;
			}
			String[] data = relation[j].split("@");
			if (!CommonUtil.isMatching(data[0], REGEX_MOBILE)) {
				continue;
			}
			
			ralationMap.put("friendmobile", data[0]);
			ralationMap.put("friendname", data[1]);
			
			//验证手机是否已经注册
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("phone", data[0]);
			User user = this.userMapper.queryByloginname(map);
			//0 未邀请 1 已邀请 2 邀请成功 3 已被注册 
			if(user == null) {
				ralationMap.put("friendstate", "1");
				//发送短信邀请
				this.sendMessageService.sendSms(data[0], data[1],yaoqingma,invitername);
			} else {
				//用户已经被注册
				ralationMap.put("friendstate", "3");
			}
			
			paramList.add(ralationMap);
		}
		//读取oss通讯录
		boolean flag = OssUtil.doesFileExistToOSS(key);
		if(flag) {
			// 通讯录存在 下载通讯录
			String result = OssUtil.downloadFileToOSS(key);
			String[] resulttemp = result.trim().split(",");
			
			//合并通讯录结果
			List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
			for (int k = 0; k < resulttemp.length; k++) {
				String resultone = resulttemp[k];
				String[] resulto = resultone.split("\\|");
				String friendmobile = resulto[1];
				Map<String, String> resultMap = new HashMap<String, String>();
				resultMap.put("friendname", resulto[0]);
				resultMap.put("friendmobile", resulto[1]);
				resultMap.put("friendstate", resulto[2]);
				for (int i = 0; i < paramList.size(); i++) {
					String mobile = paramList.get(i).get("friendmobile");
					String state = paramList.get(i).get("friendstate");
					if (mobile.equals(friendmobile)) {
						// 比较相同的手机号，设置状态
						resultMap.put("friendstate", state);
						
						// 相同手机号的删除，剩余不相同的手机号
						paramList.remove(i);
					}
				}
				resultList.add(resultMap);
			}
			if (paramList.size() != 0) {
				// 剩余不同手机号 全部同步到通讯录列表中
				resultList.addAll(paramList);
			}
			Collections.sort(resultList, new Comparator<Map<String, String>>() {  
	            public int compare(Map<String, String> m1, Map<String, String> m2) {
	            	String friendname1 = m1.get("friendname");
	            	String friendname2 = m2.get("friendname");
	            	Comparator<Object> com = Collator.getInstance(java.util.Locale.CHINA);
	                return com.compare(friendname1, friendname2);
	            };  
	        });
			// 上传内容列表
			List<String> contentList = new ArrayList<String>();

			for (int i = 0; i < resultList.size(); i++) {
				StringBuffer content = new StringBuffer();
				String name = resultList.get(i).get("friendname");
				String mobile = resultList.get(i).get("friendmobile");
				String state = resultList.get(i).get("friendstate");
				content.append(name).append("|").append(mobile).append("|").append(state).append(",");
				contentList.add(content.toString());
			}
			OssUtil.uploadInputstream(contentList, key);
			obj[0] = 1;
			res.put("msg", contentList);
			obj[1] = res;
			return obj;
		}
		obj[0] = 1;
		res.put("msg", "邀请成功");
		obj[1] = res;
		return obj;
	}

}
