<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <style>
        body{
            background-image:url(image/register.jpg);
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
            height:300px;
            margin:150px auto;
            background-color:rgba(183,225,228,.8);
        }


    </style>
</head>
<body>
<div id="box" >
    <div id="title" style="text-align: center;color:black;font-weight:bold;font-family:宋体;font-size:25px;">四川大学讲厅管理系统</div>
    <form  method="post"action="try_register">
        <fieldset>
            <legend>账号注册</legend>
            <p> 输入账号：<input name="id" type="text"  >  </p>
            <p> &#8195用户名：<input name="name" type="text"  >  </p>
            <p> 输入密码：<input name="password" type="password" > </p>
            <p> 确认密码：<input name="passwordcon" type="password" > </p>
            <p>
                  <input type="submit" name="cts" value="学生注册"/>
                  <input type="submit" name="cts" value="教师注册"/>
                  <input type="submit" name="cts" value="企业注册"/>
            </p>
        </fieldset>
    </form>


</div>

</div>

</body>
</html>
     