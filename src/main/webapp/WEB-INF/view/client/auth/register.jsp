<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <title>Đăng ký</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <style>
        body {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            padding: 2rem 0;
        }
        .register-card {
            background: white;
            border-radius: 15px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }
        .register-header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 2rem;
            text-align: center;
        }
        .register-body {
            padding: 2rem;
        }
        .form-control {
            border-radius: 10px;
            border: 2px solid #e9ecef;
            padding: 12px 15px;
        }
        .form-control:focus {
            border-color: #667eea;
            box-shadow: 0 0 0 0.2rem rgba(102, 126, 234, 0.25);
        }
        .btn-register {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            border: none;
            border-radius: 10px;
            padding: 12px;
            font-weight: 600;
            text-transform: uppercase;
            letter-spacing: 1px;
        }
        .btn-register:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
        }
    </style>
</head>

<body>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-6">
                <div class="register-card">
                    <div class="register-header">
                        <h2 class="mb-0">
                            <i class="fas fa-user-plus me-2"></i>
                            Đăng ký
                        </h2>
                        <p class="mb-0 mt-2">Tạo tài khoản mới</p>
                    </div>
                    <div class="register-body">
                        <c:if test="${not empty errorMessage}">
                            <div class="alert alert-danger" role="alert">
                                <i class="fas fa-exclamation-triangle me-2"></i>${errorMessage}
                            </div>
                        </c:if>

                        <form method="post" action="/register">
                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label class="form-label">
                                        <i class="fas fa-user me-2"></i>Họ
                                    </label>
                                    <input type="text" class="form-control" name="firstName" 
                                           value="${registerUser.firstName}" placeholder="Nhập họ" required />
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label class="form-label">
                                        <i class="fas fa-user me-2"></i>Tên
                                    </label>
                                    <input type="text" class="form-control" name="lastName" 
                                           value="${registerUser.lastName}" placeholder="Nhập tên" required />
                                </div>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">
                                    <i class="fas fa-envelope me-2"></i>Email
                                </label>
                                <input type="email" class="form-control" name="email" 
                                       value="${registerUser.email}" placeholder="Nhập email" required />
                            </div>
                            <div class="mb-3">
                                <label class="form-label">
                                    <i class="fas fa-lock me-2"></i>Mật khẩu
                                </label>
                                <input type="password" class="form-control" name="password" 
                                       placeholder="Nhập mật khẩu (tối thiểu 6 ký tự)" required minlength="6" />
                            </div>
                            <div class="mb-3">
                                <label class="form-label">
                                    <i class="fas fa-lock me-2"></i>Xác nhận mật khẩu
                                </label>
                                <input type="password" class="form-control" name="confirmPassword" 
                                       placeholder="Nhập lại mật khẩu" required />
                            </div>
                            <div class="d-grid">
                                <button class="btn btn-primary btn-register" type="submit">
                                    <i class="fas fa-user-plus me-2"></i>Đăng ký
                                </button>
                            </div>
                        </form>

                        <hr class="my-4">
                        <div class="text-center">
                            <p class="mb-0">Đã có tài khoản? 
                                <a href="/login" class="text-decoration-none">
                                    <strong>Đăng nhập ngay</strong>
                                </a>
                            </p>
                        </div>
                        <div class="text-center mt-3">
                            <a href="/" class="text-decoration-none">
                                <i class="fas fa-home me-1"></i>Về trang chủ
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>