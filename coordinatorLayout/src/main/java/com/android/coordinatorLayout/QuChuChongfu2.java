package com.android.coordinatorLayout;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class QuChuChongfu2 {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        HashMap<Long, Integer> longLongHashMap = new HashMap<>();
        ArrayList<Long> filterList = new ArrayList<>();
        String filePath = "I:\\B 111111";
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
                        filterList.add(length);
                    }
                }
            }
            System.out.println(filterList.size()+"---------------------------------"+filterList.size());
            filterList.sort(new Comparator<Long>() {
                @Override
                public int compare(Long o1, Long o2) {
                    return o1<=o2?1:-1;
                }
            });

            for (Long length : filterList) {

                DecimalFormat df = new DecimalFormat("#.00");
                String size = df.format((double) length / 1024) + "KB";
                System.out.println("size  ---  "+size);
            }
        }else{
	        System.out.println("--------dir no exist-------------------------");
        }
    }
}