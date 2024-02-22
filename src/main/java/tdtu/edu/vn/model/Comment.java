package tdtu.edu.vn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Comment")
public class Comment {
    @Id
    @Field(name="_id")
    private String id;
    @DBRef
    private Book book;
    @DBRef
    private User user;
    private String content;
    private String date;
    private String status;//0: chưa duyệt, 1: đã duyệt

    public Comment(Book book, User user, String content, String date, String status) {
        this.book = book;
        this.user = user;
        this.content = content;
        this.date = date;
        this.status = status;
    }

}
