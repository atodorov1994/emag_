package com.emag.service;

import com.emag.model.pojo.Product;
import com.emag.model.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public abstract class AbstractService {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected AddressRepository addressRepository;

    @Autowired
    protected ProductRepository productRepository;

    @Autowired
    protected CategoryRepository categoryRepository;

    @Autowired
    protected DiscountRepository discountRepository;

    @Autowired
    protected OrderRepository orderRepository;

//    @Autowired
//    protected ProductImageRepository productImageRepository;

    @Autowired
    protected ReviewRepository reviewRepository;
//
    @Autowired
    protected SubCategoryRepository subCategoryRepository;
//
//    @Autowired
//    protected UserImageRepository userImageRepository;

    @Autowired
    protected CartRepository cartRepository;


    @Autowired
    protected ModelMapper modelMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    protected static final String[] ACCEPTED_IMAGE_FORMATS = {"jpeg" , "png" , "jpg"};
    protected static final String[] SORTED_BY = {"price_asc" , "price_desc" ,  "reviews" , "added_desc" };


}
