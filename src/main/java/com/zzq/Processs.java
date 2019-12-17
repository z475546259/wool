package com.zzq;

import com.zzq.util.HttpClientUtil;

import java.util.HashMap;
import java.util.Map;

public class Processs {
    public static void main(String[] args) {
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        Map<String,String> map = new HashMap<>();
        map.put("x","MKlO6c5BJBz/0ipR");
        httpClientUtil.doPost("http://api.shanchong.mobi/v2/ecapi.order.broadcast",map,"utf-8");
        httpClientUtil.doPost("http://api.shanchong.mobi/v2/ecapi.splash.list",map,"utf-8");
        map.clear();
        map.put("x","LGS/v8iUW7UoAk7V");
        httpClientUtil.doPost("http://api.shanchong.mobi/v2/ecapi.member.wallet.total",map,"utf-8");
        map.clear();
        map.put("x","JvbEFcInsisbp757");
        httpClientUtil.doPost("http://api.shanchong.mobi/v2/ecapi.user.profile.get",map,"utf-8");
        httpClientUtil.doPost("http://api.shanchong.mobi/v2/ecapi.alipay.oauth",map,"utf-8");

    }

    public void flow(){

    }
}
