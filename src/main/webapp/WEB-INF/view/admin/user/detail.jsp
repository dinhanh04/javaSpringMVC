<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <title>User detail</title>
                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
                <style>
                    .small-btn {
                        padding: 6px 14px;
                        font-size: 0.9rem;
                    }
                </style>
            </head>

            <body class="sb-nav-fixed">
                <jsp:include page="../layout/header.jsp" />
                <div id="layoutSidenav">
                    <jsp:include page="../layout/sidebar.jsp" />
                    <div id="layoutSidenav_content">
                        <main>
                            <div class="container-fluid px-4">
                                <h1 class="mt-4">User Detail</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                                    <li class="breadcrumb-item"><a href="/admin/user">Users</a></li>
                                    <li class="breadcrumb-item active">Detail</li>
                                </ol>
                                <div class="card mb-4">
                                    <div class="card-header">
                                        User information with id = ${user2.id}
                                    </div>
                                    <div class="card-body">
                                        <ul class="list-group list-group-flush">
                                            <li class="list-group-item">ID: ${user2.id}</li>
                                            <li class="list-group-item">Email: ${user2.email}</li>
                                            <li class="list-group-item">FullName: ${user2.fullName}</li>
                                            <li class="list-group-item">Address: ${user2.address}</li>

                                            <li class="list-group-item">Image:
                                                <img src="${user2.avatar.url}" alt="User avatar"
                                                    style="max-width:150px; height:auto;" />
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="card-footer d-flex">
                                        <a href="${pageContext.request.contextPath}/admin/user"
                                            class="btn btn-primary small-btn">
                                            Back
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </main>
                        <jsp:include page="../layout/footer.jsp" />
                    </div>
                </div>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
                <script src="/js/scripts.js"></script>
            </body>

            </html>