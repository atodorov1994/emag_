package com.emag.service;


import com.emag.exception.BadRequestException;
import com.emag.model.dto.EditCategoryDTO;
import com.emag.model.pojo.Category;
import com.emag.model.repository.CategoryRepository;
import org.springframework.stereotype.Service;

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
}
