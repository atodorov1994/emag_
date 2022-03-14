package com.emag.service;

import com.emag.model.pojo.Discount;
import com.emag.model.pojo.Product;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;


@Configuration
@EnableScheduling
public class Scheduler extends AbstractService{

    @Transactional
<<<<<<< HEAD:src/main/java/com/emag/service/Scheduler.java
    @Scheduled(fixedDelay = 1000)
=======
    @Scheduled(fixedDelay = 10*1000)
>>>>>>> 7455794e361dcb1877caa80b4a61ca41c203af6e:Emag/src/main/java/com/emag/service/Scheduler.java
    void checkExpiredDiscounts(){
        System.out.println("Discount deletion started");
        List<Discount> discounts = discountRepository.findAll();
        discounts.forEach(d -> {
            if (d.getExpireDate().toLocalDateTime().isBefore(LocalDateTime.now())){
                List<Product> products = productRepository.findAllByDiscount(d);
                products.forEach(p -> {
                    p.setDiscountedPrice(0.0);
                    p.setDiscount(null);
                    productRepository.save(p);
                } );
                discountRepository.delete(d);
                System.out.println("Discount " + d + "deleted!");
            }
        });

    }

}
