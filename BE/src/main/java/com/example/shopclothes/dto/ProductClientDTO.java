package com.example.shopclothes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ProductClientDTO {
    public Long id;

    public String productName;

    public Double price;

    public String imageLink;
}
