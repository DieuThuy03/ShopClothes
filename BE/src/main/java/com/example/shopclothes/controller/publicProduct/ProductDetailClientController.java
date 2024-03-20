package com.example.shopclothes.controller.publicProduct;

import com.example.shopclothes.dto.ProductDetailCol;
import com.example.shopclothes.dto.ProductDetailMate;
import com.example.shopclothes.dto.ProductDetailResponseDto;
import com.example.shopclothes.dto.ProductDetailSize;
import com.example.shopclothes.dto.ResponseHandler;
import com.example.shopclothes.entity.Product;
import com.example.shopclothes.entity.ProductDetail;
import com.example.shopclothes.repositories.ProductDetailRepo;
import com.example.shopclothes.service.impl.ProductDetailService;
import com.example.shopclothes.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/api/v1/public/productDetail")
public class ProductDetailClientController {

    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private ProductDetailRepo productDetailRepo;

    @Autowired
    private ProductService productService;


    @GetMapping("/getAllByProductId")
    public ResponseEntity<?> findAllByProductId(@RequestParam Long productId,
                                                @RequestParam(defaultValue = "0") Integer pageNo,
                                                @RequestParam(defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<ProductDetailResponseDto> responseDtoPage = productDetailService.findAllByProductId(productId, pageable);
        List<ProductDetailResponseDto> detailResponseDtoList = responseDtoPage.getContent();
        return ResponseHandler.generateResponse(
                HttpStatus.OK
                , detailResponseDtoList
                , responseDtoPage);
    }

//    @GetMapping("/hien-thi")
//    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") Integer page){
//        Pageable pageable = PageRequest.of(page, 5);
//        Page<ProductDetail> productDetails = productDetailRepo.getAll(pageable);
//        return ResponseEntity.ok(productDetails.getContent());
//    }

    @GetMapping("filterProductDetailBySize")
    public ResponseEntity<List<ProductDetailSize>> filterProductDetailBySize(@RequestParam Long idSize){
        List<ProductDetailSize> sizeProductList = productDetailService.fillterProductDetailBySize(idSize);
        return ResponseEntity.status(HttpStatus.OK).body(sizeProductList);
    }

    @GetMapping("filterProductDetailByCol")
    public ResponseEntity<List<ProductDetailCol>> filterProductDetailByCol(@RequestParam Long idCol){
        List<ProductDetailCol> sizeProductList = productDetailService.fillterProductDetailByCol(idCol);
        return ResponseEntity.status(HttpStatus.OK).body(sizeProductList);
    }

    @GetMapping("filterProductDetailByMate")
    public ResponseEntity<List<ProductDetailMate>> filterProductDetailByMate(@RequestParam Long idMate){
        List<ProductDetailMate> sizeProductList = productDetailService.fillterProductDetailByMate(idMate);
        return ResponseEntity.status(HttpStatus.OK).body(sizeProductList);
    }


}
