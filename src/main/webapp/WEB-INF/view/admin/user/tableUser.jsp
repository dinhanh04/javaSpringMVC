<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Table users</title>
    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container py-4">
    <div class="d-flex align-items-center mb-3">
        <h2 class="fw-bold mb-0">Table users</h2>
        <div class="ms-auto">
            <a href="${pageContext.request.contextPath}/admin/user/create" class="btn btn-primary">Create a User</a>
        </div>
    </div>

    <div class="table-responsive">
        <table class="table table-bordered align-middle">
            <thead class="table-light">
            <tr>
                <th style="width: 80px;">ID</th>
                <th>Email</th>
                <th>Full Name</th>
                <th style="width: 280px;">Action</th>
            </tr>
            </thead>
            <tbody>
            <!-- Hardcode user 1 -->
            <c:forEach var="user" items="${users1}">
                <tr>
                    <td>${user.id}</td>
                    <td>${weather[1]}</td>
                    <td class="text-center">${weather[2]}&deg;C</td>
                    <td class="text-center">${weather[3]}&deg;C</td>
                </tr>

            </c:forEach>

            <tr>
                <td>1</td>
                <td>Mark</td>
                <td>Otto</td>
                <td>
                    <div class="d-flex gap-2">
                        <a href="#" class="btn btn-success">View</a>
                        <a href="#" class="btn btn-warning">Update</a>
                        <a href="#" class="btn btn-danger">Delete</a>
                    </div>
                </td>
            </tr>

            <!-- Hardcode user 2 -->
            <tr>
                <td>2</td>
                <td>Jacob</td>
                <td>Thornton</td>
                <td>
                    <div class="d-flex gap-2">
                        <a href="#" class="btn btn-success">View</a>
                        <a href="#" class="btn btn-warning">Update</a>
                        <a href="#" class="btn btn-danger">Delete</a>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
