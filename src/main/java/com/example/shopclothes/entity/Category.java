package com.example.shopclothes.entity;

import com.example.shopclothes.entity.propertis.Propertis;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Category")
public class Category extends Propertis {

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idCategory")
    List<ProductDetail> productDetails;
}
