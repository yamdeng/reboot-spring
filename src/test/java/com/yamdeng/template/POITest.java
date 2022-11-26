package com.yamdeng.template;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yamdeng.template.annotation.ExcelColumnInfo;
import com.yamdeng.template.dto.ExcelDto;
import com.yamdeng.template.service.ExcelService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

public class POITest {

    @Test
    public void test1() {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        List<ExcelDto> list = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            ExcelDto excelDto = new ExcelDto();
            excelDto.setAge(i + 10);
            excelDto.setAmount(10.0 + i * 0.5);
            excelDto.setName("yamdeng" + i );
            excelDto.setDesc("요약 : " + i);
            list.add(excelDto);
        }

        ExcelService excelService = new ExcelService();
//        excelService.createExcelSheet(xssfWorkbook, "test10", list, ExcelDto.class);
        excelService.createExcelSheet(xssfWorkbook, "test100", list);

        try
        {
            FileOutputStream out = new FileOutputStream(new File("test.xlsx"));
            xssfWorkbook.write(out);
            out.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        List<ExcelDto> list = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            ExcelDto excelDto = new ExcelDto();
            excelDto.setAge(i + 10);
            excelDto.setAmount(10.0 + i * 0.5);
            excelDto.setName("yamdeng" + i );
            excelDto.setDesc("요약 : " + i);
            list.add(excelDto);
        }

        List<Map<String, Object>> mapList = new ArrayList<>();
        for(ExcelDto excelDto : list) {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> map = objectMapper.convertValue(excelDto, Map.class);
            mapList.add(map);
        }

        List<String> headerLabelList = new ArrayList<>();
        headerLabelList.add("이름");
        headerLabelList.add("가격");
        headerLabelList.add("나이");
        headerLabelList.add("설명");

        List<String> keyList = new ArrayList<>();
        keyList.add("name");
        keyList.add("amount");
        keyList.add("age");
        keyList.add("desc");

        ExcelService excelService = new ExcelService();
        excelService.createExcelSheetByMap(xssfWorkbook, "test1-1", mapList, headerLabelList, keyList);

        try
        {
            FileOutputStream out = new FileOutputStream(new File("test.xlsx"));
            xssfWorkbook.write(out);
            out.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
