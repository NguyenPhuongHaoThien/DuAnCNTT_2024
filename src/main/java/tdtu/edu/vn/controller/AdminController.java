package tdtu.edu.vn.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tdtu.edu.vn.model.Document;
import tdtu.edu.vn.model.User;
import tdtu.edu.vn.service.ebook.DocumentService;
import tdtu.edu.vn.service.ebook.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin")
public class AdminController {
    private DocumentService documentService;
    private UserService userService;
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/create-document")
    public ResponseEntity<Document> createDocument(@RequestBody Document newBook) {
        newBook.setId(null);

        Document savedDocument = documentService.createDocument(newBook);

        if (savedDocument == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.ok(savedDocument);
    }

    @PostMapping("/update-document")
    public ResponseEntity<Document> updateDocument(@RequestBody Document updatedBook) {
        Document existingDocument = documentService.getDocumentById(updatedBook.getId());

        if (existingDocument == null) {
            return ResponseEntity.notFound().build();
        }

        Document savedDocument = documentService.updateDocument(updatedBook);

        if (savedDocument == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.ok(savedDocument);
    }

    @PostMapping("/delete-document")
    public ResponseEntity<Document> deleteDocument(@RequestBody Document document) {
        Document existingDocument = documentService.getDocumentById(document.getId());

        if (existingDocument == null) {
            return ResponseEntity.notFound().build();
        }

        if (documentService.deleteDocument(document.getId())) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
