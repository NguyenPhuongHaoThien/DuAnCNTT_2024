package tdtu.edu.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tdtu.edu.vn.service.ebook.BookService;

import java.io.IOException;

@RestController
@RequestMapping("/read")
public class ReadController {
    @Autowired
    private BookService bookService;

    @GetMapping("/{id}/pdf/{activationCode}")
    public ResponseEntity<InputStreamResource> getBookPdf(@PathVariable String id, @PathVariable String activationCode) throws IOException {
        return bookService.getBookPdf(id, activationCode);
    }
}
