package com.example.java_application.controller;


import com.example.java_application.entity.Category;
import com.example.java_application.pojo.CategoryPojo;
import com.example.java_application.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class HomeController {
    private final CategoryService categoryService;


    @GetMapping("/dash")
    public String home(){
        return "backend/dash";
    }



    @GetMapping("/dash/category")
    public String getCategory(Model model){
        List<Category> categories=categoryService.fetchAll();
        model.addAttribute("categories", categories.stream().map(category ->
                Category.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .build()
        ));
        return "backend/category/index_category";

    }

    @GetMapping("/dash/add_category")
    public String getCategoryAdd(Model model){
        model.addAttribute("category", new CategoryPojo());
        return "backend/category/add_category";
    }

    @PostMapping("/dash/add_category")
    public String addCategory(@Valid CategoryPojo categoryPojo,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {

        Map<String, String> requestError = validateRequest(bindingResult);
        if (requestError != null) {
            redirectAttributes.addFlashAttribute("requestError", requestError);
            return "redirect:/user/dash/add_category";
        }

        categoryService.save(categoryPojo);
        redirectAttributes.addFlashAttribute("successMsg", "Category saved successfully");


        return "redirect:/user/dash/category";
    }

    public Map<String, String> validateRequest(BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return null;
        }
        Map<String, String> errors = new HashMap<>();
        bindingResult.getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return errors;

    }

    @GetMapping("/dash/category/delete/{id}")
    public String deleteCategory(@PathVariable("id") int id, RedirectAttributes redirectAttributes){
        categoryService.deleteById(id);
        redirectAttributes.addFlashAttribute("deleteMsg", "Category deleted successfully");
        return "redirect:/user/dash/category";
    }

    @GetMapping ("/dash/category/update/{id}")
    public String updateCategory(@PathVariable("id") int id, Model model){
        Category category=categoryService.fetchById(id);
        model.addAttribute("category",new CategoryPojo(category));
        return "backend/category/edit_category";
    }

    @PostMapping("/dash/category/update/{id}")
    public String updateCategoryPost(@PathVariable("id") int id, @ModelAttribute("category") Category category, Model model){
        Category category1=categoryService.fetchById(id);
        category1.setId(id);
        category1.setName(category.getName());

        categoryService.updateCategory(category1);
        return "redirect:/user/dash/category";
    }


}
