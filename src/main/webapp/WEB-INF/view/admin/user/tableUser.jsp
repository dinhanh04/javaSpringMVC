<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Table users</title>
    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container-fluid w-75">
    <div class="container mt-5 px-4 px-md-5">
        <div class="row">
            <div class="col-12 mx-auto">
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
</html>
