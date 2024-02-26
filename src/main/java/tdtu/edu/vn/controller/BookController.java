package tdtu.edu.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tdtu.edu.vn.model.Book;
import tdtu.edu.vn.service.ebook.BookService;

@RestController
@RequestMapping("/home")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping()
    public Page<Book> getAllBooks(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size){
        return bookService.getAllBooks(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable String id)
    {
        Book book = bookService.getBookById(id);
        if(book == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    @GetMapping("/search")
    public Page<Book> searchBooks(@RequestParam String searchTerm,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size){
        return bookService.searchBooks(searchTerm, PageRequest.of(page, size));
    }

    @GetMapping("category/{categoryId}")
    public Page<Book> getBooksByCategory(@PathVariable String categoryId,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size){
        return bookService.findCategory(categoryId, PageRequest.of(page, size));

    }


}
