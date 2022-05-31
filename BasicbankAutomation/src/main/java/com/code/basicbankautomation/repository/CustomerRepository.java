package com.code.basicbankautomation.repository;

import com.code.basicbankautomation.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,String> {
}
