package com.emag.model.repository;

import com.emag.model.pojo.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    Discount findDiscountByDiscountPercentAndStartDateAndExpireDate(int discountPercent, Timestamp startDate, Timestamp expireDate);
}
