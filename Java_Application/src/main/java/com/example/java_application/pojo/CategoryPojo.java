package com.example.java_application.pojo;

import com.example.java_application.entity.Category;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPojo {
    private Integer id;

    @NotEmpty(message = "Category field can't be empty")
    private String name;

    public CategoryPojo(Category category){
        this.id=category.getId();
        this.name= category.getName();
    }
}
