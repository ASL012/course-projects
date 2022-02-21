<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="am.MyBean.Result" %>
<jsp:useBean id="resultbean" class="am.MyBean.Result" scope="request" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>查询和借阅</title>
    <style>
        body{
            font-family:Verdans;font-size:12px;line-height:1.5;
            background-image:url(image/sr.jpg);
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
            width:500px;
            height:300px;
            margin:10px auto;
            background-color:rgba(233,226,193,.5);
            border-radius:3px;


        }
        form{
            width:500px;
            margin:5px auto;
            background-color:rgba(233,226,193,.5);
            border-radius:3px;
        }
    </style>

</head>
<body>
<div id="box" style="padding-top:10px;text-align:center;color:black;font-weight:bold;font-family:宋体;font-size:28px;">讲厅查询</div>


<font size="3">
    <form action="try_search" method=post name=form >
        &#8195&#8195校区：
        <select name="campus" size="1" >
            <option selected value="望江校区">望江校区</option>
            <option value="江安校区">江安校区</option>
            <option value="华西校区">华西校区</option>
        </select>
        <br>&#8195教学楼：
        <select name="building" size="1">
            <option selected value="望江基础教学A">望江基础教学A</option>
            <option value="望江基础教学B">望江基础教学B</option>
            <option value="望江基础教学C">望江基础教学C</option>
            <option value="望江东三教学楼">望江东三教学楼</option>
            <option value="江安一教A">江安一教A</option>
            <option value="江安一教B">江安一教B</option>
            <option value="江安一教C">江安一教C</option>
            <option value="江安综合楼">江安综合楼</option>
            <option value="江安二基楼">江安二基楼</option>
            <option value="华西第一教学楼">华西第一教学楼</option>
            <option value="华西第二教学楼">华西第二教学楼</option>
        </select>
         <!--<br >&#8195教室ID：
         <select name="result3" size="1" >
            <option selected value="2">基教A501</option>
            <option value="3">基教A601</option>
            <option value="4">基教B501</option>
            <option value="5">基教B601</option>
            <option value="6">基教C501</option>
            <option value="7">基教C601</option>
            <option value="8">东三201</option>
            <option value="9">东三301</option>
            <option value="10">一教A501</option>
            <option value="11">一教A601</option>
            <option value="12">一教B501</option>
            <option value="13">一教B601</option>
            <option value="14">一教C501</option>
            <option value="15">一教C601</option>
            <option value="16">综503</option>
            <option value="17">综504</option>
            <option value="18">华一会议室A</option>
            <option value="19">华一会议室B</option>
            <option value="20">华二会议室A</option>
            <option value="21">华二会议室B</option>
        </select>
        -->
        <br >容纳人数：
        <select name="volume" size="1" >
            <option selected value="50">50人</option>
            <option value="100">100人</option>
            <option value="150">150人</option>
            <option value="200">200人</option>
        </select>
        <br >&#8195&#8195日期：
        <select name="date" size="1" id="date">
            <script>
                var date = new Date();
                var month = date.getMonth()+1;
                var day = date.getDate();
                var dateStr = month + "月" + day + "日";
                console.log(dateStr);
                var temp=document.getElementById("date");
                var newOption=new Option(dateStr,dateStr);
                temp.appendChild(newOption);

                var date = new Date();
                var month = date.getMonth()+1;
                var day = date.getDate()+1;
                var dateStr = month + "月" + day + "日";
                console.log(dateStr);
                var newOption=new Option(dateStr,dateStr);
                temp.appendChild(newOption);

                var date = new Date();
                var month = date.getMonth()+1;
                var day = date.getDate()+2;
                var dateStr = month + "月" + day + "日";
                console.log(dateStr);
                var newOption=new Option(dateStr,dateStr);
                temp.appendChild(newOption);

                var date = new Date();
                var month = date.getMonth()+1;
                var day = date.getDate()+3;
                var dateStr = month + "月" + day + "日";
                console.log(dateStr);
                var newOption=new Option(dateStr,dateStr);
                temp.appendChild(newOption);

                var date = new Date();
                var month = date.getMonth()+1;
                var day = date.getDate()+4;
                var dateStr = month + "月" + day + "日";
                console.log(dateStr);
                var newOption=new Option(dateStr,dateStr);
                temp.appendChild(newOption);
            </script>
        </select>
        <br >&#8195时间段：
        <select name="time" size="1" >
            <option selected value="9:00-11:00">9:00-11:00</option>
            <option value="13:00-15:00">13:00-15:00</option>
            <option value="19:00-21:00">19:00-21:00</option>
        </select>
        <input style="margin-left:20px;" type="submit" value="查询" name="Button">
        <input type="submit" name="Button" value="返回主页" style="margin-top:10px; margin-left:300px;width:80px; text-align:center" />
        <input type="submit" name="Button" value="返回个人中心" style=" margin-left:10px;width:100px; text-align:center" />
    </form>
    <div id="box2">
    
    <jsp:getProperty name="resultbean" property="result" />

    </div>
</body>
</html>