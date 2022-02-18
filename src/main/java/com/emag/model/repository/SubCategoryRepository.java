package com.emag.model.repository;

import com.emag.model.pojo.Category;
import com.emag.model.pojo.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    SubCategory findBySubcategoryName(String subcategoryName);
    SubCategory findById(long id);
}
