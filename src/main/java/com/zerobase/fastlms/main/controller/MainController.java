package com.zerobase.fastlms.main.controller;


import com.zerobase.fastlms.admin.dto.AdminBannerDto;
import com.zerobase.fastlms.admin.service.AdminBannerService;
import com.zerobase.fastlms.components.MailComponents;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final MailComponents mailComponents;
    private final AdminBannerService adminBannerService;
    
    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {

        List<AdminBannerDto> adminBannerDtoList = adminBannerService.sortedList();
        model.addAttribute("list", adminBannerDtoList);
        model.addAttribute("listCount", adminBannerDtoList.size());

        return "index";
    }
    
    
    
    @RequestMapping("/error/denied")
    public String errorDenied() {
        
        return "error/denied";
    }
    
    
    
}
