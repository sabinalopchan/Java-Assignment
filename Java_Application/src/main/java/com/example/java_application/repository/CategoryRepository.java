package com.example.java_application.repository;

import com.example.java_application.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

//provides basics methods to save in database
public interface CategoryRepository extends JpaRepository <Category, Integer>{

}
