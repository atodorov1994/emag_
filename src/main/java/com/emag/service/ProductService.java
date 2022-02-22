package com.emag.service;

import com.emag.exception.BadRequestException;
import com.emag.exception.NotFoundException;
import com.emag.model.dto.product.LikedProductsForUserDTO;
import com.emag.model.dto.product.RequestProductDTO;
import com.emag.model.dto.product.ResponseProductDTO;
import com.emag.model.pojo.Product;
import com.emag.model.pojo.SubCategory;
import com.emag.model.pojo.User;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ProductService extends AbstractService{

    public ResponseProductDTO addProduct(RequestProductDTO p){
        SubCategory subCategory = subCategoryRepository.findById( p.getSubCategoryId())
                .orElseThrow(() -> new NotFoundException("No such subcategory !"));
//        TODO not working with model mapper
//        Product product = modelMapper.map(p , Product.class);
//        product.setSubCategory(subCategory);
        Product product = new Product();
        product.setPrice(p.getPrice());
        product.setDescription(p.getDescription());
        product.setBrand(p.getBrand());
        product.setModel(p.getModel());
        product.setName(p.getName());
        product.setQuantity(p.getQuantity());
        product.setSubCategory(subCategory);
        //TODO validations
        product.setAddedAt(Timestamp.valueOf(LocalDateTime.now()));
        return modelMapper.map(productRepository.save(product) , ResponseProductDTO.class);
    }

    public ResponseProductDTO editProduct(RequestProductDTO p, long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product does not exist!"));
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
                .orElseThrow(() -> new NotFoundException("Product doest not exist"));
        productRepository.delete(product);
        return modelMapper.map(product , ResponseProductDTO.class);
    }


    public ResponseProductDTO getProductById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product does not exist"));
//        TODO some validations

        return modelMapper.map(product , ResponseProductDTO.class);
    }


    public LikedProductsForUserDTO addProductToFavourites(long id, User user) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product does not exist"));
        List<Product> likedProducts = user.getLikedProducts();
        if (likedProducts.contains(product)){
            throw new BadRequestException("Product already liked!");
        }
        likedProducts.add(product);
        product.getUsersLikedThisProduct().add(user);
        productRepository.save(product);
        return modelMapper.map(user , LikedProductsForUserDTO.class);
    }

    public LikedProductsForUserDTO removeProductFromFavourites(long id, User user) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product does not exist"));
        List<Product> likedProducts = user.getLikedProducts();
        if (!likedProducts.contains(product)){
            throw new BadRequestException("Product is not liked !");
        }
        likedProducts.remove(product);
        user.setLikedProducts(likedProducts);
        userRepository.save(user);
        return modelMapper.map(user , LikedProductsForUserDTO.class);
    }

    public List<ResponseProductDTO> getProductsBySubcategory(long id) {
        SubCategory subCategory = subCategoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Subcategory not found!"));
        List<Product> products = productRepository.getProductsBySubCategory(subCategory);
        List<ResponseProductDTO> responseProductDTOS = new ArrayList<>();
        products.forEach(product -> responseProductDTOS.add(modelMapper.map(product, ResponseProductDTO.class)));
        return responseProductDTOS;
    }

    public List<ResponseProductDTO> searchProductsByKeyword(String keywordSequence) {
        HashSet<Product> foundProducts = new HashSet<>();
        String[] splitKeywords = keywordSequence.trim().split("\\s+");
        for (String keyword : splitKeywords) {
            foundProducts.addAll(
                    productRepository.
                            findByNameContainingOrDescriptionContaining(keyword, keyword)
            );
        }
        if (foundProducts.isEmpty()){
            throw new NotFoundException("No products found");
        }
        List<ResponseProductDTO> foundProductsDTOs = new ArrayList<>();
        foundProducts.forEach(product -> foundProductsDTOs.add(modelMapper.map(product , ResponseProductDTO.class)));
        return foundProductsDTOs;
    }

    public List<ResponseProductDTO> getAllFavouriteProducts(User user) {
        List<Product> likedProducts = user.getLikedProducts();
        List<ResponseProductDTO> likedProductsDTO = new ArrayList<>();
        likedProducts.forEach(product -> likedProductsDTO.add(modelMapper.map(product , ResponseProductDTO.class)));
        return likedProductsDTO;
    }

    public List<ResponseProductDTO> getProductsBySubcategorySortedBy(SubCategory subCategory, String sortedBy) {
        if(subCategory == null) {
            throw new NotFoundException("Subcategory not found!");
        }
        if (!Arrays.asList(SORTED_BY).contains(sortedBy.toLowerCase())){
            throw new BadRequestException("Invalid sorting type");
        }

        List<Product> products = productRepository.getProductsBySubCategory(subCategory);
        ArrayList<ResponseProductDTO> productDTO = new ArrayList<>();
        products.forEach(product -> productDTO.add(modelMapper.map(product , ResponseProductDTO.class)));
        productDTO.sort(Comparator.comparingDouble(ResponseProductDTO::getPrice));
        switch (sortedBy) {
            case "price_asc" : {
                productDTO.sort(Comparator.comparingDouble(ResponseProductDTO::getPrice));
                return productDTO;
            }
            case "price_desc" : {
                productDTO.sort((o1, o2) -> Double.compare(o2.getPrice(), o1.getPrice()));
                return productDTO;
            }
//               TODO add by review count
//               case  "reviews" -> {
//                productDTO.sort((o1, o2) -> o1.get);
//               }
            case "added_desc" : {
                productDTO.sort(Comparator.comparing(ResponseProductDTO::getAddedAt));
                return productDTO;
            }
            default:
                throw new BadRequestException("Unexpected value: " + sortedBy);
        }
    }

    public List<Product> getProductsBetween(SubCategory subCategory, double min, double max) {
        return productRepository.findAllBySubCategoryAndPriceIsBetween(subCategory, min, max);
    }
}
