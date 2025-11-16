package fit.iuh.www_t9.service;

import fit.iuh.www_t9.entity.NhaCungCap;
import fit.iuh.www_t9.repository.NhaCungCapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NhaCungCapService {
    @Autowired
    private NhaCungCapRepository nhaCungCapRepository;

    public List<NhaCungCap> getAllNCC(){
        return nhaCungCapRepository.findAll();
    }
}
