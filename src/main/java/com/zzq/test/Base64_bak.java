////package mylibrary.encryption;
//
//public final class Base64
//{
//    private static final int BASELENGTH = 128;
//    private static final int EIGHTBIT = 8;
//    private static final int FOURBYTE = 4;
//    private static final int LOOKUPLENGTH = 64;
//    private static char PAD = '=';
//    private static final int SIGN = -128;
//    private static final int SIXTEENBIT = 16;
//    private static final int TWENTYFOURBITGROUP = 24;
//    private static byte[] base64Alphabet = new byte[128];
//    private static char[] lookUpBase64Alphabet = new char[64];
//
//    static
//    {
//        int n = 0;
//        int i = 0;
//        while (i < 128)
//        {
//            base64Alphabet[i] = -1;
//            i += 1;
//        }
//        i = 90;
//        while (i >= 65)
//        {
//            base64Alphabet[i] = ((byte)(i - 65));
//            i -= 1;
//        }
//        i = 122;
//        int j;
//        for (;;)
//        {
//            j = 26;
//            if (i < 97) {
//                break;
//            }
//            base64Alphabet[i] = ((byte)(26 + (i - 97)));
//            i -= 1;
//        }
//        i = 57;
//        int i1;
//        for (;;)
//        {
//            i1 = 52;
//            if (i < 48) {
//                break;
//            }
//            base64Alphabet[i] = ((byte)(52 + (i - 48)));
//            i -= 1;
//        }
//        base64Alphabet[43] = 62;
//        base64Alphabet[47] = 63;
//        i = 0;
//        while (i <= 25)
//        {
//            lookUpBase64Alphabet[i] = ((char)(65 + i));
//            i += 1;
//        }
//        i = 0;
//        int k;
//        int m;
//        for (;;)
//        {
//            k = n;
//            m = i1;
//            if (j > 51) {
//                break;
//            }
//            lookUpBase64Alphabet[j] = ((char)(97 + i));
//            j += 1;
//            i += 1;
//        }
//        while (m <= 61)
//        {
//            lookUpBase64Alphabet[m] = ((char)(48 + k));
//            m += 1;
//            k += 1;
//        }
//        lookUpBase64Alphabet[62] = '+';
//        lookUpBase64Alphabet[63] = '/';
//    }
//
//    public static byte[] decode(String paramString)
//    {
//        Object localObject = null;
//        if (paramString == null) {
//            return null;
//        }
//        char[] arrayOfChar = paramString.toCharArray();
//        int i = removeWhiteSpace(arrayOfChar);
//        if (i % 4 != 0) {
//            return null;
//        }
//        int n = i / 4;
//        if (n == 0) {
//            return new byte[0];
//        }
//        byte[] arrayOfByte = new byte[n * 3];
//        int j = 0;
//        i = j;
//        int m = i;
//        int k = i;
//        i = m;
//        int i1;
//        char c2;
//        while (j < n - 1)
//        {
//            m = k + 1;
//            char c1 = arrayOfChar[k];
//            paramString = (String)localObject;
//            if (!isData(c1)) {
////                return paramString;
//                return null;
//            }
//            i1 = m + 1;
//            c2 = arrayOfChar[m];
//            paramString = (String)localObject;
//            if (!isData(c2)) {
////                return paramString;
//                return null;
//            }
//            k = i1 + 1;
//            char c3 = arrayOfChar[i1];
//            paramString = (String)localObject;
//            if (!isData(c3)) {
////                return paramString;
//                return null;
//            }
//            char c4 = arrayOfChar[k];
//            if (!isData(c4)) {
//                return null;
//            }
//            int i4 = base64Alphabet[c1];
//            m = base64Alphabet[c2];
//            i1 = base64Alphabet[c3];
//            int i2 = base64Alphabet[c4];
//            int i3 = i + 1;
//            arrayOfByte[i] = ((byte)(i4 << 2 | m >> 4));
//            i = i3 + 1;
//            arrayOfByte[i3] = ((byte)((m & 0xF) << 4 | i1 >> 2 & 0xF));
//            arrayOfByte[i] = ((byte)(i1 << 6 | i2));
//            j += 1;
//            i += 1;
//            k += 1;
//        }
//        m = k + 1;
//        char c1 = arrayOfChar[k];
//        paramString = (String)localObject;
//        if (isData(c1))
//        {
//            n = m + 1;
//            c2 = arrayOfChar[m];
//            if (!isData(c2)) {
//                return null;
//            }
//            k = base64Alphabet[c1];
//            m = base64Alphabet[c2];
//            c1 = arrayOfChar[n];
//            c2 = arrayOfChar[(n + 1)];
//            if ((isData(c1)) && (isData(c2)))
//            {
//                j = base64Alphabet[c1];
//                n = base64Alphabet[c2];
//                i1 = i + 1;
//                arrayOfByte[i] = ((byte)(k << 2 | m >> 4));
//                arrayOfByte[i1] = ((byte)((m & 0xF) << 4 | j >> 2 & 0xF));
//                arrayOfByte[(i1 + 1)] = ((byte)(n | j << 6));
//                return arrayOfByte;
//            }
//            if ((isPad(c1)) && (isPad(c2)))
//            {
//                if ((m & 0xF) != 0) {
//                    return null;
//                }
//                j *= 3;
//                byte[] bArr2 = new byte[j + 1];
//                System.arraycopy(arrayOfByte, 0, bArr2, 0, j);
//                bArr2[i] = ((byte)(k << 2 | m >> 4));
//                return bArr2;
//            }
//            paramString = (String)localObject;
//            if (!isPad(c1))
//            {
//                paramString = (String)localObject;
//                if (isPad(c2))
//                {
//                    n = base64Alphabet[c1];
//                    if ((n & 0x3) != 0) {
//                        return null;
//                    }
//                    j *= 3;
//                    byte[] bArr3 = new byte[j + 2];
//                    System.arraycopy(arrayOfByte, 0, bArr3, 0, j);
//                    bArr3[i] = ((byte)(k << 2 | m >> 4));
//                    paramString[(i + 1)] = ((byte)(n >> 2 & 0xF | (m & 0xF) << 4));
//                }
//            }
//        }
//        return paramString;
//    }
//
//    public static String encode(byte[] paramArrayOfByte)
//    {
//        if (paramArrayOfByte == null) {
//            return null;
//        }
//        int i = paramArrayOfByte.length * 8;
//        if (i == 0) {
//            return "";
//        }
//        int n = i % 24;
//        int m = i / 24;
//        if (n != 0) {
//            i = m + 1;
//        } else {
//            i = m;
//        }
//        char[] arrayOfChar = new char[i * 4];
//        int j = 0;
//        int k = 0;
//        i = k;
//        int i1;
//        while (j < m)
//        {
//            int i2 = k + 1;
//            k = paramArrayOfByte[k];
//            i1 = i2 + 1;
//            int i6 = paramArrayOfByte[i2];
//            i2 = paramArrayOfByte[i1];
//            int i3 = (byte)(i6 & 0xF);
//            int i4 = (byte)(k & 0x3);
//            if ((k & 0xFFFFFF80) == 0) {
//                k >>= 2;
//            }
//            int i5;
//            for (;;)
//            {
//                i5 = (byte)k;
//                break;
//                k = k >> 2 ^ 0xC0;
//            }
//            if ((i6 & 0xFFFFFF80) == 0) {}
//            for (k = i6 >> 4;; k = i6 >> 4 ^ 0xF0)
//            {
//                i6 = (byte)k;
//                break;
//            }
//            if ((i2 & 0xFFFFFF80) == 0) {}
//            for (k = i2 >> 6;; k = i2 >> 6 ^ 0xFC)
//            {
//                k = (byte)k;
//                break;
//            }
//            int i7 = i + 1;
//            arrayOfChar[i] = lookUpBase64Alphabet[i5];
//            i = i7 + 1;
//            arrayOfChar[i7] = lookUpBase64Alphabet[(i6 | i4 << 4)];
//            i4 = i + 1;
//            arrayOfChar[i] = lookUpBase64Alphabet[(i3 << 2 | k)];
//            arrayOfChar[i4] = lookUpBase64Alphabet[(i2 & 0x3F)];
//            j += 1;
//            i = i4 + 1;
//            k = i1 + 1;
//        }
//        if (n == 8)
//        {
//            j = paramArrayOfByte[k];
//            k = (byte)(j & 0x3);
//            if ((j & 0xFFFFFF80) == 0) {
//                j >>= 2;
//            }
//            for (;;)
//            {
//                m = (byte)j;
//                break;
//                j = j >> 2 ^ 0xC0;
//            }
//            j = i + 1;
//            arrayOfChar[i] = lookUpBase64Alphabet[m];
//            i = j + 1;
//            arrayOfChar[j] = lookUpBase64Alphabet[(k << 4)];
//            arrayOfChar[i] = PAD;
//            arrayOfChar[(i + 1)] = PAD;
//        }
//        else if (n == 16)
//        {
//            j = paramArrayOfByte[k];
//            i1 = paramArrayOfByte[(k + 1)];
//            k = (byte)(i1 & 0xF);
//            m = (byte)(j & 0x3);
//            if ((j & 0xFFFFFF80) == 0) {
//                j >>= 2;
//            }
//            for (;;)
//            {
//                n = (byte)j;
//                break;
//                j = j >> 2 ^ 0xC0;
//            }
//            if ((i1 & 0xFFFFFF80) == 0) {}
//            for (j = i1 >> 4;; j = i1 >> 4 ^ 0xF0)
//            {
//                j = (byte)j;
//                break;
//            }
//            i1 = i + 1;
//            arrayOfChar[i] = lookUpBase64Alphabet[n];
//            i = i1 + 1;
//            arrayOfChar[i1] = lookUpBase64Alphabet[(j | m << 4)];
//            arrayOfChar[i] = lookUpBase64Alphabet[(k << 2)];
//            arrayOfChar[(i + 1)] = PAD;
//        }
//        return new String(arrayOfChar);
//    }
//
//    private static boolean isData(char paramChar)
//    {
//        return (paramChar < '?') && (base64Alphabet[paramChar] != -1);
//    }
//
//    private static boolean isPad(char paramChar)
//    {
//        char c = PAD;
//        boolean bool = false;
//        if (paramChar == c) {
//            bool = true;
//        }
//        return bool;
//    }
//
//    private static boolean isWhiteSpace(char paramChar)
//    {
//        boolean bool = false;
//        if ((paramChar == ' ') || (paramChar == '\r') || (paramChar == '\n') || (paramChar == '\t')) {
//            bool = true;
//        }
//        return bool;
//    }
//
//    private static int removeWhiteSpace(char[] paramArrayOfChar)
//    {
//        int i = 0;
//        if (paramArrayOfChar == null) {
//            return 0;
//        }
//        int m = paramArrayOfChar.length;
//        int k;
//        for (int j = 0; i < m; j = k)
//        {
//            k = j;
//            if (!isWhiteSpace(paramArrayOfChar[i]))
//            {
//                paramArrayOfChar[j] = paramArrayOfChar[i];
//                k = j + 1;
//            }
//            i += 1;
//        }
//        return j;
//    }
//}
