package com.muteng.dgjs.common.utils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class HttpUtils {
	
	/**
	 * get
	 * 
	 * @param host
	 * @param path
	 * @param method
	 * @param headers
	 * @param querys
	 * @return
	 * @throws Exception
	 */
	public static HttpResponse doGet(String host, String path, String method, 
			Map<String, String> headers, 
			Map<String, String> querys)
            throws Exception {    	
    	HttpClient httpClient = wrapClient(host);

    	HttpGet request = new HttpGet(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
        	request.addHeader(e.getKey(), e.getValue());
        }
        
        return httpClient.execute(request);
    }
	
	/**
	 * post form
	 * 
	 * @param host
	 * @param path
	 * @param method
	 * @param headers
	 * @param querys
	 * @param bodys
	 * @return
	 * @throws Exception
	 */
	public static HttpResponse doPost(String host, String path, String method, 
			Map<String, String> headers, 
			Map<String, String> querys, 
			Map<String, String> bodys)
            throws Exception {    	
    	HttpClient httpClient = wrapClient(host);

    	HttpPost request = new HttpPost(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
        	request.addHeader(e.getKey(), e.getValue());
        }

        if (bodys != null) {
            List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();

            for (String key : bodys.keySet()) {
                nameValuePairList.add(new BasicNameValuePair(key, bodys.get(key)));
            }
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nameValuePairList, "utf-8");
            formEntity.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
            request.setEntity(formEntity);
        }

        return httpClient.execute(request);
    }	
	
	/**
	 * Post String
	 * 
	 * @param host
	 * @param path
	 * @param method
	 * @param headers
	 * @param querys
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public static HttpResponse doPost(String host, String path, String method, 
			Map<String, String> headers, 
			Map<String, String> querys, 
			String body)
            throws Exception {    	
    	HttpClient httpClient = wrapClient(host);

    	HttpPost request = new HttpPost(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
        	request.addHeader(e.getKey(), e.getValue());
        }

        if (StringUtils.isNotBlank(body)) {
        	request.setEntity(new StringEntity(body, "utf-8"));
        }

        return httpClient.execute(request);
    }
	
	/**
	 * Post stream
	 * 
	 * @param host
	 * @param path
	 * @param method
	 * @param headers
	 * @param querys
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public static HttpResponse doPost(String host, String path, String method, 
			Map<String, String> headers, 
			Map<String, String> querys, 
			byte[] body)
            throws Exception {    	
    	HttpClient httpClient = wrapClient(host);

    	HttpPost request = new HttpPost(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
        	request.addHeader(e.getKey(), e.getValue());
        }

        if (body != null) {
        	request.setEntity(new ByteArrayEntity(body));
        }

        return httpClient.execute(request);
    }
	
	/**
	 * Put String
	 * @param host
	 * @param path
	 * @param method
	 * @param headers
	 * @param querys
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public static HttpResponse doPut(String host, String path, String method, 
			Map<String, String> headers, 
			Map<String, String> querys, 
			String body)
            throws Exception {    	
    	HttpClient httpClient = wrapClient(host);

    	HttpPut request = new HttpPut(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
        	request.addHeader(e.getKey(), e.getValue());
        }

        if (StringUtils.isNotBlank(body)) {
        	request.setEntity(new StringEntity(body, "utf-8"));
        }

        return httpClient.execute(request);
    }
	
	/**
	 * Put stream
	 * @param host
	 * @param path
	 * @param method
	 * @param headers
	 * @param querys
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public static HttpResponse doPut(String host, String path, String method, 
			Map<String, String> headers, 
			Map<String, String> querys, 
			byte[] body)
            throws Exception {    	
    	HttpClient httpClient = wrapClient(host);

    	HttpPut request = new HttpPut(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
        	request.addHeader(e.getKey(), e.getValue());
        }

        if (body != null) {
        	request.setEntity(new ByteArrayEntity(body));
        }

        return httpClient.execute(request);
    }
	
	/**
	 * Delete
	 *  
	 * @param host
	 * @param path
	 * @param method
	 * @param headers
	 * @param querys
	 * @return
	 * @throws Exception
	 */
	public static HttpResponse doDelete(String host, String path, String method, 
			Map<String, String> headers, 
			Map<String, String> querys)
            throws Exception {    	
    	HttpClient httpClient = wrapClient(host);

    	HttpDelete request = new HttpDelete(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
        	request.addHeader(e.getKey(), e.getValue());
        }
        
        return httpClient.execute(request);
    }
	
	private static String buildUrl(String host, String path, Map<String, String> querys) throws UnsupportedEncodingException {
    	StringBuilder sbUrl = new StringBuilder();
    	sbUrl.append(host);
    	if (!StringUtils.isBlank(path)) {
    		sbUrl.append(path);
        }
    	if (null != querys) {
    		StringBuilder sbQuery = new StringBuilder();
        	for (Map.Entry<String, String> query : querys.entrySet()) {
        		if (0 < sbQuery.length()) {
        			sbQuery.append("&");
        		}
        		if (StringUtils.isBlank(query.getKey()) && !StringUtils.isBlank(query.getValue())) {
        			sbQuery.append(query.getValue());
                }
        		if (!StringUtils.isBlank(query.getKey())) {
        			sbQuery.append(query.getKey());
        			if (!StringUtils.isBlank(query.getValue())) {
        				sbQuery.append("=");
        				sbQuery.append(URLEncoder.encode(query.getValue(), "utf-8"));
        			}        			
                }
        	}
        	if (0 < sbQuery.length()) {
        		sbUrl.append("?").append(sbQuery);
        	}
        }
    	
    	return sbUrl.toString();
    }
	
	private static HttpClient wrapClient(String host) {
		HttpClient httpClient = new DefaultHttpClient();
		if (host.startsWith("https://")) {
			sslClient(httpClient);
		}
		
		return httpClient;
	}
	
	@SuppressWarnings("all")
	private static void sslClient(HttpClient httpClient) {
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] xcs, String str) {
                	
                }
                public void checkServerTrusted(X509Certificate[] xcs, String str) {
                	
                }
            };
            ctx.init(null, new TrustManager[] { tm }, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx);
            ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm = httpClient.getConnectionManager();
            SchemeRegistry registry = ccm.getSchemeRegistry();
            registry.register(new Scheme("https", 443, ssf));
        } catch (KeyManagementException ex) {
            throw new RuntimeException(ex);
        } catch (NoSuchAlgorithmException ex) {
        	throw new RuntimeException(ex);
        }
    }
	public static void main(String[] args)throws Exception {
//	    String host = "https://dm-51.data.aliyun.com";
//	    String path = "/rest/160601/ocr/ocr_idcard.json";
//	    String method = "POST";
//	    Map<String, String> headers = new HashMap<String, String>();
//	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
//	    headers.put("Authorization", "APPCODE 963c93cef90c410b8cdd46e32308961d");
//	    Map<String, String> querys = new HashMap<String, String>();
//	    String img = "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";
//	    String bodys = "{\"inputs\":[{\"image\":{\"dataType\":50,\"dataValue\":\""+img+"\"},\"configure\":{\"dataType\":50,\"dataValue\":\"{\\\"side\\\":\\\"face\\\"}\"}}]}";
//
//	    try {
//	    	/**
//	    	* 重要提示如下:
//	    	* HttpUtils请从
//	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
//	    	* 下载
//	    	*
//	    	* 相应的依赖请参照
//	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
//	    	*/
//	    	HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
////	    	HttpEntity en = response.getEntity();
////	    	System.out.println(en.getContent());
//	    	//获取response的body
//	    	System.out.println(response);
//	    	String str = EntityUtils.toString(response.getEntity());
//	    	System.out.println(str);
//	    	Gson json = new GsonBuilder().serializeNulls().create();
//	    	IDcardInfo m = json.fromJson(str, IDcardInfo.class);
//	    	Map map = (Map)m.getOutputs().get(0);
//	    	OutputValue ov = json.fromJson(map.get("outputValue").toString(),OutputValue.class);
//			System.out.println(ov.getDataValue().getAddress());
//			System.out.println(json.toJson(ov.getDataValue()));
//	    } catch (Exception e) {
//	    	e.printStackTrace();
//	    }
	  
	}

    public static String doGet(String url, Map<String, String> param) {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static String doGet(String url) {
        return doGet(url, null);
    }


}