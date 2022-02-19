package com.emag.service;

import com.emag.model.pojo.Discount;
import com.emag.model.pojo.Product;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

public class Scheduler extends AbstractService{

    @Scheduled(fixedDelay = 1000)
    void checkExpiredDiscounts(){
        List<Discount> discounts = discountRepository.findAll();
        discounts.forEach(d -> {
            if (d.getExpireDate().toLocalDateTime().isBefore(LocalDateTime.now())){
                List<Product> products = productRepository.findAllByDiscount(d);
                products.forEach(p -> {
                    double price = p.getPrice();
                    double regularPrice = price*(d.getDiscountPercent()/100+1);
                } );
                discountRepository.delete(d);
            }
        });

    }

}
