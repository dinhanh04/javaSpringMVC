<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Users show</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
    <link href="/css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
<jsp:include page="../layout/header.jsp" />
<div id="layoutSidenav">
    <jsp:include page="../layout/sidebar.jsp" />
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h1 class="mt-4">Manager User</h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item active"><a href="/admin">dashboard</a>/user</li>
                </ol>
               <body>

<div class="container-fluid w-100 px-4">
    <div class="container-fluid mt-5">
        <div class="row">
            <div class="col-12">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h3 class="fw-bold mb-0">Table users</h3>
                    <a href="${pageContext.request.contextPath}/admin/user/create" class="btn btn-primary">
                        Create a User
                    </a>
                </div>
            </div>
        </div>
    </div>
    <hr />
    <div class="table_resposive">
        <table class="table table-bordered table-hover align-middle">
            <thead class="table-light">
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Email</th>
                <th scope="col">Full Name</th>
                <th scope="col">Role</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users1}">
            <tr>
                    <%--            <th>${user.id}</th>--%>
                    <%--            <td>${user.email}</td>--%>
                <td class="text-center">${user.id}</td>
                <td class="text-center">${user.email}</td>
                <td class="text-center">${user.fullName}</td>
                <td class="text-center">${user.roleId}</td>
                <td>
                    <div class="d-flex gap-2">
                        <a href="/admin/user/${user.id}" class="btn btn-success">View</a>
                        <a href="/admin/user/update/${user.id}" class="btn btn-warning">Update</a>
                        <c:url value="/admin/user/delete/${user.id}" var="delUrl"/>
                        <a href="${delUrl}" class="btn btn-danger btn-sm">Delete</a>

                    </div>
                </td>

            </tr>
            </c:forEach>

        </table>
    </div>

</div>
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
            </div>
        </main>
        <jsp:include page="../layout/footer.jsp" />
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="js/scripts.js"></script>
</body>
</html>

