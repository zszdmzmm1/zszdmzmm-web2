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
        <div>
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
                                                <a class="dropdown-item"
                                                   href="https://github.com/zszdmzmm1">个人主页</a>
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
            <main class="container p-4 mx-auto mb-5">
                <div class="d-flex">
                    <div class="flex-shrink-0 mx-5">
                        <div class="border border-opacity-25 border-2 p-2">
                            <div class="mx-auto d-flex justify-content-center p-2">
                                <img src="./image/QQ图片20230720103937.jpg" alt="头像" width="150"
                                     class="rounded-circle">
                            </div>
                            <div class="text-danger mt-2 text-center">个人资料</div>
                        </div>
                    </div>
                    <div class="flex-grow-1">
                        <div class="card">
                            <div class="card-header">个人资料</div>
                            <div class="card-body">
                                <div class="my-2">邮箱: ${user.getEmail()}</div>
                                <div class="my-2">密码: ${user.getPassword()}</div>
                                <button class="btn btn-danger" id="logout">退出登录</button>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>

        <footer class="mt-5 border-top text-center p-3">
            <p class="d-inline text-body-secondary">Designed by</p>
            <p class="d-inline fw-bold">@zszdmzmm</p>
            <p class="d-inline text-body-secondary">-From X to Z</p>
        </footer>
    </div>
    <script src="build/assets/app.js"></script>
    <script>
        $("#logout").click(function (){
            $.ajax({
                method: "GET",
                url: "logout",
            })
                .done(function(){
                    window.location.reload();
                })
        })
    </script>
</body>
</html>
