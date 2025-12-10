# Test Chức năng Login

## ✅ Ứng dụng đã khởi động thành công!

Server đang chạy tại: **http://localhost:8080**

## Các bước test:

### 1. Test trang chủ
- Truy cập: http://localhost:8080/
- Kiểm tra header có hiển thị menu đăng nhập/đăng ký

### 2. Test trang đăng ký
- Truy cập: http://localhost:8080/register
- Điền form đăng ký:
  - Họ: Nguyễn
  - Tên: Văn A
  - Email: test@example.com
  - Mật khẩu: 123456
  - Xác nhận mật khẩu: 123456
- Nhấn "Đăng ký"
- Kiểm tra chuyển về trang login với thông báo thành công

### 3. Test trang đăng nhập
- Truy cập: http://localhost:8080/login
- Đăng nhập với tài khoản vừa tạo:
  - Email: test@example.com
  - Mật khẩu: 123456
- Nhấn "Đăng nhập"
- Kiểm tra chuyển về trang chủ và header hiển thị tên user

### 4. Test đăng xuất
- Nhấn vào dropdown menu user ở header
- Chọn "Đăng xuất"
- Kiểm tra chuyển về trang chủ và header trở về trạng thái chưa đăng nhập

### 5. Test validation
- Thử đăng ký với email đã tồn tại
- Thử đăng ký với mật khẩu không khớp
- Thử đăng nhập với thông tin sai

## Lưu ý:
- Database cần có sẵn và kết nối thành công
- Bảng `roles` sẽ được tự động tạo với dữ liệu mặc định
- Mật khẩu được mã hóa BCrypt
- Session được quản lý tự động

## Nếu có lỗi:
1. Kiểm tra database connection
2. Kiểm tra console log
3. Kiểm tra network tab trong browser
4. Restart server nếu cần