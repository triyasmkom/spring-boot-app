// Membuat Repository Interface

package com.example.customer.repositories;

import com.example.customer.entities.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, String>{
    public List<Customer> findByNameContaining (String name);
}
