package com.zerobase.fastlms.admin.model;

import lombok.Data;

@Data
public class AdminBannerInput {

    long id;
    String bannerName;
    String LinkAddress;
    String OpenMethod;
    long   sortOrder;
    String strPublic;

    String idList;

    String filename;
    String urlFilename;
}
