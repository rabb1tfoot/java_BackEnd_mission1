package com.zerobase.fastlms.loginHistory.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginInput {

    String userId;
    String clientIp;
    String userAgent;
}
