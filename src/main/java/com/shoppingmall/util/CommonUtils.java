package com.shoppingmall.util;

import java.io.Closeable;
import java.io.IOException;

public class CommonUtils {
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

    public static boolean isNotNull(Object...args){
        for(Object obj:args){
            if(obj == null)
                return false;
        }
        return true;
    }
}
