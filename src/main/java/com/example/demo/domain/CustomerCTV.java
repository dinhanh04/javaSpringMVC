package com.example.demo.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "customer_ctv")
public class CustomerCTV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ctv_id")
    private Integer ctvId;

    @Column(name = "ho_ten", nullable = false, length = 100)
    private String hoTen;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "so_dien_thoai", length = 15)
    private String soDienThoai;

    @Column(name = "ngay_gia_nhap", nullable = false)
    private LocalDate ngayGiaNhap;

    @Column(name = "dia_chi", length = 255)
    private String diaChi;

    @Column(name = "tai_khoan_ngan_hang", length = 50)
    private String taiKhoanNganHang;

    @Column(name = "ten_ngan_hang", length = 50)
    private String tenNganHang;

    @Column(name = "ma_so_thue", length = 20)
    private String maSoThue;

    @Column(name = "ty_le_hoa_hong", precision = 5, scale = 2)
    private BigDecimal tyLeHoaHong;

    public CustomerCTV() {
    }

    public Integer getCtvId() {
        return ctvId;
    }

    public void setCtvId(Integer ctvId) {
        this.ctvId = ctvId;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public LocalDate getNgayGiaNhap() {
        return ngayGiaNhap;
    }

    public void setNgayGiaNhap(LocalDate ngayGiaNhap) {
        this.ngayGiaNhap = ngayGiaNhap;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getTaiKhoanNganHang() {
        return taiKhoanNganHang;
    }

    public void setTaiKhoanNganHang(String taiKhoanNganHang) {
        this.taiKhoanNganHang = taiKhoanNganHang;
    }

    public String getTenNganHang() {
        return tenNganHang;
    }

    public void setTenNganHang(String tenNganHang) {
        this.tenNganHang = tenNganHang;
    }

    public String getMaSoThue() {
        return maSoThue;
    }

    public void setMaSoThue(String maSoThue) {
        this.maSoThue = maSoThue;
    }

    public BigDecimal getTyLeHoaHong() {
        return tyLeHoaHong;
    }

    public void setTyLeHoaHong(BigDecimal tyLeHoaHong) {
        this.tyLeHoaHong = tyLeHoaHong;
    }
}



