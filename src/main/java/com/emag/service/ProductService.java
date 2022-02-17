package com.emag.service;

import com.emag.exception.BadRequestException;
import com.emag.exception.MethodNotFoundException;
import com.emag.model.dto.product.LikedProductsForUserDTO;
import com.emag.model.dto.product.RequestProductDTO;
import com.emag.model.dto.product.ResponseProductDTO;
import com.emag.model.pojo.Product;
import com.emag.model.pojo.User;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService extends AbstractService{

    public ResponseProductDTO addProduct(RequestProductDTO p){
        Product product = modelMapper.map(p , Product.class);

        //TODO validations

        product.setAddedAt(Timestamp.valueOf(LocalDateTime.now()));
        return modelMapper.map(productRepository.save(product) , ResponseProductDTO.class);
    }

    public ResponseProductDTO editProduct(RequestProductDTO p, long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new MethodNotFoundException("Product does not exist!"));
        String brand = p.getBrand();
        if (!brand.isBlank()){
            product.setBrand(brand);
        }
        String description = p.getDescription();
        if (!description.isBlank()) {
            product.setDescription(description);
        }
//        TODO all parameters


        return modelMapper.map(productRepository.save(product) , ResponseProductDTO.class);
    }

    public ResponseProductDTO deleteProduct(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new MethodNotFoundException("Product doest not exist"));
        productRepository.delete(product);
        return modelMapper.map(product , ResponseProductDTO.class);
    }


    public ResponseProductDTO getProductById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new MethodNotFoundException("Product does not exist"));
//        TODO some validations

        return modelMapper.map(product , ResponseProductDTO.class);
    }


    public LikedProductsForUserDTO addProductToFavourites(long id, User user) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new MethodNotFoundException("Product does not exist"));
        List<Product> likedProducts = user.getLikedProducts();
        if (likedProducts.contains(product)){
            throw new BadRequestException("Product already liked!");
        }
        likedProducts.add(product);
        user.setLikedProducts(likedProducts);
// TODO test if works
        return modelMapper.map(user , LikedProductsForUserDTO.class);
    }

    public LikedProductsForUserDTO removeProductFromFavourites(long id, User user) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new MethodNotFoundException("Product does not exist"));
        List<Product> likedProducts = user.getLikedProducts();
        if (!likedProducts.contains(product)){
            throw new BadRequestException("Product is not liked !");
        }
        likedProducts.remove(product);
        user.setLikedProducts(likedProducts);
// TODO test if works
        return modelMapper.map(user , LikedProductsForUserDTO.class);
    }
}
