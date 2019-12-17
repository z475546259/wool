package com.zzq.encryption;


//import android.content.Context;
//import com.stub.StubApp;

import java.io.UnsupportedEncodingException;

public final class XXTEA {
    private static final int DELTA = -1640531527;

    private XXTEA() {
    }

    private static int MX(int i, int i2, int i3, int i4, int i5, int[] iArr) {
        return ((i ^ i2) + (iArr[(i4 & 3) ^ i5] ^ i3)) ^ (((i3 >>> 5) ^ (i2 << 2)) + ((i2 >>> 3) ^ (i3 << 4)));
    }

    public static byte[] decrypt(byte[] bArr, String str) {
        try {
            return decrypt(bArr, str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static byte[] decrypt(byte[] bArr, byte[] bArr2) {
        return bArr.length == 0 ? bArr : toByteArray(decrypt(toIntArray(bArr, false), toIntArray(fixKey(bArr2), false)), true);
    }

    private static int[] decrypt(int[] iArr, int[] iArr2) {
        int length = iArr.length - 1;
        if (length >= 1) {
            int i = iArr[0];
            for (int i2 = ((52 / (length + 1)) + 6) * DELTA; i2 != 0; i2 += 1640531527) {
                int i3 = (i2 >>> 2) & 3;
                int i4 = length;
                while (i4 > 0) {
                    i = iArr[i4] - MX(i2, i, iArr[i4 - 1], i4, i3, iArr2);
                    iArr[i4] = i;
                    i4--;
                }
                i = iArr[0] - MX(i2, i, iArr[length], i4, i3, iArr2);
                iArr[0] = i;
            }
        }
        return iArr;
    }

    public static byte[] decryptBase64String(String str, String str2) {
        return decrypt(Base64.decode(str), str2);
    }

    public static byte[] decryptBase64String(String str, byte[] bArr) {
        return decrypt(Base64.decode(str), bArr);
    }

//    public static String decryptBase64StringToString(Context context, String str, String str2) {
    public static String decryptBase64StringToString( String str, String str2) {
//        if (!"com.example.administrator.jspmall".equals(StubApp.getOrigApplicationContext(context.getApplicationContext()).getPackageName())) {
//            System.exit(0);
//        }
        try {
            byte[] decrypt = decrypt(Base64.decode(str), str2);
            if (decrypt == null) {
                return null;
            }
            return new String(decrypt, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static String decryptBase64StringToString(String str, byte[] bArr) {
        try {
            byte[] decrypt = decrypt(Base64.decode(str), bArr);
            if (decrypt == null) {
                return null;
            }
            return new String(decrypt, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static String decryptToString(byte[] bArr, String str) {
        try {
            byte[] decrypt = decrypt(bArr, str);
            if (decrypt == null) {
                return null;
            }
            return new String(decrypt, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static String decryptToString(byte[] bArr, byte[] bArr2) {
        try {
            byte[] decrypt = decrypt(bArr, bArr2);
            if (decrypt == null) {
                return null;
            }
            return new String(decrypt, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static byte[] encrypt(String str, String str2) {
        try {
            return encrypt(str.getBytes("UTF-8"), str2.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static byte[] encrypt(String str, byte[] bArr) {
        try {
            return encrypt(str.getBytes("UTF-8"), bArr);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static byte[] encrypt(byte[] bArr, String str) {
        try {
            return encrypt(bArr, str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static byte[] encrypt(byte[] bArr, byte[] bArr2) {
        return bArr.length == 0 ? bArr : toByteArray(encrypt(toIntArray(bArr, true), toIntArray(fixKey(bArr2), false)), false);
    }

    private static int[] encrypt(int[] iArr, int[] iArr2) {
        int length = iArr.length - 1;
        if (length >= 1) {
            int i = iArr[length];
            int i2 = 0;
            for (int i3 = (52 / (length + 1)) + 6; i3 > 0; i3--) {
                i2 -= 1640531527;
                int i4 = (i2 >>> 2) & 3;
                int i5 = 0;
                while (i5 < length) {
                    i = iArr[i5] + MX(i2, iArr[i5 + 1], i, i5, i4, iArr2);
                    iArr[i5] = i;
                    i5++;
                }
                i = iArr[length] + MX(i2, iArr[0], i, i5, i4, iArr2);
                iArr[length] = i;
            }
        }
        return iArr;
    }

    public static String encryptToBase64String(String str, String str2) {
        byte[] encrypt = encrypt(str, str2);
        if (encrypt == null) {
            return null;
        }
        return Base64.encode(encrypt);
    }

    public static String encryptToBase64String(String str, byte[] bArr) {
        byte[] encrypt = encrypt(str, bArr);
        if (encrypt == null) {
            return null;
        }
        return Base64.encode(encrypt);
    }

    public static String encryptToBase64String(byte[] bArr, String str) {
        byte[] encrypt = encrypt(bArr, str);
        if (encrypt == null) {
            return null;
        }
        return Base64.encode(encrypt);
    }

    public static String encryptToBase64String(byte[] bArr, byte[] bArr2) {
        byte[] encrypt = encrypt(bArr, bArr2);
        if (encrypt == null) {
            return null;
        }
        return Base64.encode(encrypt);
    }

    private static byte[] fixKey(byte[] bArr) {
        if (bArr.length == 16) {
            return bArr;
        }
        byte[] bArr2 = new byte[16];
        if (bArr.length < 16) {
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        } else {
            System.arraycopy(bArr, 0, bArr2, 0, 16);
        }
        return bArr2;
    }

    private static byte[] toByteArray(int[] iArr, boolean z) {
        byte[] bArr;
        int i;
        int length = iArr.length << 2;
        if (z) {
            i = iArr[iArr.length - 1];
            int i2 = length - 4;
            if (i < i2 - 3) {
                bArr = null;
                return bArr;
            } else if (i > i2) {
//                return null;
            }
        } else {
            i = length;
        }
        byte[] bArr2 = new byte[i];
        for (int i3 = 0; i3 < i; i3++) {
            bArr2[i3] = (byte) (iArr[i3 >>> 2] >>> ((i3 & 3) << 3));
        }
        bArr = bArr2;
        return bArr;
    }

    private static int[] toIntArray(byte[] bArr, boolean z) {
        int[] iArr;
        int length = (bArr.length & 3) == 0 ? bArr.length >>> 2 : (bArr.length >>> 2) + 1;
        if (z) {
            iArr = new int[(length + 1)];
            iArr[length] = bArr.length;
        } else {
            iArr = new int[length];
        }
        int length2 = bArr.length;
        for (int i = 0; i < length2; i++) {
            int i2 = i >>> 2;
            iArr[i2] = iArr[i2] | ((bArr[i] & 255) << ((i & 3) << 3));
        }
        return iArr;
    }
}