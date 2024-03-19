package com.example.shopclothes.service;

import com.example.shopclothes.dto.UserFilterRequestDto;
import com.example.shopclothes.dto.UserResponseDto;
import org.springframework.data.domain.Page;

public interface UserServiceIPL {

    public Page<UserResponseDto> getUsersByFilter(UserFilterRequestDto requestDto);
}
