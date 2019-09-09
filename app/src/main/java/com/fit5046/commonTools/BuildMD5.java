package com.fit5046.commonTools;

import java.math.BigInteger;
import java.security.MessageDigest;

public class BuildMD5 {
    public static String getMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            String md5 = new BigInteger(1, md.digest()).toString(16);
            //BigInteger will del "0"
            //fill MD5 to 32 bits
            return fillMD5(md5);
        } catch (Exception e) {
            throw new RuntimeException("MD5加密错误:"+e.getMessage(),e);
        }
    }

    public static String fillMD5(String md5){
        return md5.length()==32?md5:fillMD5("0"+md5);
    }
}
