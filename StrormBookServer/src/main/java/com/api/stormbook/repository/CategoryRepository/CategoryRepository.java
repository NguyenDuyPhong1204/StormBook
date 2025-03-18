package com.api.stormbook.repository.CategoryRepository;


import com.api.stormbook.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findById(long id);
    Optional<Category> findByName(String name);
}
