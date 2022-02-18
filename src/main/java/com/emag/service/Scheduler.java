package com.emag.service;

import com.emag.model.pojo.Discount;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

public class Scheduler extends AbstractService{

    @Scheduled(cron = "0 0 * * * *")
    void checkExpiredDiscounts(){
        List<Discount> discounts = discountRepository.findAll();
        discounts.forEach(d -> {
            if (d.getExpireDate().toLocalDateTime().isBefore(LocalDateTime.now())){
                discountRepository.delete(d);
            }
        });
    }
}
