package com.domain.restapi.controller;


import com.domain.restapi.models.entities.Product;
import com.domain.restapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {
//    injek product service
//    Controller memanggil service, service memanggil repositori
    @Autowired
    private ProductService productService;

//    @RequestBody = Parameter yang akan dikirimkan dari aplikasi client
    @PostMapping
    public Product create(@RequestBody Product product){
        return productService.save(product);
    }
    @GetMapping
    public Iterable<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findOne(@PathVariable("id") Long id){
        return productService.findOne(id);
    }

    @PutMapping
    public Product update(@RequestBody Product product){
        return productService.save(product);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id){
        productService.removeOne(id);
    }
}
