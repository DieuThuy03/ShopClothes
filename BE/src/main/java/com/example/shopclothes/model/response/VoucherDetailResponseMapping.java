package com.example.shopclothes.model.response;

import com.example.shopclothes.common.ComonEnum;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.SqlResultSetMapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SqlResultSetMapping(
        name = "VoucherChiTietMapping",
        classes = {
                @ConstructorResult(
                        targetClass = VoucherDetailResponseMapping.class,
                        columns = {
                                @ColumnResult(name = "id", type = Long.class),
                                @ColumnResult(name = "numberOfUses", type = Integer.class),
                                @ColumnResult(name = "dateCreate", type = LocalDateTime.class),
                                @ColumnResult(name = "dateUpdate", type = LocalDateTime.class),
                                @ColumnResult(name = "status", type = ComonEnum.TrangThaiVoucherChiTiet.class),
//                                @ColumnResult(name = "taiKhoanId", type = Long.class), // Use taiKhoanId instead of TaiKhoan,
                                @ColumnResult(name = "used", type = Long.class),
                        }
                )
        }
)
public class VoucherDetailResponseMapping {

    private Long id;
    private Integer numberOfUses;
    private LocalDateTime dateCreate;
    private LocalDateTime dateUpdate;
    private ComonEnum.TrangThaiVoucherChiTiet status;
    //private Long taiKhoanId; // Use taiKhoanId instead of TaiKhoan
   // private TaiKhoan taiKhoan;
    private Long used;
}
