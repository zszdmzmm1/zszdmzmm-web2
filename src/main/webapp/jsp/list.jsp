<%@ page import="day0818.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 86173
  Date: 2023/8/21
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>list</title>
</head>
<body>
    <%
        List<User> userList = new ArrayList<User>();
        userList.add(new User("Tom", "123", "cat"));
        userList.add(new User("Jerry", "123", "mouse"));
        userList.add(new User("z", "123", "man"));
    %>
    <div>
        <table>
            <%
                for (int i = 0; i < userList.size(); i++) {
                    User user = userList.get(i);
            %>
            <tr>
                <td><%=i + 1%></td>
                <td><%=user.getEmail()%></td>
                <td><%=user.getPassword()%></td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
</body>
</html>
