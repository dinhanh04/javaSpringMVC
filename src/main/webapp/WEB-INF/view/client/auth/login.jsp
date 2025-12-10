<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <title>Đăng nhập</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <style>
        body {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
        }
        .login-card {
            background: white;
            border-radius: 15px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }
        .login-header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 2rem;
            text-align: center;
        }
        .login-body {
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
        .btn-login {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            border: none;
            border-radius: 10px;
            padding: 12px;
            font-weight: 600;
            text-transform: uppercase;
            letter-spacing: 1px;
        }
        .btn-login:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
        }
        .alert {
            border-radius: 10px;
        }
    </style>
</head>

<body>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-5">
                <div class="login-card">
                    <div class="login-header">
                        <h2 class="mb-0">
                            <i class="fas fa-user-circle me-2"></i>
                            Đăng nhập
                        </h2>
                        <p class="mb-0 mt-2">Chào mừng bạn trở lại!</p>
                    </div>
                    <div class="login-body">
                        <c:if test="${param.error != null}">
                            <div class="alert alert-danger" role="alert">
                                <i class="fas fa-exclamation-triangle me-2"></i>
                                Email hoặc mật khẩu không đúng!
                            </div>
                        </c:if>
                        <c:if test="${param.registered != null}">
                            <div class="alert alert-success" role="alert">
                                <i class="fas fa-check-circle me-2"></i>
                                Đăng ký thành công! Vui lòng đăng nhập.
                            </div>
                        </c:if>
                        <c:if test="${param.logout != null}">
                            <div class="alert alert-info" role="alert">
                                <i class="fas fa-info-circle me-2"></i>
                                Bạn đã đăng xuất thành công!
                            </div>
                        </c:if>

                        <form method="post" action="/do-login">
                            <div class="mb-3">
                                <label class="form-label">
                                    <i class="fas fa-envelope me-2"></i>Email
                                </label>
                                <input class="form-control" type="email" name="username" 
                                       placeholder="Nhập email của bạn" required />
                            </div>
                            <div class="mb-3">
                                <label class="form-label">
                                    <i class="fas fa-lock me-2"></i>Mật khẩu
                                </label>
                                <input class="form-control" type="password" name="password" 
                                       placeholder="Nhập mật khẩu" required />
                            </div>
                            <div class="d-grid">
                                <button class="btn btn-primary btn-login" type="submit">
                                    <i class="fas fa-sign-in-alt me-2"></i>Đăng nhập
                                </button>
                            </div>

                        </form>

                        <hr class="my-4">
                        <div class="text-center">
                            <p class="mb-0">Chưa có tài khoản? 
                                <a href="/register" class="text-decoration-none">
                                    <strong>Đăng ký ngay</strong>
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