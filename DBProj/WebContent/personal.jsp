<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人界面</title>
</head>
<style>
    body{
        font-family:Verdans;font-size:12px;line-height:1.5;
        background-image:url(image/personal.jpg);
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




</style>
<body>
      <div id="box" style="padding-top:10px;text-align:center;color:black;font-weight:bold;font-family:宋体;font-size:27px;">个人主页</div>
      <form method="post"action="try_person">
      <br>
      <br>
      <input type="submit" name="Button" value="修改个人信息" style="margin-top:250px;margin-left:230px;width:80px; text-align:center" />
      <input type="submit" name="Button" value="讲厅查询&租借" style=" margin-left:120px;width:85px; text-align:center"/>
      <input type="submit" name="Button" value="预约记录" style="margin-left:125px; ;width:76px; text-align:center" />
      <input type="submit" name="Button" value="租借记录" style=" margin-left:130px;width:76px; text-align:center" />
          <br><input type="submit" name="Button" value="返回主页" style=" margin-left:750px;margin-top:35px;width:80px; text-align:center" />
      </form>
</body>
</html>