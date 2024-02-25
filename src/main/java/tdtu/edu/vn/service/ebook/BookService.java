package tdtu.edu.vn.service.ebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tdtu.edu.vn.model.Book;
import tdtu.edu.vn.repository.BookRepository;
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Page<Book> getAllBooks(Pageable pageable){
        return bookRepository.findAll(pageable);
    }

    public Book getBookById(String id){
        return bookRepository.findById(id).get();
    }


}
