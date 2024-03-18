package com.example.shopclothes.service.impl;

import com.example.shopclothes.common.ComonEnum;
import com.example.shopclothes.common.GenCode;
import com.example.shopclothes.entity.Account;
import com.example.shopclothes.entity.Cart;
import com.example.shopclothes.entity.Role;
import com.example.shopclothes.exception.BadRequestException;
import com.example.shopclothes.exception.NotFoundException;
import com.example.shopclothes.model.dto.PasswordRequest;
import com.example.shopclothes.model.mapper.AccountMapper;
import com.example.shopclothes.model.request.create_request.AccountCreateRequest;
import com.example.shopclothes.model.request.update_request.AccountUpdateRequest;
import com.example.shopclothes.model.response.AccountResponse;
import com.example.shopclothes.repositories.AccountRepo;
import com.example.shopclothes.repositories.RoleRepo;
import com.example.shopclothes.service.AccountService;
import com.example.shopclothes.service.IService;
import com.example.shopclothes.utils.EmailSend;
import jakarta.validation.constraints.Email;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service

public class AccountServiceImpl implements AccountService {


    @Autowired
    private AccountRepo repository;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private EmailSend emailSender;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private GioHangRepository gioHangRepository;


    @Override
    public Page<AccountResponse> getAll(Integer page, Integer pageSize, String sortField, String sortOrder, String sex, String searchText, String status) {
        Sort sort;
        if ("ascend".equals(sortOrder)) {
            sort = Sort.by(sortField).ascending();
        } else if ("descend".equals(sortOrder)) {
            sort = Sort.by(sortField).descending();
        } else {
            sort = Sort.by("dateCreate").descending();
        }

        ComonEnum.GioiTinh sexs;

        if (sex == null || sex.equals("")) {
            sexs = null;
        }else {
            sexs = ComonEnum.GioiTinh.valueOf(sex);
        }
        ComonEnum.TrangThaiThuocTinh statu;

        if (status == null || status.equals("")) {
            statu = null;
        } else {
            statu = ComonEnum.TrangThaiThuocTinh.valueOf(status);
        }
        Pageable pageable = PageRequest.of(page - 1, pageSize, sort);
        Page<Account> taiKhoanPage = repository.findAllByVaiTro(pageable, searchText,statu,sexs);
        return taiKhoanPage.map(accountMapper::convertEntityToResponse);

    }

    @Override
    public List<Account> getAllKhachHang1() {
        List<Account> accounts = repository.findAllKhachHang();
        return accounts;
    }

    @Override
    public Page<AccountResponse> getAllKhachHang(Integer page, Integer pageSize, String sortField, String sortOrder, String sex, String searchText, String status) {
        Sort sort;
        if ("ascend".equals(sortOrder)) {
            sort = Sort.by(sortField).ascending();
        } else if ("descend".equals(sortOrder)) {
            sort = Sort.by(sortField).descending();
        } else {
            sort = Sort.by("dateCreate").descending();
        }

        ComonEnum.GioiTinh gioiTinh;

        if (sex == null || sex.equals("")) {
            gioiTinh = null;
        }else {
            gioiTinh = ComonEnum.GioiTinh.valueOf(sex);
        }
        ComonEnum.TrangThaiThuocTinh trangThai;

        if (status == null || status.equals("")) {
            trangThai = null;
        } else {
            trangThai = ComonEnum.TrangThaiThuocTinh.valueOf(status);
        }
        Pageable pageable = PageRequest.of(page - 1, pageSize, sort);
        Page<Account> taiKhoanPage = repository.findAllByVaiTro2(pageable, searchText,trangThai,gioiTinh);
        return taiKhoanPage.map(accountMapper::convertEntityToResponse);

    }

    @Override
    public AccountResponse add(AccountCreateRequest request) {
        Account canCuocCongDan = repository.findByCitizenIdentificationCard(request.getCitizenIdentificationCard());
        Account soDienThoai = repository.findByPhoneNumber(request.getPhoneNumber());
        if (canCuocCongDan != null) {
            throw new BadRequestException("CMT/CCCD đã tồn tại trong hệ thống!");
        }
        if (soDienThoai != null) {
            throw new BadRequestException("Số điện thoại đã tồn tại trong hệ thống!");
        }
        if(request.getSex()==null){
            request.setSex(ComonEnum.GioiTinh.OTHER);
        }
        Account createdTaiKhoan = accountMapper.convertCreateRequestToEntity(request);
        createdTaiKhoan.setStatus(ComonEnum.TrangThaiThuocTinh.ACTIVE);
        createdTaiKhoan.setRole(roleRepo.findId(Long.valueOf(2)));
        createdTaiKhoan.setAvatar("defaultAvatar.jpg");
        createdTaiKhoan.setPassword(emailSender.randomPasswords());
        Account savedTaiKhoan = repository.save(createdTaiKhoan);
        emailSender.sendEmail(savedTaiKhoan);
//        createdTaiKhoan.setMatKhau(new BCryptPasswordEncoder().encode(savedTaiKhoan.getMatKhau()));
//        savedTaiKhoan = taiKhoanRepository.save(createdTaiKhoan);
        return accountMapper.convertEntityToResponse(savedTaiKhoan);
    }

    @Override
    public AccountResponse update(Long id, AccountUpdateRequest request) {
        Optional<Account> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Tài khoản không tồn tại");
        }

        Account soDienThoai = repository.findByPhoneNumber(request.getPhoneNumber());

        if (soDienThoai != null && !request.getPhoneNumber().equals(soDienThoai.getPhoneNumber())) {
            if (repository.existsByPhoneNumber(request.getPhoneNumber())) {
                throw new BadRequestException("Số điện thoại đã tồn tại trong hệ thống. Vui lòng sử dụng số điện thoại khác!");
            }
        }

        Account detail = optional.get();
        accountMapper.convertUpdateRequestToEntity(request, detail);
        return accountMapper.convertEntityToResponse(repository.save(detail));

    }

    @Override
    public AccountResponse updateKhachHang(Long id, AccountUpdateRequest request) {
        Optional<Account> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Tài khoản không tồn tại");
        }

        Account soDienThoai = repository.findByPhoneNumber(request.getPhoneNumber());

        if (soDienThoai != null && !request.getPhoneNumber().equals(soDienThoai.getPhoneNumber())) {
            if (repository.existsByPhoneNumber(request.getPhoneNumber())) {
                throw new BadRequestException("Số điện thoại đã tồn tại trong hệ thống. Vui lòng sử dụng số điện thoại khác!");
            }
        }

        Account detail = optional.get();
        accountMapper.convertUpdateRequestToEntity(request, detail);
        return accountMapper.convertEntityToResponse(repository.save(detail));

    }

    @Override
    public void delete(Long id) {
        Optional<Account> optional = this.repository.findById(id);
        repository.delete(optional.get());

    }

    @Override
    public AccountResponse findById(Long id) {
        Optional<Account> taiKhoan = repository.findById(id);
        if (taiKhoan.isEmpty()) {
            throw new NotFoundException("Tài khoản không tồn tại");
        }
        return accountMapper.convertEntityToResponse(taiKhoan.get());

    }

    @Override
    public AccountResponse addKhachHang(AccountCreateRequest request) {
        Account phoneNumber = repository.findByPhoneNumber(request.getPhoneNumber());
        if (phoneNumber != null) {
            throw new BadRequestException("Số điện thoại đã tồn tại trong hệ thống!");
        }
        if(request.getSex()==null){
            request.setSex(ComonEnum.GioiTinh.OTHER);
        }

        Account createdTaiKhoan = accountMapper.convertCreateRequestToEntity(request);
        createdTaiKhoan.setStatus(ComonEnum.TrangThaiThuocTinh.ACTIVE);
        createdTaiKhoan.setRole(roleRepo.findId(Long.valueOf(3)));
        createdTaiKhoan.setAvatar("defaultAvatar.jpg");
        createdTaiKhoan.setPassword(emailSender.randomPasswords());
        Account savedTaiKhoan = repository.save(createdTaiKhoan);
        emailSender.sendEmail(savedTaiKhoan);
        //createdTaiKhoan.setPassword(new BCryptPasswordEncoder().encode(savedTaiKhoan.getMatKhau()));
        savedTaiKhoan = repository.save(createdTaiKhoan);
//        Cart gioHang = new Cart();
//        gioHang.setCode(GenCode.generateGioHangCode());
//        gioHang.setStatus(1);
//        gioHang.setAccount(repository.getOne(savedTaiKhoan.getId()));
//        gioHangRepository.save(gioHang);
        return accountMapper.convertEntityToResponse(savedTaiKhoan);
    }

    @Override
    public Account getAllTaiKhoan(String email) {
        Account listTaiKhoan = repository.findAccountByEmail(email);
        return listTaiKhoan;
    }

    @Override
    public String changePassword(PasswordRequest passwordRequest) {
//        Optional<Account> optionalTaiKhoan = repository.findById(passwordRequest.getId());
//        if (optionalTaiKhoan.isPresent()) {
//            Account accountId = optionalTaiKhoan.get();
//            System.out.println("aaaa"+accountId);
//            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(accountId.getSoDienThoai(), passwordRequest.getMatKhauCu()));
//
//            if (authentication.isAuthenticated()) {
//                System.out.println("da vao");
//
//                if(!passwordRequest.getMatKhauMoi().equals(passwordRequest.getNhapLaiMatKhau())){
//                    return"Mật khẩu nhập lại phải trùng nhau";
//                }
//
//                accountId.setMatKhau(passwordEncoder.encode(passwordRequest.getNhapLaiMatKhau()));
//                taiKhoanRepository.save(accountId);
//                return"Bạn đã đổi mật khẩu thành công";
//            }
//
//            else {
//                return"Mật khẩu cũ không trùng với mật khẩu của tài khoản";
//            }
//        } else {
//            return "Đổi mật khẩu thất bại";
//        }
        return null;
   }

    @Override
    public byte[] exportExcelTaiKhoan() throws IOException {
//        List<TaiKhoan> taiKhoanList = taiKhoanRepository.findAllNhanVienExcel(); // Implement this method in your service
//
//        XSSFWorkbook workbook = new XSSFWorkbook();
//        XSSFSheet sheet = workbook.createSheet("TaiKhoan");
//
//        // Create header row
//        Row headerRow = sheet.createRow(0);
//        headerRow.createCell(0).setCellValue("ID");
//        headerRow.createCell(1).setCellValue("Ho va Ten");
//        headerRow.createCell(2).setCellValue("Can Cuoc Cong Dan");
//        headerRow.createCell(3).setCellValue("Ngay sinh");
//        headerRow.createCell(4).setCellValue("Gioi tinh");
//        headerRow.createCell(5).setCellValue("So dien thoai");
//        headerRow.createCell(6).setCellValue("Email");
//        headerRow.createCell(7).setCellValue("Thanh pho");
//        headerRow.createCell(8).setCellValue("Quyen huyen");
//        headerRow.createCell(9).setCellValue("Phuong xa");
//        headerRow.createCell(10).setCellValue("Dia chi cu the");
//        headerRow.createCell(11).setCellValue("Anh dai dien");
//        headerRow.createCell(12).setCellValue("Mat khau");
//        headerRow.createCell(13).setCellValue("Ngay tao");
//        headerRow.createCell(14).setCellValue("Ngay sua");
//        headerRow.createCell(15).setCellValue("Trang thai");
//        headerRow.createCell(16).setCellValue("Vai tro ID");
//
//        // Create data rows
//        int rowNum = 1;
//        for (TaiKhoan taiKhoan : taiKhoanList) {
//            Row row = sheet.createRow(rowNum++);
//            row.createCell(0).setCellValue(taiKhoan.getId());
//            row.createCell(1).setCellValue(taiKhoan.getHoVaTen());
//            row.createCell(2).setCellValue(taiKhoan.getCanCuocCongDan());
//            row.createCell(3).setCellValue(taiKhoan.getNgaySinh());
//            if (taiKhoan.getGioiTinh() != null) {
//                row.createCell(4).setCellValue(taiKhoan.getGioiTinh().getMoTa());
//            } else {
//                row.createCell(4).setCellValue((String) null);
//            }
//            row.createCell(5).setCellValue(taiKhoan.getSoDienThoai());
//            row.createCell(6).setCellValue(taiKhoan.getEmail());
//            row.createCell(7).setCellValue(taiKhoan.getThanhPho());
//            row.createCell(8).setCellValue(taiKhoan.getQuanHuyen());
//            row.createCell(9).setCellValue(taiKhoan.getPhuongXa());
//            row.createCell(10).setCellValue(taiKhoan.getDiaChiCuThe());
//            row.createCell(11).setCellValue(taiKhoan.getAnhDaiDien());
//            row.createCell(12).setCellValue(taiKhoan.getMatKhau());
//            row.createCell(13).setCellValue(taiKhoan.getNgayTao());
//            row.createCell(14).setCellValue(taiKhoan.getNgaySua());
//            if (taiKhoan.getTrangThai() != null) {
//                row.createCell(15).setCellValue(taiKhoan.getTrangThai().getMoTa());
//            } else {
//                row.createCell(15).setCellValue((String) null);
//            }
//
//            row.createCell(16).setCellValue(taiKhoan.getVaiTro().getTen());
//        }
//
//        // Set the response headers
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        workbook.write(outputStream);
//        workbook.close();
//        return outputStream.toByteArray();
        return null;
    }

    @Override
    public byte[] exportExcelTaiKhoan1() throws IOException {
//        List<TaiKhoan> taiKhoanList = taiKhoanRepository.findAllKhachHangExcel(); // Implement this method in your service
//
//        XSSFWorkbook workbook = new XSSFWorkbook();
//        XSSFSheet sheet = workbook.createSheet("TaiKhoan");
//
//        // Create header row
//        Row headerRow = sheet.createRow(0);
//        headerRow.createCell(0).setCellValue("ID");
//        headerRow.createCell(1).setCellValue("Ho va Ten");
//        headerRow.createCell(2).setCellValue("Can Cuoc Cong Dan");
//        headerRow.createCell(3).setCellValue("Ngay sinh");
//        headerRow.createCell(4).setCellValue("Gioi tinh");
//        headerRow.createCell(5).setCellValue("So dien thoai");
//        headerRow.createCell(6).setCellValue("Email");
//        headerRow.createCell(7).setCellValue("Thanh pho");
//        headerRow.createCell(8).setCellValue("Quyen huyen");
//        headerRow.createCell(9).setCellValue("Phuong xa");
//        headerRow.createCell(10).setCellValue("Dia chi cu the");
//        headerRow.createCell(11).setCellValue("Anh dai dien");
//        headerRow.createCell(12).setCellValue("Mat khau");
//        headerRow.createCell(13).setCellValue("Ngay tao");
//        headerRow.createCell(14).setCellValue("Ngay sua");
//        headerRow.createCell(15).setCellValue("Trang thai");
//        headerRow.createCell(16).setCellValue("Vai tro ID");
//
//        // Create data rows
//        int rowNum = 1;
//        for (TaiKhoan taiKhoan : taiKhoanList) {
//            Row row = sheet.createRow(rowNum++);
//            row.createCell(0).setCellValue(taiKhoan.getId());
//            row.createCell(1).setCellValue(taiKhoan.getHoVaTen());
//            row.createCell(2).setCellValue(taiKhoan.getCanCuocCongDan());
//            row.createCell(3).setCellValue(taiKhoan.getNgaySinh());
//            if (taiKhoan.getGioiTinh() != null) {
//                row.createCell(4).setCellValue(taiKhoan.getGioiTinh().getMoTa());
//            } else {
//                row.createCell(4).setCellValue((String) null);
//            }
//            row.createCell(5).setCellValue(taiKhoan.getSoDienThoai());
//            row.createCell(6).setCellValue(taiKhoan.getEmail());
//            row.createCell(7).setCellValue(taiKhoan.getThanhPho());
//            row.createCell(8).setCellValue(taiKhoan.getQuanHuyen());
//            row.createCell(9).setCellValue(taiKhoan.getPhuongXa());
//            row.createCell(10).setCellValue(taiKhoan.getDiaChiCuThe());
//            row.createCell(11).setCellValue(taiKhoan.getAnhDaiDien());
//            row.createCell(12).setCellValue(taiKhoan.getMatKhau());
//            row.createCell(13).setCellValue(taiKhoan.getNgayTao());
//            row.createCell(14).setCellValue(taiKhoan.getNgaySua());
//            if (taiKhoan.getTrangThai() != null) {
//                row.createCell(15).setCellValue(taiKhoan.getTrangThai().getMoTa());
//            } else {
//                row.createCell(15).setCellValue((String) null);
//            }
//
//            row.createCell(16).setCellValue(taiKhoan.getVaiTro().getTen());
//        }
//
//        // Set the response headers
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        workbook.write(outputStream);
//        workbook.close();
//        return outputStream.toByteArray();
        return new byte[0];
    }

    @Override
    public List<Account> getAllTaiKhoan() {
        return repository.findAll();
    }

    @Override
    public void updateStatus(Long id) {
        Account account = repository.findById(id).orElse(null);
        account.setStatus(ComonEnum.TrangThaiThuocTinh.INACTIVE);
        repository.save(account);
    }

    @Override
    public List<Account> getall1() {
        return repository.getAll11();
    }
}
