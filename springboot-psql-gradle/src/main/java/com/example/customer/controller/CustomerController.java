// Membuat Rest API Controller
package com.example.customer.controller;

import com.example.customer.entities.Customer;
import com.example.customer.repositories.CustomerRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    
    @GetMapping("/customers")
        public ResponseEntity<List<Customer>> findAll(
                @RequestParam(name = "name", 
                        required = false, 
                        defaultValue = "") String name) {
            try {
                List<Customer> customers;
                if (StringUtils.hasText(name)) {
                    customers = customerRepository.findByNameContaining(name);
                } else {
                    customers = customerRepository.findAll();
                }

                if (customers.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(customers, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    
    
     @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> findById(
            @PathVariable("id") String id) {
         
        Optional<Customer> customerData = customerRepository.findById(id);
 
        if (customerData.isPresent()) {
            return new ResponseEntity<>(customerData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
 
    @PostMapping("/customers")
    public ResponseEntity<Customer> create(
            @RequestBody Customer customer) {
        try {
            Customer newCustomer = new Customer();
            newCustomer.setId(UUID.randomUUID().toString());
            newCustomer.setName(customer.getName());
            newCustomer.setAddress(customer.getAddress());
            newCustomer.setEmail(customer.getEmail());
            newCustomer.setPhone(customer.getPhone());
            return new ResponseEntity<>(customerRepository.save(newCustomer), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
 
    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> update(
            @PathVariable("id") String id,
            @RequestBody Customer customer) {
 
        Optional<Customer> customerData = customerRepository.findById(id);
 
        if (customerData.isPresent()) {
            Customer updatedCustomer = customerData.get();
            updatedCustomer.setName(customer.getName());
            updatedCustomer.setAddress(customer.getAddress());
            updatedCustomer.setEmail(customer.getEmail());
            updatedCustomer.setPhone(customer.getPhone());
            return new ResponseEntity<>(customerRepository.save(updatedCustomer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
 
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<HttpStatus> delete(
            @PathVariable("id") String id) {
        try {
            customerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
 
    @DeleteMapping("/customers")
    public ResponseEntity<HttpStatus> deleteAll() {
        try {
            customerRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
