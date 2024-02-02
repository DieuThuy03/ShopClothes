package com.example.shopclothes.repositories;

import com.example.shopclothes.entity.Category;
import com.example.shopclothes.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository

public interface MaterialRepo extends JpaRepository<Material, Long> {

    @Modifying
    @Transactional
    @Query(value = "update Material set status = 0 where id =?1", nativeQuery = true)
    void delete(Long id);

    @Transactional
    @Query(value = "select * from Material where status = ?1", nativeQuery = true)
    List<Category> select(String status);
}
