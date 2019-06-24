package com.cjdjyf.outbuyservice.utils;

import com.cjdjyf.outbuyservice.base.BaseEntity;
import com.cjdjyf.outbuyservice.utils.MyUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : cjd
 * @description : 自动代码生成POJO
 * @date : 2018/5/3 17:25
 */
public class CodeCreate extends BaseEntity implements Serializable {
    private String sourcePath;
    //表名
    private String tableName;
    //表名
    private String tableDesc;
    //包名
    private String packageName;

    private String firstUpperTableName;
    private String firstLowerTableName;
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
    private String service;
    private String serviceURL;
    //控制器存放路径
    private String controller;
    private String controllerURL;
    //控制器存放路径
    private String jsp;
    private String jspURL;

    //作者
    private String author;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;


    public CodeCreate(String sourcePath) {
        this.sourcePath = sourcePath;
    }


    /**
     * @author : cjd
     * @description : 生成Mapper
     * @return : void
     * @date : 20:08 2018/5/4
     */
    public void generatorMapper() throws Exception {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        //指定 逆向工程配置文件
        URL url = getClass().getClassLoader().getResource("generator/generatorConfig.xml");
        //获取项目根目录存放临时文件
       /* MyUtils.deleteDir(savePath);*/

        File file = new File(sourcePath);
        if (!file.exists()) {
            file.mkdir();
        }
        //主要用来获取context 设置属性
        Configuration config = new ConfigurationParser(warnings).parseConfiguration(new File(url.getFile()));
        for (Context context : config.getContexts()) {
            setContext(context, sourcePath);
        }

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        //替换指定字符
        MyUtils.propertiesChange(mapperURL, "Copyright(C)", "Copyright(C) " + author);
        MyUtils.propertiesChange(pojoURL, "Copyright(C)", "Copyright(C) " + author);
        MyUtils.propertiesChange(pojoURL, "@author", "@author " + author);

    }

    public void generatorController() throws Exception {
        String content = "package " + controller + ";\n" +
                "\n" +
                "import com.cjdjyf.outbuyservice.base.PageBean;\n" +
                "import com.cjdjyf.outbuyservice.base.ResultBean;\n" +
                "import com.cjdjyf.outbuyservice.pojo." + packageName + "." + firstUpperTableName + ";\n" +
                "import com.cjdjyf.outbuyservice.service." + packageName + "." + firstUpperTableName + "Service;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.stereotype.Controller;\n" +
                "import org.springframework.web.bind.annotation.GetMapping;\n" +
                "import org.springframework.web.bind.annotation.PostMapping;\n" +
                "import org.springframework.web.bind.annotation.RequestMapping;\n" +
                "import org.springframework.web.bind.annotation.ResponseBody;" +
                "\n" +
                "import javax.servlet.http.HttpServletRequest;\n" +
                "/**\n" +
                " * @author : cjd\n" +
                " * @description :\n" +
                " * @create : " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\n" +
                " **/\n" +
                "@Controller\n" +
                "@RequestMapping(\"/" + packageName + "/" + firstLowerTableName + "\")\n" +
                "public class " + firstUpperTableName + "Controller" + " {\n" +
                "    @Autowired\n" +
                "    private " + firstUpperTableName + "Service " + firstLowerTableName + "Service;\n" +
                "\n" +
                "    @GetMapping(\"/list\")\n" +
                "    public String list() {\n" +
                "        return \"" + packageName + "/" + firstLowerTableName + "/" + firstLowerTableName + "List\";\n" +
                "    }\n" +
                "\n" +
                "    @GetMapping(\"/addList\")\n" +
                "    public String " + firstLowerTableName + "AddList(String id, HttpServletRequest request) {\n" +
                "        request.setAttribute(\"" + firstLowerTableName + "\", " + firstLowerTableName + "Service.findById(id));\n" +
                "        return \"" + packageName + "/" + firstLowerTableName + "/" + firstLowerTableName + "AddList\";\n" +
                "    }\n" +
                "\n" +
                "    @PostMapping(\"/list\")\n" +
                "    @ResponseBody\n" +
                "    public PageBean<" + firstUpperTableName + "> forList(" + firstUpperTableName + " " + firstLowerTableName + ") {\n" +
                "        return " + firstLowerTableName + "Service.findPageBean(" + firstLowerTableName + ");\n" +
                "    }\n" +
                "\n" +
                "    @PostMapping(\"save\")\n" +
                "    @ResponseBody\n" +
                "    public ResultBean<String> save(" + firstUpperTableName + " " + firstLowerTableName + ") {\n" +
                "        return new ResultBean<>(" + firstLowerTableName + "Service.save(" + firstLowerTableName + "));\n" +
                "    }\n" +
                "\n" +
                "    @PostMapping(\"del\")\n" +
                "    @ResponseBody\n" +
                "    public ResultBean<String> del(" + firstUpperTableName + " " + firstLowerTableName + ") {\n" +
                "        return new ResultBean<>(" + firstLowerTableName + "Service.del(" + firstLowerTableName + "));\n" +
                "    }\n" +
                "}";
        generator(controllerURL, content);
    }

    public void generatorService() throws Exception {
        String content = "package " + service + ";\n" +
                "\n" +
                "import com.cjdjyf.outbuyservice.base.BaseService;\n" +
                "import " + mapper + "." + firstUpperTableName + "Mapper;\n" +
                "import " + pojo + "." + firstUpperTableName + ";\n" +
                "import org.springframework.stereotype.Service;\n" +
                "\n" +
                "\n" +
                "/**\n" +
                " * @author : cjd\n" +
                " * @description : \n" +
                " * @date : " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\n" +
                " */\n" +
                "@Service\n" +
                "public class " + firstUpperTableName + "Service extends BaseService<" + this.firstUpperTableName + "Mapper" + ", " + this.firstUpperTableName + "> {\n" +
                "}\n";
        generator(serviceURL, content);

    }

    public void generatorJsp() throws Exception {
        String content = "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\n" +
                "         pageEncoding=\"UTF-8\" %>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <%@include file=\"/WEB-INF/head/headCss.jsp\" %>\n" +
                "    <%@include file=\"/WEB-INF/head/headTag.jsp\" %>\n" +
                "    <%@include file=\"/WEB-INF/head/headJs.jsp\" %>\n" +
                "</head>\n" +
                "<body>\n" +
                "<loading:loading>\n" +
                "</loading:loading>\n" +
                "<div class=\"easyui-layout\" data-options=\"fit:true,border:false\">\n" +
                "    <div data-options=\"region:'center'\">\n" +
                "        <table id=\"" + firstLowerTableName + "List_list\" class=\"easyui-datagrid\"></table>\n" +
                "    </div>\n" +
                "    <form id=\"" + firstLowerTableName + "List_searchForm\">\n" +
                "        <div data-options=\"region:'east',iconCls:'icon-reload',title:'搜索条件',split:true\" class=\"searchForm-east\">\n" +
                "            <div class=\"easyui-layout\">\n" +
                "                <div data-options=\"region:'center'\" class=\"center\">\n" +
                "                    <div>\n" +
                "                        <span> 表名： </span>\n" +
                "                        <input class=\"easyui-textbox\" name=\"tabName\" title=\"\">\n" +
                "                    </div>\n" +
                "                    <div>\n" +
                "                        <span> 列名： </span>\n" +
                "                        <input class=\"easyui-textbox\" name=\"columnName\" title=\"\">\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "\n" +
                "                <div data-options=\"region:'south'\" class=\"south\">\n" +
                "                    <a class=\"easyui-linkbutton search_btn\" data-options=\"iconCls:'icon-search'\" id=\"" + firstLowerTableName + "List_search\"\n" +
                "                       onClick=\"$('#" + firstLowerTableName + "List_list').datagrid('load',$.serializeObject($('#" + firstLowerTableName + "List_searchForm')));\">搜索</a>\n" +
                "                    <a class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-clear'\" id=\"" + packageName + "AccountList_clear\"\n" +
                "                       onClick=\"$('#" + firstLowerTableName + "List_searchForm').form('clear');$('#" + firstLowerTableName + "List_list').datagrid('load',$.serializeObject($('#" + firstLowerTableName + "List_searchForm')));\">清空</a>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </form>\n" +
                "</div>\n" +
                "<div id=\"" + firstLowerTableName + "List_toolbar\">\n" +
                "    <a onclick=\"" + firstLowerTableName + "List_add(null);\" href=\"javascript:void(0);\"\n" +
                "       class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'fa fa-plus'\">新增</a>\n" +
                "    <a onclick=\"" + firstLowerTableName + "List_del();\" href=\"javascript:void(0);\"\n" +
                "       class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'fa fa-times '\">删除</a>\n" +
                "</div>\n" +
                "<script>\n" +
                "    $(function () {\n" +
                "        $('#" + firstLowerTableName + "List_list').datagrid({\n" +
                "            title: \"" + tableDesc + "\",\n" +
                "            url: '" + packageName + "/" + firstLowerTableName + "/list',\n" +
                "            method: 'post',\n" +
                "            toolbar: '#" + firstLowerTableName + "List_toolbar',\n" +
                "            singleSelect: true,\n" +
                "            loadMsg: '数据正在加载,请耐心等待...',\n" +
                "            fit: true,\n" +
                "            fitColumns: true,\n" +
                "            striped: true,\n" +
                "            animate: true,\n" +
                "            pagination: true,\n" +
                "            pageSize: 10,\n" +
                "            pageList: [5, 10, 15, 20, 30, 50],\n" +
                "            onLoadSuccess: function () {\n" +
                "                $('." + firstLowerTableName + "List_change').linkbutton({text: '修改', plain: true, iconCls: 'fa fa-repeat'});\n" +
                "            },\n" +
                "            columns: [[\n" +
                "                {title: 'id', field: 'id', checkbox: true},\n";
        Class cls = Class.forName(pojo + "." + firstUpperTableName);
        //得到所有属性
        Field[] fields = cls.getDeclaredFields();
        int num = fields.length + 1;
        int length = num < 7 ? 98 / num : 30;
        for (int i = 0; i < fields.length; i++) {//遍历
            //得到属性
            Field field = fields[i];
            //打开私有访问
            field.setAccessible(true);
            //获取属性
            String name = field.getName();
            content = content + "\n" + "{title: '表名', field: '" + name + "', width: '" + length + "%', align: 'center'},";
        }

        content = content + "\n" +
                "                {title: '操作列', field: 'a', width: '" + length + "%', align: 'center', formatter: operate}\n" +
                "            ]]\n" +
                "        });\n" +
                "    });\n" +
                "\n" +
                "    //操作列\n" +
                "    function operate(val, row, index) {\n" +
                "        var operation = '';\n" +
                "        operation += '<a href=\"javascript:void(0);\" href=\"javascript:void(0);\" class=\"" + firstLowerTableName + "List_change\" '\n" +
                "            + 'onClick=\"" + firstLowerTableName + "List_add(\\'' + row.id + '\\')\">修改</a>';\n" +
                "        return operation;\n" +
                "    }\n" +
                "\n" +
                "    //添加 修改\n" +
                "    function " + firstLowerTableName + "List_add(id) {\n" +
                "        window.location = document.getElementsByTagName(\"base\")[0].getAttribute(\"href\") + '" + packageName + "/" + firstLowerTableName + "/addList?id=' + id;\n" +
                "    }\n" +
                "\n" +
                "    //删除\n" +
                "    function " + firstLowerTableName + "List_del() {\n" +
                "        var " + firstLowerTableName + "List_list = $('#" + firstLowerTableName + "List_list');\n" +
                "        var row = " + firstLowerTableName + "List_list.datagrid('getSelected');\n" +
                "        $.messager.confirm('删除', '确认要删除吗？', function (r) {\n" +
                "            if (r) {\n" +
                "                $.ajax({\n" +
                "                    type: 'POST',\n" +
                "                    data: {\n" +
                "                        id: row.id\n" +
                "                    },\n" +
                "                    url: '" + packageName + "/" + firstLowerTableName + "/del',\n" +
                "                    success: function (data) {\n" +
                "                        if (data.code === 200) {\n" +
                "                            " + firstLowerTableName + "List_list.datagrid('reload');\n" +
                "                            showMsg(data.data);\n" +
                "                        } else {\n" +
                "                            showMsg('删除失败');\n" +
                "                        }\n" +
                "                    }\n" +
                "                })\n" +
                "            }\n" +
                "        });\n" +
                "    }\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>\n" +
                "\n";
        generator(jspURL, content);
    }

    private void generator(String savePath, String str) {
        File file = new File(savePath.substring(0, savePath.lastIndexOf("/")));
        MyUtils.mkDir(file);
        //字符流
        FileWriter fw = null;
        //缓冲流
        BufferedWriter bw = null;
        //读取文件内容保证在list中
        try {
            fw = new FileWriter(new File(savePath));
            bw = new BufferedWriter(fw);
            bw.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
        this.firstUpperTableName = MyUtils.toUpperCaseFirst(tableName.split("_"));
        this.firstLowerTableName = MyUtils.toLowerCaseFirstOne(firstUpperTableName);
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
        this.mapperURL = sourcePath + "/" + mapper.replaceAll("\\.", "/") + "/" + firstUpperTableName + "Mapper.java";
    }

    public String getPojoURL() {
        return pojoURL;
    }

    public void setPojo(String pojo) {
        this.pojo = pojo;
        this.pojoURL = sourcePath + "/" + pojo.replaceAll("\\.", "/") + "/" + firstUpperTableName + ".java";
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
        this.controllerURL = sourcePath + "/" + controller.replaceAll("\\.", "/") + "/" + firstUpperTableName + "Controller.java";
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getFirstUpperTableName() {
        return firstUpperTableName;
    }

    public void setFirstUpperTableName(String firstUpperTableName) {
        this.firstUpperTableName = firstUpperTableName;
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

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
        this.serviceURL = sourcePath + "/" + service.replaceAll("\\.", "/") + "/" + firstUpperTableName + "Service.java";
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
        setMapper("com.cjdjyf.outbuyservice.mapper." + packageName);
        setPojo("com.cjdjyf.outbuyservice.pojo." + packageName);
        setController("com.cjdjyf.outbuyservice.controller." + packageName);
        setService("com.cjdjyf.outbuyservice.service." + packageName);
    }

    public String getJsp() {
        return jsp;
    }

    public void setJsp(String jsp) {
        this.jsp = jsp;
        this.jspURL = jsp + firstLowerTableName + "/" + firstLowerTableName + "List.jsp";
    }

    public String getJspURL() {
        return jspURL;
    }

    public void setJspURL(String jspURL) {
        this.jspURL = jspURL;
    }

    public String getTableDesc() {
        return tableDesc;
    }

    public void setTableDesc(String tableDesc) {
        this.tableDesc = tableDesc;
    }
}
