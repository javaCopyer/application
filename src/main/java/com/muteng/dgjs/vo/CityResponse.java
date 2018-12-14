package com.muteng.dgjs.vo;

import java.util.List;

import lombok.Data;

@Data
public class CityResponse {
	private String code;
	private List<City> citys;
	@Data
	
	public class City {
		private String name;
		private Long provinceid;
		private Long cityid;
		private String hot;
		private String initial;
	}
}
