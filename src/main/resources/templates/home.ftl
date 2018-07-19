<#include "layout/header.ftl">
<#include "layout/menu.ftl">
<#include "layout/pager.ftl">

<html>
    <@header>
    <title>Home</title>
    </@header>
    <body>
        <@menu />
        <div class="ui container">
            <div class="ui center aligned segment">
                <div class="ui form">
                    <div class="inline field">
                        <select id="searchYear" class="ui dropdown">
                            <#list years as year>
                                <option value="#{year}">#{year}</option>
                            </#list>
                        </select>
                        <select id="searchMonth" class="ui dropdown">
                            <#list 1..12 as month>
                                <option value="#{month}">#{month}</option>
                            </#list>
                        </select>
                        <button class="ui primary button" onclick="search()">Search</button>
                    </div>
                </div>
                <table class="ui very basic celled table">
                    <thead>
                        <th>#</th>
                        <th>时间</th>
                        <th>金额</th>
                        <th>备注</th>
                    </thead>
                    <tbody id="searchContent">
                    </tbody>
                </table>
                <@pager />
            </div>
        </div>
        <script>
            var allMoneys = 0;
            // html就绪处理函数
            $(document).ready( function () {
                var now = new Date();
                $("#searchMonth").val(now.getMonth() + 1);
            });
            // 按照月份搜索账单
            function search() {
                var year = $("#searchYear").val();
                var month = $("#searchMonth").val();
                $.get("/search?year=" + year +"&month=" + month, function (data) {
                    allDatas = data;
                    allMoneys = summaryMoney(data);
                    splitData(0);
                })
            }
            // 显示数据
            function showData(data) {
                $("#searchContent").empty();
                for(i = 0;i<data.length;i++) {
                    $("#searchContent").append(
                            "<tr>" +
                            "<td>"+ data[i].id +"</td>" +
                            "<td>"+ new Date(data[i].date).toLocaleDateString() +"</td>" +
                            "<td class='money'>"+ data[i].money.toFixed(2) +"</td>" +
                            "<td>"+ data[i].info +"</td>" +
                            "</tr>"
                    );
                }
                $("#searchContent").append(
                        "<tr>" +
                        "<td>本月总计: </td>" +
                        "<td class='money'>"+ allMoneys.toFixed(2) +"</td>" +
                        "<td>本页总计: </td>" +
                        "<td class='money'>"+ summaryMoney(data).toFixed(2) +"</td>" +
                        "</tr>"
                );
                showMoney();
            }
        </script>
    </body>
</html>