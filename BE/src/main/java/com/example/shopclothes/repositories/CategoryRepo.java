package com.example.shopclothes.repositories;

import com.example.shopclothes.entity.Category;
import com.example.shopclothes.entity.Color;
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

public interface CategoryRepo extends JpaRepository<Category, Long> {

    @Modifying
    @Transactional
    @Query(value = "update Category set status = 0 where id =?1", nativeQuery = true)
    void delete(Long id);

    @Query(value = "select c from Category c where c.status = 'DANG_HOAT_DONG'")
    List<Category> getAll();

    Page<Category> getAllByStatus(Status status, Pageable pageable);


    @Query("SELECT c FROM Category c WHERE c.status = 'DANG_HOAT_DONG' ORDER BY c.dateCreate DESC")
    Category findByName(String name);
}