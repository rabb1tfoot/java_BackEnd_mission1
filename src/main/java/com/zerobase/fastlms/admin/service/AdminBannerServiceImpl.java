package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.AdminBannerDto;
import com.zerobase.fastlms.admin.entity.AdminBanner;
import com.zerobase.fastlms.admin.mapper.AdminBannerMapper;
import com.zerobase.fastlms.admin.model.AdminBannerInput;
import com.zerobase.fastlms.admin.repository.AdminBannerRepository;
import com.zerobase.fastlms.course.dto.CourseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AdminBannerServiceImpl implements AdminBannerService {
    
    private final AdminBannerRepository adminBannerRepository;
    private final AdminBannerMapper adminBannerMapper;

    
    @Override
    public List<AdminBannerDto> list() {
        List<AdminBanner> adminBanner = adminBannerRepository.findAll();
        return AdminBannerDto.of(adminBanner);
    }
    
    @Override
    public boolean add(AdminBannerInput adminBannerInput) {
        AdminBanner adminBanner = AdminBanner.builder()
                .bannerName(adminBannerInput.getBannerName())
                .linkAddress(adminBannerInput.getLinkAddress())
                .openMethod(adminBannerInput.getOpenMethod())
                .sortOrder(adminBannerInput.getSortOrder())
                .strPublic(adminBannerInput.getStrPublic())
                .filename(adminBannerInput.getFilename())
                .urlFilename(adminBannerInput.getUrlFilename())
                .regDt(LocalDateTime.now())
                .build();
        adminBannerRepository.save(adminBanner);
        
        return true;
    }

    @Override
    public boolean delete(String idList) {
        if (idList != null && idList.length() > 0) {
            String[] ids = idList.split(",");
            for (String x : ids) {
                long id = 0L;
                try {
                    id = Long.parseLong(x);
                } catch (Exception e) {
                }

                if (id > 0) {
                    adminBannerRepository.deleteById(id);
                }
            }
        }

        return true;
    }

    @Override
    public boolean set(AdminBannerInput parameter) {

        Optional<AdminBanner> adminBannerOptional = adminBannerRepository.findById(parameter.getId());
        if (!adminBannerOptional.isPresent()) {
            //수정할 데이터가 없음
            return false;
        }

        AdminBanner adminBanner = adminBannerOptional.get();
        adminBanner.setId(parameter.getId());
        adminBanner.setBannerName(parameter.getBannerName());
        adminBanner.setLinkAddress(parameter.getLinkAddress());
        adminBanner.setOpenMethod(parameter.getOpenMethod());
        adminBanner.setSortOrder(parameter.getSortOrder());
        adminBanner.setStrPublic(parameter.getStrPublic());
        adminBanner.setRegDt(LocalDateTime.now());
        adminBanner.setFilename(parameter.getFilename());
        adminBanner.setUrlFilename(parameter.getUrlFilename());

        adminBannerRepository.save(adminBanner);

        return true;
    }

    @Override
    public AdminBannerDto getById(long id) {

        Optional<AdminBanner> optionalAdminBanner = adminBannerRepository.findById(id);
        if(!optionalAdminBanner.isPresent()){
            return null;
        }
        return AdminBannerDto.of(optionalAdminBanner.get());
    }

    @Override
    public List<AdminBannerDto> sortedList() {
        return adminBannerMapper.sortedList();
    }

    private LocalDate getLocalDate(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(value, formatter);
        } catch (Exception e) {
        }
        return null;
    }

//    @Override
//    public List<CategoryDto> frontList(CategoryDto parameter) {
//
//        return categoryMapper.select(parameter);
//    }
}
