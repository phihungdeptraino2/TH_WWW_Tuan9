package fit.iuh.www_t9.controller;



import fit.iuh.www_t9.entity.DienThoai;
import fit.iuh.www_t9.repository.DienThoaiRepository;
import fit.iuh.www_t9.repository.NhaCungCapRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.*;

@Controller // [Chương 7] Thay thế cho tất cả Servlet
public class PhoneController {

    // [Chương 4] "Tiêm" (Inject) các Repository vào Controller
    @Autowired
    private DienThoaiRepository dienThoaiRepo;

    @Autowired
    private NhaCungCapRepository nhaCungCapRepo;

    // === CHỨC NĂNG 1: XEM DANH SÁCH (Thay cho DanhSachDienThoaiNCCServlet) ===
    @GetMapping("/")
    public String showPhoneList(Model model) {
        model.addAttribute("phones", dienThoaiRepo.findAll());
        return "list-phones"; // Trả về file list-phones.html
    }

    // === CHỨC NĂNG 2: THÊM MỚI (Thay cho DienThoaiFormServlet) ===
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("phone", new DienThoai()); // Tạo object rỗng
        model.addAttribute("suppliers", nhaCungCapRepo.findAll()); // Gửi DS nhà cung cấp
        return "form-phone"; // Trả về file form-phone.html
    }

    @PostMapping("/save")
    public String savePhone(@Valid @ModelAttribute("phone") DienThoai phone,
                            BindingResult bindingResult, // [Chương 7] Kết quả Validation
                            @RequestParam("fileHinhAnh") MultipartFile hinhAnhFile,
                            Model model) {

        // [Chương 7] Nếu validation thất bại, quay lại form
        if (bindingResult.hasErrors()) {
            model.addAttribute("suppliers", nhaCungCapRepo.findAll());
            return "form-phone";
        }

        // Xử lý Upload file ảnh [cite: 2370]
        if (!hinhAnhFile.isEmpty()) {
            try {
                // Lưu file vào thư mục static
                Path uploadDir = Paths.get("src/main/resources/static/uploads/");
                if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
                }

                String tenFile = hinhAnhFile.getOriginalFilename();
                InputStream inputStream = hinhAnhFile.getInputStream();
                Files.copy(inputStream, uploadDir.resolve(tenFile), StandardCopyOption.REPLACE_EXISTING);

                // [Chương 6] Lưu tên file vào DB
                phone.setHinhAnh(tenFile); // Lưu tên file vào cột HINHANH
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        dienThoaiRepo.save(phone); // [Chương 6] Lưu vào DB
        return "redirect:/"; // Quay về trang chủ
    }

    // === CHỨC NĂNG 3: XÓA SẢN PHẨM (Thay cho QuanLyFormServlet) ===
    @GetMapping("/delete/{maDT}")
    public String deletePhone(@PathVariable("maDT") String maDT) {
        dienThoaiRepo.deleteById(maDT);
        return "redirect:/";
    }
}