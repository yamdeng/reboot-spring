package com.yamdeng.template.controller;

import com.yamdeng.template.common.DBMessageSource;
import com.yamdeng.template.common.LogMode;
import com.yamdeng.template.common.MessageSourceService;
import com.yamdeng.template.properties.BasicDataSourceProperties;
import com.yamdeng.template.properties.SecondDataSourceProperties;
import com.yamdeng.template.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/download")
public class DownloadController {

    @Autowired
    private ExcelService excelService;
    @GetMapping("/excel")
    public void downloadExcel(HttpServletRequest request, HttpServletResponse response) {
//        excelService.downloadExcelFile(request, response, "test2.xlsx");
    }

}
