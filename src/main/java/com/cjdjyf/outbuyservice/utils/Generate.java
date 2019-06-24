package com.cjdjyf.outbuyservice.utils;

import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * @author ：cjd
 * @description: 代码生成
 * @create 2019-06-24 12:06
 **/
public class Generate {
    @Test
    public void generatorJava() {
        String sourcePath = new File("").getAbsolutePath() + "/src/main/java";
        CodeCreate codeCreate = new CodeCreate(sourcePath);
        codeCreate.setTableName("sys_test");
        codeCreate.setTableDesc("测试数据");
        codeCreate.setPackageName("sys");
        codeCreate.setAuthor("cjd");
        codeCreate.setJsp(new File("").getAbsolutePath() + "/src/main/webapp/WEB-INF/jsp/sys/");
        //项目根目录下的代码生成文件夹
        try {
            codeCreate.generatorMapper();
            codeCreate.generatorController();
            codeCreate.generatorService();
        } catch (Exception e) {
        }
    }

    @Test
    public void generatorJsp() {
        String sourcePath = new File("").getAbsolutePath() + "/src/main/java";
        CodeCreate codeCreate = new CodeCreate(sourcePath);
        codeCreate.setTableName("sys_test");
        codeCreate.setTableDesc("测试数据");
        codeCreate.setPackageName("sys");
        codeCreate.setAuthor("cjd");
        codeCreate.setJsp(new File("").getAbsolutePath() + "/src/main/webapp/WEB-INF/jsp/sys/");
        //项目根目录下的代码生成文件夹
        try {
            codeCreate.generatorJsp();
        } catch (Exception e) {
        }
    }

}
