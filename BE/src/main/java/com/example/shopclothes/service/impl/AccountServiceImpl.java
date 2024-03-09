package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.Account;
import com.example.shopclothes.repositories.AccountRepo;
import com.example.shopclothes.service.AccountService;
import com.example.shopclothes.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AccountServiceImpl implements AccountService {


    @Autowired
    private AccountRepo repository;

}
