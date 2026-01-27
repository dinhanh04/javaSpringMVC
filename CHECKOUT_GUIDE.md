# Hướng dẫn sử dụng chức năng thanh toán

## Tổng quan
Chức năng thanh toán đã được hoàn thiện với các tính năng sau:
- Xem giỏ hàng và cập nhật số lượng sản phẩm
- Trang checkout với form nhập thông tin giao hàng
- Xử lý đặt hàng và lưu vào database
- Trang xác nhận đặt hàng thành công
- Xem lịch sử đơn hàng

## Luồng thanh toán

### 1. Thêm sản phẩm vào giỏ hàng
- Từ trang chi tiết sản phẩm, click "Thêm vào giỏ hàng"
- Sản phẩm sẽ được thêm vào giỏ hàng của user đã đăng nhập

### 2. Xem và chỉnh sửa giỏ hàng
- Truy cập `/cart` để xem giỏ hàng
- Có thể tăng/giảm số lượng sản phẩm
- Có thể xóa sản phẩm khỏi giỏ hàng
- Xem tổng tiền tạm tính

### 3. Thanh toán
- Từ trang giỏ hàng, click nút "Thanh toán"
- Chuyển đến trang `/checkout`
- Điền thông tin người nhận:
  - Họ tên (mặc định từ thông tin user)
  - Số điện thoại (mặc định từ thông tin user)
  - Địa chỉ giao hàng (mặc định từ thông tin user)
  - Ghi chú đơn hàng (tùy chọn)
- Xem lại danh sách sản phẩm và tổng tiền
- Chọn "Thanh toán khi nhận hàng (COD)"
- Click "Đặt hàng"

### 4. Xác nhận đặt hàng
- Sau khi đặt hàng thành công, chuyển đến trang `/checkout/success/{orderId}`
- Hiển thị mã đơn hàng và thông tin xác nhận
- Có thể về trang chủ hoặc tiếp tục mua sắm

### 5. Xem lịch sử đơn hàng
- Truy cập `/orders` hoặc click "Đơn hàng của tôi" trong menu user
- Xem danh sách tất cả đơn hàng đã đặt
- Thông tin bao gồm:
  - Mã đơn hàng
  - Ngày đặt hàng
  - Trạng thái đơn hàng
  - Thông tin người nhận
  - Tổng tiền

## Cấu trúc Database

### Bảng `orders`
```sql
CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    total_price DOUBLE NOT NULL,
    receiver_name VARCHAR(255) NOT NULL,
    receiver_phone VARCHAR(20) NOT NULL,
    receiver_address TEXT NOT NULL,
    note TEXT,
    status VARCHAR(50) DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
```

### Bảng `order_detail`
```sql
CREATE TABLE order_detail (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity BIGINT NOT NULL,
    price DOUBLE NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);
```

## Trạng thái đơn hàng
- `PENDING`: Chờ xác nhận
- `CONFIRMED`: Đã xác nhận
- `SHIPPING`: Đang giao hàng
- `DELIVERED`: Đã giao hàng
- `CANCELLED`: Đã hủy

## Các file đã tạo/cập nhật

### Controllers
- `CartController.java` - Thêm các method cho checkout và lịch sử đơn hàng

### Services
- `OrderService.java` - Service xử lý logic đặt hàng

### DTOs
- `OrderFormDTO.java` - DTO cho form đặt hàng

### Repositories
- `OrderRepository.java` - Repository cho Order
- `OrderDetailRepository.java` - Repository cho OrderDetail
- Cập nhật `UserRepository.java`, `CartDetailRepository.java`

### Domain Models
- Cập nhật `Order.java` - Thêm các field cần thiết
- Cập nhật `OrderDetail.java` - Thêm getters/setters

### Views (JSP)
- `checkout.jsp` - Trang thanh toán
- `order-success.jsp` - Trang xác nhận đặt hàng thành công
- `history.jsp` - Trang lịch sử đơn hàng
- Cập nhật `cart.jsp` - Sửa nút thanh toán

### Database
- Cập nhật `data.sql` - Thêm script tạo bảng orders và order_detail

## Lưu ý
- User phải đăng nhập mới có thể thêm sản phẩm vào giỏ hàng và thanh toán
- Sau khi đặt hàng thành công, giỏ hàng sẽ được xóa sạch
- Hiện tại chỉ hỗ trợ thanh toán COD (thanh toán khi nhận hàng)
- Có thể mở rộng thêm các phương thức thanh toán khác trong tương lai