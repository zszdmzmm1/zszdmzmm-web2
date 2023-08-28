<%--
  Created by IntelliJ IDEA.
  User: 86173
  Date: 2023/8/23
  Time: 15:58
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
    <title>管理员控制台</title>
    <base href="http://localhost:8080/zszdmzmm_web2_war/">
    <link rel="stylesheet" href="build/assets/app.css">
</head>
<body class="sidebar-mini" style="height: auto;">
    <div class="wrapper">

        <nav class="main-header navbar navbar-expand navbar-white navbar-light">

            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
                </li>
                <li class="nav-item d-none d-sm-inline-block">
                    <a href="page/login.html" class="nav-link">退出登录</a>
                </li>
            </ul>
        </nav>


        <aside class="main-sidebar sidebar-dark-primary elevation-4">

            <div class="sidebar">

                <div class="user-panel mt-3 pb-3 mb-3 d-flex">
                    <div class="image">
                        <img src="./image/QQ图片20230720103937.jpg" class="img-circle elevation-2" alt="User Image">
                    </div>
                    <div class="info">
                        <a href="#" class="d-block">管理员</a>
                    </div>
                </div>

                <nav class="mt-2">
                    <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu"
                        data-accordion="false">

                        <li class="nav-item">
                            <a href="#" class="nav-link">
                                <i class="nav-icon fas fa-tachometer-alt"></i>
                                <p>
                                    Dashboard
                                    <i class="right fas fa-angle-left"></i>
                                </p>
                            </a>
                            <ul class="nav nav-treeview">
                                <li class="nav-item">
                                    <a href="#" class="nav-link">
                                        <i class="far fa-circle nav-icon"></i>
                                        <p>Dashboard v1</p>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a href="#" class="nav-link">
                                        <i class="far fa-circle nav-icon"></i>
                                        <p>Dashboard v2</p>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a href="#" class="nav-link">
                                        <i class="far fa-circle nav-icon"></i>
                                        <p>Dashboard v3</p>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="nav-item menu-open">
                            <a href="#" class="nav-link active">
                                <i class="nav-icon far fa-plus-square"></i>
                                <p>
                                    管理员
                                    <i class="fas fa-angle-left right"></i>
                                </p>
                            </a>
                            <ul class="nav nav-treeview">
                                <li class="nav-item">
                                    <a href="../examples/blank.html" class="nav-link active">
                                        <i class="far fa-circle nav-icon"></i>
                                        <p>用户列表</p>
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </nav>

            </div>

        </aside>

        <div class="content-wrapper" style="min-height: 1604.44px;">

            <section class="content-header">
                <div class="container-fluid">
                    <div class="row mb-2">
                        <div class="col-sm-6">
                            <h1>用户列表</h1>
                        </div>
                        <div class="col-sm-6">
                            <ol class="breadcrumb float-sm-right">
                                <nav aria-label="Page navigation example">
                                    <ul class="pagination">
                                        <c:if test="${page != 1}">
                                            <li class="page-item">
                                                <a class="page-link" aria-label="Previous">
                                                    <span aria-hidden="true">&laquo;</span>
                                                </a>
                                            </li>
                                        </c:if>
                                        <li class="page-item"><span class="page-link">${page}</span></li>
                                        <c:if test="${page != pageCount}">
                                            <li class="page-item">
                                                <a class="page-link" aria-label="Next">
                                                    <span aria-hidden="true">&raquo;</span>
                                                </a>
                                            </li>
                                        </c:if>
                                    </ul>
                                </nav>
                            </ol>
                        </div>
                    </div>
                </div>
            </section>

            <section class="content">

                <div class="card mb-2">
                    <div class="card-body">
                        <div class="row border-bottom border-3 py-4">
                            <div class="col-2">#</div>
                            <div class="col-5">邮箱</div>
                            <div class="col-4">密码</div>
                            <div class="col-1 text-end" id="add-trigger">添加</div>
                        </div>
                        <form class="border border-1 rounded-2 p-2 my-3 bg-light update_form" id="add"
                              style="display: none;">
                            <div class="mb-3">
                                <label for="add-email" class="form-label">邮箱</label>
                                <input type="email" class="form-control" id="add-email" aria-describedby="emailHelp">
                            </div>
                            <div class="mb-3">
                                <label for="add-password" class="form-label">密码</label>
                                <input type="password" class="form-control" id="add-password">
                            </div>
                            <button type="button" class="btn btn-primary" id="add-submit">添加</button>
                        </form>
                        <c:forEach items="${userList}" var="user" varStatus="status">
                            <div class="row border-bottom border-top border-2 py-4" id="${user.getId()}">
                                <div class="col-2">${status.count + (page - 1) * 10}</div>
                                <div class="col-5">${user.getEmail()}</div>
                                <div class="col-4">${user.getPassword()}</div>
                                <div class="col-1 text-end">
                                    <span class="me-1 delete">删除</span>
                                    <span class="update">修改</span>
                                </div>
                                <form class="border border-1 rounded-2 p-2 my-3 bg-light update_form"
                                      id="${user.getId()}-update" style="display: none;">
                                    <div class="mb-3">
                                        <label for="update-${user.getId()}" class="form-label">邮箱</label>
                                        <input type="email" class="form-control" id="update-${user.getId()}"
                                               placeholder="不填写可保持不变">
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">密码</label>
                                        <input type="text" class="form-control"
                                               placeholder="不填写可保持不变">
                                    </div>
                                    <button type="button" class="btn btn-primary" id="${user.getId()}-submit">更新
                                    </button>
                                </form>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="card-footer">
                        <span class="text-secondary">当前第${page}页，总计${pageCount}页，共${count}条记录</span>
                    </div>
                </div>
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <c:if test="${page != 1}">
                            <li class="page-item">
                                <a class="page-link" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <li class="page-item"><span class="page-link">${page}</span></li>
                        <c:if test="${page != pageCount}">
                            <li class="page-item">
                                <a class="page-link" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </section>

        </div>
        <footer class="main-footer">
            <strong>Design by <a href="https://adminlte.io">zszdmzmm</a>.</strong>
        </footer>

    </div>


    <script src="build/assets/app.js"></script>
    <script>
        $(".page-link").click(function () {
            let page = ${page};
            if ($(this).attr("aria-label") === "Next") {
                page += 1;
            } else if ($(this).attr("aria-label") === "Previous") {
                page -= 1;
            } else {
                page = parseInt($(this).text());
            }
            if(page < 1){
                page =  1;
            }else if (page > ${pageCount}){
                page = ${pageCount};
            }
            window.location.href = "admin/user?page=" + page;
        })


        $(".delete").click(function () {
            let isConfirm = confirm("是否确定删除？");
            if(isConfirm){
                let id = $(this).parent().parent().attr("id");
                $.ajax({
                    method: "GET",
                    url: "delete",
                    data: {id: id}
                })
                    .done(function () {
                        window.location.reload();
                        alert("删除成功！");
                    })
                    .fail(function () {
                        alert("something wrong, please check again");
                    })
            }
        });

        $(".update").click(function () {
            let id = $(this).parent().parent().attr("id");
            $(`#${"${id}"}-update`).toggle();
            $(`#${"${id}"}-submit`).click(function () {
                let email = $(this).prev().prev().find("input").val();
                let password = $(this).prev().find("input").val();
                $.ajax({
                    method: "GET",
                    url: "update",
                    data: {id: id, email: email, password: password}
                })
                    .done(function () {
                        window.location.reload();
                        alert("更新成功！");
                    })
                    .fail(function () {
                        alert("something wrong, please check again");
                    })
            })
        });

        $("#add-trigger").click(function () {
            $("#add").toggle();
            $("#add-submit").click(function () {
                let email = $("#add-email").val();
                let password = $("#add-password").val();
                $.ajax({
                    method: "GET",
                    url: "add",
                    data: {email: email, password: password}
                })
                    .done(function () {
                        window.location.reload();
                        alert("增加成功！");
                    })
                    .fail(function () {
                        alert("账号或密码不能为空！");
                    })
            })
        });
    </script>
</body>
</html>