package com.zzq.encryption;

//package mylibrary.encryption;

//import com.facebook.imageutils.JfifUtil;
//import uk.co.senab.photoview.BuildConfig;

public final class Base64 {
    private static final int BASELENGTH = 128;
    private static final int EIGHTBIT = 8;
    private static final int FOURBYTE = 4;
    private static final int LOOKUPLENGTH = 64;
    private static char PAD = '=';
    private static final int SIGN = -128;
    private static final int SIXTEENBIT = 16;
    private static final int TWENTYFOURBITGROUP = 24;
    private static byte[] base64Alphabet = new byte[128];
    private static char[] lookUpBase64Alphabet = new char[64];

//    static {
//////        int i;
//////        int i2;
//////        int i3 = 0;
//////        for (int i4 = 0; i4 < 128; i4++) {
//////            base64Alphabet[i4] = -1;
//////        }
//////        for (int i5 = 90; i5 >= 65; i5--) {
//////            base64Alphabet[i5] = (byte) (i5 - 65);
//////        }
////////        int i6 = BuildConfig.VERSION_CODE;
//////        int i6 = 122;
//////        while (true) {
//////            i = 26;
//////            if (i6 < 97) {
//////                break;
//////            }
//////            base64Alphabet[i6] = (byte) ((i6 - 97) + 26);
//////            i6--;
//////        }
//////        int i7 = 57;
//////        while (true) {
//////            int i8 = i7;
//////            i2 = 52;
//////            if (i8 < 48) {
//////                break;
//////            }
//////            base64Alphabet[i8] = (byte) ((i8 - 48) + 52);
//////            i7 = i8 - 1;
//////        }
//////        base64Alphabet[43] = 62;
//////        base64Alphabet[47] = 63;
//////        for (int i9 = 0; i9 <= 25; i9++) {
//////            lookUpBase64Alphabet[i9] = (char) (i9 + 65);
//////        }
//////        int i10 = 0;
//////        while (i <= 51) {
//////            lookUpBase64Alphabet[i] = (char) (i10 + 97);
//////            i++;
//////            i10++;
//////        }
//////        while (i2 <= 61) {
//////            lookUpBase64Alphabet[i2] = (char) (i3 + 48);
//////            i2++;
//////            i3++;
//////        }
//////        lookUpBase64Alphabet[62] = '+';
//////        lookUpBase64Alphabet[63] = '/';
//////    }

    static {
        for (int i = 0; i < BASELENGTH; i++) {
            base64Alphabet[i] = -1;
        }
        for (int i = 'Z'; i >= 'A'; i--) {
            base64Alphabet[i] = (byte) (i - 'A');
        }
        for (int i = 'z'; i>= 'a'; i--) {
            base64Alphabet[i] = (byte) (i - 'a' + 26);
        }

        for (int i = '9'; i >= '0'; i--) {
            base64Alphabet[i] = (byte) (i - '0' + 52);
        }

        base64Alphabet['+'] = 62;
        base64Alphabet['/'] = 63;

        for (int i = 0; i <= 25; i++) {
            lookUpBase64Alphabet[i] = (char)('A' + i);
        }

        for (int i = 26,  j = 0; i <= 51; i++, j++) {
            lookUpBase64Alphabet[i] = (char)('a' + j);
        }

        for (int i = 52,  j = 0; i <= 61; i++, j++) {
            lookUpBase64Alphabet[i] = (char)('0' + j);
        }
        lookUpBase64Alphabet[62] = '+';
        lookUpBase64Alphabet[63] = '/';
    }

    public static byte[] decode(String str) {
        if (str == null) {
            return null;
        }
        char[] charArray = str.toCharArray();
        int removeWhiteSpace = removeWhiteSpace(charArray);
        if (removeWhiteSpace % 4 != 0) {
            return null;
        }
        int i = removeWhiteSpace / 4;
        if (i == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[(i * 3)];
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i - 1) {
            int i5 = i2 + 1;
            char c = charArray[i2];
            if (!isData(c)) {
                return null;
            }
            int i6 = i5 + 1;
            char c2 = charArray[i5];
            if (!isData(c2)) {
                return null;
            }
            int i7 = i6 + 1;
            char c3 = charArray[i6];
            if (!isData(c3)) {
                return null;
            }
            char c4 = charArray[i7];
            if (!isData(c4)) {
                return null;
            }
            byte b = base64Alphabet[c];
            byte b2 = base64Alphabet[c2];
            byte b3 = base64Alphabet[c3];
            byte b4 = base64Alphabet[c4];
            int i8 = i4 + 1;
            bArr[i4] = (byte) ((b << 2) | (b2 >> 4));
            int i9 = i8 + 1;
            bArr[i8] = (byte) (((b2 & 15) << 4) | ((b3 >> 2) & 15));
            bArr[i9] = (byte) ((b3 << 6) | b4);
            i3++;
            i4 = i9 + 1;
            i2 = i7 + 1;
        }
        int i10 = i2 + 1;
        char c5 = charArray[i2];
        if (!isData(c5)) {
            return null;
        }
        int i11 = i10 + 1;
        char c6 = charArray[i10];
        if (!isData(c6)) {
            return null;
        }
        byte b5 = base64Alphabet[c5];
        byte b6 = base64Alphabet[c6];
        char c7 = charArray[i11];
        char c8 = charArray[i11 + 1];
        if (isData(c7) && isData(c8)) {
            byte b7 = base64Alphabet[c7];
            byte b8 = base64Alphabet[c8];
            int i12 = i4 + 1;
            bArr[i4] = (byte) ((b5 << 2) | (b6 >> 4));
            bArr[i12] = (byte) (((b6 & 15) << 4) | ((b7 >> 2) & 15));
            bArr[i12 + 1] = (byte) ((b7 << 6) | b8);
            return bArr;
        } else if (!isPad(c7) || !isPad(c8)) {
            if (isPad(c7) || !isPad(c8)) {
                return null;
            }
            byte b9 = base64Alphabet[c7];
            if ((b9 & 3) != 0) {
                return null;
            }
            int i13 = i3 * 3;
            byte[] bArr2 = new byte[(i13 + 2)];
            System.arraycopy(bArr, 0, bArr2, 0, i13);
            bArr2[i4] = (byte) ((b5 << 2) | (b6 >> 4));
            bArr2[i4 + 1] = (byte) (((b9 >> 2) & 15) | ((b6 & 15) << 4));
            return bArr2;
        } else if ((b6 & 15) != 0) {
            return null;
        } else {
            int i14 = i3 * 3;
            byte[] bArr3 = new byte[(i14 + 1)];
            System.arraycopy(bArr, 0, bArr3, 0, i14);
            bArr3[i4] = (byte) ((b5 << 2) | (b6 >> 4));
            return bArr3;
        }
    }

    public static String encode(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length * 8;
        if (length == 0) {
            return "";
        }
        int i = length % 24;
        int i2 = length / 24;
        char[] cArr = new char[((i != 0 ? i2 + 1 : i2) * 4)];
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = i3 + 1;
            byte b = bArr[i3];
            int i7 = i6 + 1;
            byte b2 = bArr[i6];
            byte b3 = bArr[i7];
            byte b4 = (byte) (b2 & 15);
            byte b5 = (byte) (b & 3);
//            byte b6 = (byte) ((b & Byte.MIN_VALUE) == 0 ? b >> 2 : (b >> 2) ^ JfifUtil.MARKER_SOFn);
            byte b6 = (byte) ((b & Byte.MIN_VALUE) == 0 ? b >> 2 : (b >> 2) ^ 0xC0);
            byte b7 = (byte) ((b2 & Byte.MIN_VALUE) == 0 ? b2 >> 4 : (b2 >> 4) ^ 240);
            int i8 = (b3 & Byte.MIN_VALUE) == 0 ? b3 >> 6 : (b3 >> 6) ^ 252;
            int i9 = i4 + 1;
            cArr[i4] = lookUpBase64Alphabet[b6];
            int i10 = i9 + 1;
            cArr[i9] = lookUpBase64Alphabet[b7 | (b5 << 4)];
            int i11 = i10 + 1;
            cArr[i10] = lookUpBase64Alphabet[((byte) i8) | (b4 << 2)];
            cArr[i11] = lookUpBase64Alphabet[b3 & 63];
            i4 = i11 + 1;
            i3 = i7 + 1;
        }
        if (i == 8) {
            byte b8 = bArr[i3];
            byte b9 = (byte) (b8 & 3);
            int i12 = i4 + 1;
//            cArr[i4] = lookUpBase64Alphabet[(byte) ((b8 & Byte.MIN_VALUE) == 0 ? b8 >> 2 : (b8 >> 2) ^ JfifUtil.MARKER_SOFn)];
            cArr[i4] = lookUpBase64Alphabet[(byte) ((b8 & Byte.MIN_VALUE) == 0 ? b8 >> 2 : (b8 >> 2) ^ 0xC0)];
            int i13 = i12 + 1;
            cArr[i12] = lookUpBase64Alphabet[b9 << 4];
            cArr[i13] = PAD;
            cArr[i13 + 1] = PAD;
        } else if (i == 16) {
            byte b10 = bArr[i3];
            byte b11 = bArr[i3 + 1];
            byte b12 = (byte) (b11 & 15);
            byte b13 = (byte) (b10 & 3);
//            byte b14 = (byte) ((b10 & Byte.MIN_VALUE) == 0 ? b10 >> 2 : (b10 >> 2) ^ JfifUtil.MARKER_SOFn);
            byte b14 = (byte) ((b10 & Byte.MIN_VALUE) == 0 ? b10 >> 2 : (b10 >> 2) ^ 0xC0);
            int i14 = (b11 & Byte.MIN_VALUE) == 0 ? b11 >> 4 : (b11 >> 4) ^ 240;
            int i15 = i4 + 1;
            cArr[i4] = lookUpBase64Alphabet[b14];
            int i16 = i15 + 1;
            cArr[i15] = lookUpBase64Alphabet[((byte) i14) | (b13 << 4)];
            cArr[i16] = lookUpBase64Alphabet[b12 << 2];
            cArr[i16 + 1] = PAD;
        }
        return new String(cArr);
    }

    private static boolean isData(char c) {
        return c < 128 && base64Alphabet[c] != -1;
    }

    private static boolean isPad(char c) {
        return c == PAD;
    }

    private static boolean isWhiteSpace(char c) {
        return c == ' ' || c == 13 || c == 10 || c == 9;
    }

    private static int removeWhiteSpace(char[] cArr) {
        if (cArr == null) {
            return 0;
        }
        int length = cArr.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            if (!isWhiteSpace(cArr[i2])) {
                cArr[i] = cArr[i2];
                i++;
            }
        }
        return i;
    }
}