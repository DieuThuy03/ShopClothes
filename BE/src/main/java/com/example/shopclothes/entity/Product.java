package com.example.shopclothes.entity;

import com.example.shopclothes.entity.propertis.Propertis;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Product")
public class Product extends Propertis {

    @Column(name = "discribe")
    private String discribe;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idProduct")
    List<ProductDetail> productDetails;
}
