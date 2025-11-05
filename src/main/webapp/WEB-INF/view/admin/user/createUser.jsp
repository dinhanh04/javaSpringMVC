<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Create User</title>
    <!-- Bootstrap CSS -->
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
            crossorigin="anonymous">

    <style>
        .avatar-preview {
            width: 150px;
            height: 150px;
            border: 2px dashed #dee2e6;
            border-radius: 8px;
            display: flex;
            align-items: center;
            justify-content: center;
            overflow: hidden;
            background-color: #f8f9fa;
            margin-top: 10px;
        }
        .avatar-preview img {
            max-width: 100%;
            max-height: 100%;
            object-fit: cover;
        }
        .avatar-preview-text {
            color: #6c757d;
            text-align: center;
            padding: 10px;
            font-size: 14px;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-lg-10">
            <div class="card shadow-lg border-0 rounded-3">
                <div class="card-header text-black text-left py-3">
                    <h3 class="mb-0">Create User</h3>
                </div>
                <div class="card-body p-4">
                    <%--@elvariable id="newUser" type=""--%>
                    <form:form action="/admin/user/create" method="post" modelAttribute="newUser" enctype="multipart/form-data">

                        <div class="row">
                            <!-- Email -->
                            <div class="col-md-6 mb-3">
                                <label for="email" class="form-label">Email</label>
                                <form:input path="email" class="form-control" id="email" type="email"/>
                            </div>

                            <!-- Password -->
                            <div class="col-md-6 mb-3">
                                <label for="password" class="form-label">Password</label>
                                <form:input path="password" class="form-control" id="password" type="password"/>
                            </div>
                        </div>

                        <div class="row">
                            <!-- Full Name -->
                            <div class="col-md-6 mb-3">
                                <label for="fullname" class="form-label">Full Name</label>
                                <form:input path="fullName" class="form-control" id="fullname"/>
                            </div>

                            <!-- Phone Number -->
                            <div class="col-md-6 mb-3">
                                <label for="phone" class="form-label">Phone Number</label>
                                <form:input path="phoneNumber" class="form-control" id="phone" type="tel"/>
                            </div>
                        </div>

                        <div class="row">
                            <!-- Address -->
                            <div class="col-md-6 mb-3">
                                <label for="address" class="form-label">Address</label>
                                <form:input path="address" class="form-control" id="address"/>
                            </div>

                            <!-- Role -->
                            <div class="col-md-6 mb-3">
                                <label class="form-label">Role</label>
                                <form:select  class="form-select" path="role">
                                    <form:option value="ADMIN">Admin</form:option>
                                    <form:option value="USER">User</form:option>
                                </form:select>
                            </div>
                        </div>

                        <!-- Avatar Upload -->
                        <div class="mb-3">
                            <label for="avatarFile" class="form-label">Avatar</label>
                            <input type="file" class="form-control" id="avatarFile" name="avatarFile" accept="image/*">
                            <div class="form-check mt-2">
                                <input class="form-check-input" type="checkbox" id="noAvatar">
                                <label class="form-check-label" for="noAvatar">
                                    No Avatar
                                </label>
                            </div>
                            
                            <!-- Avatar Preview -->
                            <div class="avatar-preview" id="avatarPreview">
                                <div class="avatar-preview-text">No image selected</div>
                            </div>
                        </div>

                        <!-- Submit Button -->
                        <div class="mt-4 text-start">
                            <button type="submit" class="btn btn-success">
                                Create User
                            </button>
                        </div>

                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- jQuery PHẢI ĐẶT TRƯỚC -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<script>
    $(document).ready(function() {
        // Preview avatar khi chọn file
        $('#avatarFile').on('change', function(event) {
            const file = event.target.files[0];
            
            if (file) {
                const reader = new FileReader();
                
                reader.onload = function(e) {
                    $('#avatarPreview').html('<img src="' + e.target.result + '" alt="Avatar Preview">');
                }
                
                reader.readAsDataURL(file);
                
                // Uncheck "No Avatar" nếu user chọn file
                $('#noAvatar').prop('checked', false);
            }
        });
        
        // Toggle avatar upload khi check/uncheck "No Avatar"
        $('#noAvatar').on('change', function() {
            if ($(this).is(':checked')) {
                // Disable file input và clear preview
                $('#avatarFile').val('').prop('disabled', true);
                $('#avatarPreview').html('<div class="avatar-preview-text">No avatar will be uploaded</div>');
            } else {
                // Enable file input
                $('#avatarFile').prop('disabled', false);
                $('#avatarPreview').html('<div class="avatar-preview-text">No image selected</div>');
            }
        });
    });
</script>

</body>
</html>