package com.emag.service;


import com.emag.exception.BadRequestException;
import com.emag.model.dto.EditCategoryDTO;
import com.emag.model.pojo.Category;
import org.springframework.stereotype.Service;
import com.emag.model.dto.category.CategoryWithoutIdDTO;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService extends AbstractService {


    public Category addCategory(Category c) {
        String name = c.getCategoryName();
        if (name.trim().length()<=1){
            throw new BadRequestException("Wrong credentials!");
        }
        if (categoryRepository.findByCategoryName(name) != null){
            throw new BadRequestException("Category already exists!");
        }
        return categoryRepository.save(c);
    }


    public Category editCategory(EditCategoryDTO c) {
        String oldName = c.getOldName();
        String newName = c.getNewName();
        if (categoryRepository.findByCategoryName(oldName) == null){
            throw new BadRequestException("The category you try to change don't exists!");
        }
        if (newName.trim().length()<=1){
            throw new BadRequestException("Wrong credentials!");
        }
        if (categoryRepository.findByCategoryName(newName) != null){
            throw new BadRequestException("Category already exists!");
        }
        Category category = categoryRepository.findByCategoryName(oldName);
        category.setCategoryName(newName);
        return categoryRepository.save(category);
    }

    public List<CategoryWithoutIdDTO> getAllCategories() {
        List<CategoryWithoutIdDTO> categoriesWithoutId = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();
        categories.forEach(category -> categoriesWithoutId.add(modelMapper.map(category, CategoryWithoutIdDTO.class)));
        return categoriesWithoutId;
    }

    public CategoryWithoutIdDTO deleteCategory(CategoryWithoutIdDTO categoryWithoutId) {
        String categoryName = categoryWithoutId.getName();
        if (categoryRepository.findByCategoryName(categoryName) == null){
            throw new BadRequestException("No such category!");
        }
        Category category = categoryRepository.findByCategoryName(categoryName);
        categoryRepository.delete(category);
        return categoryWithoutId;
    }
}