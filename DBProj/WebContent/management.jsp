<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="am.MyBean.Result" %>
<jsp:useBean id="resultbean" type="am.MyBean.Result" scope="request" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理员审核</title>
    <style>
        body{
            font-family:Verdans;font-size:12px;line-height:1.5;
            background-image:url(image/manager.jpg);
            background-repeat:no-repeat;
            background-size:100% 100%;
            background-attachment: fixed;
        }
        #box{
            width:130px;
            height:40px;
            background-color:rgba(228,218,169,0.7);
            border-radius:8px;
            margin:auto;
        }
        #box2{
            width:800px;
            height:40px;
            margin:10px auto;
        }
    </style>
</head>
<body>
<div id="box" style="padding-top:10px;text-align:center;color:black;font-weight:bold;font-family:宋体;font-size:28px;">数据管理</div>
<div id="box2"> <jsp:getProperty name="resultbean" property="result" /></div>

</body>
</html>