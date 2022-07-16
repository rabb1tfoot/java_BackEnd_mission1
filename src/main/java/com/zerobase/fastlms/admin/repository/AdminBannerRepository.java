package com.zerobase.fastlms.admin.repository;

import com.zerobase.fastlms.admin.entity.AdminBanner;
import org.springframework.boot.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface AdminBannerRepository extends JpaRepository<AdminBanner, Long> {
    Optional<AdminBanner> findByBannerName(String bannerName);
}
