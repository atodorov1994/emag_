package com.emag;

import com.emag.controller.DiscountController;
import com.emag.exception.BadRequestException;
import com.emag.model.dto.DiscountDTO;
import com.emag.model.dto.review.DoReviewDTO;
import com.emag.model.pojo.Discount;
import com.emag.model.pojo.Product;
import com.emag.model.pojo.SubCategory;
import com.emag.model.pojo.User;
import com.emag.model.repository.DiscountRepository;
import com.emag.model.repository.ProductRepository;
import com.emag.model.repository.UserRepository;
import com.emag.service.DiscountService;
import com.emag.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@SpringBootApplication
@Configuration
@EnableScheduling
public class EmagApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmagApplication.class, args);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

//    @Bean
//    CommandLineRunner run(DiscountService discountService , ProductRepository productRepository , ReviewService reviewService , UserRepository userRepository){
//        return args -> IntStream.rangeClosed(1,2400).forEach(i -> {
//            if (i%3 == 0) {
//                DiscountDTO dto = new DiscountDTO();
//                dto.setDiscountPercent(new Random().nextInt(1 , 56));
//                dto.setExpireDate(Timestamp
//                        .valueOf(LocalDateTime.now()
//                                .plusDays(new Random().nextInt(1,15))));
//                Product product = productRepository.findById((long) i)
//                        .orElseThrow(() -> new BadRequestException("No such product"));
//                Discount discount = discountService.createDiscount(dto , i);
//                product.setDiscount(discount);
//                product.setDiscountedPrice(product.getPrice() - product.getPrice()*discount.getDiscountPercent()/100);
//                productRepository.save(product);
//            }

//                Product product = productRepository.findById((long) i)
//                        .orElseThrow(() -> new BadRequestException("No such product"));
//            SubCategory subCategory = product.getSubCategory();
//            product.setDescription(subCategory.getCategory().getCategoryName() + "," + subCategory.getSubcategoryName());
//                productRepository.save(product);


//            if (i%2 == 0){
//                DoReviewDTO doReviewDTO = new DoReviewDTO();
//                doReviewDTO.setRating(new Random().nextInt(1,6));
//                doReviewDTO.setDescription("Description " + i);
//                doReviewDTO.setTitle("Title " + i);
//                List<Product> products = productRepository.findAll();
//                Product product = products.get(new Random().nextInt(products.size()));
//                product.setReviews(new ArrayList<>());
//                List<User> users = userRepository.findAll();
//                User user = users.get(new Random().nextInt(users.size()));
//                reviewService.addReview(doReviewDTO , product.getId() , user.getId() );
//            }
//        });
//    }
}
