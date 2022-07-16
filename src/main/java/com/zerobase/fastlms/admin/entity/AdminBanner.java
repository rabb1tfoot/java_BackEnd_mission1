package com.zerobase.fastlms.admin.entity;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class AdminBanner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String bannerName;
    String linkAddress;
    String openMethod;
    long   sortOrder;
    String strPublic;
    LocalDateTime regDt;

    String filename;
    String urlFilename;

}
