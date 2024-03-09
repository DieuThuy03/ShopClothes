package com.example.shopclothes.repositories;

import com.example.shopclothes.common.ComonEnum;
import com.example.shopclothes.entity.VocherDetail;
import com.example.shopclothes.model.response.VoucherDetailResponseMapping;
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

public interface VocherDetailRepo extends JpaRepository<VocherDetail, Long> {

    @Modifying
    @Transactional
    @Query(value = "update VocherDetail set status = 0 where id =?1", nativeQuery = true)
    void delete(Long id);


    @Query("SELECT obj FROM VocherDetail obj")
    Page<VocherDetail> getAllPage(Pageable pageable);

    @Query("SELECT obj FROM VocherDetail obj WHERE obj.vocher.id = :idVoucher")
    List<VocherDetail> getAllList(@Param("idVoucher") Long idVoucher);

    @Query("SELECT new com.example.shopclothes.model.response.VoucherDetailResponseMapping(" +
            "vc.id, vc.numberOfUses, vc.dateCreate, vc.dateUpdate, vc.status,  COUNT(hd.id)) " +
            "FROM VocherDetail vc " +
            "LEFT JOIN Bill hd ON hd.vocher.id = vc.vocher.id " +
            "WHERE vc.vocher.id = :voucherId " +
            "GROUP BY vc.id, vc.numberOfUses, vc.dateCreate, vc.dateUpdate, vc.status")
    List<VoucherDetailResponseMapping> findByVoucherId(@Param("voucherId") Long voucherId);

    @Modifying
    @jakarta.transaction.Transactional
    @Query("UPDATE VocherDetail vc SET vc.status = :deleteStatus WHERE vc.id = :voucherDetailId")
    void updateTrangThaiToDeleted(@Param("deleteStatus") ComonEnum.TrangThaiVoucherChiTiet deleteStatus,
                                  @Param("voucherDetailId") Long voucherDetailId);


}
