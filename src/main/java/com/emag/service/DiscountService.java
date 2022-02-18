package com.emag.service;

import com.emag.exception.BadRequestException;
import com.emag.model.dto.DiscountDTO;
import com.emag.model.pojo.Category;
import com.emag.model.pojo.Discount;
import com.emag.model.pojo.Product;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class DiscountService extends AbstractService{
    public Discount createDiscount(DiscountDTO dto){
        this.validateDiscount(dto);
        Discount discount = new Discount();
        discount.setDiscountPercent(dto.getDiscountPercent());
        if(dto.getStartDate() != null && dto.getStartDate().length() > 0){
            LocalDateTime startDate = parseDate(dto.getStartDate());
            discount.setStartDate(Timestamp.valueOf(startDate));
            if(dto.getExpireDate() != null && dto.getExpireDate().length() > 0){
                LocalDateTime expireDate = parseDate(dto.getExpireDate());
                discount.setExpireDate(Timestamp.valueOf(expireDate));
            }
        }
        Discount discount1 = discountRepository.findDiscountByDiscountPercentAndStartDateAndExpireDate(discount.getDiscountPercent(), discount.getStartDate(),discount.getExpireDate());
        if (discount1 != null){
            return discount1;
        }
        return discountRepository.save(discount);
    }


    private LocalDateTime parseDate(String startDate) {
        try {
            LocalDateTime checkDate = LocalDateTime.parse(startDate);
            return checkDate;
        }catch (Exception e){
            throw new BadRequestException("Date is not valid");
        }
    }

    private void validateDiscount(DiscountDTO dto){
        if(dto.getDiscountPercent() == null){
            throw new BadRequestException("Enter a valid discount percent");
        }else {
            if (dto.getDiscountPercent() == 0){
                throw new BadRequestException("Enter a valid discount percent");
            }
        }
        LocalDateTime startDate;
        if(dto.getStartDate() == null){
            throw new BadRequestException("Start date is mandatory");
        } else{
            startDate = parseDate(dto.getStartDate());
        }
        if (startDate.isBefore(LocalDateTime.now())){
            throw new BadRequestException("Invalid date time!");
        }
        if (dto.getExpireDate()!=null) {
            if (dto.getExpireDate().trim().length() > 0) {
                LocalDateTime endDate = parseDate(dto.getExpireDate());
                if (endDate.isBefore(startDate)) {
                    throw new BadRequestException("Enter a valid expiration date");
                }
            }
        }
    }
}
