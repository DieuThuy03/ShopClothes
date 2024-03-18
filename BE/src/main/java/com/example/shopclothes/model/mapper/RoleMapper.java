package com.example.shopclothes.model.mapper;

import com.example.shopclothes.entity.Role;
import com.example.shopclothes.model.request.create_request.RoleCreateRequest;
import com.example.shopclothes.model.request.update_request.RooleUpdateRequest;
import com.example.shopclothes.model.response.RoleResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    @Autowired
    private ModelMapper mapper;

    public RoleResponse convertEntityToResponse(Role role){
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return mapper.map(role,RoleResponse.class);
    }

    public Role convertCreateRequestToEntity(RoleCreateRequest request){
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return mapper.map(request,Role.class);
    }

    public void convertUpdateRequestToEntity(RooleUpdateRequest request, Role role) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        mapper.map(request, role);
    }
}
