package com.cjdjyf.outbuyservice.utils;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : cjd
 * @description : 工具类
 * @date : 22:39 2018/3/7
 */
public class MyUtils {

    /**
     * @return :java.lang.String
     * @Author : cjd
     * @Description : 获取UUID
     * @params :[]
     * @Date : 10:54 2018/4/19
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * @return : java.lang.String
     * @author : cjd
     * @description : 分割后的数组首字母大写 用于数据库表转换实体类
     * @params : [strings]
     * @date : 20:52 2018/5/4
     */
    public static String toUpperCaseFirst(String[] strings) {
        StringBuilder upper = new StringBuilder("");
        for (String s : strings) {
            upper.append(s.substring(0, 1).toUpperCase().concat(s.substring(1).toLowerCase()));
        }
        return upper.toString();
    }
    /**
     * @author : cjd
     * @description : 单字符首字母小子
     * @return : java.lang.String
     * @date : 14:03 2019/6/24
     */
    public static String toLowerCaseFirstOne(String s){
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }
    /**
     * @return : boolean
     * @author : cjd
     * @description :  删除文件夹
     * @params : [path]
     * @date : 20:28 2018/5/4
     */
    public static boolean deleteDir(String path) {
        File file = new File(path);
        if (!file.exists()) {//判断是否待删除目录是否存在
            System.err.println("The dir are not exists!");
            return false;
        }

        String[] content = file.list();//取得当前目录下所有文件和文件夹
        for (String name : content) {
            File temp = new File(path, name);
            if (temp.isDirectory()) {//判断是否是目录
                deleteDir(temp.getAbsolutePath());//递归调用，删除目录里的内容
                temp.delete();//删除空目录
            } else {
                if (!temp.delete()) {//直接删除文件
                    System.err.println("Failed to delete " + name);
                }
            }
        }
        return true;
    }

    /**
     * @return : void
     * @author : cjd
     * @description : 替换文件中的指定内容
     * @params : [filePath, srcStr, desStr]
     * @date : 9:47 2018/5/21
     */
    public static void propertiesChange(String filePath, String srcStr, String desStr) {
        //字符流
        FileReader fr = null;
        FileWriter fw = null;
        //缓冲流
        BufferedReader br = null;
        BufferedWriter bw = null;

        List list = new ArrayList<>();
        //读取文件内容保证在list中
        try {
            fr = new FileReader(new File(filePath));
            br = new BufferedReader(fr);
            String line = br.readLine();    //逐行复制
            while (line != null) {
                //修改指定内容
                if (line.contains(srcStr)) {
                    line = line.replace(srcStr, desStr);
                }
                list.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭流，顺序与打开相反
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //将list中内容输出到原文件中
        try {
            fw = new FileWriter(filePath);
            bw = new BufferedWriter(fw);
            for (Object s : list) {
                bw.write((String) s);
                bw.newLine();  //换行输出
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭流，顺序与打开相反
                bw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getStringNoBlank(String str) {
        if (str != null && !"".equals(str)) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            String strNoBlank = m.replaceAll("");
            return strNoBlank;
        } else {
            return str;
        }
    }


    /**
     * @return :void
     * @Author : cjd
     * @Description : 递归生成目录
     * @params :[file]
     * @Date : 9:59 2018/5/22
     */
    public static void mkDir(File file) {
        if (file.getParentFile().exists()) {
            file.mkdir();
        } else {
            mkDir(file.getParentFile());
            file.mkdir();
        }
    }



    public static String getUserName() {
        return "cjd";
    }
}
