package com.zzq.util;

import com.alibaba.fastjson.JSONObject;
import com.zzq.encryption.HmacSHA256;
import com.zzq.encryption.XXTEA;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;

/*
 * 薅羊毛的单元
 */
public class HttpClientUtil2 {
    String yunscsginkey = "q781cXa9Z8hp0Z7aJL8ssG5oiP1R9nA7";
    String yunsckey = "aV2woc00FmtOi8HCLsbPMQD0iih0vQ3h";
    String Authorization = "";
    private static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(HttpClientUtil2.class);
    private HttpClient httpClient = null;
    public List<String> cookies =new ArrayList<String>();
    HttpHost target = new HttpHost("127.0.0.1", 8888,  "http");
    public HttpHost getTarget() {
        return target;
    }
    public void setTarget(HttpHost target) {
        this.target = target;
    }

    Date date = Calendar.getInstance().getTime();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
    public String cnUserID ;
    public String deviceID ;
    public String user_agent;
    public String getUser_agent() {
        return user_agent;
    }
    public void setUser_agent(String user_agent) {
        this.user_agent = user_agent;
    }
    public String getDeviceID() {
        return deviceID;
    }
    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }
    public String getCnUserID() {
        return cnUserID;
    }
    public void setCnUserID(String cnUserID) {
        this.cnUserID = cnUserID;
    }
    public HttpClientUtil2(){
        try {
            this.httpClient =  new SSLClient();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public String doPost(String url,Map<String,String> map,String charset){
//        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try{
//            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            // 设置通用的请求属性
            httpPost.addHeader("Accept","*/*");
            httpPost.addHeader("Accept-Encoding","gzip, deflate");
            httpPost.addHeader("Accept-Language","zh-Hans-HK;q=1");
            httpPost.addHeader("User-Agent","YshanChong/2.0.2 (iPhone; iOS 12.4.1; Scale/3.00)");
            //post的通用方式設置
            httpPost.addHeader("Content-Type","application/x-www-form-urlencoded");
            //transport的通用設置
            httpPost.addHeader("Connection","keep-alive");
            httpPost.addHeader("Connection","keep-alive");
            httpPost.addHeader("Host","apis.shanchong.mobi");
            //用户的静态头属性
            httpPost.addHeader("X-ECAPI-Authorization",Authorization);
            httpPost.addHeader("X-ECAPI-IMEI","5497D697-0552-4342-8E71-7044D2544C7C");
            httpPost.addHeader("X-ECAPI-SerialNumber","CEC30CB8-5104-4D79-8AE7-91B6416D7E61");
            httpPost.addHeader("X-ECAPI-UDID","xmYE1GvReR9j2t5FIitvqX43ekGIxEt2");
            httpPost.addHeader("X-ECAPI-UserAgent","Platform/iOS, Device/iPhone, Lang/zh-Hans-HK, ScreenWidth/414.000000, ScreenHeight/736.000000, timestamp/1571715984");
            httpPost.addHeader("X-ECAPI-Ver","2.0.2");
            //动态签名头属性
            Long currentTimeStr  = System.currentTimeMillis()/1000;
            String str = "";
            if(map.size()>0){
                str = XXTEA.decryptBase64StringToString(map.get("x"),"aV2woc00FmtOi8HCLsbPMQD0iih0vQ3h");
            }
            String signStr =  HmacSHA256.getHmacSHA256(currentTimeStr+ str, yunscsginkey)+","+currentTimeStr;
            httpPost.addHeader("X-ECAPI-Sign",signStr);

            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while(iterator.hasNext()){
                Entry<String,String> elem = (Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
            }
            if(list.size() > 0){
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);
                httpPost.setEntity(entity);
            }
            for(int i=0;i<cookies.size();i++){
                httpPost.addHeader("Cookie", cookies.get(i));
            }
            HttpResponse response;
            if(target==null){
                response = httpClient.execute(httpPost);
            }else{
                response = httpClient.execute(target,httpPost);
            }


//
            if(response != null){
//            	System.out.println(response.getAllHeaders()[0].getName());
                for(Header header:response.getAllHeaders() ){
//            		System.out.println(header.getName()+"="+header.getValue());
                    if(header.getName().equalsIgnoreCase("Set-Cookie")){
                        cookies.add(header.getValue());
                    }
                }
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,charset);
//                    System.out.println(result);
                    String data = JSONObject.parseObject(result).getString("data");
//                    System.out.println(data);
                    if(data!=null){
                        String decodeStr = XXTEA.decryptBase64StringToString(data,"aV2woc00FmtOi8HCLsbPMQD0iih0vQ3h");
                        System.out.println("返回data解码后："+decodeStr);
                        if(JSONObject.parseObject(decodeStr).getString("token")!=null){
                            Authorization = JSONObject.parseObject(decodeStr).getString("token");
                        }
                    }
                    httpPost.releaseConnection();
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
            httpPost.releaseConnection();
        }
        return result;
    }


    public String doFormPost(String url,Map<String,String> map,String charset){
        HttpPost httpPost = null;
        String result = null;
        try{
            httpPost = new HttpPost(url);
            // 设置通用的请求属性
            httpPost.addHeader("accept","application/json");
            httpPost.addHeader("Accept-Encoding","gzip");
            httpPost.addHeader("user-agent",user_agent);
//          httpPost.addHeader("Content-Length","556");
            httpPost.addHeader("Content-Type","multipart/form-data; boundary=d8067f7c-133d-4ec6-94ae-ae1da7c45225");
            httpPost.addHeader("accept-secret","c9373cf0994ac5dc1ef3827698881cd76865b84020f2dc5e5eb54c52ba1a4baa");
            httpPost.addHeader("accept-time",sdf.format(date));
            httpPost.addHeader("cnos","android");
            httpPost.addHeader("cnpid","cainiaolc");
            httpPost.addHeader("cnuser",cnUserID);
            httpPost.addHeader("cnver","V1");
            httpPost.addHeader("cnversion","1.1.8");
            httpPost.addHeader("deviceid",deviceID);
            httpPost.addHeader("Connection","Keep-Alive");
            httpPost.addHeader("Host","app.cainiaolc.com");

            MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE,"d8067f7c-133d-4ec6-94ae-ae1da7c45225", Charset.defaultCharset());
            multipartEntity.addPart("content", new StringBody(map.get("content"), Charset.forName("UTF-8")));
            multipartEntity.addPart("category", new StringBody("p2p", Charset.forName("UTF-8")));
            multipartEntity.addPart("cateId", new StringBody("225410", Charset.forName("UTF-8")));
            multipartEntity.addPart("upload", new StringBody("0", Charset.forName("UTF-8")));
            httpPost.setEntity(multipartEntity);
            HttpResponse response;
            if(target==null){
                response = httpClient.execute(httpPost);
            }else{
                response = httpClient.execute(target,httpPost);
            }
            if(response != null){
                for(Header header:response.getAllHeaders() ){
                    if(header.getName().equalsIgnoreCase("Set-Cookie")){
                        cookies.add(header.getValue());
                    }
                }
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,charset);
//                  System.out.println(result);
                    httpPost.releaseConnection();
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
            httpPost.releaseConnection();
        }
        return result;
    }

    public String doGet(String url,String charset){
//      HttpClient httpClient = null;
        HttpGet httpGet = null;
        String result = null;
        try{
//          httpClient = new SSLClient();
            httpGet = new HttpGet(url);

            // 设置通用的请求属性
//    	  httpGet.addHeader("accept","application/json");
//    	  httpGet.addHeader("Accept-Encoding","gzip");
            httpGet.addHeader("user-agent",user_agent);
//    	  httpGet.addHeader("Content-Length","95");
//    	  httpGet.addHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
            httpGet.addHeader("accept-secret","8eb478d177537ade7587bfafd52b1c9299e46c0a3881877469e6a5276e2a68d2");
//    	  accept-secret: 60f1aef485e6944df9954893f5d23f3cf830e834ac0dd95de580561c17e3dbaf
            httpGet.addHeader("accept-time",sdf.format(date));
            httpGet.addHeader("cnos","android");
            httpGet.addHeader("cnpid","cainiaolc");
            httpGet.addHeader("cnuser",cnUserID);
            httpGet.addHeader("cnver","V1");
            httpGet.addHeader("cnversion","1.1.8");
            httpGet.addHeader("deviceid",deviceID);
            httpGet.addHeader("Connection","Keep-Alive");
            httpGet.addHeader("Host","app.cainiaolc.com");
            for(int i=0;i<cookies.size();i++){
                httpGet.addHeader("Cookie", cookies.get(i));
                System.out.println("已有cookie=="+cookies.get(i));
            }

            //璁剧疆鍙傛暟
            HttpResponse response;
            if(target==null){
                response = httpClient.execute(httpGet);
            }else{
                response = httpClient.execute(target,httpGet);
            }
            if(response != null){
                for(Header header:response.getAllHeaders() ){
//        		System.out.println(header.getName()+"="+header.getValue());
                    if(header.getName().equalsIgnoreCase("Set-Cookie")){
                        cookies.add(header.getValue());
                        System.out.println("cookie=="+header.getValue());
                    }
                }
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,charset);
                    httpGet.releaseConnection();
//                  System.out.println(result);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
            httpGet.releaseConnection();
        }
        return result;
    }



    public String doQuTouTiaoGet(String url,String charset){
//      HttpClient httpClient = null;
        HttpGet httpGet = null;
        String result = null;
        try{
//          httpClient = new SSLClient();
            httpGet = new HttpGet(url);

            // 设置通用的请求属性
            httpGet.addHeader("Accept-Encoding","gzip");
            httpGet.addHeader("user-agent","qukan_android");
            httpGet.addHeader("Connection","Keep-Alive");
            httpGet.addHeader("Host","api.1sapp.com");
            for(int i=0;i<cookies.size();i++){
                httpGet.addHeader("Cookie", cookies.get(i));
                System.out.println("已有cookie=="+cookies.get(i));
            }

            //璁剧疆鍙傛暟
            HttpResponse response;
            if(target==null){
                response = httpClient.execute(httpGet);
            }else{
                response = httpClient.execute(target,httpGet);
            }
            if(response != null){
                for(Header header:response.getAllHeaders() ){
//        		System.out.println(header.getName()+"="+header.getValue());
                    if(header.getName().equalsIgnoreCase("Set-Cookie")){
                        cookies.add(header.getValue());
                        System.out.println("cookie=="+header.getValue());
                    }
                }
                HttpEntity resEntity = response.getEntity();
                InputStream is = resEntity.getContent();
                try{
                    GZIPInputStream gzin = new GZIPInputStream(is);
                    InputStreamReader isr = new InputStreamReader(gzin,"utf-8");
                    result = Utils.getStringFromInputStreamReader(isr);
                }catch (IOException exception){
                    LOGGER.error(exception);
                }
                httpGet.releaseConnection();
            }
        }catch(Exception ex){
            ex.printStackTrace();
            httpGet.releaseConnection();
        }
        return result;
    }
    /**
     * 趣头条的post请求
     * @param url
     * @param map
     * @param charset
     * @return
     */
    public String doQuTouTiaoPost(String url,Map<String,String> map,String charset){
//      HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try{
//          httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            // 设置通用的请求属性
            httpPost.addHeader("Accept-Encoding","gzip");
            httpPost.addHeader("user-agent","qukan_android");
            httpPost.addHeader("Connection","Keep-Alive");
            httpPost.addHeader("Host","api.1sapp.com");
//          httpPost.addHeader("Content-Length", "556");
            httpPost.addHeader("Content-Type","application/x-www-form-urlencoded");
            //璁剧疆鍙傛暟
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while(iterator.hasNext()){
                Entry<String,String> elem = (Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
            }
            if(list.size() > 0){
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);
                httpPost.setEntity(entity);
            }
            for(int i=0;i<cookies.size();i++){
                httpPost.addHeader("Cookie", cookies.get(i));
            }
            HttpResponse response;
            if(target==null){
                response = httpClient.execute(httpPost);
            }else{
                response = httpClient.execute(target,httpPost);
            }


//
            if(response != null){
//          	System.out.println(response.getAllHeaders()[0].getName());
                for(Header header:response.getAllHeaders() ){
//          		System.out.println(header.getName()+"="+header.getValue());
                    if(header.getName().equalsIgnoreCase("Set-Cookie")){
                        cookies.add(header.getValue());
                    }
                }
                HttpEntity resEntity = response.getEntity();
                InputStream is = resEntity.getContent();
                try{
                    GZIPInputStream gzin = new GZIPInputStream(is);
                    InputStreamReader isr = new InputStreamReader(gzin,"utf-8");
                    result = Utils.getStringFromInputStreamReader(isr);
                }catch (IOException exception){
                    LOGGER.error(exception);
                }
                httpPost.releaseConnection();
            }
        }catch(Exception ex){
            ex.printStackTrace();
            httpPost.releaseConnection();
        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println("中文");
    }


}