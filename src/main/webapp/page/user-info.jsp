<%--
  Created by IntelliJ IDEA.
  User: 86173
  Date: 2023/8/23
  Time: 8:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>个人主页</title>
    <base href="http://localhost:8080/zszdmzmm_web2_war/">
    <link rel="stylesheet" href="build/assets/app.css">
</head>
<body>
    <div class="vh-100 px-2 mb-5 d-flex flex-column justify-content-between">
        <header class="mb-5">
            <!--            导航栏-->
            <nav class="navbar navbar-expand-sm bg-body-tertiary mb-5 shadow">
                <div class="container-fluid">
                    <div class="navbar-brand">
                        <img src="image/QQ图片20230720103937.jpg" alt="头像" height="40">
                        <a href="index.html"
                           class="link-body-emphasis text-body-secondary text-decoration-none">我的主页</a>
                    </div>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
                            aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                        <div class="navbar-nav ms-auto">
                            <div class="d-flex">
                                <i class="bi bi-box py-2 nav-item me-1"></i>
                                <a class="me-3 py-2 link-body-emphasis text-decoration-none nav-link"
                                   href="resume/resume2.html">简历</a>
                            </div>
                            <div class="d-flex">
                                <i class="bi bi-journal-text py-2 nav-item me-1"></i>
                                <a class="me-3 py-2 link-body-emphasis text-decoration-none nav-link"
                                   href="page/article.html">博客</a>
                            </div>
                            <div class="d-flex">
                                <i class="bi bi-github py-2 nav-item me-1"></i>
                                <div class="nav-item dropdown" onmouseleave="dropDownMenuHidden()"
                                     onmouseenter="dropDownMenuShow()">
                                    <a class="nav-link dropdown-toggle me-3 py-2 text-decoration-none link-body-emphasis"
                                       href="https://github.com/zszdmzmm1" role="button"
                                       data-bs-toggle="dropdown" aria-expanded="false">
                                        GitHub
                                    </a>
                                    <ul id="dropdown" class="dropdown-menu">
                                        <li>
                                            <a class="dropdown-item" href="https://github.com/zszdmzmm1">个人主页</a>
                                        </li>
                                        <li>
                                            <hr class="dropdown-divider">
                                        </li>
                                        <li><a class="dropdown-item"
                                               href="https://github.com/zszdmzmm1/learnning">Java仓库</a>
                                        </li>
                                        <li><a class="dropdown-item"
                                               href="https://github.com/zszdmzmm1/zszdmzmm-web">Web仓库</a>
                                        </li>
                                        <li><a class="dropdown-item"
                                               href="https://zszdmzmm1.github.io/bilibili-plugin/">bilibili插件介绍页</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </nav>
        </header>
        <main class="container w-50 p-4 mx-auto mb-5">
            <c:if test="${type == 1}">
                <form action="../homepage" method="post" class="card">
                    <div class="card-body p-4">
                        <div class="d-flex justify-content-center mb-2 flex-column flex-md-column">
                            <div class="text-danger display-5 my-5 align-self-center">${message} </div>
                            <button class="btn btn-outline-danger my-5"><a href="page/login.html" class="link-body-emphasis text-decoration-none">返回登陆界面</a></button>
                        </div>
                    </div>
                </form>
            </c:if>
            <c:if test="${type == 2}">
                <div class="card d-flex flex-column justify-content-around p-5 ">
                    <c:forEach items="${userList}" var="user" varStatus="status">
                        <div class="border-2 border-danger rounded-2 m-2 p-1">
                            <div>${status.count}</div>
                            <div>email: ${user.getEmail()}</div>
                            <div>password: ${user.getPassword()}</div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
            <c:if test="${type == 3}">
                <div class="card p-5">
                    <div>email: ${user.getEmail()}</div>
                    <div>password: ${user.getPassword()}</div>
                </div>
            </c:if>
        </main>
        <footer class="mt-5 border-top text-center p-3">
            <p class="d-inline text-body-secondary">Designed by</p>
            <p class="d-inline fw-bold">@zszdmzmm</p>
            <p class="d-inline text-body-secondary">-From X to Z</p>
        </footer>
    </div>
    <script src="build/assets/app.js"></script>
</body>
</html>
