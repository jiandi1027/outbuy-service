package com.cjdjyf.outbuyservice.utils;

import com.cjdjyf.outbuyservice.pojo.tool.codeCreate.CodeCreate;
import com.cjdjyf.outbuyservice.pojo.tool.codeCreate.MapperCreate;

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
        codeCreate.setTableName("test");
        codeCreate.setMapper("com.cjdjyf.outbuyservice.mapper.test");
        codeCreate.setPojo("com.cjdjyf.outbuyservice.pojo.test");
        codeCreate.setController("com.cjdjyf.outbuyservice.controller.test");
        codeCreate.setAuthor("cjd");
        //构造器 传入参数
        MapperCreate mapperCreate = new MapperCreate(
                codeCreate.getTableName(),
                codeCreate.getMapper(),
                codeCreate.getPojo());
        //项目根目录下的代码生成文件夹
        //generatorController(codeCreate);
        try {
            //生成代码
            mapperCreate.generator(sourcePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //替换指定字符
        MyUtils.propertiesChange(codeCreate.getMapperURL(), "Copyright(C)", "Copyright(C) " + codeCreate.getAuthor());
        MyUtils.propertiesChange(codeCreate.getPojoURL(), "Copyright(C)", "Copyright(C) " + codeCreate.getAuthor());
        MyUtils.propertiesChange(codeCreate.getPojoURL(), "@author", "@author " + codeCreate.getAuthor());
    }

    private static void generatorController(String sourcePath) {
        File file = new File(sourcePath + "/com/cjdjyf.outbuyservice/controller/ControllerTemplate");
        System.out.println(file.length());


    }
}
