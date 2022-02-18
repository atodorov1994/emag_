package com.emag.controller;

import com.emag.exception.UnauthorizedException;
import com.emag.model.dto.DiscountDTO;
import com.emag.model.pojo.Discount;
import com.emag.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class DiscountController {

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private DiscountService discountService;

    @PostMapping("/discounts")
    public Discount addDiscount(@RequestBody @Valid DiscountDTO dto, HttpServletRequest request){
        if (!sessionManager.userHasPrivileges(request)){
            throw new UnauthorizedException("No permission!");
        }
        return discountService.createDiscount(dto);
    }
}
