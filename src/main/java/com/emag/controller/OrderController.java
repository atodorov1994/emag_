package com.emag.controller;

import com.emag.model.pojo.Order;
import com.emag.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Access;
import javax.servlet.http.HttpServletRequest;

@RestController
public class OrderController {

    @Autowired
    SessionManager sessionManager;

    @Autowired
    OrderService orderService;

    @PostMapping("/orders")
    Order createOrder (HttpServletRequest request) {
        return orderService.createOrder(sessionManager.getLoggedUser(request));
    }
}
