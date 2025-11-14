package fit.iuh.www_t9.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "NHACUNGCAP")
public class NhaCungCap {
    @Id
    @Column(name = "MANCC")
    private String maNCC; // Ví dụ: "NCC01"

    @Column(name = "TENNHACC")
    private String tenNhaCC;

    // [Chương 6] Một nhà cung cấp có nhiều điện thoại
    @OneToMany(mappedBy = "nhaCungCap")
    private Set<DienThoai> dienThoais;

    // Bỏ qua DIACHI, SODIENTHOAI cho ví dụ đơn giản...
    // ... Thêm Constructors, Getters, Setters ...
}