<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2022/5/19
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>login</title>

    <link rel="stylesheet" href="css/loginCss.css" type="text/css">
    <c:if test="${!empty requestScope}">
        <script>
            alert("登录失败，请重试");
        </script>
    </c:if>
</head>
<body>
    <section>
        <div class = "square">
            <span></span>
            <span></span>
            <span></span>
            <div class = "content">
                <h1>简单水闸管理系统</h1>
                <form action="LoginServlet" method="get">
                    <div class="user_name">账 号: <input type="text" value="" name="user_name" /></div><br/>
                    <div class="password">密 码: <input type="password" value="" name="password" /></div><br />
                    <div class="login"><input type="submit" value="登录" name="login" class="loginButton"/></div>
                    <p>制作人:<br /> 谢光宇 周钰洁 严双佳</p>
                </form>
            </div>
        </div>
    </section>
    <div id="stars"><span id = "star"></span></div>
    <script>
        window.onload = function () {
            // 屏幕的尺寸
            var screenW = document.body.clientWidth;
            var screenH = document.body.clientHeight;
            // 2. 动态创建多个星星
            for(var i=0; i<100 ; i++){
                var span = document.createElement("span");
                span.setAttribute("id","star");
                document.getElementById("stars").appendChild(span);

                //位置随机
                var x = parseInt(Math.random() * screenW);
                var y = parseInt(Math.random() * screenH);
                span.style.left = x + 'px';
                span.style.top = y + 'px';
                span.style.zIndex = "0";

                //大小随机
                var scale = Math.random() * 1.5;
                span.style.transform = 'scale('+ scale + ', ' + scale + ')';

                //频率随机
                var rate = Math.random() * 1.5;
                span.style.animationDelay = rate + 's';

                function flash(){
                    var screenW = document.body.clientWidth;
                    var screenH = document.body.clientHeight;
                    //获取随机
                    var stars = document.getElementById("stars").childNodes;
                    var index = parseInt(Math.random() * stars.length);
                    var x = parseInt(Math.random() * screenW);
                    var y = parseInt(Math.random() * screenH);

                    //位置随机
                    stars[index].style.left = x + 'px';
                    stars[index].style.top = y + 'px';

                    //大小随机
                    var scale = Math.random() * 1.5;
                    span.style.transform = 'scale('+ scale + ', ' + scale + ')';

                    //频率随机
                    var rate = Math.random() * 1.5;
                    span.style.animationDelay = rate + 's';
                }

                setInterval(()=>setTimeout(flash),5000);
            }
        }
    </script>
</body>
</html>
