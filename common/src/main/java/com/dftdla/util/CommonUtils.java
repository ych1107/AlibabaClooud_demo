package com.dftdla.util;

import java.text.SimpleDateFormat;

/**
 * @author 14501
 */
public class CommonUtils {

    private static final SimpleDateFormat SDF=new SimpleDateFormat("yyyy-MM-dd/HH:mm");

    public static String dateNow(){
        return SDF.format(System.currentTimeMillis());
    }

    public static Boolean isEmptyString(String s){
        if(s == null||s.trim().length() == 0) {
            return true;
        }
        return false;
    }

}
