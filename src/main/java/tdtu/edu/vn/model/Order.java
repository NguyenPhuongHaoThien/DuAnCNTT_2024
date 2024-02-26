package tdtu.edu.vn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Order")
public class Order {
    @Id
    private String id;
    private String userId;
    private List<String> activationCodeIds; // Danh sách mã kích hoạt mua
    private double totalAmount; // Tổng số tiền
    private Date orderDate; // Ngày đặt hàng
    private OrderStatus orderStatus; // Trạng thái đơn hàng

    public Order(String userId, List<String> activationCodeIds, double totalAmount, Date orderDate, OrderStatus orderStatus) {
        this.userId = userId;
        this.activationCodeIds = activationCodeIds;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }
    private enum OrderStatus {
        ORDERED,
        DELIVERED,
        CANCELLED
    }








}
