<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <%@include file="/WEB-INF/head/headCss.jsp" %>
    <%@include file="/WEB-INF/head/headTag.jsp" %>
    <%@include file="/WEB-INF/head/headJs.jsp" %>
</head>
<body>
<loading:loading>
</loading:loading>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center'">
        <table id="sysTestList_list" class="easyui-datagrid"></table>
    </div>
    <form id="sysTestList_searchForm">
        <div data-options="region:'east',iconCls:'icon-reload',title:'搜索条件',split:true" class="searchForm-east">
            <div class="easyui-layout">
                <div data-options="region:'center'" class="center">
                    <div>
                        <span> 表名： </span>
                        <input class="easyui-textbox" name="tabName" title="">
                    </div>
                    <div>
                        <span> 列名： </span>
                        <input class="easyui-textbox" name="columnName" title="">
                    </div>
                </div>

                <div data-options="region:'south'" class="south">
                    <a class="easyui-linkbutton search_btn" data-options="iconCls:'icon-search'" id="sysTestList_search"
                       onClick="$('#sysTestList_list').datagrid('load',$.serializeObject($('#sysTestList_searchForm')));">搜索</a>
                    <a class="easyui-linkbutton" data-options="iconCls:'icon-clear'" id="sysAccountList_clear"
                       onClick="$('#sysTestList_searchForm').form('clear');$('#sysTestList_list').datagrid('load',$.serializeObject($('#sysTestList_searchForm')));">清空</a>
                </div>
            </div>
        </div>
    </form>
</div>
<div id="sysTestList_toolbar">
    <a onclick="sysTestList_add(null);" href="javascript:void(0);"
       class="easyui-linkbutton" data-options="plain:true,iconCls:'fa fa-plus'">新增</a>
    <a onclick="sysTestList_del();" href="javascript:void(0);"
       class="easyui-linkbutton" data-options="plain:true,iconCls:'fa fa-times '">删除</a>
</div>
<script>
    $(function () {
        $('#sysTestList_list').datagrid({
            title: "测试数据",
            url: 'sys/sysTest/list',
            method: 'post',
            toolbar: '#sysTestList_toolbar',
            singleSelect: true,
            loadMsg: '数据正在加载,请耐心等待...',
            fit: true,
            fitColumns: true,
            striped: true,
            animate: true,
            pagination: true,
            pageSize: 10,
            pageList: [5, 10, 15, 20, 30, 50],
            onLoadSuccess: function () {
                $('.sysTestList_change').linkbutton({text: '修改', plain: true, iconCls: 'fa fa-repeat'});
            },
            columns: [[
                {title: 'id', field: 'id', checkbox: true},

{title: '表名', field: 'username', width: '16%', align: 'center'},
{title: '表名', field: 'password', width: '16%', align: 'center'},
{title: '表名', field: 'vvv', width: '16%', align: 'center'},
{title: '表名', field: 'aaa', width: '16%', align: 'center'},
{title: '表名', field: 'ccc', width: '16%', align: 'center'},
                {title: '操作列', field: 'a', width: '16%', align: 'center', formatter: operate}
            ]]
        });
    });

    //操作列
    function operate(val, row, index) {
        var operation = '';
        operation += '<a href="javascript:void(0);" href="javascript:void(0);" class="sysTestList_change" '
            + 'onClick="sysTestList_add(\'' + row.id + '\')">修改</a>';
        return operation;
    }

    //添加 修改
    function sysTestList_add(id) {
        window.location = document.getElementsByTagName("base")[0].getAttribute("href") + 'sys/sysTest/addList?id=' + id;
    }

    //删除
    function sysTestList_del() {
        var sysTestList_list = $('#sysTestList_list');
        var row = sysTestList_list.datagrid('getSelected');
        $.messager.confirm('删除', '确认要删除吗？', function (r) {
            if (r) {
                $.ajax({
                    type: 'POST',
                    data: {
                        id: row.id
                    },
                    url: 'sys/sysTest/del',
                    success: function (data) {
                        if (data.code === 200) {
                            sysTestList_list.datagrid('reload');
                            showMsg(data.data);
                        } else {
                            showMsg('删除失败');
                        }
                    }
                })
            }
        });
    }
</script>
</body>
</html>

