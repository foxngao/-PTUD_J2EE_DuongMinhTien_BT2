package com.example.demo.service;

import com.example.demo.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Book operations.
 * Uses an in-memory List to store books.
 */
@Service
public class BookService {

    private final List<Book> books = new ArrayList<>();
    private Long nextId = 1L;

    // Initialize with some sample data
    public BookService() {
        books.add(new Book(nextId++, "Java Programming", "James Gosling"));
        books.add(new Book(nextId++, "Spring in Action", "Craig Walls"));
        books.add(new Book(nextId++, "Clean Code", "Robert C. Martin"));
    }

    /**
     * Get all books
     * 
     * @return List of all books
     */
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    /**
     * Get a book by ID
     * 
     * @param id Book ID
     * @return Optional containing the book if found
     */
    public Optional<Book> getBookById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }

    /**
     * Add a new book
     * 
     * @param book Book to add
     * @return The added book with generated ID
     */
    public Book addBook(Book book) {
        book.setId(nextId++);
        books.add(book);
        return book;
    }

    /**
     * Update an existing book
     * 
     * @param id          Book ID to update
     * @param updatedBook Updated book data
     * @return true if updated, false if not found
     */
    public boolean updateBook(Long id, Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(id)) {
                updatedBook.setId(id);
                books.set(i, updatedBook);
                return true;
            }
        }
        return false;
    }

    /**
     * Delete a book by ID
     * 
     * @param id Book ID to delete
     * @return true if deleted, false if not found
     */
    public boolean deleteBook(Long id) {
        return books.removeIf(book -> book.getId().equals(id));
    }
}
