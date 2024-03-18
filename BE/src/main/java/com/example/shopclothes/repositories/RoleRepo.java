package com.example.shopclothes.repositories;

import com.example.shopclothes.common.ComonEnum;
import com.example.shopclothes.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository

public interface RoleRepo extends JpaRepository<Role, Long> {

    @Modifying
    @Transactional
    @Query(value = "update Rele set status = '' where id = ?1", nativeQuery = true)
    void updateStatus(Long id);

    @Query("SELECT obj FROM Role obj WHERE (obj.name LIKE %:searchText%) AND (:status IS NULL OR obj.status = :status)")
    Page<Role> findByAll(Pageable pageable, String searchText, ComonEnum.TrangThaiThuocTinh status);

    Role findByName(String name);

    @Query("SELECT vc FROM Role vc WHERE vc.id =:id")
    Role findId(@Param("id") Long id);

    Optional<Role> findRoleById(Long id);
}
