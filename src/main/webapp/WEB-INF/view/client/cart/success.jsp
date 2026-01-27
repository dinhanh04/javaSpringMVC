<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <meta name="description" content="" />
                <meta name="author" content="" />
                <title>Đặt hàng thành công - Laptopshop</title>
                <!-- Google Web Fonts -->
                <link rel="preconnect" href="https://fonts.googleapis.com">
                <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
                <link
                    href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap"
                    rel="stylesheet">

                <!-- Icon Font Stylesheet -->
                <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
                <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
                    rel="stylesheet">

                <!-- Libraries Stylesheet -->
                <link href="/client/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
                <link href="/client/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

                <!-- Customized Bootstrap Stylesheet -->
                <link href="/client/css/bootstrap.min.css" rel="stylesheet">

                <!-- Template Stylesheet -->
                <link href="/client/css/style.css" rel="stylesheet">
            </head>

            <body>

                <!-- Spinner Start -->
                <div id="spinner"
                    class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
                    <div class="spinner-grow text-primary" role="status"></div>
                </div>
                <!-- Spinner End -->

                <jsp:include page="../layout/header.jsp" />

                <!-- Single Page Header start -->
                <div class="container-fluid page-header py-5">
                    <h1 class="text-center text-white display-6">Đặt hàng thành công</h1>
                    <ol class="breadcrumb justify-content-center mb-0">
                        <li class="breadcrumb-item"><a href="/">Trang chủ</a></li>
                        <li class="breadcrumb-item"><a href="/cart">Giỏ hàng</a></li>
                        <li class="breadcrumb-item active text-white">Đặt hàng thành công</li>
                    </ol>
                </div>
                <!-- Single Page Header End -->

                <!-- Success Page Start -->
                <div class="container-fluid py-5">
                    <div class="container py-5">
                        <div class="row justify-content-center">
                            <div class="col-lg-8">
                                <!-- Success Message -->
                                <div class="alert alert-success text-center mb-5" role="alert">
                                    <i class="fas fa-check-circle fa-3x mb-3 text-success"></i>
                                    <h4 class="alert-heading">Đặt hàng thành công!</h4>
                                    <p class="mb-0">Cảm ơn bạn đã đặt hàng. Chúng tôi sẽ liên hệ với bạn sớm nhất.</p>
                                </div>

                                <!-- Order Information -->
                                <div class="card mb-4">
                                    <div class="card-header bg-primary text-white">
                                        <h5 class="mb-0">Thông tin đơn hàng #${order.id}</h5>
                                    </div>
                                    <div class="card-body">
                                        <div class="row mb-3">
                                            <div class="col-md-6">
                                                <p class="mb-2"><strong>Người nhận:</strong> ${order.receiverName}</p>
                                                <p class="mb-2"><strong>Số điện thoại:</strong> ${order.receiverPhone}
                                                </p>
                                                <p class="mb-2"><strong>Địa chỉ:</strong> ${order.receiverAddress}</p>
                                            </div>
                                            <div class="col-md-6">
                                                <p class="mb-2"><strong>Trạng thái:</strong>
                                                    <span class="badge bg-warning text-dark">
                                                        <c:choose>
                                                            <c:when test="${order.status == 'PENDING'}">Chờ xác nhận
                                                            </c:when>
                                                            <c:when test="${order.status == 'CONFIRMED'}">Đã xác nhận
                                                            </c:when>
                                                            <c:when test="${order.status == 'SHIPPING'}">Đang giao hàng
                                                            </c:when>
                                                            <c:when test="${order.status == 'DELIVERED'}">Đã giao hàng
                                                            </c:when>
                                                            <c:when test="${order.status == 'CANCELLED'}">Đã hủy
                                                            </c:when>
                                                        </c:choose>
                                                    </span>
                                                </p>
                                                <p class="mb-2"><strong>Ngày đặt:</strong>
                                                    ${order.createdAt}
                                                </p>
                                                <p class="mb-2"><strong>Tổng tiền:</strong>
                                                    <span class="text-primary fw-bold">
                                                        <fmt:formatNumber value="${order.totalPrice}" type="number" />đ
                                                    </span>
                                                </p>
                                            </div>
                                        </div>
                                        <c:if test="${not empty order.note}">
                                            <div class="row">
                                                <div class="col-12">
                                                    <p class="mb-0"><strong>Ghi chú:</strong> ${order.note}</p>
                                                </div>
                                            </div>
                                        </c:if>
                                    </div>
                                </div>

                                <!-- Order Details -->
                                <div class="card mb-4">
                                    <div class="card-header bg-light">
                                        <h5 class="mb-0">Chi tiết sản phẩm</h5>
                                    </div>
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table class="table table-bordered">
                                                <thead class="table-light">
                                                    <tr>
                                                        <th>Sản phẩm</th>
                                                        <th>Đơn giá</th>
                                                        <th>Số lượng</th>
                                                        <th>Tổng</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="detail" items="${order.orderDetails}">
                                                        <tr>
                                                            <td>
                                                                <div class="d-flex align-items-center">
                                                                    <img src="${detail.product.image}"
                                                                        class="img-fluid rounded me-3"
                                                                        style="width: 60px; height: 60px; object-fit: cover;"
                                                                        alt="${detail.product.name}">
                                                                    <span>${detail.product.name}</span>
                                                                </div>
                                                            </td>
                                                            <td>
                                                                <fmt:formatNumber value="${detail.price}"
                                                                    type="number" />đ
                                                            </td>
                                                            <td>${detail.quantity}</td>
                                                            <td>
                                                                <fmt:formatNumber
                                                                    value="${detail.price * detail.quantity}"
                                                                    type="number" />đ
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                                <tfoot>
                                                    <tr>
                                                        <td colspan="3" class="text-end"><strong>Tổng cộng:</strong>
                                                        </td>
                                                        <td>
                                                            <strong class="text-primary">
                                                                <fmt:formatNumber value="${order.totalPrice}"
                                                                    type="number" />đ
                                                            </strong>
                                                        </td>
                                                    </tr>
                                                </tfoot>
                                            </table>
                                        </div>
                                    </div>
                                </div>

                                <!-- Action Buttons -->
                                <div class="text-center">
                                    <a href="/" class="btn btn-primary rounded-pill py-3 px-5 me-3">
                                        <i class="fas fa-home me-2"></i>Về trang chủ
                                    </a>
                                    <a href="/orders" class="btn btn-outline-primary rounded-pill py-3 px-5">
                                        <i class="fas fa-list me-2"></i>Xem đơn hàng của tôi
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Success Page End -->

                <jsp:include page="../layout/footer.jsp" />

                <!-- Back to Top -->
                <a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i
                        class="fa fa-arrow-up"></i></a>

                <!-- JavaScript Libraries -->
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
                <script src="/client/lib/easing/easing.min.js"></script>
                <script src="/client/lib/waypoints/waypoints.min.js"></script>
                <script src="/client/lib/lightbox/js/lightbox.min.js"></script>
                <script src="/client/lib/owlcarousel/owl.carousel.min.js"></script>

                <!-- Template Javascript -->
                <script src="/client/js/main.js"></script>
            </body>

            </html>