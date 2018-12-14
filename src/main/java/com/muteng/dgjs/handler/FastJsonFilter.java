package com.muteng.dgjs.handler;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.serializer.PropertyFilter;
/**
 * 过滤不需要序列化的字段
 * @author KINLON
 *
 */
public class FastJsonFilter implements PropertyFilter {
	
	private String[] filter  = {};
	
	public FastJsonFilter(String[] filter){
		this.filter = filter;
	}
	
	@Override
	public boolean apply(Object obj, String name, Object value) {
		for (String o : filter) {
			if (StringUtils.equals(name,o)) {
				return false;
			}
		}
		return true;
	}
}
