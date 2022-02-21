<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登陆</title>
    <style>

        body{
            background-image:url(image/login.jpg);
            background-repeat:no-repeat;
            background-size:100% 100%;
            background-attachment: fixed;
        }
        #title{
            width: 100%;
            margin:auto;
        }
        #box{
            width:400px;
            height:220px;
            margin:150px auto;
            background-color:rgba(255,255,204,0.6);
        }


    </style>
</head>
<body>
<div id="box" >
    <div id="title" style="text-align: center;color:black;font-weight:bold;font-family:宋体;font-size:25px;">四川大学讲厅管理系统</div>
    <form  method="post"action="try_login">
        <fieldset>
            <legend>账号登录</legend>
        <p> 输入账号：<input name="id" type="text" >  </p>
        <p> 输入密码：<input name="password" type="password" > </p>
        <p>
           <input type=submit name="stc" value="学生登陆"/>
           <input type=submit name="stc" value="教师登陆"/>
           <input type=submit name="stc" value="企业登陆"/>
           <input type=submit name="stc" value="管理员登陆"/>
         </p>
        </fieldset>
    </form>


</div>


</div>

</body>
</html>

