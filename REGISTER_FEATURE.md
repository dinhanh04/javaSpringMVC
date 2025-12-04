# Tính năng Đăng ký Tài khoản

## Các file đã tạo

### 1. Controller
- **AuthController.java** (`src/main/java/com/example/demo/controller/client/AuthController.java`)
  - `GET /login` - Hiển thị trang đăng nhập
  - `GET /register` - Hiển thị trang đăng ký
  - `POST /register` - Xử lý đăng ký tài khoản mới

### 2. DTO
- **RegisterDTO.java** (`src/main/java/com/example/demo/dto/RegisterDTO.java`)
  - Chứa các trường: firstName, lastName, email, password, confirmPassword
  - Có validation cho tất cả các trường

### 3. View (JSP)
- **register.jsp** (`src/main/webapp/WEB-INF/view/client/auth/register.jsp`)
  - Form đăng ký với đầy đủ các trường
  - Hiển thị lỗi validation
  - Responsive design với Bootstrap

- **login.jsp** (`src/main/webapp/WEB-INF/view/client/auth/login.jsp`)
  - Form đăng nhập
  - Hiển thị thông báo đăng ký thành công
  - Link đến trang đăng ký

### 4. Cập nhật các file hiện có

#### UserRepository.java
- Thêm method `existsByEmail(String email)` để kiểm tra email đã tồn tại

#### UserService.java
- Thêm method `checkEmailExist(String email)` để kiểm tra email

#### SecurityConfig.java
- Cấu hình SecurityFilterChain
- Cho phép truy cập public: `/register`, `/login`, `/`, `/product/**`
- Cấu hình form login và logout

## Cách sử dụng

1. **Truy cập trang đăng ký**: `http://localhost:8080/register`

2. **Điền thông tin**:
   - Họ (bắt buộc)
   - Tên (bắt buộc)
   - Email (bắt buộc, phải đúng định dạng)
   - Mật khẩu (bắt buộc, tối thiểu 6 ký tự)
   - Xác nhận mật khẩu (bắt buộc, phải khớp với mật khẩu)

3. **Validation**:
   - Kiểm tra tất cả trường bắt buộc
   - Kiểm tra định dạng email
   - Kiểm tra độ dài mật khẩu
   - Kiểm tra mật khẩu xác nhận khớp
   - Kiểm tra email đã tồn tại trong hệ thống

4. **Sau khi đăng ký thành công**:
   - Chuyển hướng đến trang login với thông báo thành công
   - Có thể đăng nhập với tài khoản vừa tạo

## Lưu ý

- Mật khẩu được mã hóa bằng BCrypt trước khi lưu vào database
- Role mặc định cho user mới là `roleId = 1` (USER)
- FullName được tạo từ firstName + lastName
- Cần đảm bảo có role với id = 1 trong database

## Chạy ứng dụng

```bash
.\mvnw.cmd spring-boot:run
```

Sau đó truy cập: `http://localhost:8080/register`
