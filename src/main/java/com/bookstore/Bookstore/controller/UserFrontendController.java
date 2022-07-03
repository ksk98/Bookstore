package com.bookstore.Bookstore.controller;

import com.bookstore.Bookstore.models.BookEntity;
import com.bookstore.Bookstore.models.UserEntity;
import com.bookstore.Bookstore.repositories.BookRepository;
import com.bookstore.Bookstore.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserFrontendController {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public UserFrontendController(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/books")
    public String bookCatalog(Model model) {
        List<BookEntity> books = bookRepository.findAll();
        model.addAttribute("books", books);

        if (SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            UserEntity caller = userRepository.findByUsername(
                    ((org.springframework.security.core.userdetails.User) SecurityContextHolder
                            .getContext()
                            .getAuthentication()
                            .getPrincipal())
                            .getUsername());
            model.addAttribute("userid", caller.getId());
        }

        return "books";
    }
}
