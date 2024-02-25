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
    private String quantity;
    private Author authorId;
    private Publisher publisherId;
    private Category categoryId;
    private String thumbnail;
    private String pdfUrl;
    private String publicationDate;
    private String description;
    private Discount discountId;
    private Boolean drmEnabled;
    private String status;


    public Book(String name, String price, String quantity, Author authorId, Publisher publisherId, Category categoryId, String thumbnail, String pdfUrl, String publicationDate, String description, Discount discountId, Boolean drmEnabled, String status) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.authorId = authorId;
        this.publisherId = publisherId;
        this.categoryId = categoryId;
        this.thumbnail = thumbnail;
        this.pdfUrl = pdfUrl;
        this.publicationDate = publicationDate;
        this.description = description;
        this.discountId = discountId;
        this.drmEnabled = drmEnabled;
        this.status = status;
    }

}
