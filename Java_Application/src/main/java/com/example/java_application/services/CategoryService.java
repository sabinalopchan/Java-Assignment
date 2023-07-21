package com.example.java_application.services;



import com.example.java_application.entity.Category;
import com.example.java_application.pojo.CategoryPojo;

import java.io.IOException;
import java.util.List;

public interface CategoryService {
    CategoryPojo save(CategoryPojo categoryPojo) throws IOException;

    List<Category> fetchAll();

    Category fetchById(Integer id);

    void deleteById(Integer id);

    Category updateCategory(Category category);


}
