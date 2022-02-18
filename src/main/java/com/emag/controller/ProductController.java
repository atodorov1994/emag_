package com.emag.controller;

import com.emag.exception.UnauthorizedException;
import com.emag.model.dto.product.LikedProductsForUserDTO;
import com.emag.model.dto.product.RequestProductDTO;
import com.emag.model.dto.product.ResponseProductDTO;
import com.emag.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class ProductController {

    @Autowired
    SessionManager sessionManager;

    @Autowired
    ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<ResponseProductDTO> addProduct(@RequestBody RequestProductDTO p , HttpServletRequest request){
        if(!sessionManager.userHasPrivileges(request)){
            throw new UnauthorizedException("Not admin!");
        }
        return ResponseEntity.ok(productService.addProduct(p));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ResponseProductDTO> editProduct(@PathVariable long id , @RequestBody RequestProductDTO p , HttpServletRequest request){
        if(!sessionManager.userHasPrivileges(request)){
            throw new UnauthorizedException("Not admin!");
        }
        return ResponseEntity.ok(productService.editProduct(p , id));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<ResponseProductDTO> deleteProduct(@PathVariable long id , HttpServletRequest request){
        if(!sessionManager.userHasPrivileges(request)){
            throw new UnauthorizedException("Not admin!");
        }
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ResponseProductDTO> getProductById (@PathVariable long id , HttpServletRequest request){
        if(!sessionManager.userHasPrivileges(request)){
            throw new UnauthorizedException("Not admin!");
        }
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping("/products/{id}/fav")
    public ResponseEntity<LikedProductsForUserDTO> addProductToFavourites (@PathVariable long id , HttpServletRequest request){
        return ResponseEntity.ok(productService.addProductToFavourites(id , sessionManager.getLoggedUser(request)));
    }

    @DeleteMapping("/products/{id}/fav")
    public ResponseEntity<LikedProductsForUserDTO> removeProductFromFavourites (@PathVariable long id , HttpServletRequest request){
        return ResponseEntity.ok(productService.removeProductFromFavourites(id , sessionManager.getLoggedUser(request)));
    }












}
