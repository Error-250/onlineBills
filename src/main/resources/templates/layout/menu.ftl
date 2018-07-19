<#macro menu>
<div class="ui inverted large top menu">
    <div class="ui container">
        <a class="active item" href="/home">Home</a>
        <a class="item" href="/addBill">Add bill</a>
        <div class="ui dropdown item">
            Summary
            <i class="dropdown icon"></i>
            <div class="menu">
                <a class="item" href="/viewSummaryMonthly">By Month</a>
                <a class="item" href="/viewSummaryYearly">By Year</a>
                <a class="item" href="/viewSummaryByComments">By Comments</a>
            </div>
        </div>
        <div class="right menu">
            <#if Session.user??>
                <a class="item">${Session.user.username}</a>
                <a class="item" href="/signout">Sign out</a>
            <#else >
                <a class="active blue item" onclick="$('.ui.modal').modal('show');">Sign in</a>
                <a class="item" href="/register">Sign Up</a>
            </#if>
        </div>
    </div>
</div>

<script>
    $(".ui.dropdown.item").dropdown();
</script>
<#if  !username??>
<div class="ui modal">
    <i class="close icon"></i>
    <div class="content">
        <div class="ui middle aligned center aligned grid">
            <div class="column">
                <h2 class="ui teal image header">
                    <div class="content">
                        Sign in
                    </div>
                </h2>
                <form class="ui large form" action="/login" id="loginForm" method="post">
                    <div class="ui stacked segment">
                        <div class="field">
                            <div class="ui left icon input">
                                <i class="user icon"></i>
                                <input type="text" id="username" name="username" required placeholder="username">
                            </div>
                        </div>
                        <div class="field">
                            <div class="ui left icon input">
                                <i class="lock icon"></i>
                                <input type="password" id="password" name="password" required placeholder="Password">
                            </div>
                        </div>
                        <div class="ui fluid large teal submit button" onclick="login();">Login</div>
                    </div>

                    <div class="ui error message">${message!!}</div>

                </form>

                <div class="ui message">
                    New to us? <a href="/register">Sign Up</a>
                </div>
            </div>
        </div>
    </div>
    <script>
        function login() {
            var username = $("#username").val();
            var password = $("#password").val();
            $.post("/login", {'username': username, 'password': password}, function (data) {
                if(data.login) {
                    // 登陆成功
                    window.location = "/home";
                }else {
                    $(".ui.error.message").text("账号或密码错误!");
                    $(".ui.error.message").css({"display":"block"});
                }
            })
        }
    </script>
</div>
</#if>
</#macro>