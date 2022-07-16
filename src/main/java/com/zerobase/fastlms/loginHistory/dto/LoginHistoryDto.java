package com.zerobase.fastlms.loginHistory.dto;


import com.zerobase.fastlms.admin.dto.CategoryDto;
import com.zerobase.fastlms.admin.entity.Category;
import com.zerobase.fastlms.loginHistory.entity.LoginHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginHistoryDto {

    Long id;

    String loginId;
    LocalDateTime loginDt;
    String clientIp;
    String userAgent;

    long seq;

    public static LoginHistoryDto of(LoginHistory loginHistory) {
        return LoginHistoryDto.builder()
                .id(loginHistory.getId())
                .loginDt(loginHistory.getLoginDt())
                .clientIp(loginHistory.getClientIp())
                .userAgent(loginHistory.getUserAgent())
                .loginId(loginHistory.getLoginId())
                .build();
    }
}
