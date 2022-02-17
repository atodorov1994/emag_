package com.emag.controller;

import com.emag.exception.UnauthorizedException;
import com.emag.model.dto.product.RequestProductDTO;
import com.emag.model.dto.product.ResponseProductDTO;
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
    public ResponseEntity<ResponseProductDTO> addProduct(@RequestBody RequestProductDTO p , HttpSession session){
        if(!sessionManager.userHasPrivileges(session)){
            throw new UnauthorizedException("Not admin!");
        }
        return ResponseEntity.ok(productService.addProduct(p));
    }

}
