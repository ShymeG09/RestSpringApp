package org.example.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import org.example.model.Product;
import org.example.repo.ProductRepo;

@RestController
@RequestMapping("/products")
public class RestProducts {

    private ProductRepo productRepo;

    public RestProducts(ProductRepo productRepository) {
        this.productRepo = productRepository;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @GetMapping("/{id}")
    public Product getOneProduct(@PathVariable Integer id) {
        Optional<Product> product = productRepo.findById(id);
        return product.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Brak produktu nr " + id));
    }

    @GetMapping("/{id}/price")
    public BigDecimal getPrice(@PathVariable("id") Integer productId) {
        return getOneProduct(productId).getPrice();
    }

    @PutMapping("/{id}/price")
    public void setPrice(@PathVariable("id") Integer productId, @RequestBody BigDecimal newPrice) {
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setPrice(newPrice);
            productRepo.save(product);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Brak produktu nr " + productId);
        }
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Integer productId, @RequestBody Product product) {
        product.setProductId(productId);
        productRepo.save(product);
    }

    @PostMapping
    public Product insert(@RequestBody Product product) {
        product.setProductId(null);
        productRepo.save(product);
        return product;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer productId) {
        try {
            productRepo.deleteById(productId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Brak produktu nr " + productId);
        }
    }
}

