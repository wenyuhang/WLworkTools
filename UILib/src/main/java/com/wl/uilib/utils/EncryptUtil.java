package com.wl.uilib.utils;


import java.security.MessageDigest;

/**
 * 加密解密工具类
 */
public class EncryptUtil {
    private static EncryptUtil me;

    private EncryptUtil() {
    }

    //双重锁
    public static EncryptUtil getInstance() {
        if (me == null) {
            synchronized (EncryptUtil.class) {
                if (me == null) {
                    me = new EncryptUtil();
                }
            }
        }
        return me;
    }

    /**
     * sha1加密
     *
     * @param password
     * @return
     */
    public String sha1Encode(String password) {
        StringBuffer hexValue = new StringBuffer();
        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            char[] charArray = password.toCharArray();
            byte[] byteArray = new byte[charArray.length];
            for (int i = 0; i < charArray.length; i++) byteArray[i] = (byte) charArray[i];
            byte[] sha1Bytes = sha1.digest(byteArray);
            for (int i = 0; i < sha1Bytes.length; i++) {
                int val = ((int) sha1Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hexValue.toString();
    }

    /**
     * sha256加密
     *
     * @param password
     * @return
     */
    public String sha256Encode(String password) {
        StringBuffer hexValue = new StringBuffer();
        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA-256");
            char[] charArray = password.toCharArray();
            byte[] byteArray = new byte[charArray.length];
            for (int i = 0; i < charArray.length; i++) byteArray[i] = (byte) charArray[i];
            byte[] sha1Bytes = sha1.digest(byteArray);
            for (int i = 0; i < sha1Bytes.length; i++) {
                int val = ((int) sha1Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hexValue.toString();
    }

    /**
     * md5加密
     *
     * @param password
     * @return
     */
    public String md5Encode(String password) {
        StringBuffer hexValue = new StringBuffer();
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            char[] charArray = password.toCharArray();
            byte[] byteArray = new byte[charArray.length];
            for (int i = 0; i < charArray.length; i++) byteArray[i] = (byte) charArray[i];
            byte[] md5Bytes = md5.digest(byteArray);
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hexValue.toString();
    }

    public static String sha1(String data) {
        StringBuffer buf = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(data.getBytes());
            byte[] bits = md.digest();
            for (int i = 0; i < bits.length; i++) {
                int a = bits[i];
                if (a < 0) a += 256;
                if (a < 16) buf.append("0");
                buf.append(Integer.toHexString(a));
            }
        } catch (Exception e) {
        }
        return buf.toString();
    }
}
