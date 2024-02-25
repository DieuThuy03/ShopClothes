package com.example.shopclothes.repositories;

import com.example.shopclothes.entity.Material;
import com.example.shopclothes.entity.Size;
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

public interface MaterielRepo extends JpaRepository<Material, Long> {

    @Modifying
    @Transactional
    @Query(value = "update material set status = 'NGUNG_HOAT_DONG' where id = ?1", nativeQuery = true)
    void delete(Long id);

    @Query(value = "select c from Material c where c.status = 'DANG_HOAT_DONG'")
    List<Material> getAll();

    Page<Material> getAllByStatus(Status status, Pageable pageable);


    //    // Seacrh
    @Query(value = "SELECT * FROM material " +
            "WHERE (:key IS NULL OR material.code LIKE CONCAT('%', :key, '%')) " +
            "AND (:key IS NULL OR material.name LIKE CONCAT('%', :key , '%')) " +
            "AND (:status IS NULL OR material.status = :status)",
            nativeQuery = true)
    Page<Material> searchPageChatLieu(@Param("key") String key,
                                   @Param("status") Integer trangThai,
                                   Pageable pageable);
}
