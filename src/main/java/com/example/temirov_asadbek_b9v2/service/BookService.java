package com.example.temirov_asadbek_b9v2.service;

import com.example.temirov_asadbek_b9v2.dto.BookDTO;
import com.example.temirov_asadbek_b9v2.dto.Response;
import com.example.temirov_asadbek_b9v2.entity.Book;
import com.example.temirov_asadbek_b9v2.entity.Category;
import com.example.temirov_asadbek_b9v2.repository.BookRepository;
import com.example.temirov_asadbek_b9v2.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CategoryRepository categoryRepository;


    public List<Book> getAll() {
        List<Book> all = bookRepository.findAll();
        return all;
    }

    public Response add(BookDTO bookDTO) {
        String name = bookDTO.getName();

        Book book = new Book();
        book.setName(name);
        book.setAuthor(bookDTO.getAuthor());
        List<Book> all = bookRepository.findAll();
        if (!Services.checkName(name, all)) {
            return new Response("Xatolik", false);
        }
        Optional<Category> byId = categoryRepository.findById(bookDTO.getCategory());
        Category category = byId.get();
        book.setCategory(category);
        bookRepository.save(book);
        return new Response("Qo'shildi", true);
    }

    public Response edit(Integer id, BookDTO bookDTO) {
        Book book = bookRepository.getById(id);
        String name = bookDTO.getName();
        book.setName(name);
        book.setAuthor(book.getAuthor());
        List<Book> all = bookRepository.findAll();
        if (!Services.checkName(name, all)) {
            return new Response("Xatolik", false);
        }
        Optional<Category> byId = categoryRepository.findById(bookDTO.getCategory());
        Category category = byId.get();
        book.setCategory(category);
        bookRepository.save(book);
        return new Response("O`zgartirildi", true);
    }

    public Response delete(Integer id) {
        bookRepository.deleteById(id);
        return new Response("O`chirildi", true);
    }
}
