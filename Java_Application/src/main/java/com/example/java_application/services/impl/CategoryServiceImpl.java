package com.example.java_application.services.impl;


import com.example.java_application.entity.Category;
import com.example.java_application.pojo.CategoryPojo;
import com.example.java_application.repository.CategoryRepository;
import com.example.java_application.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    @Autowired // for automatic dependency injection.
    private final CategoryRepository categoryRepository;

    @Override

//    create method
    public CategoryPojo save(CategoryPojo categoryPojo) throws IOException{
        Category category;
        if (categoryPojo.getId()!=null){
            category=categoryRepository.findById(categoryPojo.getId()).orElseThrow(() -> new RuntimeException("Not Found"));
        }else{
            category=new Category();
        }
//        category.setId(categoryPojo.getId());
        category.setName(categoryPojo.getName());

        categoryRepository.save(category);
        return new CategoryPojo(category);
    }

//    retrieve method
    public List<Category> fetchAll() {
        return categoryRepository.findAll();
    }

//    update method
    public Category fetchById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

//    delete method
    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }


}
//
//@Service
//public class CategoryService {
//    @Autowired // for automatic dependency injection.
//    CategoryRepository categoryRepository;
//
////    retrieve method
//    public List<Category> getAllCategory(){
//        return categoryRepository.findAll();
//    }
//
////    create method
//    public void addCategory(Category category){
//        categoryRepository.save(category);
//    }
//
////    delete method
//    public void removeCategoryById(int id){
//        categoryRepository.deleteById(id);
//    }
//
////    update method
//    public Optional<Category> findCategoryById(int id){
//        return categoryRepository.findById(id);
//    }
//}
