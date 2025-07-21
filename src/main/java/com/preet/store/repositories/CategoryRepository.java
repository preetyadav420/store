package com.preet.store.repositories;

import com.preet.store.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Byte> {
}
