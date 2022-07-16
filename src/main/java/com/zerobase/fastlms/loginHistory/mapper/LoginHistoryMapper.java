package com.zerobase.fastlms.loginHistory.mapper;

import com.zerobase.fastlms.loginHistory.dto.LoginHistoryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginHistoryMapper {

    List<LoginHistoryDto> selectList(String userId);

}
