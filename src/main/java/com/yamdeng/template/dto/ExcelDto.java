package com.yamdeng.template.dto;

import com.yamdeng.template.annotation.ExcelColumnInfo;
import lombok.Data;

@Data
public class ExcelDto {

    @ExcelColumnInfo(columnName = "이름", columnWidth = 9500)
    private String name;

    @ExcelColumnInfo(columnName = "가격", columnWidth = 3500)
    private Double amount;

    @ExcelColumnInfo(columnName = "나이")
    private Integer age;

    private String desc;

}
