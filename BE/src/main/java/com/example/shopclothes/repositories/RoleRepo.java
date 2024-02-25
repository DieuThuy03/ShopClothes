package com.example.shopclothes.repositories;

import com.example.shopclothes.entity.Role;
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

public interface RoleRepo extends JpaRepository<Role, Long> {

    @Modifying
    @Transactional
    @Query(value = "update ROLE set status = 'NGUNG_HOAT_DONG' where id = ?1", nativeQuery = true)
    void delete(Long id);

    @Query(value = "select c from Role c where c.status = 'DANG_HOAT_DONG'")
    List<Role> getAll();

    Page<Role> getAllByStatus(Status status, Pageable pageable);


    //    // Seacrh
    @Query(value = "SELECT * FROM role " +
            "WHERE (:key IS NULL OR role.code LIKE CONCAT('%', :key, '%')) " +
            "AND (:key IS NULL OR role.name LIKE CONCAT('%', :key , '%')) " +
            "AND (:status IS NULL OR role.status = :status)",
            nativeQuery = true)
    Page<Role> searchRole(@Param("key") String key,
                                   @Param("status") Integer trangThai,
                                   Pageable pageable);
}
