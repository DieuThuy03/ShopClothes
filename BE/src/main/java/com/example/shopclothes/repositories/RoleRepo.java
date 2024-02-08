package com.example.shopclothes.repositories;

import com.example.shopclothes.entity.Category;
import com.example.shopclothes.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository

public interface RoleRepo extends JpaRepository<Role, Long> {

    @Modifying
    @Transactional
    @Query(value = "update Role set status = 0 where id = ?1", nativeQuery = true)
    void delete(Long id);

    @Transactional
    @Query(value = "select * from Role where status = ?1", nativeQuery = true)
    List<Category> select(String status);


}
