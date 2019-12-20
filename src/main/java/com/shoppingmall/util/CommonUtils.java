package com.shoppingmall.util;



import java.io.Closeable;
import java.io.IOException;

public class CommonUtils {
    private static char[] charCode = new char[62];
    static {
        int cur = 0;
        for(int i = 0;i < 10;i++){
            charCode[cur++] = (char)(i + '0');
        }
        for(int i = 0;i < 26;i++){
            charCode[cur++] = (char)(i + 'a');
        }
        for(int i = 0;i < 26;i++){
            charCode[cur++] = (char)(i + 'A');
        }
    }

    public static char[] getCharCode() {
        return charCode;
    }

    public static void close(AutoCloseable...args){
        for(AutoCloseable arg:args){
            try {
                if(arg != null)
                    arg.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isNotNull(String...args){
        for(String obj:args){
            if(obj == null || obj.trim().length() < 1)
                return false;
        }
        return true;
    }
}
