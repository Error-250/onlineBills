<#macro pager>
<div class="ui pagination menu">
    <a class="item" id="pagePre" onclick="prePage()">
        上一页
    </a>
    <div class="disabled item" id="pageContent">
        ...
    </div>
    <a class="item" id="pageNext" onclick="nextPage()">
        下一页
    </a>
</div>
<script>
    // 全部数据
    var allDatas = [];
    // 每页数据量
    var dataPerPage = 10;
    // 当前页
    var nowPage = 0;

    // 查分数据
    function splitData(page) {
        if(allDatas.length > dataPerPage) {
            // 超过一页计算页数
            var pages = 0;
            if(allDatas.length % dataPerPage === 0)
                pages = allDatas / dataPerPage;
            else
                pages = parseInt(allDatas.length / dataPerPage) + 1;

            // 容错处理
            if(page < 0)
                page = 0;
            if(page >= pages)
                page = pages - 1;
            // 显示当前页和总页数
            $("#pageContent").text((page + 1) + " / " + pages);
            if(page === 0)
                $("#pagePre").removeClass("disabled").removeClass("item")
                        .addClass("disabled").addClass("item");
            else
                $("#pagePre").removeClass("disabled").removeClass("item")
                        .addClass("item");

            if(page === pages - 1)
                $("#pageNext").removeClass("disabled").removeClass("item")
                        .addClass("disabled").addClass("item");
            else
                $("#pageNext").removeClass("disabled").removeClass("item")
                        .addClass("item");
        }else {
            page = 0;
            $("#pageContent").text("1 / 1");
            $("#pagePre").removeClass("disabled").removeClass("item")
                    .addClass("disabled").addClass("item");
            $("#pageNext").removeClass("disabled").removeClass("item")
                    .addClass("disabled").addClass("item");
        }
        nowPage = page;
        var subDatas = [];
        var start = page * dataPerPage;
        var end = (page + 1) * dataPerPage;
        if(end >= allDatas.length)
            end = allDatas.length;
        for(i = start;i< end;i++) {
            subDatas.push(allDatas[i]);
        }
        showData(subDatas);
    }

    function prePage() {
        splitData(nowPage - 1);
    }

    function nextPage() {
        splitData(nowPage + 1);
    }

    // 求所有money的和
    function summaryMoney(data) {
        var moneys = 0;
        for(i = 0;i < data.length;i++) {
            moneys += data[i].money;
        }
        return moneys;
    }

    function showMoney() {
        var tags = document.getElementsByClassName("money");
        for(i = 0;i < tags.length;i++) {
            if(parseInt(tags[i].innerHTML) < 0)
                tags[i].style.color = '#f00';
            else
                tags[i].style.color = '#0f0';
        }
    }
</script>
</#macro>