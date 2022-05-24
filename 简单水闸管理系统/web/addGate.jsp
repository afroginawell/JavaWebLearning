<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2022/5/19
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>addGate</title>
    <link rel="stylesheet" type="text/css" href="css/addGateCss.css">
</head>
<body>
    <section>
        <div class="container">
            <div class="box">
                <span class="span1"></span>
                <span class="span1"></span>
                <span class="span1"></span>
                <span class="span1"></span>
                <div class="content">
                    <h2>新 建 水 闸 信 息</h2>
                    <form action="AddGateServlet" method="get">
                        <table id="table1">
                            <tr>
                                <td><span>编号:</span></td>
                                <td><input type="text" name="watergateid" value="" required></td>
                            </tr>
                            <tr>
                                <td><span>水闸名:</span></td>
                                <td><input type="text" name = "watergatename" value="" required></td>
                            </tr>
                            <tr>
                                <td><span>建成时间:</span></td>
                                <td><input type="text" name = "build_time" value="" required></td>
                            </tr>
                            <tr>
                                <td><span>负责人:</span></td>
                                <td><input type="text" name = "principal" value="" required></td>
                            </tr>
                            <tr>
                                <td><span>经度:</span></td>
                                <td><input type="text" name = "longitude" value=""required></td>
                            </tr>
                            <tr>
                                <td><span>纬度:</span></td>
                                <td><input type="text" name = "latitude" value="" required></td>
                            </tr>
                            <tr>
                                <td><span>简介:</span></td>
                                <td><textarea name = "introduction" required></textarea></td>
                            </tr>
                        </table>
                        <input type="submit" name="modify" value="新增" class="button1">
                        <input type="button" onclick="location.href='main.jsp'" name="cancel" value="取消" class="button1">
                    </form>
                </div>
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
