package com.emag.service;

import com.emag.exception.BadRequestException;
import com.emag.exception.NotFoundException;
import com.emag.model.dto.DiscountDTO;
import com.emag.model.pojo.Discount;
import com.emag.model.pojo.Product;
import com.emag.model.pojo.User;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

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
        product.setDiscountedPrice(discountedPrice);
        List<User> users = userRepository.findAllBySubscribedIsTrue();
        String mailText = product.getName()+ " is only now with "+discount.getDiscountPercent()+"% down of the price. " +
                "Hurry up!" + " http://localhost:9999/products/"+product.getId();
        users.forEach(user -> emailService.sendSimpleMessage(user.getEmail(), "New discount in town", mailText));
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
