<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <%@include file="/WEB-INF/head/headCss.jsp" %>
    <%@include file="/WEB-INF/head/headTag.jsp" %>
    <%@include file="/WEB-INF/head/headJs.jsp" %>
</head>
<body>
<loading:loading>
</loading:loading>
<div class="add_btn">
    <a id="sysTestAddList_save" class="easyui-linkbutton" data-options="iconCls: 'fa fa-floppy-o'"
       href="javascript:void(0);">保存</a>
    <a id="sysTestAddList_close" class="easyui-linkbutton" data-options="iconCls: 'fa fa-remove'"
       href="javascript:void(0);">返回</a>
</div>
<form method="post" id="sysTestAddList_form">
    <table id="sysTestAddList_table" class="add_table">
        <tr>
            <td>username：</td>
            <td><input name="username" class="easyui-textbox easyui-validatebox"
                       data-options="required:false,validType:['length[0,20]'],delay:'0'"
                       value="${sysTest.username}" title=""/></td>
        </tr>
        <tr>
            <td>password：</td>
            <td><input name="password" class="easyui-textbox easyui-validatebox"
                       data-options="required:false,validType:['length[0,20]'],delay:'0'"
                       value="${sysTest.password}" title=""/></td>
        </tr>
        <tr>
            <td>vvv：</td>
            <td><input name="vvv" class="easyui-textbox easyui-validatebox"
                       data-options="required:false,validType:['length[0,20]'],delay:'0'"
                       value="${sysTest.vvv}" title=""/></td>
        </tr>
        <tr>
            <td>aaa：</td>
            <td><input name="aaa" class="easyui-textbox easyui-validatebox"
                       data-options="required:false,validType:['length[0,20]'],delay:'0'"
                       value="${sysTest.aaa}" title=""/></td>
        </tr>
        <tr>
            <td>ccc：</td>
            <td><input name="ccc" class="easyui-textbox easyui-validatebox"
                       data-options="required:false,validType:['length[0,20]'],delay:'0'"
                       value="${sysTest.ccc}" title=""/></td>
        </tr>

    </table>
</form>
<script>
    $(function () {
    });
    //关闭
    $('#sysTestAddList_close').click(function () {
        window.history.go(-1);
    });
    //保存
    $('#sysTestAddList_save').click(function () {
        var sysTestAddList_form = $("#sysTestAddList_form");
        if (sysTestAddList_form.form('validate')) {
            sysTestAddList_save();
        } else {
            $.messager.show({
                title: '提示',
                msg: '数据不能为空'
            });
        }
    });

    function sysTestAddList_save() {
        $.ajax({
            type: 'POST',
            data: $.param({'id': '${sysTest.id}'}) + '&' + $('#sysTestAddList_form').serialize(),
            url: 'sys/sysTest/save',
            success: function (data) {
                if (data.code === 200) {
                    window.location = document.getElementsByTagName("base")[0].getAttribute("href") + "sys/sysTest/list";
                    showMsg(data.data);
                } else {
                    showMsg('编辑失败');
                }
            }
        });
    }
</script>
</body>
</html>
