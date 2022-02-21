<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="am.MyBean.Teacher" %>
<jsp:useBean id="tbean" type="am.MyBean.Teacher" scope="request" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>教师修改个人信息</title>
    <style>
        body{
            font-family:Verdans;font-size:12px;line-height:1.5;
            background-image:url(image/modinfo.jpg);
            background-repeat:no-repeat;
            background-size:100% 100%;

            background-attachment: fixed;
        }
        #box{
            width:200px;
            height:40px;
            background-color:rgba(228,218,169,0.7);
            border-radius:8px;
            margin:auto;
        }
        form{
            width:600px;
            margin:30px auto;
        }
        fieldset{
            margin:auto;
            background-color:rgba(233,226,193,.5);
        }
    </style>
</head>
<body>
<div id="box" style="padding-top:10px;text-align:center;color:black;font-weight:bold;font-family:宋体;font-size:28px;">修改教师信息</div>
<form  method="post"action="try_modify">
    <fieldset>
        <p style="padding-left:10px;text-align:center;color:black;font-weight:bold;font-family:宋体;font-size:18px"> 姓名：<input name="name" type="text"  value=<jsp:getProperty name="tbean" property="name" /> > </p>
        <p style="padding-left:10px;text-align:center;color:black;font-weight:bold;font-family:宋体;font-size:18px"> 性别：<input name="gender" type="text" value=<jsp:getProperty name="tbean" property="gender" /> > </p>
        <p style="padding-left:10px;text-align:center;color:black;font-weight:bold;font-family:宋体;font-size:18px"> 年龄：<input name="age" type="text" value=<jsp:getProperty name="tbean" property="age" />   > </p>
        <p style="padding-left:10px;text-align:center;color:black;font-weight:bold;font-family:宋体;font-size:18px"> 学院：<input name="institute" type="text" value=<jsp:getProperty name="tbean" property="institute" />  > </p>
        <p style="padding-left:10px;text-align:center;color:black;font-weight:bold;font-family:宋体;font-size:18px"> 职称：<input name="title" type="text" value=<jsp:getProperty name="tbean" property="title" /> > </p>
        <p style="padding-left:10px;text-align:center;color:black;font-weight:bold;font-family:宋体;font-size:18px"> 密码：<input name="password" type="password"  >  </p>
        <p style="padding-left:10px;text-align:center;color:black;font-weight:bold;font-family:宋体;font-size:18px"> 确认密码：<input name="passwordcon" type="password"  > </p>
        <p style="padding-left:10px;text-align:center;color:black;font-weight:bold;font-family:宋体;font-size:18px"> 联系电话：<input name="tel" type="text" value=<jsp:getProperty name="tbean" property="tel" />  > </p>
        <p>
            <input type="submit" name="Button" value="确定" style=" margin-left:200px;width:50px; text-align:center" />
            <input type="submit" name="Button" value="取消" style=" margin-left:90px;width:50px; text-align:center"/>
        </p>
    </fieldset>
</form>
</body>
</html>