package com.example.shopclothes.repositories;

import com.example.shopclothes.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository

public interface CategoryRepo extends JpaRepository<Category, Long> {

    @Modifying
    @Transactional
    @Query(value = "update Category set status = 0 where id =?1", nativeQuery = true)
    void delete(Long id);
}
