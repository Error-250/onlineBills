<#include "layout/header.ftl">
<#include "layout/menu.ftl">
<#include "layout/pager.ftl">

<html>
    <@header>
    <title>SummaryByComments</title>
    </@header>
<body>
    <@menu />
    <div class="ui container">
        <div class="ui center aligned segment">
            <div class="ui form">
                <div class="inline field">
                    <select id="searchYear" class="ui dropdown">
                        <option value="-1">全部</option>
                        <#list years as year>
                            <option value="#{year}">#{year}</option>
                        </#list>
                    </select>
                    <select id="searchMonth" class="ui dropdown">
                        <option value="-1">全部</option>
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
                <th>备注</th>
                <th>金额</th>
                </thead>
                <tbody id="searchContent">
                    <#--<#if datas?size != 0>-->
                        <#--<#assign sum = 0>-->
                        <#--<#list datas as data>-->
                            <#--<tr>-->
                                <#--<td>#{data_index + 1}</td>-->
                                <#--<td>${data.info}</td>-->
                                <#--<td>#{data.money}</td>-->
                            <#--</tr>-->
                            <#--<#assign sum = sum + data.money>-->
                        <#--</#list>-->
                        <#--<tr>-->
                            <#--<td>总计: </td>-->
                            <#--<td>#{sum}</td>-->
                            <#--<td></td>-->
                        <#--</tr>-->
                    <#--<#else >-->
                        <#--<h2>您还没有账单呢!</h2>-->
                    <#--</#if>-->
                </tbody>
            </table>
            <@pager />
        </div>
    </div>
    <script>
        var allMoneys = 0;
        // 根据备注汇总账单
        function search() {
            var year = $("#searchYear").val();
            var month = $("#searchMonth").val();
            $.get("/summaryByComments?year=" + year + "&month=" + month, function (data) {
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
                        "<td>"+ (i + 1) +"</td>" +
                        "<td>"+ data[i].info +"</td>" +
                        "<td class='money'>"+ data[i].money.toFixed(2) +"</td>" +
                        "</tr>"
                );
            }
            $("#searchContent").append(
                    "<tr>" +
                    "<td>总计/ 本页统计: </td>" +
                    "<td class='money'>"+ allMoneys.toFixed(2) +"</td>" +
                    "<td class='money'>"+ summaryMoney(data).toFixed(2) +"</td>" +
                    "</tr>"
            );
            showMoney();
        }
    </script>
</body>
</html>