package com.muteng.dgjs.common.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.muteng.dgjs.common.constant.OssConstants;

public class OssUtil {
	public static String uploadInputstreamToOSS(InputStream inputstream, String key) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		String keys = year + "/" + month + "/" + day + "/" + key;
		return uploadFileToOSS(inputstream, keys);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianwu.service.intf.CommonIntf#uploadFileToOSS(java.io.InputStream,
	 *      java.lang.String) 上传文件到oss 返回oss存储文件路径
	 */
	public static String uploadFileToOSS(InputStream inputstream, String key) {
		if (inputstream == null) {
			throw new RuntimeException("文件为空");
		}
		OSSClient client = new OSSClient(OssConstants.aliOssendpoint, OssConstants.aliOssAccessKeyId,
				OssConstants.aliOssAccessKeySecret);
		client.putObject(OssConstants.aliOssbucket, key, inputstream);
		client.shutdown();
		String url = OssConstants.aliOssUrl + "/" + key;
		return url;
	}
	
	/**
	 * 判断oss上是否有该对象
	 */
	public static boolean doesFileExistToOSS(String key) {
		// 创建OSSClient实例
		OSSClient client = new OSSClient(OssConstants.aliOssendpoint, OssConstants.aliOssAccessKeyId,
				OssConstants.aliOssAccessKeySecret);
		// Object是否存在
		boolean found = client.doesObjectExist(OssConstants.aliOssbucket, key);
		// 关闭client
		client.shutdown();
		return found;
	}
	
	/**
	 * 从oss下载文件， 返回inputStream
	 */
	public static String downloadFileToOSS(String key) {
		OSSClient client = new OSSClient(OssConstants.aliOssendpoint, OssConstants.aliOssAccessKeyId,
				OssConstants.aliOssAccessKeySecret);
		OSSObject ossObject = client.getObject(OssConstants.aliOssbucket, key);
		// InputStream inputStream = ossObject.getObjectContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));
		String line;
		StringBuffer result = new StringBuffer();
		while (true) {
			try {
				line = reader.readLine();

				if (line == null)
					break;
				result.append(line);
				System.out.println("\n" + line);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		client.shutdown();
		return result.toString();
	}
	
	/**
	 * 读本地文件，上传oss公用方法
	 * @param paramMap
	 * @return
	 */
	public static String uploadInputstream(List<String> contentList, String key) {

		StringBuffer content = new StringBuffer();
		for (String str : contentList) {
			content = content.append(str + "\n");
		}
		InputStream input = new ByteArrayInputStream(content.toString().getBytes());

		return uploadFileToOSS(input, key);
	}
}
