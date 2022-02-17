package com.emag.controller;

import com.emag.exception.UnauthorizedException;
import com.emag.model.pojo.Product;
import com.emag.model.pojo.User;
import com.emag.model.repository.ProductRepository;
import com.emag.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class ProductController {


    @Autowired
    SessionManager sessionManager;

    @Autowired
    ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product p , HttpSession session){
        if(!sessionManager.userHasPrivileges(session)){
            throw new UnauthorizedException("Not admin!");
        }
        return ResponseEntity.ok(productService.addProduct(p));
    }

}
