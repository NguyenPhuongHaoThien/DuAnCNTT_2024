package tdtu.edu.vn.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import tdtu.edu.vn.model.Book;

public interface BookRepository extends MongoRepository<Book, String> {
    Book findByName(String name);
    Page<Book> findAll(Pageable pageable);

}
