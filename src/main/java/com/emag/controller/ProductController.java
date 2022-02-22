package com.emag.controller;

import com.emag.exception.UnauthorizedException;
import com.emag.model.dto.category.CategoryWithoutIdDTO;
import com.emag.model.dto.product.LikedProductsForUserDTO;
import com.emag.model.dto.product.RequestProductDTO;
import com.emag.model.dto.product.ResponseProductDTO;
import com.emag.model.dto.subcategory.SubCategoriesWithNameDTO;
import com.emag.model.pojo.Product;
import com.emag.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/subcategories/{id}/products")
    public List<ResponseProductDTO> getProductsBySubcategory(@PathVariable long id , HttpSession session){
        sessionManager.setSubcategoryId(session , id);
        return productService.getProductsBySubcategory(id);
    }

    @GetMapping("/subcategories/products/{sortedBy}")
    public List<ResponseProductDTO> getProductsBySubcategorySortedBy (@PathVariable String sortedBy , HttpServletRequest request){
        return productService.getProductsBySubcategorySortedBy (sessionManager.getSubcategory(request) , sortedBy);
    }

<<<<<<< HEAD
//TODO fix sessionManager.getSession method
    @GetMapping("/subcategories/products/{min}/{max}")
    public List<Product> getProductsBetween (@PathVariable double min, @PathVariable double max, HttpServletRequest request){
        return productService.getProductsBetween (sessionManager.getSubcategory(request), min, max);
=======

    @GetMapping("/subcategories/products/{min}/{max}")
    public List<Product> getProductsBetween (@PathVariable double min, @PathVariable double max, HttpServletRequest request){
        return productService.getProductsBetween (sessionManager.getSubcategoryId(request), min, max);
>>>>>>> refs/remotes/origin/main
    }

    @GetMapping("/products/search/{keywordSequence}")
    public List<ResponseProductDTO> searchProductsByKeyword(@PathVariable String keywordSequence){
        return productService.searchProductsByKeyword(keywordSequence);
    }

    @GetMapping("/products/fav")  ResponseEntity<List<ResponseProductDTO>> getAllFavouriteProducts(HttpServletRequest request){
//        TODO refactor
        if (!sessionManager.userHasPrivileges(request , sessionManager.getLoggedUser(request).getId())){
            throw new UnauthorizedException("No privileges!");
        }
        return ResponseEntity.ok(productService.getAllFavouriteProducts(sessionManager.getLoggedUser(request)));
    }









}
