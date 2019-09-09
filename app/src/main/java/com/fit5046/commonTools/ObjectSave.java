package com.fit5046.commonTools;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectSave {

    private static final String SP_NAME = "sp_config";

    public static void saveObject(Context context, Object object, String stringName){ // keyword = "currentUser"
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bos);
            // write Serializable Entity into outputStream
            os.writeObject(object);
            // save as HexString
            String bytesToHexString = bytesToHexString(bos.toByteArray());
            editor.putString(stringName, bytesToHexString);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object readObject(Context context, String keyword){ // keyword = "currentUser"
        try{
            SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if(sharedPreferences.contains(keyword)){
                String string = sharedPreferences.getString(keyword, "");
                if(!(string.isEmpty() || string == null)){
                    byte[] stringToBytes = StringToBytes(string);
                    ByteArrayInputStream bis=new ByteArrayInputStream(stringToBytes);
                    ObjectInputStream is=new ObjectInputStream(bis);
                    Object readObject = is.readObject();
                    return readObject;
                }else{
                    return null;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static String bytesToHexString(byte[] byteArray){
        if(byteArray == null){
            return null;
        }
        if(byteArray.length == 0){
            return "";
        }
        StringBuffer sb = new StringBuffer(byteArray.length);
        String sTemp;
        for (int i = 0; i < byteArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & byteArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    private static byte[] StringToBytes(String string){
        String hexString = string.toUpperCase().trim();
        if (hexString.length()%2!=0) {
            return null;
        }
        byte[] retData=new byte[hexString.length()/2];
        for(int i=0;i<hexString.length();i++)
        {
            int int_ch;
            char hex_char1 = hexString.charAt(i);
            int int_ch3;
            if(hex_char1 >= '0' && hex_char1 <='9')
                int_ch3 = (hex_char1-48)*16;
            else if(hex_char1 >= 'A' && hex_char1 <='F')
                int_ch3 = (hex_char1-55)*16;
            else
                return null;
            i++;
            char hex_char2 = hexString.charAt(i);
            int int_ch4;
            if(hex_char2 >= '0' && hex_char2 <='9')
                int_ch4 = (hex_char2-48);
            else if(hex_char2 >= 'A' && hex_char2 <='F')
                int_ch4 = hex_char2-55;
            else
                return null;
            int_ch = int_ch3+int_ch4;
            retData[i/2]=(byte) int_ch;
        }
        return retData;
    }
}
