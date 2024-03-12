package com.example.shopclothes.repositories;

import com.example.shopclothes.common.ComonEnum;
import com.example.shopclothes.entity.Vocher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository

public interface VocherRepo extends JpaRepository<Vocher, Long> {

//    @Modifying
//    @Transactional
//    @Query(" update Vocher vc set vc.status = 'CANCELLED' where vc.id = :id")
//    void updateStatus(@Param("id") Long id);


    @Query("SELECT obj FROM Vocher obj WHERE (obj.code LIKE %:searchText% OR obj.name LIKE %:searchText%)" +
            "AND (:status IS NULL OR obj.status = :status)" +
            "AND (:startTime IS NULL OR obj.startTime >= :startTime)" +
            "AND (:endTime IS NULL OR obj.endTime <= :endTime)")
    Page<Vocher> findByAll(Pageable pageable, String searchText, ComonEnum.TrangThaiVoucher status,
                            LocalDateTime startTime, LocalDateTime endTime);
    boolean existsByName(String name);

    ////
    @Query("SELECT v FROM Vocher v WHERE v.status IN ('ONGOING', 'UPCOMING', 'ENDING_SOON') ORDER BY v.dateUpdate DESC")
    List<Vocher> getListVoucher();

    ///

    @Query("SELECT v \n" +
            "FROM Vocher v\n" +
            "WHERE v.id NOT IN (SELECT vct.vocher.id FROM VocherDetail vct WHERE v.status IN ('ONGOING','ENDING_SOON'))\n")
    List<Vocher> getListVoucherOK(@Param("idTaiKhoan") Long id);

    ///
//    @Query("SELECT COUNT(hd) " +
//            "FROM Bill hd " +
//            "WHERE hd.vocher.id = :idVoucher " +
//            "AND CAST (hd.datePayment AS date)  BETWEEN :startTime AND :endTime")
//    Long soLanDaSuDung(@Param("idVoucher") Long idVoucher,
//                       @Param("startTime") LocalDate startTime,
//                       @Param("endTime") LocalDate endTime);

    ///
    @Query(value = "SELECT * FROM Vocher WHERE status != 'CANCELLED'", nativeQuery = true)
    List<Vocher> danhSachVoucherKhongHuy();

    Optional<Vocher> findByCode(String code);

}
