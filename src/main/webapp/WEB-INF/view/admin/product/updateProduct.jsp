<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Update Product</title>
    <link href="/css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
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

<body class="sb-nav-fixed">
    <jsp:include page="../layout/header.jsp" />
    <div id="layoutSidenav">
        <jsp:include page="../layout/sidebar.jsp" />
        <div id="layoutSidenav_content">
            <main>
                <div class="container-fluid px-4">
                    <h1 class="mt-4">Update Product</h1>
                    <ol class="breadcrumb mb-4">
                        <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                        <li class="breadcrumb-item"><a href="/admin/product">Products</a></li>
                        <li class="breadcrumb-item active">Update</li>
                    </ol>
                    <div class="row justify-content-center">
                        <div class="col-lg-10 mt-5">
                            <div class="card shadow-lg border-0 rounded-3">
                                <div class="card-header text-black text-left py-3">
                                    <h3 class="mb-0">Update Product</h3>
                                </div>
                                <div class="card-body p-4">
                                    <%--@elvariable id="newProduct" type=""--%>
                                    <form:form action="/admin/product/update/${id}" method="post"
                                        modelAttribute="newProduct" enctype="multipart/form-data">

                                        <div class="mb-3" style="display:none;">
                                            <label for="id" class="form-label">Id</label>
                                            <form:input path="id" class="form-control" id="id" />
                                        </div>

                                        <div class="row">
                                            <!-- Name -->
                                            <div class="col-md-6 mb-3">
                                                <label for="name" class="form-label">Product Name</label>
                                                <form:input path="name" class="form-control" id="name" required="true"/>
                                            </div>

                                            <!-- Price -->
                                            <div class="col-md-6 mb-3">
                                                <label for="price" class="form-label">Price</label>
                                                <form:input path="price" class="form-control" id="price" type="number" step="0.01" min="0" required="true"/>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <!-- Quantity -->
                                            <div class="col-md-6 mb-3">
                                                <label for="quantity" class="form-label">Quantity</label>
                                                <form:input path="quantity" class="form-control" id="quantity" type="number" min="0" required="true"/>
                                            </div>

                                            <!-- Factory -->
                                            <div class="col-md-6 mb-3">
                                                <label for="factory" class="form-label">Factory</label>
                                                <form:input path="factory" class="form-control" id="factory"/>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <!-- Target -->
                                            <div class="col-md-6 mb-3">
                                                <label for="target" class="form-label">Target</label>
                                                <form:input path="target" class="form-control" id="target"/>
                                            </div>

                                            <!-- Sold -->
                                            <div class="col-md-6 mb-3">
                                                <label for="sold" class="form-label">Sold</label>
                                                <form:input path="sold" class="form-control" id="sold" type="number" min="0"/>
                                            </div>
                                        </div>

                                        <!-- Short Description -->
                                        <div class="mb-3">
                                            <label for="shortDesc" class="form-label">Short Description</label>
                                            <form:textarea path="shortDesc" class="form-control" id="shortDesc" rows="3"/>
                                        </div>

                                        <!-- Detail Description -->
                                        <div class="mb-3">
                                            <label for="detailDesc" class="form-label">Detail Description</label>
                                            <form:textarea path="detailDesc" class="form-control" id="detailDesc" rows="5"/>
                                        </div>

                                        <!-- Image Upload -->
                                        <div class="mb-3">
                                            <label for="imageFile" class="form-label">Product Image</label>
                                            <c:if test="${newProduct.image != null && newProduct.image.url != null}">
                                                <div class="mb-2">
                                                    <label class="form-label">Current Image:</label>
                                                    <div>
                                                        <img src="${newProduct.image.url}" alt="Current product image"
                                                            style="max-width: 150px; max-height: 150px; object-fit: contain;" />
                                                    </div>
                                                </div>
                                            </c:if>
                                            <input type="file" class="form-control" id="imageFile"
                                                name="imageFile" accept="image/*">
                                            <div class="form-check mt-2">
                                                <input class="form-check-input" type="checkbox"
                                                    id="noImage">
                                                <label class="form-check-label" for="noImage">
                                                    No Image
                                                </label>
                                            </div>

                                            <!-- Image Preview -->
                                            <div class="avatar-preview" id="imagePreview">
                                                <div class="avatar-preview-text">No image selected</div>
                                            </div>
                                        </div>

                                        <!-- Submit Button -->
                                        <div class="mt-4 text-start">
                                            <button type="submit" class="btn btn-success">
                                                Update Product
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

    <script>
        $(document).ready(function () {
            $('#imageFile').on('change', function (event) {
                const file = event.target.files[0];

                if (file) {
                    const reader = new FileReader();

                    reader.onload = function (e) {
                        $('#imagePreview').html('<img src="' + e.target.result + '" alt="Image Preview">');
                    }

                    reader.readAsDataURL(file);

                    $('#noImage').prop('checked', false);
                }
            });

            $('#noImage').on('change', function () {
                if ($(this).is(':checked')) {
                    $('#imageFile').val('').prop('disabled', true);
                    $('#imagePreview').html('<div class="avatar-preview-text">No image will be uploaded</div>');
                } else {
                    $('#imageFile').prop('disabled', false);
                    $('#imagePreview').html('<div class="avatar-preview-text">No image selected</div>');
                }
            });
        });
    </script>

</body>

</html>




