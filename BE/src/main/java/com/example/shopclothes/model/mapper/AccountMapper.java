package com.example.shopclothes.model.mapper;

import com.example.shopclothes.entity.Account;
import com.example.shopclothes.model.request.create_request.AccountCreateRequest;
import com.example.shopclothes.model.request.update_request.AccountUpdateRequest;
import com.example.shopclothes.model.response.AccountResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    @Autowired
    private ModelMapper modelMapper;

    public AccountResponse convertEntityToResponse(Account account) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(account, AccountResponse.class);
    }

    public Account convertCreateRequestToEntity(AccountCreateRequest createTaiKhoanRequest) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

        TypeMap<AccountCreateRequest, Account> typeMap = modelMapper.getTypeMap(AccountCreateRequest.class, Account.class);

        if (typeMap == null) {
            typeMap = modelMapper.createTypeMap(AccountCreateRequest.class, Account.class);
            typeMap.addMappings(mapper -> {
                mapper.map(source -> source.getRole(), Account::setRole);
                // Ánh xạ các thuộc tính khác tại đây nếu cần
            });
        }

        return modelMapper.map(createTaiKhoanRequest, Account.class);
    }




    public void convertUpdateRequestToEntity(AccountUpdateRequest updateRequest, Account detail) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(updateRequest, detail);
    }
}
