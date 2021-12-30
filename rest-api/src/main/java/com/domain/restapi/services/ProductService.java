package com.domain.restapi.services;

import com.domain.restapi.models.entities.Product;
import com.domain.restapi.models.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
// fungsi ini dapat dipanggil dari controller atau Url
// bisnis logic/proses (flow proses transer uang)

@Service
@Transactional
public class ProductService {
//    injection
    @Autowired
    private ProductRepo productRepo;

//    save data
    public Product save(Product product){
        return productRepo.save(product);
    }

//    Ambil satu product
    public Product findOne(Long id){
        Optional<Product> product = productRepo.findById(id);
        if(!product.isPresent()){
            return null;
        }
        return  product.get();
//        return  productRepo.findById(id).get();
    }
//    list data

    public Iterable<Product> findAll(){
        return productRepo.findAll();
    }

//    Hapus
    public void removeOne(Long id){
        productRepo.deleteById(id);
    }
//    Search Custom
    public List<Product> findByName(String nama){
        return productRepo.findByNameContaining(nama);
    }
}
