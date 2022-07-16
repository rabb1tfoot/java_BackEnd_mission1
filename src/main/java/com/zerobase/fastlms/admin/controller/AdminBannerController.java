package com.zerobase.fastlms.admin.controller;


import com.zerobase.fastlms.admin.dto.AdminBannerDto;
import com.zerobase.fastlms.admin.model.AdminBannerInput;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.admin.service.AdminBannerService;
import com.zerobase.fastlms.course.controller.BaseController;
import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.model.CourseInput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AdminBannerController extends BaseController {
    
    private final AdminBannerService adminBannerService;
    
    @GetMapping("/admin/banner/list.do")
    public String list(Model model, MemberParam parameter) {
        
        List<AdminBannerDto> list = adminBannerService.list();
        long totalCount = list.size();
        String queryString = parameter.getQueryString();
        String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);
        model.addAttribute("pager", pagerHtml);
        model.addAttribute("list", list);
        model.addAttribute("totalCount", totalCount);

        return "admin/banner/list";
    }

    @GetMapping(value = {"/admin/banner/add.do", "/admin/banner/edit.do"})
    public String add(Model model, HttpServletRequest request
            , AdminBannerInput adminBannerInput) {

        boolean editMode = request.getRequestURI().contains("/edit.do");
        AdminBannerDto detail = new AdminBannerDto();

        if (editMode) {
            long id = adminBannerInput.getId();
            AdminBannerDto existBanner = adminBannerService.getById(id);
            if (existBanner == null) {
                // error 처리
                model.addAttribute("message", "강좌정보가 존재하지 않습니다.");
                return "common/error";
            }
            detail = existBanner;
        }

        model.addAttribute("editMode", editMode);
        model.addAttribute("detail", detail);

        return "admin/banner/add";
    }

    
    @PostMapping(value = {"/admin/banner/add.do", "/admin/banner/edit.do"})
    public String add(Model model, AdminBannerInput adminBannerInput, HttpServletRequest request
    , MultipartFile file){

        if(file != null){
            String originalFilename = file.getOriginalFilename();
            String baseLocalPath = "D:\\프로젝트\\java_mission1\\files\\";
            String basePath = "\\files\\";
            String[] saveArrayFilename = getNewSaveFile(baseLocalPath, basePath, originalFilename);
            try{
                File newFile = new File(saveArrayFilename[0]);
                FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(newFile));
            }
            catch (IOException e){
                log.info("#################");
                log.info(e.getMessage());
            }
            adminBannerInput.setFilename(saveArrayFilename[0]);
            adminBannerInput.setUrlFilename(saveArrayFilename[1]);
        }
        boolean editMode = request.getRequestURI().contains("/edit.do");
        if(editMode){
            long id = adminBannerInput.getId();
            AdminBannerDto existBanner = adminBannerService.getById(id);
            if(existBanner == null){
                model.addAttribute("message", "배너정보가 없습니다.");
                return "common/error";
            }
            boolean result = adminBannerService.set(adminBannerInput);
        }
        else{
            boolean result = adminBannerService.add(adminBannerInput);
        }
    
        return "redirect:/admin/banner/list.do";
    }

    private String[] getNewSaveFile(String baseLocalPath, String baseUrlPath, String originalFilename) {

        LocalDate now = LocalDate.now();
        String[] dirs = {
                String.format("%s%d\\", baseLocalPath, now.getYear()),
        String.format("%s%d\\%02d\\", baseLocalPath, now.getYear(), now.getMonthValue()),
        String.format("%s%d\\%02d\\%02d\\", baseLocalPath, now.getYear(), now.getMonth().getValue(), LocalDate.now().getDayOfYear())
        };

        String urlDir = String.format("%s%d\\%02d\\%02d\\", baseUrlPath, now.getYear(), now.getMonth().getValue(), LocalDate.now().getDayOfYear());

        for(String dir : dirs){
            File file = new File(dir);
            if(!file.isDirectory()){
                file.mkdir();
            }
        }
        String fileExtension = "";
        if(originalFilename != null){
            int dotPos = originalFilename.lastIndexOf(".");
            if(dotPos > -1){
                fileExtension = originalFilename.substring(dotPos + 1);
            }
        }
        String uuid = UUID.randomUUID().toString().replace("-","");
        String newFilename = String.format("%s%s", dirs[2], uuid);
        String newUrlFilename = String.format("%s%s", urlDir, uuid);
        if(fileExtension.length() > 0){
            newFilename += "." + fileExtension;
            newUrlFilename += "." + fileExtension;
        }
        return new String[]{newFilename, newUrlFilename};
    }

    @PostMapping("/admin/banner/delete.do")
    public String del(Model model, HttpServletRequest request, AdminBannerInput adminBannerInput){
        boolean result = adminBannerService.delete(adminBannerInput.getIdList());
        return "redirect:/admin/banner/list.do";
    }
    
}
