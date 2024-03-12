package tdtu.edu.vn.service.ebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tdtu.edu.vn.model.ActivationCode;
import tdtu.edu.vn.model.Book;
import tdtu.edu.vn.repository.BookRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class DocumentService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ActivationCodeService activationCodeRes;

    public Page<Book> getAllBooks(Pageable pageable){
        return bookRepository.findAll(pageable);
    }

    public Book getBookById(String id){
        return bookRepository.findById(id).get();
    }

    public ResponseEntity<InputStreamResource> getBookPdf(String id, String activationCode) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            if(book.getDrmEnabled()){
                List<ActivationCode> codes = activationCodeRes.findAllByCode(activationCode);
                if(codes.isEmpty()){
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
                }
                ActivationCode code = codes.get(0);
                System.out.println("Activation code status: " + code.getStatus());
                if(code.getStatus() != ActivationCode.ActivationCodeStatus.UNUSED){
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
                }
            }
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

    public Page<Book> findCategory(String categoryId, Pageable pageable) {
        return bookRepository.findByCategoryId(categoryId, pageable);
    }


}
