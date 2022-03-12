package com.example.temirov_asadbek_b9v2.controller;

import com.example.temirov_asadbek_b9v2.dto.BookDTO;
import com.example.temirov_asadbek_b9v2.dto.Response;
import com.example.temirov_asadbek_b9v2.entity.Book;
import com.example.temirov_asadbek_b9v2.entity.Category;
import com.example.temirov_asadbek_b9v2.repository.BookRepository;
import com.example.temirov_asadbek_b9v2.repository.CategoryRepository;
import com.example.temirov_asadbek_b9v2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    BookService bookService;


    @GetMapping("/show")
    public String show(Model model){
        model.addAttribute("message","All Books");
        model.addAttribute("bookList",bookRepository.findAll());
        return "book-show";
    }
    @GetMapping("/crud")
    public String getHomePage(Model model){
        model.addAttribute("message","All Category");
        model.addAttribute("bookList",bookRepository.findAll());
        List<Category> all = categoryRepository.findAll();
        model.addAttribute("categoryList",all);
        return "book";
    }
    @GetMapping("/add")
    public String productSavePage(@ModelAttribute BookDTO bookDTO, Model model){
bookService.add(bookDTO);
        List<Book> bookList=bookService.getAll();
        model.addAttribute("bookList",bookList);
        List<Category> all = categoryRepository.findAll();
        model.addAttribute("categoryList",all);
        return "book";
    }

    @PostMapping("/add")
    public String saveBook(@ModelAttribute BookDTO bookDTO, Model model){
        Response add = bookService.add(bookDTO);
        model.addAttribute("message",add.getMessage());
        List<Book>categoryList=bookService.getAll();
        model.addAttribute("bookList",categoryList);
        List<Category> all = categoryRepository.findAll();
        model.addAttribute("categoryList",all);
        return "book";
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable Integer id, Model model) {
        model.addAttribute("book", bookRepository.findById(id));
        List<Category> all = categoryRepository.findAll();
        model.addAttribute("categoryList",all);
        return "edit_book";
    }

    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable Integer id,
                                 @ModelAttribute("book") BookDTO bookDTO,
                                 Model model) {

        Response edit = bookService.edit(id, bookDTO);
        model.addAttribute("message","Edited Book!");
        model.addAttribute("bookList",bookRepository.findAll());
        List<Category> all = categoryRepository.findAll();
        model.addAttribute("categoryList",all);
        return "book";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Integer id,Model model) {
        Optional<Book> byId = bookRepository.findById(id);
        Book book = byId.get();
        book.setActive(false);
        bookRepository.save(book);
        model.addAttribute("message","Deleted Book!");
        model.addAttribute("bookList",bookRepository.findAll());
        List<Category> all = categoryRepository.findAll();
        model.addAttribute("categoryList",all);
        return "redirect:/book/crud";
    }
}
