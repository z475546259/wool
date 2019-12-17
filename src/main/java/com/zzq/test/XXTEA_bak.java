//package com.zzq.test;
////package mylibrary.encryption;
//
////import android.content.Context;
////import com.stub.StubApp;
//import java.io.UnsupportedEncodingException;
//
//public final class XXTEA_bak
//{
//    private static final int DELTA = -1640531527;
//
//    private static int MX(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int[] paramArrayOfInt)
//    {
//        return (paramInt1 ^ paramInt2) + (paramArrayOfInt[(paramInt4 & 0x3 ^ paramInt5)] ^ paramInt3) ^ (paramInt3 >>> 5 ^ paramInt2 << 2) + (paramInt2 >>> 3 ^ paramInt3 << 4);
//    }
//
//    public static byte[] decrypt(byte[] paramArrayOfByte, String paramString)
//    {
//        try
//        {
//            return decrypt(paramArrayOfByte, paramString.getBytes("UTF-8"));
//        }
//        catch (UnsupportedEncodingException a) { return null;}
//
//    }
//
//    public static byte[] decrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
//    {
//        if (paramArrayOfByte1.length == 0) {
//            return paramArrayOfByte1;
//        }
//        return toByteArray(decrypt(toIntArray(paramArrayOfByte1, false), toIntArray(fixKey(paramArrayOfByte2), false)), true);
//    }
//
//    private static int[] decrypt(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
//    {
//        int m = paramArrayOfInt1.length - 1;
//        if (m < 1) {
//            return paramArrayOfInt1;
//        }
//        int j = 52 / (m + 1);
//        int i = paramArrayOfInt1[0];
//        j = (j + 6) * -1640531527;
//        while (j != 0)
//        {
//            int n = j >>> 2 & 0x3;
//            int k = i;
//            i = m;
//            while (i > 0)
//            {
//                i1 = paramArrayOfInt1[(i - 1)];
//                k = paramArrayOfInt1[i] - MX(j, k, i1, i, n, paramArrayOfInt2);
//                paramArrayOfInt1[i] = k;
//                i -= 1;
//            }
//            int i1 = paramArrayOfInt1[m];
//            i = paramArrayOfInt1[0] - MX(j, k, i1, i, n, paramArrayOfInt2);
//            paramArrayOfInt1[0] = i;
//            j += 1640531527;
//        }
//        return paramArrayOfInt1;
//    }
//
//    public static byte[] decryptBase64String(String paramString1, String paramString2)
//    {
//        return decrypt(Base64.decode(paramString1), paramString2);
//    }
//
//    public static byte[] decryptBase64String(String paramString, byte[] paramArrayOfByte)
//    {
//        return decrypt(Base64.decode(paramString), paramArrayOfByte);
//    }
//
//    public static String decryptBase64StringToString(Context paramContext, String paramString1, String paramString2)
//    {
////        if (!"com.example.administrator.jspmall".equals(StubApp.getOrigApplicationContext(paramContext.getApplicationContext()).getPackageName())) {
////            System.exit(0);
////        }
//        try
//        {
//            paramContext = decrypt(Base64.decode(paramString1), paramString2);
//            if (paramContext == null) {
//                return null;
//            }
//            return new String(paramContext, "UTF-8");
//        }
//        catch (UnsupportedEncodingException paramContext) {}
//        return null;
//    }
//
//    public static String decryptBase64StringToString(String paramString, byte[] paramArrayOfByte)
//    {
//        try
//        {
//            paramString = decrypt(Base64.decode(paramString), paramArrayOfByte);
//            if (paramString == null) {
//                return null;
//            }
//            return new String(paramString, "UTF-8");
//        }
//        catch (UnsupportedEncodingException paramString) {}
//        return null;
//    }
//
//    public static String decryptToString(byte[] paramArrayOfByte, String paramString)
//    {
//        try
//        {
//            paramArrayOfByte = decrypt(paramArrayOfByte, paramString);
//            if (paramArrayOfByte == null) {
//                return null;
//            }
//            return new String(paramArrayOfByte, "UTF-8");
//        }
//        catch (UnsupportedEncodingException paramArrayOfByte) {}
//        return null;
//    }
//
//    public static String decryptToString(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
//    {
//        try
//        {
//            paramArrayOfByte1 = decrypt(paramArrayOfByte1, paramArrayOfByte2);
//            if (paramArrayOfByte1 == null) {
//                return null;
//            }
//            return new String(paramArrayOfByte1, "UTF-8");
//        }
//        catch (UnsupportedEncodingException paramArrayOfByte1) {}
//        return null;
//    }
//
//    public static byte[] encrypt(String paramString1, String paramString2)
//    {
//        try
//        {
//            return encrypt(paramString1.getBytes("UTF-8"), paramString2.getBytes("UTF-8"));
//        }
//        catch (UnsupportedEncodingException paramString1) {}
//        return null;
//    }
//
//    public static byte[] encrypt(String paramString, byte[] paramArrayOfByte)
//    {
//        try
//        {
//            return encrypt(paramString.getBytes("UTF-8"), paramArrayOfByte);
//        }
//        catch (UnsupportedEncodingException paramString) {}
//        return null;
//    }
//
//    public static byte[] encrypt(byte[] paramArrayOfByte, String paramString)
//    {
//        try
//        {
//            return encrypt(paramArrayOfByte, paramString.getBytes("UTF-8"));
//        }
//        catch (UnsupportedEncodingException paramArrayOfByte) {}
//        return null;
//    }
//
//    public static byte[] encrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
//    {
//        if (paramArrayOfByte1.length == 0) {
//            return paramArrayOfByte1;
//        }
//        return toByteArray(encrypt(toIntArray(paramArrayOfByte1, true), toIntArray(fixKey(paramArrayOfByte2), false)), false);
//    }
//
//    private static int[] encrypt(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
//    {
//        int i1 = paramArrayOfInt1.length - 1;
//        if (i1 < 1) {
//            return paramArrayOfInt1;
//        }
//        int j = 52 / (i1 + 1);
//        int i = paramArrayOfInt1[i1];
//        j += 6;
//        int m;
//        for (int k = 0; j > 0; k = m)
//        {
//            m = k - 1640531527;
//            int i2 = m >>> 2 & 0x3;
//            int n = 0;
//            k = i;
//            i = n;
//            while (i < i1)
//            {
//                n = paramArrayOfInt1[(i + 1)];
//                k = paramArrayOfInt1[i] + MX(m, n, k, i, i2, paramArrayOfInt2);
//                paramArrayOfInt1[i] = k;
//                i += 1;
//            }
//            n = paramArrayOfInt1[0];
//            i = paramArrayOfInt1[i1] + MX(m, n, k, i, i2, paramArrayOfInt2);
//            paramArrayOfInt1[i1] = i;
//            j -= 1;
//        }
//        return paramArrayOfInt1;
//    }
//
//    public static String encryptToBase64String(String paramString1, String paramString2)
//    {
//        paramString1 = encrypt(paramString1, paramString2);
//        if (paramString1 == null) {
//            return null;
//        }
//        return Base64.encode(paramString1);
//    }
//
//    public static String encryptToBase64String(String paramString, byte[] paramArrayOfByte)
//    {
//        paramString = encrypt(paramString, paramArrayOfByte);
//        if (paramString == null) {
//            return null;
//        }
//        return Base64.encode(paramString);
//    }
//
//    public static String encryptToBase64String(byte[] paramArrayOfByte, String paramString)
//    {
//        paramArrayOfByte = encrypt(paramArrayOfByte, paramString);
//        if (paramArrayOfByte == null) {
//            return null;
//        }
//        return Base64.encode(paramArrayOfByte);
//    }
//
//    public static String encryptToBase64String(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
//    {
//        paramArrayOfByte1 = encrypt(paramArrayOfByte1, paramArrayOfByte2);
//        if (paramArrayOfByte1 == null) {
//            return null;
//        }
//        return Base64.encode(paramArrayOfByte1);
//    }
//
//    private static byte[] fixKey(byte[] paramArrayOfByte)
//    {
//        if (paramArrayOfByte.length == 16) {
//            return paramArrayOfByte;
//        }
//        byte[] arrayOfByte = new byte[16];
//        if (paramArrayOfByte.length < 16) {
//            System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 0, paramArrayOfByte.length);
//        } else {
//            System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 0, 16);
//        }
//        return arrayOfByte;
//    }
//
//    private static byte[] toByteArray(int[] paramArrayOfInt, boolean paramBoolean)
//    {
//        int i = paramArrayOfInt.length << 2;
//        Object localObject = null;
//        if (paramBoolean)
//        {
//            j = paramArrayOfInt[(paramArrayOfInt.length - 1)];
//            int k = i - 4;
//            if (j < k - 3) {
//                break label81;
//            }
//            i = j;
//            if (j > k) {
//                return null;
//            }
//        }
//        byte[] arrayOfByte = new byte[i];
//        int j = 0;
//        for (;;)
//        {
//            localObject = arrayOfByte;
//            if (j >= i) {
//                break;
//            }
//            arrayOfByte[j] = ((byte)(paramArrayOfInt[(j >>> 2)] >>> ((j & 0x3) << 3)));
//            j += 1;
//        }
//        label81:
//        return (byte[])localObject;
//    }
//
//    private static int[] toIntArray(byte[] paramArrayOfByte, boolean paramBoolean)
//    {
//        if ((paramArrayOfByte.length & 0x3) == 0) {
//            i = paramArrayOfByte.length >>> 2;
//        } else {
//            i = (paramArrayOfByte.length >>> 2) + 1;
//        }
//        int[] arrayOfInt;
//        if (paramBoolean)
//        {
//            arrayOfInt = new int[i + 1];
//            arrayOfInt[i] = paramArrayOfByte.length;
//        }
//        else
//        {
//            arrayOfInt = new int[i];
//        }
//        int j = paramArrayOfByte.length;
//        int i = 0;
//        while (i < j)
//        {
//            int k = i >>> 2;
//            arrayOfInt[k] |= (paramArrayOfByte[i] & 0xFF) << ((i & 0x3) << 3);
//            i += 1;
//        }
//        return arrayOfInt;
//    }
//}
