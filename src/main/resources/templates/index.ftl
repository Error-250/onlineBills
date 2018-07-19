<#include "layout/header.ftl">
<#include "layout/menu.ftl">

<html>
    <@header>
        <title>Home</title>
    </@header>
    <body>
        <@menu />
        <div class="ui vertical center aligned segment" style="height: 80%;">
            <div class="ui container" style="position: absolute;top: 50%;left: 50%;transform: translate( -50%, -50% );">
                <h1 class="ui header">
                    Online Bills
                </h1>
                <h2>Manage your bill online!</h2>
                <div class="ui huge primary button">Get Started <i class="right arrow icon"></i></div>
            </div>
        </div>
    </body>
</html>