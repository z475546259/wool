package com.zzq;

import com.zzq.dto.YSCUser;
import com.zzq.encryption.XXTEA;
import com.zzq.util.HttpClientUtil;
import com.zzq.util.HttpClientUtil2;

import java.util.HashMap;
import java.util.Map;

public class Processs {
    public static void main(String[] args) {




    }

//    public void flow(YSCUser user){
//        HttpClientUtil httpClientUtil = new HttpClientUtil();
//        Map<String,String> map = new HashMap<>();
//        map.put("x","LGS/v8iUW7UoAk7V");
//        httpClientUtil.doPost("http://api.shanchong.mobi/v2/ecapi.order.broadcast",map,"utf-8");
//        httpClientUtil.doPost("http://api.shanchong.mobi/v2/ecapi.splash.list",map,"utf-8");
//        map.clear();
//        map.put("x","LGS/v8iUW7UoAk7V");
//        httpClientUtil.doPost("http://api.shanchong.mobi/v2/ecapi.member.wallet.total",map,"utf-8");
//        map.clear();
//        map.put("x","JvbEFcInsisbp757");
//        httpClientUtil.doPost("http://api.shanchong.mobi/v2/ecapi.user.profile.get",map,"utf-8");
//        httpClientUtil.doPost("http://api.shanchong.mobi/v2/ecapi.alipay.oauth",map,"utf-8");
//
//        //登录
//        map.clear();
//        String loginPara =  XXTEA.encryptToBase64String("password="+user.getUserPassWord()+"&username="+user.getUserPhone(),"aV2woc00FmtOi8HCLsbPMQD0iih0vQ3h");
//        map.put("x",loginPara);
//        httpClientUtil.doPost("http://api.shanchong.mobi/v2/ecapi.auth.signin",map,"utf-8");
//
//        map.clear();
//        map.put("x","JvbEFcInsisbp757");
//        httpClientUtil.doPost("http://api.shanchong.mobi/v2/ecapi.user.profile.get",map,"utf-8");
//
//        map.clear();
//        map.put("x","LGS/v8iUW7UoAk7V");
//        httpClientUtil.doPost("http://api.shanchong.mobi/v2/ecapi.member.wallet.total",map,"utf-8");
//
//        map.clear();
//        map.put("x","MKlO6c5BJBz/0ipR");
//        httpClientUtil.doPost("http://api.shanchong.mobi/v2/ecapi.signin.today.get",map,"utf-8");
//
//        map.clear();
//        map.put("x","MKlO6c5BJBz/0ipR");
//        httpClientUtil.doPost("http://api.shanchong.mobi/v2/ecapi.member.signin.do",map,"utf-8");
//
//        map.clear();
//        map.put("x","LGS/v8iUW7UoAk7V");
//        httpClientUtil.doPost("http://api.shanchong.mobi/v2/ecapi.member.wallet.total",map,"utf-8");
//
//        map.clear();
//        map.put("x","MKlO6c5BJBz/0ipR");
//        httpClientUtil.doPost("http://api.shanchong.mobi/v2/ecapi.signin.today.get",map,"utf-8");
//    }

    public void flow2(YSCUser user){
        HttpClientUtil2 httpClientUtil = new HttpClientUtil2();
        Map<String,String> map = new HashMap<>();
        httpClientUtil.doPost("http://apis.shanchong.mobi/v2/ecapi.version.check",map,"utf-8");
        //登录
        map.clear();
        String loginPara =  XXTEA.encryptToBase64String("password="+user.getUserPassWord()+"&username="+user.getUserPhone(),"aV2woc00FmtOi8HCLsbPMQD0iih0vQ3h");
        map.put("x",loginPara);
        httpClientUtil.doPost("http://apis.shanchong.mobi/v2/ecapi.auth.signin",map,"utf-8");

        map.clear();
        httpClientUtil.doPost("http://apis.shanchong.mobi/v2/ecapi.config.get",map,"utf-8");
        map.clear();
        map.put("x","+I5Jf2FIOXZyMZaWovnYoQ==");
        httpClientUtil.doPost("http://apis.shanchong.mobi/v2/ecapi.config.get",map,"utf-8");
        map.clear();
        httpClientUtil.doPost("http://apis.shanchong.mobi/v2/news.list",map,"utf-8");

        httpClientUtil.doPost("http://apis.shanchong.mobi/v2/ecapi.signin.today.get",map,"utf-8");



        map.clear();
        map.put("x","s7RKc+6voWRHiUL4AcylQA==");
        httpClientUtil.doPost("http://apis.shanchong.mobi/v2/ecapi.banners.list",map,"utf-8");

        map.clear();
        httpClientUtil.doPost("http://apis.shanchong.mobi/v2/ecapi.member.signin.do",map,"utf-8");


    }


}
