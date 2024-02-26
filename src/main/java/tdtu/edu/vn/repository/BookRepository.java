package tdtu.edu.vn.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import tdtu.edu.vn.model.Book;

public interface BookRepository extends MongoRepository<Book, String> {
    Book findByName(String name);
    Page<Book> findAll(Pageable pageable);


    @Query(value = "{ $text: { $search:  ?0 } }", sort = "{ score: { $meta: \"textScore\" }, _id: -1 }")
    Page<Book> searchByName(String keyword, Pageable pageable);


}
