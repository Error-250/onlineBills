<#include "layout/header.ftl">
<#include "layout/menu.ftl">
<#include "layout/pager.ftl">

<html>
    <@header>
    <title>SummaryMonthly</title>
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
                    <button class="ui primary button" onclick="search()">Search</button>
                </div>
            </div>
            <table class="ui very basic celled table">
                <thead>
                <th>#</th>
                <th>时间</th>
                <th>金额</th>
                </thead>
                <tbody id="searchContent">
                </tbody>
            </table>
            <@pager />
        </div>
    </div>
    <script>
        var allMoneys = 0;
        // 按照年搜索按照月汇总账单
        function search() {
            var year = $("#searchYear").val();
            $.get("/summaryMonth?year=" + year, function (data) {
                allDatas = data;
                allMoneys = summaryMoney(data);
                splitData(0);
            })
        }
        // 显示账单数据
        function showData(data) {
            var year = $("#searchYear").val();
            $("#searchContent").empty();
            for(i = 0;i<data.length;i++) {
                $("#searchContent").append(
                        "<tr>" +
                        "<td>"+ (i + 1) +"</td>" +
                        "<td>"+ year + "-" + data[i].date +"</td>" +
                        "<td class='money'>"+ data[i].money.toFixed(2) +"</td>" +
                        "</tr>"
                );
            }
            $("#searchContent").append(
                    "<tr>" +
                    "<td>本年度总计: </td>" +
                    "<td class='money'>"+ allMoneys.toFixed(2) +"</td>" +
                    "<td></td>" +
                    "</tr>"
            );
            showMoney();
        }
    </script>
</body>
</html>