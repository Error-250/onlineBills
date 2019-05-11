<#include "layout/header.ftl">
<#include "layout/menu.ftl">
<#include "layout/pager.ftl">

<html>
    <@header>
    <title>Config</title>
    </@header>
<body>
    <@menu />
    <div class="ui container">
        <div class="ui top attached tabular menu">
            <a class="item" href="/viewUser">用户管理</a>
            <a class="item active">网站配置</a>
        </div>
        <div class="ui bottom attached segment">
            <table class="ui very basic celled table">
                <thead>
                <th>#</th>
                <th>key</th>
                <th>value</th>
                </thead>
                <tbody id="content">
                    <#list configs as config>
                        <tr>
                            <td>#{config_index + 1}</td>
                            <td>
                                <div class="ui comments">
                                    <div class="comment content">
                                        <div class="author">${config.key}</div>
                                        <div class="text">${config.comment}</div>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="ui toggle checkbox" onclick="">
                                    <#if config.value == "true">
                                        <input type="checkbox" name="public" checked="true">
                                    <#else>
                                        <input type="checkbox" name="public">
                                    </#if>
                                    <label></label>
                                </div>
                            </td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>