# Hướng dẫn sử dụng chức năng Login

## Tổng quan
Hệ thống đã được cấu hình lại với chức năng đăng nhập/đăng ký hoàn chỉnh sử dụng Spring Security.

## Các tính năng đã triển khai

### 1. Authentication Controller (`AuthController.java`)
- **GET /login**: Hiển thị trang đăng nhập
- **GET /register**: Hiển thị trang đăng ký
- **POST /register**: Xử lý đăng ký người dùng mới
- **GET /access-deny**: Trang thông báo từ chối truy cập

### 2. Security Configuration (`SecurityConfig.java`)
- Cấu hình Spring Security với form login
- Phân quyền: USER và ADMIN
- Bảo vệ các endpoint admin
- Xử lý logout và session

### 3. Custom User Details Service
- Tích hợp với database để xác thực người dùng
- Hỗ trợ phân quyền dựa trên roleId

### 4. Giao diện người dùng
- **Trang đăng nhập**: `/login` - Giao diện đẹp với Bootstrap
- **Trang đăng ký**: `/register` - Form đăng ký với validation
- **Trang từ chối truy cập**: `/access-deny` - Thông báo lỗi 403
- **Header**: Cập nhật với dropdown menu cho user đã đăng nhập

## Cách sử dụng

### 1. Khởi động ứng dụng
```bash
mvn spring-boot:run
```

### 2. Truy cập các trang
- **Trang chủ**: http://localhost:8080/
- **Đăng nhập**: http://localhost:8080/login
- **Đăng ký**: http://localhost:8080/register

### 3. Đăng ký tài khoản mới
1. Truy cập `/register`
2. Điền thông tin: Họ, Tên, Email, Mật khẩu
3. Xác nhận mật khẩu
4. Nhấn "Đăng ký"
5. Hệ thống sẽ chuyển về trang login với thông báo thành công

### 4. Đăng nhập
1. Truy cập `/login`
2. Nhập email và mật khẩu
3. Nhấn "Đăng nhập"
4. Nếu thành công, chuyển về trang chủ
5. Nếu thất bại, hiển thị thông báo lỗi

### 5. Quản lý phiên đăng nhập
- **Header**: Hiển thị tên người dùng và menu dropdown
- **Dropdown menu**: Thông tin cá nhân, đơn hàng, quản trị (nếu là admin)
- **Đăng xuất**: Nhấn "Đăng xuất" trong dropdown menu

## Cấu trúc Database

### Bảng Users
- `id`: Primary key
- `email`: Email đăng nhập (unique)
- `password`: Mật khẩu đã mã hóa BCrypt
- `full_name`: Họ tên đầy đủ
- `role_id`: ID vai trò (1=USER, 2=ADMIN)
- `address`, `phone_number`: Thông tin bổ sung

### Bảng Roles
- `id`: Primary key
- `name`: Tên vai trò (USER, ADMIN)
- `description`: Mô tả vai trò

## Phân quyền
- **Trang công khai**: `/`, `/login`, `/register`, `/client/**`, `/css/**`, `/js/**`, `/images/**`, `/product/**`
- **Trang yêu cầu đăng nhập**: Tất cả các trang khác
- **Trang admin**: `/admin/**` - Chỉ ADMIN mới truy cập được

## Lưu ý kỹ thuật
- Mật khẩu được mã hóa bằng BCrypt
- Session được quản lý tự động bởi Spring Security
- CSRF protection được bật
- Validation form với Spring Validation
- Responsive design với Bootstrap 5

## Troubleshooting
1. **Lỗi database**: Kiểm tra kết nối MySQL và tạo database `laptopshop`
2. **Lỗi 403**: Kiểm tra phân quyền và CSRF token
3. **Lỗi validation**: Kiểm tra format email và độ dài mật khẩu (tối thiểu 6 ký tự)
4. **Lỗi compile**: Chạy `mvn clean compile` để kiểm tra lỗi syntax