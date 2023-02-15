package org.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import org.example.model.Order;
import org.example.repo.OrderRepo;

@RestController
@RequestMapping("/orders")
public class RestOrders {
    @Autowired
    private OrderRepo orderRepo;

    @GetMapping
    public List<Order> readAll() {
        return orderRepo.findAll();
    }

    @GetMapping("/{id}")
    public Order readOne(@PathVariable("id") int id) {
        Optional<Order> order = orderRepo.findById(id);
        if(order.isPresent()) {
            return order.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no order with id " + id);
        }
    }

}
