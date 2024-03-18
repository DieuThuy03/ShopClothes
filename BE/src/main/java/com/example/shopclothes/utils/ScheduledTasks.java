package com.example.shopclothes.utils;

import com.example.shopclothes.service.VocherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class ScheduledTasks {

    @Autowired
    private VocherService voucherService;

    @Scheduled(fixedRate = 1000)
    public void updateVoucherStatus() {
        voucherService.updateVoucherStatus();
    }

}
