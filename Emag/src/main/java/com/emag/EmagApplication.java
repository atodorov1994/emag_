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
}
