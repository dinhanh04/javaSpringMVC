<!-- Navbar start -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="container-fluid fixed-top">
    <div class="container px-0">
        <nav class="navbar navbar-light bg-white navbar-expand-xl">
            <a href="/" class="navbar-brand"><h1 class="text-primary display-6">Laptopshop</h1></a>
            <button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="fa fa-bars text-primary"></span>
            </button>
            <div class="collapse navbar-collapse bg-white" id="navbarCollapse">
                <div class="navbar-nav mx-auto">
                    <a href="/" class="nav-item nav-link">Trang chủ</a>
                    <a href="/products" class="nav-item nav-link">Sản phẩm</a>
                </div>
                <div class="d-flex m-3 me-0">
                    <button class="btn-search btn border border-secondary btn-md-square rounded-circle bg-white me-4" data-bs-toggle="modal" data-bs-target="#searchModal">
                        <i class="fas fa-search text-primary"></i>
                    </button>
                    <a href="#" class="position-relative me-4 my-auto">
                        <i class="fa fa-shopping-bag fa-2x"></i>
                        <span class="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1" style="top: -5px; left: 15px; height: 20px; min-width: 20px;">0</span>
                    </a>
                    
                    <!-- Authentication section -->
                    <sec:authorize access="!isAuthenticated()">
                        <div class="dropdown my-auto">
                            <a href="#" class="dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="fas fa-user fa-2x"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-end">
                                <li><a class="dropdown-item" href="/login">
                                    <i class="fas fa-sign-in-alt me-2"></i>Đăng nhập
                                </a></li>
                                <li><a class="dropdown-item" href="/register">
                                    <i class="fas fa-user-plus me-2"></i>Đăng ký
                                </a></li>
                            </ul>
                        </div>
                    </sec:authorize>
                    
                    <sec:authorize access="isAuthenticated()">
                        <div class="dropdown my-auto">
                            <a href="#" class="dropdown-toggle d-flex align-items-center" data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="fas fa-user fa-2x me-2"></i>
                                <span class="d-none d-md-inline">
                                    <sec:authentication property="name" />
                                </span>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-end">
                                <li><h6 class="dropdown-header">
                                    <i class="fas fa-user-circle me-2"></i>
                                    <sec:authentication property="name" />
                                </h6></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="/profile">
                                    <i class="fas fa-user-edit me-2"></i>Thông tin cá nhân
                                </a></li>
                                <li><a class="dropdown-item" href="/orders">
                                    <i class="fas fa-shopping-cart me-2"></i>Đơn hàng của tôi
                                </a></li>
                                <sec:authorize access="hasRole('ADMIN')">
                                    <li><hr class="dropdown-divider"></li>
                                    <li><a class="dropdown-item" href="/admin">
                                        <i class="fas fa-cog me-2"></i>Quản trị
                                    </a></li>
                                </sec:authorize>
                                <li><hr class="dropdown-divider"></li>
                                <li>
                                    <form method="post" action="/logout" class="d-inline">
                                        <button type="submit" class="dropdown-item text-danger">
                                            <i class="fas fa-sign-out-alt me-2"></i>Đăng xuất
                                        </button>

                                    </form>
                                </li>
                            </ul>
                        </div>
                    </sec:authorize>
                </div>
            </div>
        </nav>
    </div>
</div>
<!-- Navbar End -->