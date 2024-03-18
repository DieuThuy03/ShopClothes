package com.example.shopclothes.repositories;

import com.example.shopclothes.common.ComonEnum;
import com.example.shopclothes.entity.Account;
import com.example.shopclothes.entity.Producer;
import com.example.shopclothes.model.response.AccountResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository

public interface AccountRepo extends JpaRepository<Account, Long> {

    @Modifying
    @Transactional
    @Query(value = "update Account set status = 'INACTIVE' where id =?1", nativeQuery = true)
    void updateStatus(Long id);

    @Query("SELECT tk FROM Account tk " +
            " WHERE (tk.fullName LIKE %:searchText% " +
            " OR tk.phoneNumber LIKE %:searchText% " +
            " OR tk.email LIKE %:searchText% " +
            " OR tk.citizenIdentificationCard LIKE %:searchText%) " +
            " AND (:status IS NULL OR tk.status = :status) " +
            " AND (:sex IS NULL OR tk.sex = :sex)"+
            " AND tk.role.id = 2")
    Page<Account> findAllByVaiTro(
            Pageable pageable,
            @Param("searchText") String searchText,
            @Param("status") ComonEnum.TrangThaiThuocTinh status,
            @Param("sex") ComonEnum.GioiTinh sex
    );
////
    @Query(value = "select c from Account c ")
    List<Account> getAll11();
////
    @Query("SELECT tk FROM Account tk " +
            "WHERE (tk.fullName LIKE %:searchText% OR tk.phoneNumber LIKE %:searchText% OR tk.email LIKE %:searchText% OR tk.citizenIdentificationCard LIKE %:searchText%) " +
            "AND (:status IS NULL OR tk.status = :status) " +
            "AND (:sex IS NULL OR tk.sex = :sex)"+
            "AND tk.role.id = 3")
    Page<Account> findAllByVaiTro2(
            Pageable pageable,
            @Param("searchText") String searchText,
            @Param("status") ComonEnum.TrangThaiThuocTinh status,
            @Param("sex") ComonEnum.GioiTinh sex
    );

    @Query("SELECT tk FROM Account tk " +
            "WHERE tk.role.id = 3")
    List<Account> findAllKhachHang();

    Account findByPhoneNumber(String phoneNumber);

    Account findByCitizenIdentificationCard(String card);

    boolean existsByCitizenIdentificationCard(String card);

    boolean existsByPhoneNumber(String phoneNumber);


    Account findAccountByEmail(String email);

    Account findAccountByPassword(String password);


    Optional<Account> findByEmail(String email);

    @Query("SELECT tk FROM Account tk WHERE tk.phoneNumber = :numberPhone")
    Optional<Account> findByPhoneNumber1(String numberPhone);

//    TaiKhoan findByVaitro(VaiTro vaiTro);

    @Query("SELECT tk FROM Account tk WHERE tk.password = :password")
    List<Account> findBypassword(String password);


    @Query("SELECT tk FROM Account tk WHERE tk.id =:id")
    Account findId(@Param("id") Long id);

    @Query("SELECT tk FROM Account tk " +
            "WHERE tk.role.id = 3")
    List<Account> findAllKhachHangExcel();

    @Query("SELECT tk FROM Account tk " +
            "WHERE tk.role.id = 2")
    List<Account> findAllNhanVienExcel();
}
