<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>User detail</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container my-5 d-flex flex-column align-items-center">

    <!-- Title -->
    <h3 class="fw-bold text-center mb-4">
        User detail with id = ${user2.id}
    </h3>

    <!-- Card -->
    <div class="card shadow w-100" style="max-width: 75%;">
        <div class="card-header">
            User information
        </div>

        <ul class="list-group list-group-flush">
            <li class="list-group-item">ID: ${user2.id}</li>
            <li class="list-group-item">Email: ${user2.email}</li>
            <li class="list-group-item">FullName: ${user2.fullName}</li>
            <li class="list-group-item">Address: ${user2.address}</li>

            <li class="list-group-item">Image:
                <img src="${user2.avatar.url}" alt="User avatar" style="max-width:150px; height:auto;"/>
            </li>

        </ul>

        <!-- Footer (Back button left) -->
        <div class="card-footer d-flex">
            <a href="${pageContext.request.contextPath}/admin/user"
               class="btn btn-primary small-btn">
                Back
            </a>
        </div>
    </div>

</div>

<style>
    .small-btn {
        padding: 6px 14px;
        font-size: 0.9rem;
    }
</style>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
