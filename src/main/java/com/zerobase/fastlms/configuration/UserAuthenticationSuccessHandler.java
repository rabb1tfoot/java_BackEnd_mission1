package com.zerobase.fastlms.configuration;

import com.zerobase.fastlms.loginHistory.model.LoginInput;
import com.zerobase.fastlms.loginHistory.service.LoginHistoryService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Resource(name="LoginHistoryService")
    private LoginHistoryService loginHistoryService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response
            , Authentication authentication) throws IOException, ServletException {

        LoginInput loginInput = new LoginInput();
        loginInput.setUserAgent(request.getHeader("user-agent"));
        loginInput.setClientIp(request.getHeader("X-FORWARDED-FOR"));
        loginInput.setUserId(request.getParameter("username"));
        if(loginInput.getClientIp() == null){
            loginInput.setClientIp(request.getRemoteAddr());
        }

        loginHistoryService.SaveLoginHistroyLog(loginInput);
        super.onAuthenticationSuccess(request, response, authentication);

    }



}
