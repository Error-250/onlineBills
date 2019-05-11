<#include "layout/header.ftl">
<#include "layout/menu.ftl">

<html>
    <@header>
        <title>User</title>
    </@header>
<body>
    <@menu />
    <div class="ui vertical center aligned segment" style="height: 80%;">
        <#if Session.user.role == "Admin">
            <div class="ui container">
                <div class="ui top attached tabular menu">
                    <a class="item active">用户管理</a>
                    <a class="item" href="/config">网站配置</a>
                </div>
                <div class="ui bottom attached segment">
                    <div class="ui form">
                        <div class="inline field">
                            <div class="ui search">
                                <div class="ui icon input">
                                    <input class="prompt" type="text" id="searchName" placeholder="Search Username">
                                    <i class="search icon"></i>
                                </div>
                                <button class="ui primary button" onclick="searchUser()">Search</button>
                                <div class="results"></div>
                            </div>
                        </div>
                    </div>
                    <table class="ui very basic celled table">
                        <thead>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Role</th>
                        <th>Operation</th>
                        </thead>
                        <tbody id="content"></tbody>
                    </table>
                </div>
            </div>
            <script>
                function searchUser() {
                    var uname = $("#searchName").val();
                    $.get("/users?user_name = " + uname, function(data) {
                        $("#content").empty();
                        for(i = 0;i < data.length;i++) {
                            $("#content").append(
                                "<tr>" +
                                    "<td>" + data[i].username + "</td>" +
                                    "<td class='ui input'><input id='p"+ i +"' placeholder='******' /></td>" +
                                    "<td>普通用户</td>" +
                                    "<td><i class='times icon' onclick=''></i></td>" +
                                "</tr>"
                            );
                        }
                    })
                }
            </script>
        <#else>
            <div class="ui container">
                <div class="ui list">
                    <p>用户名:   ${Session.user.username}</p>
                    <p>用户角色: 普通用户</p>
                </div>
            </div>
        </#if>
    </div>
</body>
</html>