package com.example.shopclothes.repositories;

import com.example.shopclothes.dto.ProductDeatilsDTO;
import com.example.shopclothes.dto.ProductDetailResponseDto;
import com.example.shopclothes.entity.ProductDetail;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository

public interface ProductDetailRepo extends JpaRepository<ProductDetail, Long> {

    @Query(value = "select sp from ProductDetail sp where sp.idProduct.id = :id")
    List<ProductDetail> detailByIdSP(Long id);

    @Transactional
    @Modifying
    @Query(value = "update ProductDetail c set c.quantity = :quantity where c.id = :id")
    void update(Integer quantity, Long id);

    @Query("SELECT new com.example.shopclothes.dto.ProductDetailResponseDto(  pd.id, p.name,i.imageLink, c.name,s.name,m.name,pd.quantity,pd.price, pd.status )" +
            "from ProductDetail pd " +
            "join Product p on pd.idProduct.id = p.id " +
            "join Imege i on i.product.id = p.id " +
            "join Color c on pd.idColor.id  = c.id " +
            "join Size s on pd.idSize.id = s.id " +
            "join Material m on pd.idMaterial.id = m.id " +
            "where pd.idProduct.id = :productId " +
            "and i.status = 'DANG_HOAT_DONG' " +
            "group by pd.id, i.imageLink, p.name, c.name, m.name,s.name,pd.quantity,pd.price, pd.status "+
            "ORDER BY pd.dateUpdate DESC")
    Page<ProductDetailResponseDto> findAllByProductId(@Param("productId") Long productId, Pageable pageable);


    @Query(value = "select * from product_detail where status = 1", nativeQuery = true)
    Page<ProductDetail> getAll(Pageable pageable);
}