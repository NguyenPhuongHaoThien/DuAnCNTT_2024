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

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Book")
public class Book implements Serializable {
    @Id
    private String id;
    private String name;
    private String price;
    private int quantity;
    private String authorId;
    private String publisherId;
    private String categoryId;
    private String thumbnail;
    private String pdfUrl;
    private String publicationDate;
    private String description;
    private String discountId;
    private Boolean drmEnabled;
    private String status;




}
