package com.emag.controller;

import com.emag.exception.UnauthorizedException;
import com.emag.model.dto.AddProductDTO;
import com.emag.model.pojo.Product;
import com.emag.model.pojo.User;
import com.emag.model.repository.ProductRepository;
import com.emag.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class ProductController {


    @Autowired
    SessionManager sessionManager;

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<AddProductDTO> addProduct(@RequestBody AddProductDTO p , HttpServletRequest request){
        if(!sessionManager.userHasPrivileges(request)){
            throw new UnauthorizedException("Not admin!");
        }
        return ResponseEntity.ok(productService.addProduct(p));
    }

}
