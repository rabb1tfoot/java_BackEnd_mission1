package com.zerobase.fastlms.admin.mapper;


import com.zerobase.fastlms.admin.dto.AdminBannerDto;
import com.zerobase.fastlms.admin.dto.CategoryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminBannerMapper {

    List<AdminBannerDto> sortedList();

}
