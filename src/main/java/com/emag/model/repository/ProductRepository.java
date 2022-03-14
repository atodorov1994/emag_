package com.emag.model.repository;

import com.emag.model.pojo.Discount;
import com.emag.model.pojo.Product;
import com.emag.model.pojo.SubCategory;
import com.emag.model.pojo.UserCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> getPageOfProductsBySubCategory(Pageable page, SubCategory subCategory);

    List<Product> getPageOfProductsBySubCategory(SubCategory subCategory);
    List<Product> findAllByDiscount(Discount discount);

    Collection<? extends Product> findByNameContainingOrDescriptionContaining (String keyword, String keyword1);
    List<Product> findAllBySubCategoryIdAndPriceIsBetween(long subCategoryId, double min, double max);

}
