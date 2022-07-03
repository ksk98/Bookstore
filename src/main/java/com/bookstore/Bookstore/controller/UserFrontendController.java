package com.bookstore.Bookstore.controller;

import com.bookstore.Bookstore.models.BookEntity;
import com.bookstore.Bookstore.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserFrontendController {
    private final BookRepository bookRepository;

    public UserFrontendController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/books")
    public String bookCatalog(Model model) {
        List<BookEntity> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "books";
    }
}
