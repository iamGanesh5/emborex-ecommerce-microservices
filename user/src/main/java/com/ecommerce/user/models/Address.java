package com.ecommerce.user.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "address") // Lowercase preferred for consistency
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;
    private String state;
    private String country;
    private String zipcode;
}
