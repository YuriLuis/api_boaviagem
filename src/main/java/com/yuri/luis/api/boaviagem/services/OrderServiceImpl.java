package com.yuri.luis.api.boaviagem.services;

import com.yuri.luis.api.boaviagem.models.Order;
import com.yuri.luis.api.boaviagem.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order add(Order order) {
        return orderRepository.save(order);
    }
}
