package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * GET /api/books - Get all books
     * @return List of all books
     */
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    /**
     * GET /api/books/{id} - Get book by ID
     * @param id Book ID
     * @return Book if found, 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST /api/books - Add a new book
     * @param book Book to add
     * @return Created book with 201 status
     */
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book createdBook = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    /**
     * PUT /api/books/{id} - Update an existing book
     * 
     * @param id   Book ID to update
     * @param book Updated book data
     * @return Success message or 404 if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable Long id, @RequestBody Book book) {
        boolean updated = bookService.updateBook(id, book);
        if (updated) {
            return ResponseEntity.ok("Book updated successfully!");
        }
        return ResponseEntity.notFound().build();
    }

    /**
      DELETE /api/books/{id} - Delete a book
      @param id Book ID to delete
      @return Success message or 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        boolean deleted = bookService.deleteBook(id);
        if (deleted) {
            return ResponseEntity.ok("Book deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
