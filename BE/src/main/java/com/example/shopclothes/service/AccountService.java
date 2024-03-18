package com.example.shopclothes.service;

import com.example.shopclothes.entity.Account;
import com.example.shopclothes.model.dto.PasswordRequest;
import com.example.shopclothes.model.request.create_request.AccountCreateRequest;
import com.example.shopclothes.model.request.update_request.AccountUpdateRequest;
import com.example.shopclothes.model.response.AccountResponse;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface AccountService {


    Page<AccountResponse> getAll(Integer page, Integer pageSize, String sortField, String sortOrder, String sex, String searchText, String status);

    List<Account> getAllKhachHang1();
    Page<AccountResponse> getAllKhachHang(Integer page, Integer pageSize, String sortField, String sortOrder,String sex, String searchText, String status);

    AccountResponse add(AccountCreateRequest request);

    AccountResponse update(Long id, AccountUpdateRequest request);

    AccountResponse updateKhachHang(Long id, AccountUpdateRequest request);

    void  delete(Long id);

    AccountResponse findById(Long id);

    AccountResponse addKhachHang(AccountCreateRequest request);

    Account getAllTaiKhoan(String email);

    String changePassword(PasswordRequest passwordRequest);

    byte[] exportExcelTaiKhoan() throws IOException;

    byte[] exportExcelTaiKhoan1() throws IOException;
    List<Account> getAllTaiKhoan();

    void updateStatus(Long id);

    List<Account> getall1();

}
