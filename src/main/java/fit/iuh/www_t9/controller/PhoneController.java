package fit.iuh.www_t9.controller;



import fit.iuh.www_t9.entity.DienThoai;
import fit.iuh.www_t9.repository.DienThoaiRepository;
import fit.iuh.www_t9.repository.NhaCungCapRepository;
import fit.iuh.www_t9.service.NhaCungCapService;
import fit.iuh.www_t9.service.PhoneService;
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
private PhoneService phoneService;

    @Autowired
    private NhaCungCapService nhaCungCapService;

    // === CHỨC NĂNG 1: XEM DANH SÁCH (Thay cho DanhSachDienThoaiNCCServlet) ===
    @GetMapping("/")
    public String showPhoneList(Model model) {
        model.addAttribute("phones", phoneService.getAllDt());
        return "list-phones"; // Trả về file list-phones.html
    }


    // === CHỨC NĂNG 2: THÊM MỚI (Thay cho DienThoaiFormServlet) ===
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("phone", new DienThoai()); // Tạo object rỗng
        model.addAttribute("suppliers", nhaCungCapService.getAllNCC()); // Gửi DS nhà cung cấp
        return "form-phone"; // Trả về file form-phone.html
    }

    @PostMapping("/save")
    public String savePhone(@Valid @ModelAttribute("phone") DienThoai phone,
                            BindingResult bindingResult, // [Chương 7] Kết quả Validation

                            Model model) {

        // [Chương 7] Nếu validation thất bại, quay lại form
        if (bindingResult.hasErrors()) {
            model.addAttribute("suppliers", nhaCungCapService.getAllNCC());
            return "form-phone";
        }




        phoneService.SavePhone(phone); // [Chương 6] Lưu vào DB
        return "redirect:/"; // Quay về trang chủ
    }

    // === CHỨC NĂNG 3: XÓA SẢN PHẨM (Thay cho QuanLyFormServlet) ===
    @GetMapping("/delete/{maDT}")
    public String deletePhone(@PathVariable("maDT") String maDT) {
        phoneService.DeleteByPhoneID(maDT);
        return "redirect:/";
    }
}