<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entity.User" %>
<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2022/5/19
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<html>
    <title>main</title>
    <link type="text/css" href="css/mainCss.css" rel="stylesheet">
    <script src="./scripts/jquery-1.7.2.min.js"></script>

    <c:if test="${!empty requestScope}">
        <script>
            alert("水闸编码重复，新增水闸失败!");
        </script>
    </c:if>

    <script type = "text/javascript">

        function showWaterGates(jsonArray){
            jsonArray = eval(jsonArray);

            $.each(jsonArray,function(index,item){
                item = eval(item);
                var tr = "";
                tr += "<tr><td>"+item.watergatename+"</td>";
                tr += "<td>"+item.watergateid+"</td>";
                tr += "<td>"+item.build_time+"</td>";
                tr += "<td>"+item.principal+"</td>";
                tr += "<td><a href='modifyGate.jsp?watergateid="+item.watergateid+"'>修改</a> " +
                    "<div class ='delete' data='"+item.watergateid+"' onclick='ajaxFunction(this)' style='text-decoration: underline;font-weight: bold;cursor:pointer; display:inline-block'>删除</div></td></tr>";
                $("#table1").append(tr);
            })
        }

        $.ajax({
            type:"get",
            url:"GetGatesServlet",
            async:true, // default-asynchronout(true), synchronous-false
            dataType:"json",
            success:function(jsonArray){
                showWaterGates(jsonArray);
            },
            error:function(e){
                alert("错误!"+e.status);
            },
            complete:function(){

            }
        })

        function  ajaxFunction(obj) {
            if(confirm("请确认是否删除!")==true){
                var url = "DeleteGateServlet?watergateid="+$(obj).attr("data");
                location.href = url;
            }else{
                return false;
            }
        }

    </script>
</head>
<body>
    <section>
        <div class="container">
            <div class="box">
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <div class="content">
                    <img src="images/watergate.jpg" height="200px" width="824px">
                    <div style="display: block">
                        <h2>欢迎回来，用户 <%=((User)session.getAttribute("user")).getUser_name()%></h2>
                        <a href="LogoutServlet" class="a1">登出系统</a>
                    </div>
                    <table id = "table1" align="center">
                        <tr>
                            <th>水闸名</th>
                            <th>id</th>
                            <th>建成时间</th>
                            <th>负责人</th>
                            <th>操作</th>
                        </tr>
                    </table>
                    <a href="addGate.jsp" class="a2">新建闸站</a>
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
