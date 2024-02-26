package tdtu.edu.vn.service.ebook;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tdtu.edu.vn.model.Book;
import tdtu.edu.vn.repository.BookRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    public ResponseEntity<InputStreamResource> getBookPdf(String id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            try {
                Path path = Paths.get(book.getPdfUrl());
                if (Files.exists(path)) {
                    InputStream inputStream = new FileInputStream(path.toFile());
                    return ResponseEntity.ok()
                            .header("Content-Disposition", "attachment; filename=" + book.getName() + ".pdf")
                            .body(new InputStreamResource(inputStream));
                } else {
                    System.out.println("File not found: " + book.getPdfUrl());
                }
            } catch (IOException e) {
                System.out.println("Cannot open file: " + book.getPdfUrl());
            }
        }
        return ResponseEntity.notFound().build();
    }

    public Page<Book> searchBooks(String searchTerm, Pageable pageable) {
        return bookRepository.searchByName(searchTerm, pageable);
    }


}
