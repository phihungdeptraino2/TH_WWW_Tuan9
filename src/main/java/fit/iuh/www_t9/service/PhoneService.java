package fit.iuh.www_t9.service;

import fit.iuh.www_t9.controller.PhoneController;
import fit.iuh.www_t9.entity.DienThoai;
import fit.iuh.www_t9.repository.DienThoaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService {

    @Autowired
private DienThoaiRepository dienThoaiRepository;

    public List<DienThoai> getAllDt(){
        return dienThoaiRepository.findAll();
    };
    public void DeleteByPhoneID(String phoneID){
         dienThoaiRepository.deleteById(phoneID);
    }
    public void SavePhone(DienThoai phone){
        dienThoaiRepository.save(phone);
    }

}
