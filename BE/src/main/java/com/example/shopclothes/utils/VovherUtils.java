package com.example.shopclothes.utils;

import com.example.shopclothes.common.ComonEnum;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Primary
public class VovherUtils {
    public static ComonEnum.TrangThaiVoucher setTrangThaiVoucher(LocalDateTime startTime, LocalDateTime endTime) {
        // Kiểm tra xem voucher đã bị hủy chưa
        LocalDateTime currentDate = LocalDateTime.now();

        if (currentDate.isBefore(startTime)) {
            return ComonEnum.TrangThaiVoucher.UPCOMING;
        } else if (currentDate.isEqual(startTime) || (currentDate.isAfter(startTime) && currentDate.isBefore(endTime))) {
            return ComonEnum.TrangThaiVoucher.ONGOING;
        } else if (currentDate.isBefore(endTime)) {
            return ComonEnum.TrangThaiVoucher.ENDING_SOON;
        } else if (currentDate.isEqual(endTime) || currentDate.isAfter(endTime)) {
            return ComonEnum.TrangThaiVoucher.EXPIRED;
        } else {
            return ComonEnum.TrangThaiVoucher.OUT_OF_STOCK;
        }
    }
}
