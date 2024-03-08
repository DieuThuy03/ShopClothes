package com.example.shopclothes.repositories;

import com.example.shopclothes.entity.Imege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.List;

@Repository

public interface ImageRepo extends JpaRepository<Imege, Long> {

    @Modifying
    @Transactional
    @Query(value = "update Imege set status = 0 where id =?1", nativeQuery = true)
    void delete(Long id);

    @Query(value = "\n" +
            "select i.date_create, i.date_update, i.id, i.id_product, i.code, i.image_link," +
            " i.image_type, i.name, i.status from imege i join product pd on i.id_product = pd.id where id_product = ?1", nativeQuery = true)
    List<Imege> findImegeByIdCtsp(Long id);

//    List<Imege> findByImageLink(String imageLink);
}