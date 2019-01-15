package com.iris.workingtest;

import org.springframework.util.StringUtils;

public class StringUtilTest {
    public static void main(String args[]){
        String arg1=null;
        String arg2="";
        //false
        System.out.println(!StringUtils.isEmpty(arg1));
        //false
        System.out.println(!StringUtils.isEmpty(arg2));
    }
}
