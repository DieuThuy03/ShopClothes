package com.example.shopclothes.repositories;

import com.example.shopclothes.entity.Color;
import com.example.shopclothes.entity.propertis.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository

public interface ColorRepo extends JpaRepository<Color, Long> {

    @Modifying
    @Transactional
    @Query(value = " update color set status = 'NGUNG_HOAT_DONG' where id =?1", nativeQuery = true)
    void delete(Long id);

    @Transactional
    Page<Color> getAllByStatus(Status status, Pageable pageable);

}
