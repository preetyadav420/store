package com.preet.store.services;

import com.preet.store.entities.Category;
import com.preet.store.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;


    public Category findById(Byte id)
    {
        return categoryRepository.findById(id).orElse(null);
    }
}
