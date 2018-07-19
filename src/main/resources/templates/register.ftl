<#include "layout/header.ftl">
<#include "layout/menu.ftl">

<html>
    <@header>
        <title>Register</title>
    </@header>
<body>
    <@menu />
    <div class="ui vertical center aligned segment" style="height: 80%;">
        <div class="ui container" style="position: absolute;top: 50%;left: 50%;transform: translate( -50%, -50% );">
            <div class="ui middle aligned center aligned grid">
                <div class="column">
                    <h2 class="ui teal image header">
                        <div class="content">
                            Sign up
                        </div>
                    </h2>
                    <form class="ui large form" id="loginForm" method="post">
                        <div class="ui stacked segment">
                            <div class="field">
                                <div class="ui left icon input">
                                    <i class="user icon"></i>
                                    <input type="text" id="registUsername" name="username" required placeholder="username" />
                                </div>
                            </div>
                            <div class="field">
                                <div class="ui left icon input">
                                    <i class="lock icon"></i>
                                    <input type="password" id="registPassword" name="password" required placeholder="Password"/>
                                </div>
                            </div>
                            <div class="field">
                                <div class="ui left icon input">
                                    <i class="lock icon"></i>
                                    <input type="password" id="confirmPassword" name="cPassword" required placeholder="Confirm Password"/>
                                </div>
                            </div>
                            <div class="ui fluid large teal submit button" onclick="regist();">Regist</div>
                        </div>

                        <div class="ui error message">${message!!}</div>

                    </form>
                </div>
            </div>
        </div>
    </div>
    <script>
        function regist() {
            var username = $("#registUsername").val();
            var password = $("#registPassword").val();
            var cPassword = $("#confirmPassword").val();
            if(password == cPassword) {
                $.post("/login", {'username': username, 'password': password}, function (data) {
                    if (data.login) {
                        // 登陆成功
                        window.location = "/home";
                    } else {
                        $(".ui.error.message").text("账号或密码错误!");
                        $(".ui.error.message").css({"display": "block"});
                    }
                })
            }else {
                $(".ui.error.message").text("密码不一致!");
                $(".ui.error.message").css({"display": "block"});
            }
        }
    </script>
</body>
</html>