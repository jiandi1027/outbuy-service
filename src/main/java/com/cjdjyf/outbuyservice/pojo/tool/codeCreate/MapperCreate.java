package com.cjdjyf.outbuyservice.pojo.tool.codeCreate;


import com.cjdjyf.outbuyservice.utils.MyUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : cjd
 * @description : 代码生成类
 * @date : 2018/5/4 10:30
 */
public class MapperCreate implements Serializable {
    private String tableName;
    private String mapper;
    private String pojo;
    //生成代码存放目录
    /*private final String SAVE_ADDRESS = ".\\src\\code";*/

    public MapperCreate(String tableName, String mapper, String pojo) {
        this.tableName = tableName;
        this.mapper = mapper;
        this.pojo = pojo;
    }

    /**
     * @author : cjd
     * @description : 代码生成
     * @return : void
     * @date : 20:08 2018/5/4
     */
    public void generator(String savePath) throws Exception {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        //指定 逆向工程配置文件
        URL url = getClass().getClassLoader().getResource("generator/generatorConfig.xml");
        //获取项目根目录存放临时文件
       /* MyUtils.deleteDir(savePath);*/

        File file = new File(savePath);
        if (!file.exists()) {
            file.mkdir();
        }
        //主要用来获取context 设置属性
        Configuration config = new ConfigurationParser(warnings).parseConfiguration(new File(url.getFile()));
        for (Context context : config.getContexts()) {
            setContext(context, savePath);
        }

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    /**
     * @author : cjd
     * @description : 设置生成mapper文件属性
     * @params : [context]
     * @return : void
     * @date : 19:50 2018/5/4
     */
    private void setContext(Context context, String savePath) {
        //mapper地址
        context.getSqlMapGeneratorConfiguration().setTargetProject(savePath);
        context.getSqlMapGeneratorConfiguration().setTargetPackage(mapper);
        context.getJavaClientGeneratorConfiguration().setTargetProject(savePath);
        context.getJavaClientGeneratorConfiguration().setTargetPackage(mapper);
        //实体类地址
        context.getJavaModelGeneratorConfiguration().setTargetProject(savePath);
        context.getJavaModelGeneratorConfiguration().setTargetPackage(pojo);
        //设置表名
        context.getTableConfigurations().get(0).setTableName(tableName);
        //首字母大写
        String[] split = tableName.split("_");
        context.getTableConfigurations().get(0).setDomainObjectName(tableName);
        //表名首字母大写
        context.getTableConfigurations().get(0).setDomainObjectName(MyUtils.toUpperCaseFirst(split));
    }

}
