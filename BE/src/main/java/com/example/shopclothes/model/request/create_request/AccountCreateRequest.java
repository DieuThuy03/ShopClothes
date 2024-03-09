package com.example.shopclothes.model.request.create_request;

import com.example.shopclothes.common.ComonEnum;
import com.example.shopclothes.entity.Role;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class AccountCreateRequest {
    @NotBlank(message = "Vui lòng nhập họ và tên")
    @Pattern(message = "Họ và tên không hợp lệ", regexp = "^[\\p{L}\\s]+$")
    private String fullname;


    @NotNull(message = "Vui lòng chọn ngày sinh")
    @PastOrPresent(message = "Ngày sinh không thể trong tương lai")
    private LocalDate birthday;

    @NotNull(message = "Vui lòng chọn giới tính")
    private ComonEnum.GioiTinh sex;

    @NotBlank(message = "Vui lòng nhập số điện thoại")
    @Pattern(message = "Số điện thoại không hợp lệ", regexp = "^0[35789]\\d{8}$")
    private String phoneNumber;

    @NotBlank(message = "Vui lòng nhập emai")
    @Email(message = "Email không hợp lệ", regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    private String email;

    @NotBlank(message = "Vui lòng chọn tỉnh/thành phố")
    private String city;

    @NotBlank(message = "Vui lòng chọn quận/huyện")
    private String district;

    @NotBlank(message = "Vui lòng chọn phường/xã")
    private String wards;

    @NotBlank(message = "Vui lòng điền địa chỉ cụ thể")
    private String specificAddress;

    private MultipartFile flieAnhDaiDien;


    private String password;

    private ComonEnum.TrangThaiThuocTinh status;

    private Role role;
}
