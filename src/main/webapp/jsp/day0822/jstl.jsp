<%--
  Created by IntelliJ IDEA.
  User: 86173
  Date: 2023/8/22
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<html>
<head>
    <title>jstl1</title>
</head>
<body>
    <table>
        <tr>
          <td>编号</td>
          <td>名字</td>
          <td>密码</td>
        </tr>
        <c:forEach items="${user}" var="u" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td>${u.email}</td>
                <td>${u.password}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
