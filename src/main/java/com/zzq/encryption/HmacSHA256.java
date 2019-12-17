package com.zzq.encryption;


import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HmacSHA256 {
    public static String getHmacSHA256(String str, String str2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), "HmacSHA256");
            Mac instance = Mac.getInstance("HmacSHA256");
            instance.init(secretKeySpec);
            byte[] doFinal = instance.doFinal(str.getBytes());
            StringBuilder sb = new StringBuilder(doFinal.length * 2);
            int length = doFinal.length;
            for (int i = 0; i < length; i++) {
                sb.append(String.format("%02x", new Object[]{Integer.valueOf(doFinal[i] & 255)}));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        } catch (InvalidKeyException e2) {
            e2.printStackTrace();
            return "";
        }
    }
}