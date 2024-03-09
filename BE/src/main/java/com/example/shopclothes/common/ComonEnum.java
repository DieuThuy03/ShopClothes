package com.example.shopclothes.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class ComonEnum {
    @AllArgsConstructor
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum TrangThaiVoucher {
        UPCOMING("UPCOMING", "Sắp diễn ra", "gold"),
        ONGOING("ONGOING", "Đang diễn ra", "green"),
        ENDING_SOON("ENDING_SOON", "Sắp hết hạn", "volcano"),
        EXPIRED("EXPIRED", "Hết hạn", "blue"),
        OUT_OF_STOCK("OUT_OF_STOCK", "Đã hết", "purple"),
        CANCELLED("CANCELLED", "Hủy bỏ", "red");

        private final String ten;
        private final String moTa;
        private final String mauSac;
    }

    @AllArgsConstructor
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum HinhThucGiam {
        ACTIVE("ACTIVE", "Hoạt động", "success"),
        INACTIVE("INACTIVE", "Không hoạt động", "error");

        private final String ten;
        private final String moTa;
        private final String mauSac;
    }

    @AllArgsConstructor
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum TrangThaiVoucherChiTiet {
        ACTIVE("ACTIVE", "Hoạt động", "success"),
        INACTIVE("INACTIVE", "Không hoạt động", "red"),
        DELETED("DELETED", "Đã xóa", "red");

        private final String ten;
        private final String moTa;
        private final String mauSac;
    }

    @AllArgsConstructor
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum TrangThaiThuocTinh {
        ACTIVE("ACTIVE", "Hoạt động", "success"),
        INACTIVE("INACTIVE", "Không hoạt động", "red");

        private final String ten;
        private final String moTa;
        private final String mauSac;
    }

    @AllArgsConstructor
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum GioiTinh {
        MALE("MALE", "Nam", "blue"),
        FEMALE("FEMALE", "Nữ", "magenta"),
        OTHER("OTHER", "Khác", "default");

        private final String ten;
        private final String moTa;
        private final String mauSac;
    }

}
