package org.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.example.model.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

}
