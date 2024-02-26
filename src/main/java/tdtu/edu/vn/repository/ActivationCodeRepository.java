package tdtu.edu.vn.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tdtu.edu.vn.model.ActivationCode;

import java.util.List;

public interface ActivationCodeRepository extends MongoRepository<ActivationCode, String> {
    List<ActivationCode> findAllByCode(String code);
}
