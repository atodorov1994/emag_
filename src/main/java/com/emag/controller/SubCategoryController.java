package com.emag.controller;

import com.emag.exception.UnauthorizedException;
import com.emag.model.dto.category.EditCategoryDTO;
import com.emag.model.dto.category.CategoryWithoutIdDTO;
import com.emag.model.dto.subcategory.AddSubCategoryDTO;
import com.emag.model.dto.subcategory.SubCategoriesWithNameDTO;
import com.emag.model.pojo.SubCategory;
import com.emag.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
public class SubCategoryController {

    @Autowired
    private SessionManager sessionManager;
    @Autowired
    private SubCategoryService subCategoryService;

    @PostMapping("/subcategories")
    public ResponseEntity<SubCategory> addSubCategory (@RequestBody @Valid AddSubCategoryDTO c, HttpServletRequest request){
        if (!sessionManager.userHasPrivileges(request)){
            throw new UnauthorizedException("No permission!");
        }
        return ResponseEntity.ok(subCategoryService.addSubCategory(c));
    }

    @PutMapping("/subcategories")
    public ResponseEntity<SubCategory> editSubCategory (@RequestBody @Valid EditCategoryDTO c, HttpServletRequest request){
        if (!sessionManager.userHasPrivileges(request)){
            throw new UnauthorizedException("No permission!");
        }
        return ResponseEntity.ok(subCategoryService.editCategory(c));
    }

    @GetMapping("/subcategories")
    public List<SubCategoriesWithNameDTO> getSubCategoriesFromCategory(@RequestBody @Valid CategoryWithoutIdDTO c){
        return subCategoryService.getSubCategoriesFromCategory(c);
    }

    @DeleteMapping("/subcategories")
    public SubCategoriesWithNameDTO deleteSubCategory(@RequestBody @Valid SubCategoriesWithNameDTO s, HttpServletRequest request){
        if (!sessionManager.userHasPrivileges(request)){
            throw new UnauthorizedException("No permission!");
        }
        return subCategoryService.deleteCategory(s);
    }
}
