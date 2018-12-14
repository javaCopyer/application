package com.muteng.dgjs.service.impl;

import com.muteng.dgjs.service.PicturesService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class PicturesImpt  implements PicturesService {
    @Override
    public Map<String, String> pictures(String workType) {
        Map<String,String> map = new HashMap<>();
        if(workType.equals("adv")){
            map.put("picture","https://lanzhipei.oss-cn-hangzhou.aliyuncs.com/advertisement/%E5%B9%BF%E5%91%8A%E9%A1%B5.jpg");
        }
        if(workType.equals("member")){
            map.put("picture","https://lanzhipei.oss-cn-hangzhou.aliyuncs.com/advertisement/%E6%88%90%E4%B8%BA%E4%BC%9A%E5%91%98%402x.png");
        }
        if (workType.equals("invite")){
            map.put("picture","https://lanzhipei.oss-cn-hangzhou.aliyuncs.com/advertisement/%E9%82%80%E8%AF%B7%E5%A5%BD%E5%8F%8B%402x.png");
        }
        return map;
    }
}
