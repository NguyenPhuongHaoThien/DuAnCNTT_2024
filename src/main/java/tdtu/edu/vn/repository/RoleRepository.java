package tdtu.edu.vn.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tdtu.edu.vn.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByName(String name);
}
