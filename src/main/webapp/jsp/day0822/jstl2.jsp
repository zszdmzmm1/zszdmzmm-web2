<%--
  Created by IntelliJ IDEA.
  User: 86173
  Date: 2023/8/22
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>jstl</title>
</head>
<body>
    <c:if test="${user == 'cat'}">
        1
    </c:if>
    <c:if test="${user == 'mouse'}">
        2
    </c:if>
</body>
</html>
