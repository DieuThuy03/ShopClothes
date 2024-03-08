package com.example.shopclothes.repositories;

import com.example.shopclothes.entity.Color;
import com.example.shopclothes.entity.Material;
import com.example.shopclothes.entity.Product;
import com.example.shopclothes.entity.propertis.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository

public interface MaterielRepo extends JpaRepository<Material, Long> {

    @Modifying
    @Transactional
    @Query(value = "update Material set status = 'NGUNG_HOAT_DONG' where id = ?1", nativeQuery = true)
    void delete(Long id);


    @Query(value = "select c from Material c where c.status = 'DANG_HOAT_DONG'")
    List<Material> getAll();


    Page<Material> getAllByStatus(Status status, Pageable pageable);

    @Query("SELECT c FROM Material c WHERE c.status = 'DANG_HOAT_DONG' ORDER BY c.dateCreate DESC")
    Material findByName(String name);


    @Query(value = "select material.date_create, material.date_update, material.id, material.code, material.ghi_chu, material.name, material.status " +
            "from material join product_detail pd on material.id = pd.id_mate where id_mate = ?1", nativeQuery = true)
    List<Material> findByIdProductMaterial(Long id);



}