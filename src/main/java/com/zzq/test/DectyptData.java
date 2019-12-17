package com.zzq.test;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.io.UnsupportedEncodingException;

public class DectyptData {
    public static void main(String[] args) {
        String ddata = decryptBase64StringToString("75Yyy7blXzWm+mw2J6oqXBxw5pBeh6oqixtSZmzWfwZf+DwMEigto3vw4htFvXlcCztjhxSlxBuyIF+rZRI127Vr1SBnQ471tUphC66GKDgYAvC1Z7JTQA6BaXFsR\\/sHncXQcg\\/gbpqLziw+JuU218aAy2ic0+od+R5CZSR5PI+0z7OgMb8+lbpWuIpXKoW3JxOR+EWwM+7ALvHJtTCoGzN0afNtJHmHHIU1xUa5VN5s\\/leH\\/yU32\\/gh2xFcOBfu6Y1Z+51mQf2nmtubqfSPHPHuCEHllhnZmZY7T7IcmbhRwzuZICCJEHXdmZpEmyKE\\/1iXAvOPnDdukQovODXKYbHe78ynUHN3rM9SX7\\/xGEvhNoh9Sf5rrJ0cCfJXoUDgfwEJqb\\/x7z\\/BXK1xr95zaQ=="+ "", "nb.fragment.title.find".getBytes());
        System.out.println(ddata);
    }

    public static String decryptBase64StringToString(String paramString, byte[] paramArrayOfByte)
    {
        try
        {
            byte[] dstr = decrypt(Base64.decode(paramString), paramArrayOfByte);
            if (paramString == null) {
                return null;
            }
            String str = new String(dstr, "UTF-8");
            return str;
        }
        catch (UnsupportedEncodingException e) {} catch (Base64DecodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] decrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    {
        if (paramArrayOfByte1.length == 0) {
            return paramArrayOfByte1;
        }
        return toByteArray(decrypt(toIntArray(paramArrayOfByte1, false), toIntArray(fixKey(paramArrayOfByte2), false)), true);
    }


    private static byte[] toByteArray(int[] paramArrayOfInt, boolean paramBoolean)
    {
        int j = paramArrayOfInt.length << 2;
        int i = j;
        Object localObject;
        if (paramBoolean)
        {
            i = paramArrayOfInt[(paramArrayOfInt.length - 1)];
            j -= 4;
            if ((i < j - 3) || (i > j))
            {
                localObject = null;
                return (byte[])localObject;
            }
        }
        byte[] arrayOfByte = new byte[i];
        j = 0;
        for (;;)
        {
            localObject = arrayOfByte;
            if (j >= i) {
                break;
            }
            arrayOfByte[j] = ((byte)(paramArrayOfInt[(j >>> 2)] >>> ((j & 0x3) << 3)));
            j += 1;
        }
        return arrayOfByte;
    }

    private static int[] toIntArray(byte[] paramArrayOfByte, boolean paramBoolean)
    {
        int i;
        int[] arrayOfInt = new int[0];
        if ((paramArrayOfByte.length & 0x3) == 0)
        {
            i = paramArrayOfByte.length >>> 2;
            if (!paramBoolean) {
                return  new int[i];
            }
            arrayOfInt = new int[i + 1];
            arrayOfInt[i] = paramArrayOfByte.length;
        }
        for (;;)
        {
            int j = paramArrayOfByte.length;
            i = 0;
            while (i < j)
            {
                int k = i >>> 2;
                arrayOfInt[k] |= (paramArrayOfByte[i] & 0xFF) << ((i & 0x3) << 3);
                i += 1;
            }
            i = (paramArrayOfByte.length >>> 2) + 1;
            break;
        }
        return arrayOfInt;
    }


    private static byte[] fixKey(byte[] paramArrayOfByte)
    {
        if (paramArrayOfByte.length == 16) {
            return paramArrayOfByte;
        }
        byte[] arrayOfByte = new byte[16];
        if (paramArrayOfByte.length < 16) {
            System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 0, paramArrayOfByte.length);
        }else{
            System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 0, 16);
        }
        return arrayOfByte;
    }


    private static int[] encrypt(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
    {
        int n = paramArrayOfInt1.length - 1;
        if (n < 1) {return paramArrayOfInt1;}
        for (;;)
        {
            int j = 52 / (n + 1);
            int i = paramArrayOfInt1[n];
            int k = 0;
            j += 6;
            while (j > 0)
            {
                int m = k - 1640531527;
                int i1 = m >>> 2 & 0x3;
                k = 0;
                while (k < n)
                {
                    int i2 = paramArrayOfInt1[(k + 1)];
                    i = paramArrayOfInt1[k] + MX(m, i2, i, k, i1, paramArrayOfInt2);
                    paramArrayOfInt1[k] = i;
                    k += 1;
                }
                int i2 = paramArrayOfInt1[0];
                i = paramArrayOfInt1[n] + MX(m, i2, i, k, i1, paramArrayOfInt2);
                paramArrayOfInt1[n] = i;
                j -= 1;
                k = m;
            }
            return paramArrayOfInt1;
        }

    }

    private static int MX(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int[] paramArrayOfInt)
    {
        return (paramInt3 >>> 5 ^ paramInt2 << 2) + (paramInt2 >>> 3 ^ paramInt3 << 4) ^ (paramInt1 ^ paramInt2) + (paramArrayOfInt[(paramInt4 & 0x3 ^ paramInt5)] ^ paramInt3);
    }

    private static int[] decrypt(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
    {
        int m = paramArrayOfInt1.length - 1;
        if (m < 1) {}
        for (;;)
        {

            int j = 52 / (m + 1);
            int i = paramArrayOfInt1[0];
            j = (j + 6) * -1640531527;
            while (j != 0)
            {
                int n = j >>> 2 & 0x3;
                int k = m;
                while (k > 0)
                {
                    int i1 = paramArrayOfInt1[(k - 1)];
                    i = paramArrayOfInt1[k] - MX(j, i, i1, k, n, paramArrayOfInt2);
                    paramArrayOfInt1[k] = i;
                    k -= 1;
                }
                int i1 = paramArrayOfInt1[m];
                i = paramArrayOfInt1[0] - MX(j, i, i1, k, n, paramArrayOfInt2);
                paramArrayOfInt1[0] = i;
                j += 1640531527;
            }
            return paramArrayOfInt1;
        }
    }
}
