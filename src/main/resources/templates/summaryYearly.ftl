<#include "layout/header.ftl">
<#include "layout/menu.ftl">
<#include "layout/pager.ftl">

<html>
    <@header>
    <title>SummaryYearly</title>
    </@header>
<body>
    <@menu />
    <div class="ui container">
        <div class="ui center aligned segment">
            <div class="ui form">
                <div class="inline field">
                    <select id="searchOption" class="ui dropdown">
                        <option value="1">最近十年</option>
                        <option value="0">全部</option>
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
            var option = $("#searchOption").val();
            $.get("/summaryYearly?option=" + option, function (data) {
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
                        "<td>"+ data[i].date +"</td>" +
                        "<td class='money'>"+ data[i].money.toFixed(2) +"</td>" +
                        "</tr>"
                );
            }
            $("#searchContent").append(
                    "<tr>" +
                    "<td>总计 / 本页总计: </td>" +
                    "<td class='money'>"+ allMoneys.toFixed(2) +"</td>" +
                    "<td class='money'>"+ summaryMoney(data).toFixed(2) +"</td>" +
                    "</tr>"
            );
            showMoney();
        }
    </script>
</body>
</html>