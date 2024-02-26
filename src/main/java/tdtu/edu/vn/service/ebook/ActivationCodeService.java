package tdtu.edu.vn.service.ebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdtu.edu.vn.model.ActivationCode;
import tdtu.edu.vn.repository.ActivationCodeRepository;

import java.util.List;

@Service
public class ActivationCodeService {
    @Autowired
    private ActivationCodeRepository activationCodeRepository;

    public List<ActivationCode> findAllByCode(String code){
        return activationCodeRepository.findAllByCode(code);
    }

    public ActivationCode.ActivationCodeStatus getStatus(){
        return ActivationCode.ActivationCodeStatus.UNUSED;
    }

    public void save(ActivationCode code) {
        activationCodeRepository.save(code);
    }
}
