package com.yuri.luis.api.boaviagem.controllers;

import com.yuri.luis.api.boaviagem.models.Order;
import com.yuri.luis.api.boaviagem.models.response.OrderDTO;
import com.yuri.luis.api.boaviagem.services.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
//    @ApiOperation(value = "Cria um pedido")
    public ResponseEntity<Order> addOrder(@RequestBody @Valid OrderDTO orderDTO) {
        var order = new Order();
        BeanUtils.copyProperties(orderDTO, order);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.add(order));
    }
}
