package com.hdy.safe;

import com.hdy.common.DateFormatUtils;

import java.io.File;
import java.util.Date;

/**
 * Created by hedongyu on 14-6-26.
 * 799374340@qq.com
 */
public class SafeUtil {
    //软件过期日期
    private static final String lastCanUseDate="2014-09-03";
    public static boolean canUse(){
        File file = new File("C:/key.txt");
        if(!file.exists()){
            return false;
        }
        Date now = new Date();
        Date last = DateFormatUtils.getDate(lastCanUseDate,"yyyy-MM-dd");
        if(now.after(last)){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(canUse());
    }
}
