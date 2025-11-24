<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1">
                <title>Update User</title>
                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
                <!-- Bootstrap CSS -->
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
                    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
                    crossorigin="anonymous">
            </head>

            <body class="sb-nav-fixed">
                <jsp:include page="../layout/header.jsp" />
                <div id="layoutSidenav">
                    <jsp:include page="../layout/sidebar.jsp" />
                    <div id="layoutSidenav_content">
                        <main>
                            <div class="container-fluid px-4">
                                <h1 class="mt-4">Update User</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                                    <li class="breadcrumb-item"><a href="/admin/user">Users</a></li>
                                    <li class="breadcrumb-item active">Update</li>
                                </ol>
                                <div class="row justify-content-center">
                                    <div class="col-lg-10">
                                        <div class="card shadow-lg border-0 rounded-3">
                                            <div class="card-header text-black text-left py-3">
                                                <h3 class="mb-0">Update User</h3>
                                            </div>
                                            <div class="card-body p-4">
                                                <%--@elvariable id="newUser" type="" --%>
                                                    <form:form action="/admin/user/update/{id}" method="post"
                                                        modelAttribute="newUser">

                                                        <!-- Email -->
                                                        <div class="mb-3" style="display:none;">
                                                            <label for="id" class="form-label">Id</label>
                                                            <form:input path="id" class="form-control" id="id" />
                                                        </div>

                                                        <!-- Password -->
                                                        <div class="mb-3">
                                                            <label for="email" class="form-label">Email</label>
                                                            <form:input path="email" class="form-control" id="email"
                                                                type="email" disabled="true" />
                                                        </div>

                                                        <!-- Full Name -->
                                                        <div class="mb-3">
                                                            <label for="fullname" class="form-label">Full Name</label>
                                                            <form:input path="fullName" class="form-control"
                                                                id="fullname" />
                                                        </div>

                                                        <!-- Address -->
                                                        <div class="mb-3">
                                                            <label for="address" class="form-label">Address</label>
                                                            <form:input path="address" class="form-control"
                                                                id="address" />
                                                        </div>

                                                        <!-- Phone Number -->
                                                        <div class="mb-3">
                                                            <label for="phone" class="form-label">Phone Number</label>
                                                            <form:input path="phoneNumber" class="form-control"
                                                                id="phone" type="tel" />
                                                        </div>

                                                        <!-- Role -->
                                                        <div class="mb-3">
                                                            <label for="roleId" class="form-label">Role</label>
                                                            <form:input path="roleId" class="form-control" id="roleId"
                                                                type="tel" />
                                                        </div>

                                                        <!-- Avatar -->
                                                        <div class="mb-3">
                                                            <label for="avatar" class="form-label">Avatar</label>
                                                            <form:input path="avatar" class="form-control" id="avatar"
                                                                type="tel" />
                                                        </div>

                                                        <!-- Submit Button -->
                                                        <div class="text-start">
                                                            <button type="submit" class="btn btn-success">
                                                                Update
                                                            </button>
                                                        </div>

                                                    </form:form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </main>
                        <jsp:include page="../layout/footer.jsp" />
                    </div>
                </div>

                <!-- jQuery PHẢI ĐẶT TRƯỚC -->
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
                <script src="/js/scripts.js"></script>

            </body>

            </html>