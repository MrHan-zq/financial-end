<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@include file="/page/admin/jsp/common/common.jspf" %>
    <%@include file="/page/admin/jsp/common/title.jspf" %>
<%--    <script src="${ctx}/static/ace/assets/js/html5shiv.js"/>--%>
<%--    <script src="${ctx}/static/ace/assets/js/respond.min.js"/>--%>
    <title>Document</title>
    <style>
        body {
            background: url('https://cdn.pixabay.com/photo/2018/08/14/13/23/ocean-3605547_1280.jpg') no-repeat;
            background-size: 100% 130%;
        }

        #login_box {
            width: 20%;
            height: 400px;
            background-color: #00000060;
            margin: auto;
            margin-top: 10%;
            text-align: center;
            border-radius: 10px;
            padding: 50px 50px;
        }

        h2 {
            color: #ffffff90;
            margin-top: 5%;
        }

        #input-box {
            margin-top: 5%;
        }

        span {
            color: #fff;
        }

        input {
            border: 0;
            width: 60%;
            font-size: 15px;
            color: #fff;
            background: transparent;
            border-bottom: 2px solid #fff;
            padding: 5px 10px;
            outline: none;
            margin-top: 10px;
        }

        button {
            margin-top: 50px;
            width: 60%;
            height: 30px;
            border-radius: 10px;
            border: 0;
            color: #fff;
            text-align: center;
            line-height: 30px;
            font-size: 15px;
            background-image: linear-gradient(to right, #30cfd0, #330867);
        }

        #sign_up {
            margin-top: 45%;
            margin-left: 60%;
        }

        a {
            color: #b94648;
        }
    </style>
</head>

<body>
<div id="login_box">
    <form action="${ctx}/client/login" method="post">
        <h2>LOGIN</h2>
        <div id="input_box">
            <input type="text" name="accountName" id="accountName" placeholder="请输入用户名"/>
        </div>
        <div class="input_box">
            <input type="password" name="password" id="password" placeholder="请输入密码"/>
        </div>
        <button type="submit">登录</button>
        <br>
    </form>
</div>
<script type="text/javascript">
    $(function () {

        $("form").submit(function () {
            var accountName = $("#accountName").val();
            var password = $("#password").val();
            if (accountName === null || accountName === undefined || accountName === '') {
                $("#message").text("请输入账号");
                return false;
            }
            if (password === null || password === undefined || password === '') {
                $("#message").text("请输入密码");
                return false;
            }
            return true;
        });

    });
</script>
</body>

</html>