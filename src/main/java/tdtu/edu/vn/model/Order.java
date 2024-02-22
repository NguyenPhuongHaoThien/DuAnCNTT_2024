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
    @Field(name="_id")
    private String id;
    @DBRef
    private User user;
    @DBRef
    private List<ActivationCode> activationCodes; // Danh sách mã kích hoạt mua
    private double totalAmount; // Tổng số tiền
    private Date orderDate; // Ngày đặt hàng
    private OrderStatus orderStatus; // Trạng thái đơn hàng

    private enum OrderStatus {
        ORDERED,
        DELIVERED,
        CANCELLED
    }

    public Order(User user, List<ActivationCode> activationCodes, double totalAmount, Date orderDate, OrderStatus orderStatus) {
        this.user = user;
        this.activationCodes = activationCodes;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }






}
