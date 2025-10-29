<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Delete user</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-4">
    <h2 class="fw-bold mb-3">Delete the user with id = ${user.id}</h2>

    <div class="alert alert-danger mb-3">
        Are you sure to delete this user?
    </div>

    <form action="${pageContext.request.contextPath}/admin/user/delete/${user.id}" method="post">
        <c:if test="${not empty _csrf}">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </c:if>
        <button type="submit" class="btn btn-danger">Confirm</button>
        <a href="${pageContext.request.contextPath}/admin/user" class="btn btn-outline-secondary ms-2">Cancel</a>
    </form>


</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
