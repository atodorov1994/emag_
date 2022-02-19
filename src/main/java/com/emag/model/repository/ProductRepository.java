package com.emag.model.repository;

import com.emag.model.pojo.Discount;
import com.emag.model.pojo.Product;
import com.emag.model.pojo.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> getProductsBySubCategory(SubCategory subCategory);
    List<Product> findAllByDiscount(Discount discount);

    Collection<? extends Product> findByNameContainingOrDescriptionContaining (String keyword, String keyword1);
}
