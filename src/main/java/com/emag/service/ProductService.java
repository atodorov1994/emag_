package com.emag.service;

import com.emag.model.dto.product.RequestProductDTO;
import com.emag.model.dto.product.ResponseProductDTO;
import com.emag.model.pojo.Product;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class ProductService extends AbstractService{

    public ResponseProductDTO addProduct(RequestProductDTO p){
        Product product = modelMapper.map(p , Product.class);

        //TODO validations

        product.setAddedAt(Timestamp.valueOf(LocalDateTime.now()));
        return modelMapper.map(productRepository.save(product) , ResponseProductDTO.class);
    }

}
