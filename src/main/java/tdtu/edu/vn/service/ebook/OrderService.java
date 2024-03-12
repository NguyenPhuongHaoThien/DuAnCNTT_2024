package tdtu.edu.vn.service.ebook;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tdtu.edu.vn.model.Document;
import tdtu.edu.vn.model.Order;
import tdtu.edu.vn.model.User;
import tdtu.edu.vn.repository.OrderRepository;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private ActivationCodeService activationCodeService;

    public Order placeOrder(User user, List<Document> documents, int validDays) {
        if (documents == null || documents.isEmpty()) {
            throw new IllegalArgumentException("Documents cannot be empty");
        }

        List<String> all_documents = documents.stream()
                .map(Document::getId)
                .toList();

        List<String> drm_documents = documents.stream()
                .filter(Document::getDrmEnabled)
                .map(Document::getId)
                .toList();

        Order order = new Order(null, user.getId(), all_documents, null, new Date(), Order.OrderStatus.ORDERED);
        Order savedOrder = orderRepository.save(order);

        if (!drm_documents.isEmpty()) {
            activationCodeService.createActivationCode(savedOrder, drm_documents, validDays);
        }

        return savedOrder;
    }
}