<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <style>
        body{
            background-image:url(image/背景图.jpg);
            background-repeat:no-repeat;
            background-size:100% 100%;
            background-attachment: fixed;
        }
        #box1{
            background-image:url(image/杜甫草堂.jpg);
            width:240px;
            height:350px;
            border-radius:5px;
            background-color:lightgoldenrodyellow;
            float:left;
            margin-top:20px;
            margin-left:120px;

        }
        #box2{
            background-image:url(image/读书会.jpg);
            width:240px;
            height:350px;
            border-radius:5px;
            background-color:lightgoldenrodyellow;
            float:left;
            margin-top:20px;
            margin-left:50px;

        }
        #box3{
            background-image:url(image/拼图.jpg);
            width:240px;
            height:350px;
            border-radius:5px;
            background-color:lightgoldenrodyellow;
            float:left;
            margin-top:20px;
            margin-left:50px;

        }

    </style>
</head>
<body>
<div id="title" style="text-align: center;margin-top:60px;color:black;font-weight:bold;font-family:宋体;font-size:30px;">四川大学讲厅管理系统</div>
    <div id="box1" ></div>
    <div id="box2" ></div>
    <div id="box3" ></div>
<p>
    <br>
    <a href="login.jsp" style="position:relative;top:350px;left:0px;">立即登录</a>
    <br>
    <a href="register.jsp" style="position:relative;top:350px;;" >立即注册</a>
</p>


</div>

</body>
</html>