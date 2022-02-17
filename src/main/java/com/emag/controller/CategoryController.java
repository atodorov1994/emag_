package com.emag.controller;


import com.emag.exception.UnauthorizedException;
import com.emag.model.dto.EditCategoryDTO;
import com.emag.model.pojo.Category;
import com.emag.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class CategoryController {

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/categories")
    public ResponseEntity<Category> addCategory (@RequestBody Category c, HttpSession session){
        if (!sessionManager.userHasPrivileges(session)){
            throw new UnauthorizedException("No permission!");
        }
        return ResponseEntity.ok(categoryService.addCategory(c));
    }

    @PutMapping("/categories")
    public ResponseEntity<Category> editCategory (@RequestBody EditCategoryDTO c, HttpSession session){
        if (!sessionManager.userHasPrivileges(session)){
            throw new UnauthorizedException("No permission!");
        }
        return ResponseEntity.ok(categoryService.editCategory(c));
    }
}
