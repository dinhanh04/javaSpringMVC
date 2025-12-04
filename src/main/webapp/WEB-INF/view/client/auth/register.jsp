<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <title>Đăng ký tài khoản</title>
    
    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap" rel="stylesheet">

    <!-- Icon Font Stylesheet -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="/client/css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="/client/css/style.css" rel="stylesheet">
    
    <style>
        .register-container {
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            padding: 20px;
        }
        .register-card {
            background: white;
            border-radius: 15px;
            box-shadow: 0 10px 40px rgba(0,0,0,0.1);
            overflow: hidden;
            max-width: 500px;
            width: 100%;
        }
        .register-header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 30px;
            text-align: center;
        }
        .register-header h2 {
            margin: 0;
            font-weight: 700;
        }
        .register-body {
            padding: 40px;
        }
        .form-control:focus {
            border-color: #667eea;
            box-shadow: 0 0 0 0.2rem rgba(102, 126, 234, 0.25);
        }
        .btn-register {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            border: none;
            padding: 12px;
            font-weight: 600;
            transition: transform 0.2s;
        }
        .btn-register:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
        }
        .error-message {
            color: #dc3545;
            font-size: 0.875rem;
            margin-top: 0.25rem;
        }
        .form-label {
            font-weight: 600;
            color: #333;
            margin-bottom: 0.5rem;
        }
    </style>
</head>
<body>

<div class="register-container">
    <div class="register-card">
        <div class="register-header">
            <h2><i class="fas fa-user-plus me-2"></i>Đăng ký tài khoản</h2>
            <p class="mb-0 mt-2">Tạo tài khoản mới để bắt đầu mua sắm</p>
        </div>
        
        <div class="register-body">
            <form:form method="post" action="/register" modelAttribute="registerUser">
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Họ <span class="text-danger">*</span></label>
                        <form:input path="firstName" class="form-control" placeholder="Nhập họ"/>
                        <form:errors path="firstName" cssClass="error-message"/>
                    </div>
                    
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Tên <span class="text-danger">*</span></label>
                        <form:input path="lastName" class="form-control" placeholder="Nhập tên"/>
                        <form:errors path="lastName" cssClass="error-message"/>
                    </div>
                </div>
                
                <div class="mb-3">
                    <label class="form-label">Email <span class="text-danger">*</span></label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                        <form:input path="email" type="email" class="form-control" placeholder="example@email.com"/>
                    </div>
                    <form:errors path="email" cssClass="error-message"/>
                </div>
                
                <div class="mb-3">
                    <label class="form-label">Mật khẩu <span class="text-danger">*</span></label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="fas fa-lock"></i></span>
                        <form:input path="password" type="password" class="form-control" placeholder="Tối thiểu 6 ký tự"/>
                    </div>
                    <form:errors path="password" cssClass="error-message"/>
                </div>
                
                <div class="mb-4">
                    <label class="form-label">Xác nhận mật khẩu <span class="text-danger">*</span></label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="fas fa-lock"></i></span>
                        <form:input path="confirmPassword" type="password" class="form-control" placeholder="Nhập lại mật khẩu"/>
                    </div>
                    <form:errors path="confirmPassword" cssClass="error-message"/>
                </div>
                
                <button type="submit" class="btn btn-primary btn-register w-100">
                    <i class="fas fa-user-plus me-2"></i>Đăng ký
                </button>
                
                <div class="text-center mt-4">
                    <p class="mb-0">Đã có tài khoản? <a href="/login" class="text-decoration-none fw-bold">Đăng nhập ngay</a></p>
                </div>
            </form:form>
        </div>
    </div>
</div>

<!-- JavaScript Libraries -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
