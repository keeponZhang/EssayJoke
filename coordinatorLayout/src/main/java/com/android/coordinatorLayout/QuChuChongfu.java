package com.android.coordinatorLayout;

import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Set;

public class QuChuChongfu {
    public static void main(String[] args) {
        HashMap<Long, Integer> longLongHashMap = new HashMap<>();
        HashMap<String, String> filter = new HashMap<>();
        String filePath = "";
        File file = new File(filePath);
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for (File subFile : files) {
                if(subFile.isFile()){
                    long length = subFile.length();
                    Integer aLong = longLongHashMap.get(length);
                    if(aLong==null){
                        longLongHashMap.put(length, 1);
                    }else{
                        longLongHashMap.put(length, aLong+1);

                        DecimalFormat df = new DecimalFormat("#.00");
                        String size = df.format((double) length / 1024) + "KB";

                        filter.put(subFile.getName(),size);
                    }
                }
            }
            System.out.println(longLongHashMap.size()+"---------------------------------"+filter.size());
            Set<String> strings = filter.keySet();
            for (String key : strings) {
                String value = filter.get(key);
                System.out.println(key+"  ---  "+value);
            }
        }else{
	        System.out.println("--------dir no exist-------------------------");
        }
    }
}