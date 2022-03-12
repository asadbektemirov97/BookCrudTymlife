package com.example.temirov_asadbek_b9v2.service;

import com.example.temirov_asadbek_b9v2.dto.CategoryDTO;
import com.example.temirov_asadbek_b9v2.dto.Response;
import com.example.temirov_asadbek_b9v2.entity.Category;
import com.example.temirov_asadbek_b9v2.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    public List<Category> getAll() {
        List<Category> all = categoryRepository.findAll();
        return all;
    }

    public Response add(CategoryDTO categoryDTO) {
        String name = categoryDTO.getName();

        Category category = new Category();
        category.setName(name);
        List<Category> all = categoryRepository.findAll();
        if (!Services.checkName(name, all)) {
            return new Response("Xatolik", false);
        }
        categoryRepository.save(category);
        return new Response("Qo'shildi", true);
    }

    public Response edit(Integer id, CategoryDTO categoryDTO) {
        Category existCategory = categoryRepository.getById(id);


        List<Category> all = categoryRepository.findAll();
        if (!Services.checkName(categoryDTO.getName(), all)) {
            return new Response("Xatolik", false);
        }
        existCategory.setName(categoryDTO.getName());
        categoryRepository.save(existCategory);
        return new Response("O`zgartirildi", true);
    }

    public Response delete(Integer id) {
        categoryRepository.deleteById(id);
        return new Response("O`chirildi", true);
    }
}
