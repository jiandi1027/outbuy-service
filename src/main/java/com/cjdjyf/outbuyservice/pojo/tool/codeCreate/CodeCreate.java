package com.cjdjyf.outbuyservice.pojo.tool.codeCreate;

import com.cjdjyf.outbuyservice.base.BaseEntity;
import com.cjdjyf.outbuyservice.utils.MyUtils;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

/**
 * @author : cjd
 * @description : 自动代码生成POJO
 * @date : 2018/5/3 17:25
 */
public class CodeCreate extends BaseEntity<CodeCreate> implements Serializable {
    private String sourcePath;

    //表名
    private String tableName;
    private String upperCaseFirstTableName;

    //表引擎
    private String engine;
    //表描述
    private String tableComment;
    //mapper存放路径
    private String mapper;
    private String mapperURL;
    //实体类存放路径
    private String pojo;
    private String pojoURL;
    //service存放路径
    private String serviceURL;
    //控制器存放路径
    private String controller;
    private String controllerURL;
    //作者
    private String author;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;


    public CodeCreate(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
        this.upperCaseFirstTableName = MyUtils.toUpperCaseFirst(tableName.split("_"));
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMapperURL() {
        return mapperURL;
    }

    public void setMapper(String mapper) {
        this.mapper = mapper;
        this.mapperURL = sourcePath + "/" + mapper.replaceAll("\\.", "/") + "/" + upperCaseFirstTableName + "Mapper.java";
    }

    public String getPojoURL() {
        return pojoURL;
    }

    public void setPojo(String pojo) {
        this.pojo = pojo;
        this.pojoURL = sourcePath + "/" + pojo.replaceAll("\\.", "/") + "/" + upperCaseFirstTableName + ".java";
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getServiceURL() {
        return serviceURL;
    }

    public void setServiceURL(String serviceURL) {
        this.serviceURL = serviceURL;
    }

    public String getControllerURL() {
        return controllerURL;
    }

    public void setController(String controller) {
        this.controller = controller;
        this.controllerURL = sourcePath + "/" + controller.replaceAll("\\.", "/") + "/" + upperCaseFirstTableName + "Controller.java";
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getUpperCaseFirstTableName() {
        return upperCaseFirstTableName;
    }

    public void setUpperCaseFirstTableName(String upperCaseFirstTableName) {
        this.upperCaseFirstTableName = upperCaseFirstTableName;
    }

    public String getMapper() {
        return mapper;
    }

    public void setMapperURL(String mapperURL) {
        this.mapperURL = mapperURL;
    }

    public String getPojo() {
        return pojo;
    }

    public void setPojoURL(String pojoURL) {
        this.pojoURL = pojoURL;
    }

    public String getController() {
        return controller;
    }

    public void setControllerURL(String controllerURL) {
        this.controllerURL = controllerURL;
    }
}
