package com.domain.restapi.models.entities;

import javax.persistence.*;
import java.io.Serializable;

// anotasi sebagai entity


// jpa akan cek apakah ada tabel "tbl_product dan jika blm ada jpa akan mengenerate tabel dengan nama tbl_product
// dan akan menghubungkan dengan class Product
// implement serializable

@Entity
@Table(name = "tbl_product")
public class Product implements Serializable {

    private static final  long serialVersionUID = 1L;

//    anotasi @Id = primary key untuk var id,
//    anotasi @GeneratedValue = auto increment

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    mengganti nama field dan panjang field tabel
    // @Column(name="product_name", length = 100)
    private String name;
    // @Column(name = "product_description",length = 500)
    private String decription;
    private double price;

    public Product() {

    }

    public Product(Long id, String name, String decription, double price) {
        this.id = id;
        this.name = name;
        this.decription = decription;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
