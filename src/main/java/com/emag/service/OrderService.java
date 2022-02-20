package com.emag.service;

import com.emag.exception.NotFoundException;
import com.emag.model.pojo.Order;
import com.emag.model.pojo.User;
import com.emag.model.pojo.UserCart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService extends AbstractService{

    public Order createOrder(User user) {
        List<UserCart> cart = cartRepository.findAllByUser(user)
                .orElseThrow(() -> new NotFoundException("Carts empty!"));

// TODO finish method
        return null;

    }
}
