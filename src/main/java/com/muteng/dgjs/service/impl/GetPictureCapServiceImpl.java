package com.muteng.dgjs.service.impl;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.muteng.dgjs.service.GetPictureCapService;

@Service
public class GetPictureCapServiceImpl implements GetPictureCapService{

	@Override
	public String getPicture() {
		
		return random();
	}

	private String random(){
		String str="0123456789";
		StringBuilder sb=new StringBuilder(4);
		for(int i=0;i<4;i++)
		{
		char ch=str.charAt(new Random().nextInt(str.length()));
		sb.append(ch);
		}
		return sb.toString();
	}
}
