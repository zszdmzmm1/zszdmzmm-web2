<%--
  Created by IntelliJ IDEA.
  User: 86173
  Date: 2023/9/13
  Time: 3:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>${blog.getTitle()}</title>
</head>
<body>
    <h2>${blog.getTitle()}</h2>
    <div>
        <span>${blog.getUserId()}</span>
        <span>${blog.getPublishTime()}</span>
    </div>
    <div>${content}</div>
</body>
</html>
