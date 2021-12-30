// membuat entity class Customer.java


package com.example.customer.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Customer implements Serializable{
    @Id
    private String id;
    private String name;
    private String address;
    private String email;
    private String phone;
    
}