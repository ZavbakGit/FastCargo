package fun.gladkikh.app.fastcargo.remote.core;

/**
 * Return String autorithation Russin
 */

public class AutorithationUtil {


    private static StringBuffer encode(byte[] data, int start, int len, StringBuffer buf) {

        final char[] charTab = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();

        if(buf == null) {
            buf = new StringBuffer(data.length * 3 / 2);
        }

        int end = len - 3;
        int i = start;
        int n = 0;

        int d;
        while(i <= end) {
            d = (data[i] & 255) << 16 | (data[i + 1] & 255) << 8 | data[i + 2] & 255;
            buf.append(charTab[d >> 18 & 63]);
            buf.append(charTab[d >> 12 & 63]);
            buf.append(charTab[d >> 6 & 63]);
            buf.append(charTab[d & 63]);
            i += 3;
            if(n++ >= 14) {
                n = 0;
                buf.append("\r\n");
            }
        }

        if(i == start + len - 2) {
            d = (data[i] & 255) << 16 | (data[i + 1] & 255) << 8;
            buf.append(charTab[d >> 18 & 63]);
            buf.append(charTab[d >> 12 & 63]);
            buf.append(charTab[d >> 6 & 63]);
            buf.append("=");
        } else if(i == start + len - 1) {
            d = (data[i] & 255) << 16;
            buf.append(charTab[d >> 18 & 63]);
            buf.append(charTab[d >> 12 & 63]);
            buf.append("==");
        }

        return buf;
    }

    public static String getStringAutorization(String username, String pass){

        if (username != null && pass != null) {
            StringBuffer buf = new StringBuffer(username);
            buf.append(':').append(pass);
            byte[] raw = buf.toString().getBytes();
            buf.setLength(0);
            buf.append("Basic ");
            encode(raw, 0, raw.length, buf);
            //org.kobjects.base64.Base64.encode(raw, 0, raw.length, buf);

            //android.util.Base64.encode( auth.getBytes(), Base64.NO_WRAP);
            return buf.toString();
        }

        return null;
    }



}
