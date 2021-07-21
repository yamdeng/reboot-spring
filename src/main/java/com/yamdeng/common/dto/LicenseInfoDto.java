package com.yamdeng.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LicenseInfoDto {

    private String name;
    private String version;
    private String springVersion;
    private String springBootVersion;

}
