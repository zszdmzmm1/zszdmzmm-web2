<%@ page import="day0818.User" %>
<%@ page import="java.util.List" %>
<%@ page import="day0818.JDBCConnection" %>
<%@ page import="java.sql.Connection" %><%--
  Created by IntelliJ IDEA.
  User: 86173
  Date: 2023/8/21
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <%!
        JDBCConnection jdbcConnection = new JDBCConnection();
        Connection connection = jdbcConnection.getConnection();
        List<User> userList = null;
        String message = "";
        boolean isQualified = true;
        boolean isAdmin = false;
        public void LoginInit(String email, String password){
            User user = jdbcConnection.getUser(connection, email);
            if (user == null) {
                message = "用户不存在";
                isQualified = false;
            } else {
                if (password.equals(user.getPassword())) {
                    if (user.getRole().equals("管理员")) {
                        userList = jdbcConnection.getAllUser(connection);
                        isAdmin = true;
                    }
                } else {
                    message = "用户输入的密码错误";
                    isQualified = false;
                }
            }
        }
        public void RegisterInit(String email){
            User user = jdbcConnection.getUser(connection, email);
            if (user != null) {
                message = "邮箱已被占用";
                isQualified = false;
            }
        }
    %>
    <%
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String originUrl = request.getHeader("Referer");
        if (originUrl.equals("http://localhost:8080/zszdmzmm_web2_war/page/login.html")) {
            LoginInit(email, password);
        } else {
            RegisterInit(email);
        }
    %>

    <%
        if (isAdmin) {
            for (User value : userList) {
    %>
    <div><%= value.getEmail() %>
    </div>
    <div><%= value.getPassword() %>
    </div>
    <div>---------------------------------------</div>
    <%
        }
    %>
    <%
    } else {
        if (isQualified) {
    %>
    <div><%="email:" + email%>
    </div>
    <div><%="password:" + password%>
    </div>
    <%
    } else {
    %>
    <div><%= message%>
    </div>
    <%
            }
        }
        isAdmin = false;
        isQualified = true;
    %>
</body>
</html>
