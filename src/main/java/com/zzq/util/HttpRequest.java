package com.zzq.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class HttpRequest {
	public static URLConnection connection;
    /**
     * ��ָ��URL����GET����������
     * 
     * @param url
     *            ���������URL
     * @param param
     *            ����������������Ӧ���� name1=value1&name2=value2 ����ʽ��
     * @return URL ������Զ����Դ����Ӧ���
     */
    public static String sendGet(URLConnection connection,String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // �򿪺�URL֮�������
            connection = realUrl.openConnection();
            // ����ͨ�õ���������
            connection.setRequestProperty("accept","application/json");
            connection.setRequestProperty("Accept-Encoding","gzip");
            connection.setRequestProperty("user-agent","Dalvik/2.1.0 (Linux; U; Android 6.0.1; Redmi 4A MIUI/V8.5.4.0.MCCCNED)");
            connection.setRequestProperty("Content-Length","95");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
//            connection.setRequestProperty("accept-secret","1ab3a18b89fe064caad6fc4138a2adfdef48a898ecfa078c46b9c2bfd0202d0d");
            connection.setRequestProperty("accept-time","20170102101022");
            connection.setRequestProperty("cnos","android");
            connection.setRequestProperty("cnpid","cainiaolc");
//            connection.setRequestProperty("cnuser","0");
            connection.setRequestProperty("cnver","V1");
            connection.setRequestProperty("cnversion","1.1.8");
            connection.setRequestProperty("deviceid","31d2b13db676f532");
            connection.setRequestProperty("Connection","Keep-Alive");
            connection.setRequestProperty("Host","app.cainiaolc.com");
            
            // ����ʵ�ʵ�����
            connection.connect();
            // ��ȡ������Ӧͷ�ֶ�
            Map<String, List<String>> map = connection.getHeaderFields();
            // �������е���Ӧͷ�ֶ�
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // ���� BufferedReader����������ȡURL����Ӧ
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("����GET��������쳣��" + e);
            e.printStackTrace();
        }
        // ʹ��finally�����ر�������
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * ��ָ�� URL ����POST����������
     * 
     * @param url
     *            ��������� URL
     * @param param
     *            ����������������Ӧ���� name1=value1&name2=value2 ����ʽ��
     * @return ������Զ����Դ����Ӧ���
     */
    public static String sendPost(URLConnection connection,String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // �򿪺�URL֮�������
            connection = realUrl.openConnection();
            // ����ͨ�õ���������
            connection.setRequestProperty("accept","application/json");
            connection.setRequestProperty("Accept-Encoding","gzip");
            connection.setRequestProperty("user-agent","Dalvik/2.1.0 (Linux; U; Android 6.0.1; Redmi 4A MIUI/V8.5.4.0.MCCCNED)");
            connection.setRequestProperty("Content-Length","95");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
//            connection.setRequestProperty("accept-secret","1ab3a18b89fe064caad6fc4138a2adfdef48a898ecfa078c46b9c2bfd0202d0d");
            connection.setRequestProperty("accept-time","20170102101022");
            connection.setRequestProperty("cnos","android");
            connection.setRequestProperty("cnpid","cainiaolc");
//            connection.setRequestProperty("cnuser","0");
            connection.setRequestProperty("cnver","V1");
            connection.setRequestProperty("cnversion","1.1.8");
            connection.setRequestProperty("deviceid","31d2b13db676f532");
            connection.setRequestProperty("Connection","Keep-Alive");
            connection.setRequestProperty("Host","app.cainiaolc.com");
            // ����POST�������������������
            connection.setDoOutput(true);
            connection.setDoInput(true);
            // ��ȡURLConnection�����Ӧ�������
            out = new PrintWriter(connection.getOutputStream());
            // �����������
            out.print(param);
            // flush������Ļ���
            out.flush();
            // ����BufferedReader����������ȡURL����Ӧ
            in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("���� POST ��������쳣��"+e);
            e.printStackTrace();
        }
        //ʹ��finally�����ر��������������
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    } 
//    public static void main(String[] args) {
//        //���� GET ����
//        String s=HttpRequest.sendGet("http://www.baidu.com", "key=123&v=456");
//        System.out.println(s);
//        
//        //���� POST ����
//        String sr=HttpRequest.sendPost("http://www.baidu.com", "key=123&v=456");
//        System.out.println(sr);
//    }
}