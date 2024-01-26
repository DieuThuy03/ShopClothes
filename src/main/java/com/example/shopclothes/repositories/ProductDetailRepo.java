package com.example.shopclothes.repositories;

import com.example.shopclothes.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository

public interface ProductDetailRepo extends JpaRepository<ProductDetail, Long> {

    @Modifying
    @Transactional
    @Query(value = "update ProductDetail set status = 0 where id =?1", nativeQuery = true)
    void delete(Long id);
}
