package com.android.coordinatorLayout;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class ReNameFile {

    public static final String SPLASH_SLOAN_PNG = "splash_sloan.png";
    public static final String SPLASH_SLOAN_PNG_RENAME = "splash_slogan.png";

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        String filePath = "F:\\hqwxsvn\\快题库\\后9个APP";
        File file = new File(filePath);
        printFileList(file);
    }

    public static void printFileList(File dirFile) {
        File current = dirFile;    //返回的必定是目录
        if (current == null || !current.exists()) {
            return;
        }
        if (current.isFile()) {
            judge(current);

        } else {
            File[] files = current.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    // System.out.println("[printFileList][dir]path:" + file.getPath());
                    printFileList(file);
                } else {
                    judge(file);
                }
            }
        }
    }

    private static void judge(File file) {
        boolean b = false;
        String name = file.getName();
         b = SPLASH_SLOAN_PNG.equals(name);
        if (b){
            String parentPath = file.getParentFile().getPath();
            File reNamefile = new File(parentPath, SPLASH_SLOAN_PNG_RENAME);

            System.out.println( "ReNameFile judge:"+name+"    parentPath="+parentPath);
            file.renameTo(reNamefile);
            System.out.println("[printFileList][file]path:" + file.getPath());
        }else{
            System.out.println("不符合="+file.getPath());
        }
    }
}