package com.zzq.test;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HmacSHA256 {

    public static String getHmacSHA256(String paramString1, String paramString2) {
        try
        {
            SecretKeySpec paramString22 = new SecretKeySpec(paramString2.getBytes(), "HmacSHA256");
            Mac localMac = Mac.getInstance("HmacSHA256");
//            Mac localMac = Mac.getInstance(paramString22.getAlgorithm());
            localMac.init(paramString22);
            byte[] paramString11 = localMac.doFinal(paramString1.getBytes());
            StringBuilder paramString222 = new StringBuilder(paramString11.length * 2);
            int j = paramString11.length;
            int i = 0;
            while (i < j)
            {
                paramString222.append(String.format("%02x", new Object[] { Integer.valueOf(paramString11[i] & 0xFF) }));
                i += 1;
            }
            String retstr = paramString222.toString();
            return retstr;
        }
        catch (NoSuchAlgorithmException paramString11)
        {
            paramString11.printStackTrace();
            return "";
        }
        catch (InvalidKeyException paramString11)
        {
            for (;;)
            {
                paramString11.printStackTrace();
            }
        }
    }
}
