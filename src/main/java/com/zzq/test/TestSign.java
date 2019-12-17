package com.zzq.test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public class TestSign {
    public static void main(String[] args) {
       String yunscsginkey = "q781cXa9Z8hp0Z7aJL8ssG5oiP1R9nA7";
       String yunsckey = "aV2woc00FmtOi8HCLsbPMQD0iih0vQ3h";


////        c738680e7f66a6ba9c008a7956211d2e8bbbc5f776e0e90380ed77ce356747b4,1575033647
////        0401a0f82145b72df6a27f83efae44d2909cb2e1cf045bca6a7d9920811b7354,1575033647
////        943167d7c14ea6c589728a3329c9984af7ed0e03980743a5a79b6d1a4f78f98a,1575033647
////        String aa = HmacSHA256.getHmacSHA256("1575033647"+""+"[x=JvbEFcInsisbp757]" + "ae4207657e41a6a47b3bcd748ac77746", "yunscsginkey");
////        String aa = HmacSHA256.getHmacSHA256("1575033647"+""+"x=JvbEFcInsisbp757&" + "ae4207657e41a6a47b3bcd748ac77746", "yunscsginkey");
////        String aa = HmacSHA256.getHmacSHA256("1575033647"+""+"x=JvbEFcInsisbp757" + "ae4207657e41a6a47b3bcd748ac77746", "yunscsginkey");
//        String aa = HmacSHA256.getHmacSHA256("1575033647"+ "password=475546259&username=18108303047"+"ae4207657e41a6a47b3bcd748ac77746", "q781cXa9Z8hp0Z7aJL8ssG5oiP1R9nA7");
////        String aa = HmacSHA256.getHmacSHA256("1575033647", "yunscsginkey");
//        System.out.println(aa);
//        aa = HmacSHA256.getHmacSHA256("1575033647"+"ae4207657e41a6a47b3bcd748ac77746", "q781cXa9Z8hp0Z7aJL8ssG5oiP1R9nA7");
////        String aa = HmacSHA256.getHmacSHA256("1575033647", "yunscsginkey");
//        System.out.println(aa);
//
////        fb3c7efe876e781007a4ac8e1a2ffd9dd199f639176a4b4f24a4d6b280bac417,1575033619
//        String bb = HmacSHA256.getHmacSHA256("1575033619"+ "test="+"ae4207657e41a6a47b3bcd748ac77746", "q781cXa9Z8hp0Z7aJL8ssG5oiP1R9nA7");
////        String aa = HmacSHA256.getHmacSHA256("1575033647", "yunscsginkey");
//        System.out.println(bb);
//        bb = HmacSHA256.getHmacSHA256("1575033647"+"ae4207657e41a6a47b3bcd748ac77746", "aV2woc00FmtOi8HCLsbPMQD0iih0vQ3h");
////        String aa = HmacSHA256.getHmacSHA256("1575033647", "yunscsginkey");
//        System.out.println(bb);
////        String bb = HmacSHA256.getHmacSHA256("1571965090" + "CEC30CB8-5104-4D79-8AE7-80A5305C6D50", "yunsckey");
////        System.out.println(bb);
////        String cc = HmacSHA256.getHmacSHA256("1571965090" + "CEC30CB8-5104-4D79-8AE7-80A5305C6D50", "nb.fragment.title.find");
////        DomDemo d = new DomDemo();
////        List<String> keys = d.parserXml("F:\\idea workspace\\yunshanchong\\src\\main\\resources\\public.xml");
////        for (int i=0;i<keys.size();i++){
////            String ss = HmacSHA256.getHmacSHA256("1571965090" +""+ "CEC30CB8-5104-4D79-8AE7-80A5305C6D50", keys.get(i)+"");
//////            String ss = HmacSHA256.getHmacSHA256(1571965090+"" , keys.get(i).toString()+"");
//////            System.out.println(ss);
////            if(ss.equals("815be9c5e4cc366c56d072fe1c70b806155dc513bf3c383cd5f17382d312ae68")){
////                System.out.println(ss);
////            }
////        }
//
////        System.out.println(cc);
////        System.out.println("a3ef8c798f34c23d4b699cea24d79fe0bd2d337343e8945e76fe41e7ecd3eb6e".length());
////        System.out.println("5f22e2f39c01bbee901e550e19a009b6ba7365da74b8617f7504ee14fb2a3d7d".length());
        System.out.println(System.currentTimeMillis()/1000);
    }
    public static String aa(String a,String b){
        return a+b;
    }


}
