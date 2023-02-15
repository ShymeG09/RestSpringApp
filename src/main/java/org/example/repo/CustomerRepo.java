package org.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.example.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, String> {

}
