package com.muteng.dgjs.handler;



import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * json响应值处理  带过滤字段
 * 使用@RestController或者@ResponseBody时选取了RequestResponseBodyMethodProcessor作为返回值的处理方法
 * 则这个handler则不会被执行，使用此handler则不能使用@ResponseBody注解
* @author ZC  
* @date 2018年10月27日 上午12:06:08
* @version
 */
public class JsonReturnHandler implements HandlerMethodReturnValueHandler{

	@Override
	public boolean supportsReturnType(MethodParameter returnType) {
		boolean hasAnnotion = returnType.getMethodAnnotation(JsonFilter.class) != null;
		return hasAnnotion;
	}

	@Override
	public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest) throws Exception {
		 // 设置这个就是最终的处理类了，处理完不再去找下一个类进行处理
        mavContainer.setRequestHandled(true);
        
        // 获得注解并执行filter方法 最后返回
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        Annotation[] annos = returnType.getMethodAnnotations();
        String[] filter = {};
        for (Annotation annotation : annos) {
			if (annotation instanceof JsonFilter) {
				JsonFilter jsonFilter = (JsonFilter) annotation;
				filter = jsonFilter.filter();
			}
		}
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        String json = JSON.toJSONString(returnValue, new FastJsonFilter(filter), SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty,
				SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteNullNumberAsZero,
				SerializerFeature.WriteNullListAsEmpty, SerializerFeature.DisableCircularReferenceDetect);
        response.getWriter().write(json);		
	}

}
