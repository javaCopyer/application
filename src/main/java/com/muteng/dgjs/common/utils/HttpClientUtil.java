package com.muteng.dgjs.common.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
/**
 * 服务器请求工具类
* @author ZC  
* @date 2018年10月29日 下午10:48:06
* @version
 */
public class HttpClientUtil {

	public static String postXML(String url, String xml) throws Exception{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		StringEntity myEntity = new StringEntity(xml, "UTF-8");
		httpPost.addHeader("Content-Type", "application/xml");
		httpPost.setEntity(myEntity);
		CloseableHttpResponse response = httpClient.execute(httpPost);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity).trim();
		}
		response.close();
		httpClient.close();
		return null;
	}
}
