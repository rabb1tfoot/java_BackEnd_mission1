package com.zerobase.fastlms.loginHistory.service;

import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.mapper.MemberMapper;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.loginHistory.dto.LoginHistoryDto;
import com.zerobase.fastlms.loginHistory.entity.LoginHistory;
import com.zerobase.fastlms.loginHistory.mapper.LoginHistoryMapper;
import com.zerobase.fastlms.loginHistory.model.LoginInput;
import com.zerobase.fastlms.loginHistory.repository.LoginHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service("LoginHistoryService")
public class LoginHistoryService {

    private final LoginHistoryRepository loginHistoryRepository;
    private final LoginHistoryMapper loginHistoryMapper;

    public boolean SaveLoginHistroyLog(LoginInput loginInput){

        LoginHistory loginHistory = LoginHistory.builder()
                .loginId(loginInput.getUserId())
                .loginDt(LocalDateTime.now())
                .clientIp(loginInput.getClientIp())
                .userAgent(loginInput.getUserAgent()).build();

        loginHistoryRepository.save(loginHistory);

        return true;
    }

    public List<LoginHistoryDto> detail(String userId) {
        List<LoginHistoryDto> list = loginHistoryMapper.selectList(userId);

        for(int i = 0; i < list.size(); ++i){
            list.get(i).setSeq(i);
        }

        return list;
    }
}
