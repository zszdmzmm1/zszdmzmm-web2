<%--
  Created by IntelliJ IDEA.
  User: 86173
  Date: 2023/9/18
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <base href="http://localhost:8080/zszdmzmm_web2_war/">
    <link rel="stylesheet" href="build/assets/app.css">
    <title>博饼</title>
</head>
<body>
    <div class="d-flex justify-content-center my-5" id="button-group">
        <c:if test="${sessionScope.from == 'init'}">
            <button type=button class="btn btn-primary me-3" id="init">提交金额</button>
            <button type=button class="btn btn-primary me-3" id="draw" disabled>开始博饼</button>
        </c:if>
        <c:if test="${sessionScope.from != 'init'}">
            <button type=button class="btn btn-primary me-3" id="init" <c:if test="${sessionScope.count > 0}">disabled</c:if>>
                提交金额
                <c:if test="${sessionScope.count > 0}">:
                    <span id="money">${sessionScope.money}</span>
                </c:if>
            </button>
            <button type=button class="btn btn-primary me-3" id="draw" <c:if test="${sessionScope.count == 0}">disabled</c:if>>
                开始博饼: (剩余<span id="count"><span id="temp">${sessionScope.count}</span></span>次)
            </button>
            <button type="button" class="btn btn-primary me-3" id="reset">重置</button>
        </c:if>
    </div>

    <div class="d-flex justify-content-center my-5">
        <img src="diceImg?point=1" alt="" class="dice">
        <img src="diceImg?point=1" alt="" class="dice">
        <img src="diceImg?point=1" alt="" class="dice">
        <img src="diceImg?point=1" alt="" class="dice">
        <img src="diceImg?point=1" alt="" class="dice">
        <img src="diceImg?point=1" alt="" class="dice">
    </div>

    <div class="my-5" id="result"></div>

    <script src="build/assets/app.js"></script>

    <script>
        $("#init").click(function () {
            let money = (Math.random() * 300).toFixed(2);
            let count = Math.floor(money / 50);
            if (count > 5) {
                count = 5;
            }
            $.ajax({
                method: "POST",
                url: "setDiceCount",
                data: {from: "start", money: money, count: count}
            })
                .done(function () {
                    window.location.reload();
                })
        })


        $("#draw").click(function () {
            let money = $("#money").text();
            let count = $("#count").text();
            $.ajax({
                method: "GET",
                url: "draw",
                dataType: "json"
            })
                .done(function (data) {
                    let gift = data.gift;
                    let result = data.result;
                    result = result.substring(1, result.length -  1);
                    let resultArray = result.split(",");
                    $.ajax({
                        method: "GET",
                        url: "setDiceCount",
                        data: {from: "dice", money: money, count: count}
                    })
                        .done(function () {
                            $("#count").load("boBing #temp");
                            if($("#temp").text() == 1){
                                $("#draw").attr("disabled", true);
                            }
                            $(".dice").each(function (index, element){
                                let point = resultArray[index].trim();
                                $(element).attr("src", "diceImg?point=" + point);
                            })
                            $("#result").append(
                                `<div class="d-flex justify-content-center animate__animated animate__fadeIn animate__delay-1s">
                                    <span>投掷结果：${'${result}'}</span>
                                    <span>${'${gift}'}</span>
                                </div>`)
                        })
                })
        })


        $("#reset").click(function () {
            $.ajax({
                method: "GET",
                url: "reset"
            }).done(function () {
                window.location.reload();
            })
        })
    </script>
</body>
</html>
