package tdtu.edu.vn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Combo")
public class Combo {
    @Id
    @Field(name="_id")
    private String id;
    private String name;
    @DBRef
    private List<Book> bookIds; // IDs of books included in the combo
    private String description;

       public Combo(String name, List<Book> bookIds, String description) {
            this.name = name;
            this.bookIds = bookIds;
            this.description = description;
        }



}
