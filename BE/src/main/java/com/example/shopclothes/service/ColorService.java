package com.example.shopclothes.service;

import com.example.shopclothes.entity.Color;
import com.example.shopclothes.entity.propertis.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ColorService {

    void save(Color color);

    void update(Color color, Long id);

    void delete(Long id);

    void search(Long id);

    Page<Color> select(Status status, Pageable pageable);
}
