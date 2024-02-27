package com.example.shopclothes.repositories;

import com.example.shopclothes.entity.Color;
import com.example.shopclothes.entity.Producer;
import com.example.shopclothes.entity.propertis.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository

public interface ColorRepo extends JpaRepository<Color, Long> {

    @Modifying
    @Transactional
    @Query(value = "update Color set status = 'NGUNG_HOAT_DONG' where id = ?1", nativeQuery = true)
    void delete(Long id);

    @Query(value = "select c from Color c where c.status = 'DANG_HOAT_DONG'")
    List<Color> getAll();

    Page<Color> getAllByStatus(Status status, Pageable pageable);

    // Seacrh
    @Query(value = "SELECT * FROM color " +
            "WHERE (:key IS NULL OR color.code LIKE CONCAT('%', :key, '%')) " +
            "AND (:key IS NULL OR color.name LIKE CONCAT('%', :key , '%')) " +
            "AND (:status IS NULL OR color.status = :status)",
            nativeQuery = true)
    Page<Producer> searchPageNSX(@Param("key") String key,
                                 @Param("status") Integer trangThai,
                                 Pageable pageable);
}
