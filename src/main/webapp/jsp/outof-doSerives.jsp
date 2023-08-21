<%--
  Created by IntelliJ IDEA.
  User: 86173
  Date: 2023/8/21
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>在doService方法外</title>
</head>
<body>
    <div>这是一个页面</div>
    <%! int i = 4; %>
    <%! public void testPrint() {
        System.out.println("这是一个doService外的方法");
    }%>
</body>
</html>
