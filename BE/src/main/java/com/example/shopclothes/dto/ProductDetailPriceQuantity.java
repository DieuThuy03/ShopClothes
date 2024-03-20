package com.example.shopclothes.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ProductDetailPriceQuantity {

    private Long id;

    private double price;

    private int quantity;
}
