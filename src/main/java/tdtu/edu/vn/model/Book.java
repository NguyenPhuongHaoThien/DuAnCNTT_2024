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
    @Field(name="_id")
    private String id;
    private String name;
    private String price;
    private String quantity;
    @DBRef
    private Author author;
    @DBRef
    private Publisher publisher;
    @DBRef
    private Category category;
    private String thumbnail;
    private String pdfUrl;
    private String publicationDate;
    private String description;
    @DBRef
    private Discount discount;
    private Boolean drmEnabled;
    private String status;


    public Book(String name, String price, String quantity, Author author, Publisher publisher, Category category, String thumbnail, String pdfUrl, String publicationDate, String description, Discount discount, Boolean drmEnabled,String status) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
        this.thumbnail = thumbnail;
        this.pdfUrl = pdfUrl;
        this.publicationDate = publicationDate;
        this.description = description;
        this.drmEnabled = drmEnabled;
        this.discount = discount;
        this.status = status;
    }


}
