package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.admin.entity.AdminBanner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.Banner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminBannerDto {

    Long id;
    String bannerName;
    String linkAddress;
    String openMethod;
    long   sortOrder;
    String strPublic;
    LocalDateTime regDt;

    String filename;
    String urlFilename;

    long seq;

    
    public static AdminBannerDto of(AdminBanner adminBanner) {
        return AdminBannerDto.builder()
                .id(adminBanner.getId())
                .bannerName(adminBanner.getBannerName())
                .linkAddress(adminBanner.getLinkAddress())
                .openMethod(adminBanner.getOpenMethod())
                .sortOrder(adminBanner.getSortOrder())
                .strPublic(adminBanner.getStrPublic())
                .regDt(adminBanner.getRegDt())
                .filename(adminBanner.getFilename())
                .urlFilename(adminBanner.getUrlFilename())
                .build();
    }


    public static List<AdminBannerDto> of(List<AdminBanner> adminBanners) {
        if (adminBanners != null) {
            List<AdminBannerDto> adminBannerList = new ArrayList<>();
            int i = 1;
            for(AdminBanner x : adminBanners) {
                adminBannerList.add(of(x));
                adminBannerList.get(adminBannerList.size() - 1).setSeq(i);
                i++;
            }
            return adminBannerList;
        }

        return null;
    }
}
