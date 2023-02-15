package org.example.repo;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.example.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    List<Product> findByProductName(String name);

    List<Product> findByProductNameContainingIgnoringCase(String name);

    List<Product> findByProductNameContainingIgnoringCaseAndPriceBetween
            (String name, BigDecimal min, BigDecimal max);

    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);
}