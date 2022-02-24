package com.emag.service;

import com.emag.exception.BadRequestException;
import com.emag.exception.NotFoundException;
import com.emag.model.dto.category.EditCategoryDTO;
import com.emag.model.dto.category.CategoryWithoutIdDTO;
import com.emag.model.dto.subcategory.AddSubCategoryDTO;
import com.emag.model.dto.subcategory.SubCategoriesWithNameDTO;
import com.emag.model.pojo.Category;
import com.emag.model.pojo.SubCategory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubCategoryService extends AbstractService{


    public SubCategory addSubCategory(AddSubCategoryDTO c) {
        String subCategoryName = c.getSubCategoryName();
        String categoryName = c.getCategoryName();
        if (subCategoryName.trim().length()<=1){
            throw new BadRequestException("Wrong credentials!");
        }
        SubCategory subCategory = subCategoryRepository.findBySubcategoryName(subCategoryName);
        if (subCategory != null){
            throw new BadRequestException("Subcategory already exists!");
        }
        Category category = categoryRepository.findByCategoryName(categoryName);
        if (category == null){
            throw new NotFoundException("Category you are trying to add subcategory doesn't exist!");
        }
        subCategory = new SubCategory();
        subCategory.setSubcategoryName(subCategoryName);
        subCategory.setCategory(category);
        return subCategoryRepository.save(subCategory);
    }

    public SubCategory editCategory(EditCategoryDTO c) {
        String oldName = c.getOldName();
        String newName = c.getNewName();
        if (subCategoryRepository.findBySubcategoryName(oldName) == null){
            throw new NotFoundException("The subcategory you try to change don't exists!");
        }
        if (newName.trim().length()<=1){
            throw new BadRequestException("Wrong credentials!");
        }
        if (subCategoryRepository.findBySubcategoryName(newName) != null){
            throw new BadRequestException("SubCategory already exists!");
        }
        SubCategory subCategory = subCategoryRepository.findBySubcategoryName(oldName);
        subCategory.setSubcategoryName(newName);
        return subCategoryRepository.save(subCategory);
    }

    public List<SubCategoriesWithNameDTO> getSubCategoriesFromCategory(long id) {
        List<SubCategoriesWithNameDTO> subCategoriesWithNameDTOS = new ArrayList<>();
        List<SubCategory> subCategories = subCategoryRepository.findAllByCategoryId(id);
        subCategories.forEach(subCategory -> subCategoriesWithNameDTOS.add(modelMapper.map(subCategory, SubCategoriesWithNameDTO.class)));
        return subCategoriesWithNameDTOS;
    }


    public SubCategoriesWithNameDTO deleteCategory(SubCategoriesWithNameDTO s) {
        String subCategoryName = s.getName();
        if (subCategoryRepository.findBySubcategoryName(subCategoryName) == null){
            throw new NotFoundException("No such subcategory!");
        }
        SubCategory subCategory = subCategoryRepository.findBySubcategoryName(subCategoryName);
        subCategoryRepository.delete(subCategory);
        return s;
    }
}
