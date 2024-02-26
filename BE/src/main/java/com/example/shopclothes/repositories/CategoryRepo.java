package com.example.shopclothes.repositories;

import com.example.shopclothes.entity.Category;
import com.example.shopclothes.entity.propertis.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository

public interface CategoryRepo extends JpaRepository<Category, Long> {

    @Modifying
    @Transactional
    @Query(value = "update Category set status = 'NGUNG_HOAT_DONG' where id =?1", nativeQuery = true)
    void delete(Long id);

    @Transactional
    Page<Category> getAllByStatus(Status status, Pageable pageable);
}
