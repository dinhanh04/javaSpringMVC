<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <title>Truy cập bị từ chối</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <style>
        body {
            background: linear-gradient(135deg, #ff6b6b 0%, #ee5a24 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
        }
        .deny-card {
            background: white;
            border-radius: 15px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            text-align: center;
        }
        .deny-header {
            background: linear-gradient(135deg, #ff6b6b 0%, #ee5a24 100%);
            color: white;
            padding: 3rem 2rem;
        }
        .deny-body {
            padding: 2rem;
        }
        .deny-icon {
            font-size: 4rem;
            margin-bottom: 1rem;
        }
        .btn-home {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            border: none;
            border-radius: 10px;
            padding: 12px 30px;
            font-weight: 600;
        }
        .btn-home:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
        }
    </style>
</head>

<body>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-6">
                <div class="deny-card">
                    <div class="deny-header">
                        <div class="deny-icon">
                            <i class="fas fa-ban"></i>
                        </div>
                        <h1 class="mb-0">403</h1>
                        <h3 class="mb-0">Truy cập bị từ chối</h3>
                    </div>
                    <div class="deny-body">
                        <p class="lead mb-4">
                            Xin lỗi, bạn không có quyền truy cập vào trang này.
                        </p>
                        <p class="text-muted mb-4">
                            Vui lòng liên hệ quản trị viên nếu bạn cho rằng đây là lỗi.
                        </p>
                        <div class="d-grid gap-2 d-md-block">
                            <a href="/" class="btn btn-primary btn-home">
                                <i class="fas fa-home me-2"></i>Về trang chủ
                            </a>
                            <a href="/login" class="btn btn-outline-secondary">
                                <i class="fas fa-sign-in-alt me-2"></i>Đăng nhập
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