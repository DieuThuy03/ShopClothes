package com.example.shopclothes.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ProductDetailSize {

    public Long id;

    public String sizeName;

    public Long idProduct;
}
