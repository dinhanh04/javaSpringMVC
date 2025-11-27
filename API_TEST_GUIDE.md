# Hướng dẫn test API Import Excel Customer CTV

## Endpoint
```
POST http://localhost:8080/api/customer-ctv/import-excel
```

## CURL Command (Windows PowerShell)

```powershell
curl -X POST "http://localhost:8080/api/customer-ctv/import-excel" `
  -H "Content-Type: multipart/form-data" `
  -F "file=@C:\path\to\your\file.xlsx"
```

## CURL Command (Windows CMD)

```cmd
curl -X POST "http://localhost:8080/api/customer-ctv/import-excel" -H "Content-Type: multipart/form-data" -F "file=@C:\path\to\your\file.xlsx"
```

## CURL Command (Linux/Mac)

```bash
curl -X POST "http://localhost:8080/api/customer-ctv/import-excel" \
  -H "Content-Type: multipart/form-data" \
  -F "file=@/path/to/your/file.xlsx"
```

## Test trên Postman

### Cách 1: Sử dụng Postman GUI

1. **Tạo Request mới:**
   - Method: `POST`
   - URL: `http://localhost:8080/api/customer-ctv/import-excel`

2. **Cấu hình Body:**
   - Chọn tab `Body`
   - Chọn `form-data`
   - Thêm key: `file` (type: File)
   - Click `Select Files` và chọn file Excel của bạn

3. **Headers:**
   - Postman sẽ tự động set `Content-Type: multipart/form-data`

4. **Click Send**

### Cách 2: Import từ cURL

1. Copy cURL command bên dưới
2. Trong Postman, click `Import` → `Raw text`
3. Paste cURL command
4. Thay đổi đường dẫn file trong `-F "file=@..."` thành đường dẫn file Excel của bạn
5. Click `Send`

### cURL Command để import vào Postman:

```bash
curl --location 'http://localhost:8080/api/customer-ctv/import-excel' \
--form 'file=@"/path/to/your/file.xlsx"'
```

## Format Excel Template

File Excel cần có cấu trúc như sau:

| ho_ten | email | so_dien_thoai | ngay_gia_nhap | dia_chi | tai_khoan_ngan_hang | ten_ngan_hang | ma_so_thue | ty_le_hoa_hong |
|--------|-------|---------------|---------------|---------|---------------------|---------------|------------|----------------|
| Nguyen Van A | user0@gmail.com | 0919815929 | 2025-04-15 | Địa chỉ 1, Quận 5, TP.HCM | 5743925842 | ACB | 689029460 | 0.05 |
| Tran Thi B | user1@gmail.com | 0923871950 | 2025-09-21 | Địa chỉ 2, Quận 10, TP.HCM | 5355771610 | MB Bank | 294821018 | 0.04 |

**Lưu ý:**
- Row 1: Header (bắt buộc)
- Row 2 trở đi: Data
- `ngay_gia_nhap`: Format YYYY-MM-DD hoặc date trong Excel
- `ty_le_hoa_hong`: Số thập phân (ví dụ: 0.05, 0.04, 0.06)
- Email là trường bắt buộc và unique (dùng để upsert)

## Response mẫu

### Thành công:
```json
{
  "success": true,
  "message": "Import thành công 10 bản ghi",
  "count": 10,
  "data": [
    {
      "ctvId": 1,
      "hoTen": "Nguyen Van A",
      "email": "user0@gmail.com",
      "soDienThoai": "0919815929",
      "ngayGiaNhap": "2025-04-15",
      "diaChi": "Địa chỉ 1, Quận 5, TP.HCM",
      "taiKhoanNganHang": "5743925842",
      "tenNganHang": "ACB",
      "maSoThue": "689029460",
      "tyLeHoaHong": 0.05
    }
  ]
}
```

### Lỗi:
```json
{
  "success": false,
  "message": "File không được để trống"
}
```

## Test Cases

1. **Test với file hợp lệ:** Upload file Excel có đúng format → Expect: success = true
2. **Test với file rỗng:** Không chọn file → Expect: "File không được để trống"
3. **Test với file không phải Excel:** Upload file .txt hoặc .pdf → Expect: "File phải là định dạng Excel"
4. **Test upsert:** Upload file có email đã tồn tại → Expect: Update record thay vì tạo mới

