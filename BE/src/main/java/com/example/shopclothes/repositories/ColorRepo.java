package com.example.shopclothes.repositories;

import com.example.shopclothes.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository

public interface ColorRepo extends JpaRepository<Color, Long> {

    @Modifying
    @Transactional
    @Query(value = " update color set status = 0 where id =?1", nativeQuery = true)
    void delete(Long id);
}
