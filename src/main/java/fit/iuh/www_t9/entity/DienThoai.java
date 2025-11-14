package fit.iuh.www_t9.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*; // Import cho Validation

@Entity
@Table(name = "DIENTHOAI")
public class DienThoai {
    @Id
    @Column(name = "MADT")
    private String maDT; // Ví dụ: "DT01"

    @Column(name = "TENDT")
    @NotEmpty(message = "Tên điện thoại không được để trống") // [Chương 7] Validation
    private String tenDT;

    @Column(name = "NAMSANXUAT")
    @Pattern(regexp = "^\\d{4}$", message = "Năm sản xuất phải là 4 chữ số") // [cite: 2394]
    private String namSanXuat;

    @Size(max = 255, message = "Cấu hình không quá 255 ký tự") // [cite: 2395]
    private String cauHinh;

    @Column(name = "HINHANH")
    private String hinhAnh; // Chỉ lưu tên file ảnh

    // [Chương 6] Nhiều điện thoại thuộc 1 nhà cung cấp
    @ManyToOne
    @JoinColumn(name = "MANCC")
    private NhaCungCap nhaCungCap;

    public String getMaDT() {
        return maDT;
    }

    public void setMaDT(String maDT) {
        this.maDT = maDT;
    }

    public String getTenDT() {
        return tenDT;
    }

    public void setTenDT(String tenDT) {
        this.tenDT = tenDT;
    }

    public String getNamSanXuat() {
        return namSanXuat;
    }

    public void setNamSanXuat(String namSanXuat) {
        this.namSanXuat = namSanXuat;
    }

    public String getCauHinh() {
        return cauHinh;
    }

    public void setCauHinh(String cauHinh) {
        this.cauHinh = cauHinh;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public NhaCungCap getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(NhaCungCap nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public DienThoai() {
    }

    public DienThoai(String maDT, String tenDT, String namSanXuat, String cauHinh, String hinhAnh, NhaCungCap nhaCungCap) {
        this.maDT = maDT;
        this.tenDT = tenDT;
        this.namSanXuat = namSanXuat;
        this.cauHinh = cauHinh;
        this.hinhAnh = hinhAnh;
        this.nhaCungCap = nhaCungCap;
    }
    // ... Thêm Constructors, Getters, Setters ...
}