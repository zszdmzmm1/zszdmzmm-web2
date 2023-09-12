<%--
  Created by IntelliJ IDEA.
  User: 86173
  Date: 2023/9/13
  Time: 3:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>文章列表</title>
  <base href="http://localhost:8080/zszdmzmm_web2_war/">
  <link rel="stylesheet" href="build/assets/app.css">
</head>
<body class="d-flex flex-column h-100">
  <header class="mb-5">
    <!--            导航栏-->
    <nav class="navbar navbar-expand-sm bg-body-tertiary mb-5 shadow">
      <div class="container-fluid">
        <div class="navbar-brand">
          <img src="image/QQ图片20230720103937.jpg" alt="头像" height="40" >
          <a href="#" class="link-body-emphasis text-body-secondary text-decoration-none">我的主页</a>
        </div>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
                aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
          <div class="navbar-nav ms-auto">
            <div class="d-flex">
              <a class="me-3 py-2 link-body-emphasis text-decoration-none nav-link"
                 href="../page/test-navigator.html">测试导航页</a>
            </div>
            <div class="d-flex">
              <i class="bi bi-box py-2 nav-item me-1"></i>
              <a class="me-3 py-2 link-body-emphasis text-decoration-none nav-link"
                 href="../resume/resume2.html">简历</a>
            </div>
            <div class="d-flex">
              <i class="bi bi-journal-text py-2 nav-item me-1"></i>
              <a class="me-3 py-2 link-body-emphasis text-decoration-none nav-link" href="../page/article-list.html">博客</a>
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
          <button class="btn btn-outline-danger nav-item">
            <a href="./login" class="nav-link">登录</a>
          </button>
        </div>
      </div>
    </nav>
  </header>
  <div class="container" id="main_content">
    <div class="mx-lg-auto">
      <div class="row">
        <c:forEach items="${blogList}" var="blog" varStatus="status">
          <div class="col-md-6">
            <article class="border-bottom h-100 pb-5  pt-md-0">
              <div class="row justify-content-between">
                <div class="col-7">
                  <h2 class="mb-2 h5"><a class="text-inherit" href="article?id=${blog.getId()}">${blog.getTitle()}</a></h2>
                  <div class="small text-muted">
                    <a href="#">${blog.getUserId()}</a>
                    <span> - </span>
                    <span>${blog.getPublishTime()} ${blog.getLastUpdateTime()}</span>
                  </div>
                </div>
                <div class="col-5">
                  <div class="d-flex">
                    <a href="show.html" class="w-100">
                      <img class="img-fluid shadow-sm rounded ratio-16x9" src="${blog.getCover()}">
                    </a>
                  </div>
                </div>
              </div>
            </article>
          </div>
        </c:forEach>
<%--        <div class="col-md-6">
          <article class="border-bottom h-100 pb-5  pt-md-0">
            <div class="row justify-content-between">
              <div class="col-7">
                <h2 class="mb-2 h5"><a class="text-inherit" href="article-show.html">||||||||||||||||||||||||||||||</a></h2>
                <div class="small text-muted">
                  <a href="#">admin</a>
                  <span> - </span>
                  <span>2023-06-11 14:59:23.0</span>
                </div>
              </div>
              <div class="col-5">
                <div class="d-flex">
                  <a href="show.html" class="w-100">
                    <img class="img-fluid shadow-sm rounded ratio-16x9" src="../img/cover/default-cover.png">
                  </a>
                </div>
              </div>
            </div>
          </article>
        </div>
        <div class="col-md-6">
          <article class="border-bottom h-100 pb-5 pt-5 pt-md-0">
            <div class="row justify-content-between">
              <div class="col-7">
                <h2 class="mb-2 h5"><a class="text-inherit" href="show.html">maven 加速，修改镜像源</a></h2>
                <div class="small text-muted">
                  <a href="#">admin</a>
                  <span> - </span>
                  <span>2023-06-11 14:59:23.0</span>
                </div>
              </div>
              <div class="col-5">
                <div class="d-flex">
                  <a href="show.html" class="w-100">
                    <img class="img-fluid shadow-sm rounded ratio-16x9" src="../img/cover/maven-cover.png">
                  </a>
                </div>
              </div>
            </div>
          </article>
        </div>
        <div class="col-md-6">
          <article class="border-bottom h-100 pb-5 pt-5 ">
            <div class="row justify-content-between">
              <div class="col-7">
                <h2 class="mb-2 h5"><a class="text-inherit" href="show.html">composer 加速，修改镜像源</a></h2>
                <div class="small text-muted">
                  <a href="#">admin</a>
                  <span> - </span>
                  <span>2023-06-11 14:59:23.0</span>
                </div>
              </div>
              <div class="col-5">
                <div class="d-flex">
                  <a href="show.html" class="w-100">
                    <img class="img-fluid shadow-sm rounded ratio-16x9" src="../img/cover/composer-cover.png">
                  </a>
                </div>
              </div>
            </div>
          </article>
        </div>
        <div class="col-md-6">
          <article class="border-bottom h-100 pb-5 pt-5 ">
            <div class="row justify-content-between">
              <div class="col-7">
                <h2 class="mb-2 h5"><a class="text-inherit" href="show.html">yarn 加速，修改镜像源</a></h2>
                <div class="small text-muted">
                  <a href="#">admin</a>
                  <span> - </span>
                  <span>2023-06-11 14:59:23.0</span>
                </div>
              </div>
              <div class="col-5">
                <div class="d-flex">
                  <a href="show.html" class="w-100">
                    <img class="img-fluid shadow-sm rounded ratio-16x9" src="../img/cover/yarn-cover.png">
                  </a>
                </div>
              </div>
            </div>
          </article>
        </div>
        <div class="col-md-6">
          <article class="border-bottom h-100 pb-5 pt-5 ">
            <div class="row justify-content-between">
              <div class="col-7">
                <h2 class="mb-2 h5"><a class="text-inherit" href="show.html">npm 加速，修改镜像源</a></h2>
                <div class="small text-muted">
                  <a href="#">admin</a>
                  <span> - </span>
                  <span>2023-06-11 14:59:23.0</span>
                </div>
              </div>
              <div class="col-5">
                <div class="d-flex">
                  <a href="show.html" class="w-100">
                    <img class="img-fluid shadow-sm rounded ratio-16x9" src="../img/cover/npm-cover.png">
                  </a>
                </div>
              </div>
            </div>
          </article>
        </div>--%>
      </div>


    </div>
  </div>

  <footer class="blog-footer mt-auto">
    <div class="container text-muted">
      <p class="text-center text-lg-left">
        <span>Designed by <a href="#">@you</a> - From X to Z</span>
      </p>
    </div>
  </footer>

  <script type="module" src="build/assets/app.js"></script>
</body>
</html>
