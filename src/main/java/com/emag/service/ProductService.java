package com.emag.service;

import com.emag.exception.BadRequestException;
import com.emag.exception.NotFoundException;
import com.emag.model.dto.product.FilterProductsDTO;
import com.emag.model.dto.product.LikedProductsForUserDTO;
import com.emag.model.dto.product.RequestProductDTO;
import com.emag.model.dto.product.ResponseProductDTO;
import com.emag.model.pojo.Product;
import com.emag.model.pojo.ProductImage;
import com.emag.model.pojo.SubCategory;
import com.emag.model.pojo.User;
import com.emag.util.ImageUtil;
import com.emag.util.ProductUtil;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ProductService extends AbstractService{


    public ResponseProductDTO addProduct(RequestProductDTO p){
        SubCategory subCategory = subCategoryRepository.findById( p.getSubCategoryId())
                .orElseThrow(() -> new NotFoundException("No such subcategory !"));
        ProductUtil.validateString(p.getName());
        ProductUtil.validateString(p.getBrand());
        ProductUtil.validateString(p.getModel());
        ProductUtil.validateDescription(p.getDescription());
        ProductUtil.validateInt((int) p.getSubCategoryId());
        ProductUtil.validateInt(p.getQuantity());
        ProductUtil.validateInt(p.getWarrantyMonths());
        ProductUtil.validateDouble(p.getPrice());
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
        product.setAddedAt(Timestamp.valueOf(LocalDateTime.now()));
        return modelMapper.map(productRepository.save(product) , ResponseProductDTO.class);
    }

    public ResponseProductDTO editProduct(RequestProductDTO p, long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product does not exist!"));
        String name = p.getName();
        if (!name.isBlank()){
            ProductUtil.validateString(name);
            product.setName(name);
        }
        long subCategoryId = p.getSubCategoryId();
        if (subCategoryId !=0 && product.getSubCategory().getId() != subCategoryId){
            product.setSubCategory(subCategoryRepository.findById(subCategoryId)
                    .orElseThrow(() -> new BadRequestException("No such subcategory")));
        }
        String brand = p.getBrand();
        if (!brand.isBlank()){
            ProductUtil.validateString(brand);
            product.setBrand(brand);
        }
        String model = p.getModel();
        if (!model.isBlank()){
            ProductUtil.validateString(model);
            product.setModel(model);
        }
        String description = p.getDescription();
        if (!description.isBlank()) {
            ProductUtil.validateDescription(description);
            product.setDescription(description);
        }
        int quantity = p.getQuantity();
        if (quantity>0){
            ProductUtil.validateInt(quantity);
            product.setQuantity(quantity);
        }
        int warrantyMonths = p.getWarrantyMonths();
        if (warrantyMonths>0){
            ProductUtil.validateInt(warrantyMonths);
            product.setWarrantyMonths(warrantyMonths);
        }
        double price = p.getPrice();
        if (price>0){
            ProductUtil.validateDouble(price);
            product.setPrice(price);
        }
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
        products.forEach(product ->
                responseProductDTOS.add(modelMapper.map(product, ResponseProductDTO.class)));
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
        foundProducts.forEach(product ->
                foundProductsDTOs.add(modelMapper.map(product , ResponseProductDTO.class)));
        return foundProductsDTOs;
    }

    public List<ResponseProductDTO> getAllFavouriteProducts(User user) {
        List<Product> likedProducts = user.getLikedProducts();
        List<ResponseProductDTO> likedProductsDTO = new ArrayList<>();
        likedProducts.forEach(product ->
                likedProductsDTO.add(modelMapper.map(product , ResponseProductDTO.class)));
        return likedProductsDTO;
    }

    public List<ResponseProductDTO> getProductsBySubcategorySortedBy(long subcategoryId, String sortedBy) {
        SubCategory subCategory = subCategoryRepository.findById(subcategoryId)
                .orElseThrow(() -> new NotFoundException("Subcategory not found!"));
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
            case  "rating" : {
                productDTO.sort((o1, o2) -> Double.compare(o2.getProductRating(), o1.getProductRating()));
            }
            case "added_desc" : {
                productDTO.sort(Comparator.comparing(ResponseProductDTO::getAddedAt));
                return productDTO;
            }
            default:
                throw new BadRequestException("Unexpected value: " + sortedBy);
        }
    }

    public List<Product> getProductsBetween(long subcategoryId, double min, double max) {
        return productRepository.findAllBySubCategoryIdAndPriceIsBetween(subcategoryId, min, max);
    }

    @SneakyThrows
    public String addImage(MultipartFile file, long id) {
        String name = ImageUtil.validateImageAndReturnName(file);
        File f = new File("products" + File.separator + "uploads" + File.separator + name);
        Files.copy(file.getInputStream() ,
                f.toPath(), StandardCopyOption.REPLACE_EXISTING);
        Product p = productRepository.getById(id);
        ProductImage productImage = new ProductImage();
        productImage.setUrl(f.toPath().toString());
        productImage.setProduct(p);
        productImageRepository.save(productImage);
        return f.toPath().toString();
    }

    public List<ResponseProductDTO> filterProducts(FilterProductsDTO dto) {
        if (dto.getSubcategoryId() != null) {
            SubCategory subCategory = subCategoryRepository.findById(dto.getSubcategoryId())
                    .orElseThrow(() -> new BadRequestException("Subcategory not found!"));
        }
        List<ResponseProductDTO> products = new ArrayList<>();
        productDAO.filter(dto)
                .forEach(id -> products.add(modelMapper.map(productRepository
                        .findById(id)
                        .orElseThrow(() -> new NotFoundException("Product not found!")) , ResponseProductDTO.class)));
        return products;
    }
}
