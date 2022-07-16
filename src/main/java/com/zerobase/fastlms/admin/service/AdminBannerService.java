package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.AdminBannerDto;
import com.zerobase.fastlms.admin.model.AdminBannerInput;
import com.zerobase.fastlms.course.dto.CourseDto;

import java.util.List;

public interface AdminBannerService {
    
    List<AdminBannerDto> list();
    
    /**
     * 카테고리 신규 추가
     */
    boolean add(AdminBannerInput adminBannerInput);


    boolean delete(String idList);

    boolean set(AdminBannerInput parameter);

    AdminBannerDto getById(long id);

    List<AdminBannerDto> sortedList();


    /**
     * 프론트 카테고리 정보
     */
    //List<CategoryDto> frontList(BannerDto parameter);
    
    

}
