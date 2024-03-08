package com.example.shopclothes.service.impl;

import com.example.shopclothes.dto.ProductDeatilsDTO;
import com.example.shopclothes.dto.ProductDetailRequestDto;
import com.example.shopclothes.dto.ProductDetailResponseDto;
import com.example.shopclothes.dto.ResponseDto;
import com.example.shopclothes.entity.ProductDetail;
import com.example.shopclothes.entity.propertis.Status;
import com.example.shopclothes.exception.ResourceNotFoundException;
import com.example.shopclothes.repositories.*;
import com.example.shopclothes.service.IService;
import com.example.shopclothes.service.ProductdetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service

public class ProductDetailService implements ProductdetailsServices {

    @Autowired
    private ProductDetailRepo productDetailRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ColorRepo colorRepo;

    @Autowired
    private SizeRepo sizeRepo;

    @Autowired
    private MaterielRepo materielRepo;




    @Override
    public List<ProductDetail> detailByIdSP(Long id) {
        return productDetailRepo.detailByIdSP(id);
    }

    @Override
    public void update(Integer quantity, Long id) {
        productDetailRepo.update(quantity, id);
    }

    @Override
    public ProductDetail add(ProductDetail chiTietSanPham) {
        return productDetailRepo.save(chiTietSanPham);
    }

    @Override
    public Boolean createProductDetails(List<ProductDetailRequestDto> productDetailRequestDtos) {
        List<ProductDetail> productDetails = new ArrayList<>();

        for (ProductDetailRequestDto productDetailRequestDto : productDetailRequestDtos) {
            ProductDetail productDetail = new ProductDetail();

            productDetail.setIdProduct(productRepo.findById(productDetailRequestDto.getProductId()).orElse(null));
            productDetail.setIdColor(colorRepo.findByName(productDetailRequestDto.getColorName()));
            productDetail.setIdSize(sizeRepo.findByName(productDetailRequestDto.getSizeName()));
            productDetail.setIdMaterial(materielRepo.findByName(productDetailRequestDto.getMaterialName()));
            productDetail.setQuantity(productDetailRequestDto.getQuantity());
            productDetail.setPrice(productDetailRequestDto.getPrice());
            productDetail.setStatus(Status.DANG_HOAT_DONG);

            productDetails.add(productDetail);
        }

        productDetailRepo.saveAll(productDetails);
        return true;
    }

    @Override
    public Boolean updateProductDetail(ProductDetailRequestDto requestDto, Long id) {
        ProductDetail productDetail = productDetailRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy id sản phẩm chi tiết này!"));

        productDetail.setIdProduct(productRepo.findById(requestDto.getProductId()).orElse(null));
        productDetail.setIdColor(colorRepo.findByName(requestDto.getColorName()));
        productDetail.setIdSize(sizeRepo.findByName(requestDto.getSizeName()));
        productDetail.setIdMaterial(materielRepo.findByName(requestDto.getMaterialName()));
        productDetail.setQuantity(requestDto.getQuantity());
        productDetail.setPrice(requestDto.getPrice());
        productDetail.setStatus(Status.DANG_HOAT_DONG);

        productDetailRepo.save(productDetail);
        return true;
    }

    @Override
    public Page<ProductDetailResponseDto> findAllByProductId(Long id, Pageable pageable) {
        return productDetailRepo.findAllByProductId(id,pageable);
    }

    @Override
    public Page<ProductDetail> getAll(Pageable pageable) {
        return productDetailRepo.getAll(pageable);
    }
}