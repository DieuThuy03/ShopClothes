package com.example.shopclothes.service.impl;

import com.example.shopclothes.common.ComonEnum;
import com.example.shopclothes.common.GenCode;
import com.example.shopclothes.entity.Vocher;
import com.example.shopclothes.exception.BadRequestException;
import com.example.shopclothes.exception.NotFoundException;
import com.example.shopclothes.model.mapper.VocherMapper;
import com.example.shopclothes.model.request.create_request.VocherCreateRequest;
import com.example.shopclothes.model.request.update_request.VocherUpdateRequest;
import com.example.shopclothes.model.response.VocherResponse;
import com.example.shopclothes.repositories.VocherRepo;
import com.example.shopclothes.service.IService;
import com.example.shopclothes.service.VocherService;
import com.example.shopclothes.utils.VovherUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class VocherServiceIpl implements VocherService {

    @Autowired
    private VocherRepo vocherRepo;

    @Autowired
    private VocherMapper mapper;

    @Autowired
    private VovherUtils voucherUtils;

    @Override
    public Page<VocherResponse> getAll(Integer pageNo, Integer pageSize, String sortField, String sortOrder, String searchText, String status, LocalDateTime startTime, LocalDateTime endTime) {
        Sort sort;
        if ("ascend".equals(sortOrder)) {
            sort = Sort.by(sortField).ascending();
        } else if ("descend".equals(sortOrder)) {
            sort = Sort.by(sortField).descending();
        } else {
            sort = Sort.by("dateCreate").descending();
        }
        ComonEnum.TrangThaiVoucher statu;
        if (status == null || status.equals("")) {
            statu = null;
        } else {
            statu = ComonEnum.TrangThaiVoucher.valueOf(status);
        }
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        Page<Vocher> voucherPage = vocherRepo.findByAll(pageable, searchText, statu, startTime, endTime);
        return voucherPage.map(mapper::convertEntityToResponse);
    }

    @Override
    public List<VocherResponse> getListVoucher(Long id) {
//
//        List<Vocher> list = vocherRepo.getListVoucherOK(id);
//        return list
//                .stream()
//                .map(mapper::convertEntityToResponse)
//                .collect(Collectors.toList());
        return null;
    }

    @Override
    public List<VocherResponse> getListVoucherSuDung(Long id) {
        return null;
    }

    @Override
    public VocherResponse add(VocherCreateRequest request) {
        if (vocherRepo.existsByName(request.getName())) {
            throw new BadRequestException("Tên voucher đã tồn tại trong hệ thống!");
        }
        Vocher createdVoucher = mapper.convertCreateRequestToEntity(request);
        createdVoucher.setCode(GenCode.generateVoucherCode());
        ComonEnum.TrangThaiVoucher status = voucherUtils.setTrangThaiVoucher(
                createdVoucher.getStartTime(),
                createdVoucher.getEndTime()
        );
        createdVoucher.setStatus(status);
        Vocher savedVoucher = this.vocherRepo.save(createdVoucher);
        return mapper.convertEntityToResponse(savedVoucher);
    }

    @Override
    public VocherResponse update(Long id, VocherUpdateRequest request) {
        Optional<Vocher> optional = vocherRepo.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Voucher không tồn tại");
        }
        if (!request.getName().equals(optional.get().getName()) && vocherRepo.existsByName(request.getName())) {
            throw new BadRequestException("Tên voucher đã tồn tại trong hệ thống!");
        }
        Vocher detail = optional.get();
        ComonEnum.TrangThaiVoucher status = voucherUtils.setTrangThaiVoucher(
                request.getStartTime(), request.getEndTime()
        );
        request.setId(detail.getId());
        request.setStatus(status);
        mapper.convertUpdateRequestToEntity(request, detail);
        Vocher savedVoucher = this.vocherRepo.save(detail);
        return mapper.convertEntityToResponse(savedVoucher);
    }

    @Override
    public VocherResponse delete(Long id) {
        Optional<Vocher> optional = this.vocherRepo.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Voucher không tồn tại");
        }
        vocherRepo.delete(optional.get());
        return mapper.convertEntityToResponse(optional.get());
    }

    @Override
    public Long soLanDaSuDung(Long idVoucher, LocalDate startTime, LocalDate endTime) {
return null;
//        return vocherRepo.soLanDaSuDung(idVoucher,startTime,endTime);
    }

    @Override
    public VocherResponse findById(Long id) {
        Optional<Vocher> optional = vocherRepo.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Voucher không tồn tại");
        }
        return mapper.convertEntityToResponse(optional.get());
    }

    @Override
    public VocherResponse findByMa(String code) {
        Optional<Vocher> optional = vocherRepo.findByCode(code);
        if (optional.isEmpty()) {
            throw new NotFoundException("Voucher không tồn tại");
        }
        return mapper.convertEntityToResponse(optional.get());
    }

    @Override
    public void updateVoucherStatus() {

        List<Vocher> vouchers = vocherRepo.danhSachVoucherKhongHuy();
        LocalDateTime now = LocalDateTime.now();

        for (Vocher voucher : vouchers) {
            ComonEnum.TrangThaiVoucher oldStatus = voucher.getStatus();

            if (now.isBefore(voucher.getStartTime())) {
                voucher.setStatus(ComonEnum.TrangThaiVoucher.UPCOMING);
            } else if (now.isEqual(voucher.getStartTime()) || (now.isAfter(voucher.getStartTime()) && now.isBefore(voucher.getEndTime()))) {
                if (now.isAfter(voucher.getEndTime().minus(1, ChronoUnit.DAYS))) {
                    voucher.setStatus(ComonEnum.TrangThaiVoucher.ENDING_SOON);
                } else {
                    voucher.setStatus(ComonEnum.TrangThaiVoucher.ONGOING);
                }
            } else if (now.isAfter(voucher.getEndTime())) {
                voucher.setStatus(ComonEnum.TrangThaiVoucher.EXPIRED);
            } else {
                // Thêm điều kiện để kiểm tra nếu voucher đã bị hủy
                voucher.setStatus(ComonEnum.TrangThaiVoucher.CANCELLED);
            }

            ComonEnum.TrangThaiVoucher newStatus = voucher.getStatus();
            if (oldStatus != newStatus) {
                System.out.println("Voucher ID: " + voucher.getId() + " - Status changed from " + oldStatus + " to " + newStatus);
            }
        }

        vocherRepo.saveAll(vouchers);
    }

    @Override
    public void cancelVoucher(Long id) {
        Vocher voucher = vocherRepo.findById(id).orElse(null);
        voucher.setStatus(ComonEnum.TrangThaiVoucher.CANCELLED);
        vocherRepo.save(voucher);
    }
}
