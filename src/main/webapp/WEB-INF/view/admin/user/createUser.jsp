<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Document</title>
    <!-- Bootstrap CSS -->
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
            crossorigin="anonymous">

    <!-- Latest compiled JavaScript for Bootstrap -->
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous">
    </script>

    <!-- jQuery (optional, if you need it for older scripts or plugins) -->
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js">
    </script>

</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
<%--        <div class="col-md-6">--%>
<%--            <!-- Card Form -->--%>
<%--            <div class="card shadow-lg border-0 rounded-3">--%>
                <div class="card-header bg-primary text-white text-center py-3">
                    <h3 class="mb-0">Create User</h3>
                </div>
    <%--@elvariable id="newUser" type=""--%>
    <form:form action="/admin/user/create" method="post" modelAttribute="newUser">

        <!-- Email -->
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <form:input path="email" class="form-control" id="email"/>
        </div>

        <!-- Password -->
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <form:input path="password" class="form-control" id="password" type="password"/>
        </div>

        <!-- Full Name -->
        <div class="mb-3">
            <label for="fullname" class="form-label">Full Name</label>
            <form:input path="fullName" class="form-control" id="fullname"/>
        </div>

        <!-- Address -->
        <div class="mb-3">
            <label for="address" class="form-label">Address</label>
            <form:input path="address" class="form-control" id="address"/>
        </div>

        <!-- Phone Number -->
        <div class="mb-3">
            <label for="phone" class="form-label">Phone Number</label>
            <form:input path="phoneNumber" class="form-control" id="phone" type="tel"/>
        </div>

        <!-- Submit Button -->
        <div class="d-grid">
            <button type="submit" class="btn btn-success btn-lg">
                Create
            </button>
        </div>

    </form:form>

    </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>