package tdtu.edu.vn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertCallback;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "OderItem")
public class OrderItem {
    @Id
    @Field(name="_id")
    private String id;
    @DBRef
    private Order order;
    @DBRef
    // ID of either a book or a combo
    private Book book;
    @DBRef
    private Combo combo;

    private int quantity;
    private Date date;
    private boolean isCombo;

}
