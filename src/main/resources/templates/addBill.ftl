<#include "layout/header.ftl">
<#include "layout/menu.ftl">

<html>
    <@header>
    <title>Home</title>
    </@header>
<body>
    <@menu />
    <div class="ui container">
        <div class="ui segment">
            <form class="ui form" action="/addBill" method="post">
                <div class="inline field">
                    <label>Money: </label>
                    <select class="ui dropdown" name="type">
                        <option value="0">支出</option>
                        <option value="1">收入</option>
                    </select>
                    <input type="text" name="money" min="0" />
                </div>
                <div class="field">
                    <label>Info: </label>
                    <select type="text" name="info" class="ui search dropdown">
                        <option></option>
                        <#if tags??>
                            <#list tags as tag>
                                <option>${tag}</option>
                            </#list>
                        </#if>
                    </select>
                </div>
                <div class="field">
                    <label>Date: </label>
                    <input type="date" name="date" />
                </div>
                <button class="ui primary button" type="submit">Submit</button>
            </form>
        </div>
    </div>
    <script>
        $("select.search.dropdown").dropdown({allowAdditions: true});
    </script>
</body>
</html>