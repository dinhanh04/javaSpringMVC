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

<div class="container mt-5">
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

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
