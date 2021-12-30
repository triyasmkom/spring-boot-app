package com.domain.restapi.models.repositories;
import com.domain.restapi.models.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends CrudRepository <Product, Long> {
//    Crud Custom, search berdasarkan nama
    List<Product> findByNameContaining(String name);
}
