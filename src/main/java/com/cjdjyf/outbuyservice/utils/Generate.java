package com.cjdjyf.outbuyservice.utils;

import java.io.File;

/**
 * @author ：cjd
 * @description: 代码生成
 * @create 2019-06-24 12:06
 **/
public class Generate {
    public static void main(String[] args) {
        String sourcePath = new File("").getAbsolutePath() + "/src/main/java";
        CodeCreate codeCreate = new CodeCreate(sourcePath);
        codeCreate.setTableName("sys_test");
        codeCreate.setPackageName("sys");
        codeCreate.setAuthor("cjd");
        codeCreate.setJsp(new File("").getAbsolutePath()+"/src/main/webapp/jsp/sys/");
        //项目根目录下的代码生成文件夹
        try {
            //生成代码
            //codeCreate.generatorMapper();
            codeCreate.generatorController();
            codeCreate.generatorService();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
