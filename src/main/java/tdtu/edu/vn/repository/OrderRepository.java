package tdtu.edu.vn.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tdtu.edu.vn.model.Order;

public interface OrderRepository extends MongoRepository<Order, String> {
    Order findByUserId(String userId);
    Order findByActivationCodeId(String activationCodeIds);
    Order findByUserIdAndBookIdsContains(String userId, String bookId);
}
