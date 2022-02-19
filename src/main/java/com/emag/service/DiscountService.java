package com.emag.service;

import com.emag.exception.BadRequestException;
import com.emag.exception.NotFoundException;
import com.emag.model.dto.DiscountDTO;
import com.emag.model.pojo.Discount;
import com.emag.model.pojo.Product;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class DiscountService extends AbstractService{
    public Discount createDiscount(DiscountDTO dto , long id){
        this.validateDiscountRequest(dto);
        int discountPercent = dto.getDiscountPercent();
        Timestamp startDate = dto.getStartDate();
        Timestamp expireDate = dto.getExpireDate();
//        If requested discount equals discount from DB -> return discount from DB , else create new discount
        Discount discount = discountRepository.findDiscountByDiscountPercentAndStartDateAndExpireDate
                (discountPercent, startDate , expireDate).orElse(modelMapper.map(dto , Discount.class));
//        Set discount and discounted price to product
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found!"));
        double price = product.getPrice();
        double discountedPrice = price - price*discountPercent/100;
        product.setDiscount(discount);
        product.setPrice(discountedPrice);
        return discountRepository.save(discount);
    }

    private void validateDiscountRequest(DiscountDTO dto){
        int discountPercent = dto.getDiscountPercent();
        Timestamp startDate = dto.getStartDate();
        Timestamp expireDate = dto.getExpireDate();
        if(discountPercent <= 0){
            throw new BadRequestException("Enter a valid discount percent");
        }
        if(startDate == null){
            throw new BadRequestException("Start date is mandatory");
        }
        if (startDate.toLocalDateTime().isBefore(LocalDateTime.now())){
            throw new BadRequestException("Invalid date time!");
        }
        if (expireDate != null) {
            if (expireDate.toLocalDateTime().isBefore(startDate.toLocalDateTime())) {
                throw new BadRequestException("Enter a valid expiration date");
            }
        }
    }
}
