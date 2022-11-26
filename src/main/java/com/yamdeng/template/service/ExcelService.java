package com.yamdeng.template.service;

import com.yamdeng.template.annotation.ExcelColumnInfo;
import com.yamdeng.template.dto.ExcelDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ExcelService {

    private static final int DEFAULT_START_ROW_NUMBER = 1;
    private static final int DEFAULT_START_COLUMN_NUMBER = 1;
    private static final int DEFAULT_COLUMN_WIDTH = 2500;
    private static final short DEFAULT_HEADER_ROW_HEIGHT = 500;

    public void createExcelSheetByMap(XSSFWorkbook xssfWorkbook, String sheetName,
                                      List<Map<String, Object>> rowList, List<String> headerLabelList, List<String> keyList) {
        try {

            // 시트 생성
            XSSFSheet sheet = xssfWorkbook.createSheet(sheetName);
            // 엑셀 시작 행
            int startRowNumber = DEFAULT_START_ROW_NUMBER;
            // 엑셀 시작 열
            int startColumnNumber = DEFAULT_START_COLUMN_NUMBER;

            // header row make
            XSSFRow headerRow = sheet.createRow(startRowNumber++);

            // 헤더 스타일 생성 및 row에 반영하기
            XSSFCellStyle headerRowStyle = xssfWorkbook.createCellStyle();
            headerRowStyle.setBorderBottom(BorderStyle.MEDIUM);
            headerRowStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            headerRowStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerRowStyle.setAlignment(HorizontalAlignment.CENTER);
            headerRowStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            XSSFFont headerRowFont = xssfWorkbook.createFont();
            headerRowFont.setBold(true);
            headerRowStyle.setFont(headerRowFont);
            headerRow.setHeight(DEFAULT_HEADER_ROW_HEIGHT);

            // dto의 filed 수만큼 반복함 : 헤더의 라벨용 cell을 생성하기 위한 반복
            for(int index = 0; index < headerLabelList.size(); index++) {
                String columnName = headerLabelList.get(index);
                // 열의 기본 넓이
                int columnWidth = DEFAULT_COLUMN_WIDTH;
                // 각 헤더의 cell 생성하기
                sheet.setColumnWidth(startColumnNumber, columnWidth);
                Cell cell = headerRow.createCell(startColumnNumber++);
                cell.setCellValue(columnName);
                cell.setCellStyle(headerRowStyle);
            }

            startColumnNumber = DEFAULT_START_COLUMN_NUMBER;

            if(rowList == null || rowList.size() == 0) {
                return;
            }

            // 데이터의 수만큼 반복함
            for(Map<String, Object> rowInfo : rowList) {
                Row dataRow = sheet.createRow(startRowNumber++);
                for(int keyIndex = 0; keyIndex < keyList.size(); keyIndex++) {
                    String key = keyList.get(0);
                    Object fieldValue = rowInfo.get(key);
                    Cell cell = dataRow.createCell(startColumnNumber++);
                    if(fieldValue instanceof String) {
                        cell.setCellValue((String)fieldValue);
                    } else if(fieldValue instanceof Integer) {
                        cell.setCellValue((Integer)fieldValue);
                    } else if(fieldValue instanceof Double) {
                        cell.setCellValue((Double)fieldValue);
                    } else {
                        cell.setCellValue(fieldValue.toString());
                    }
                }
                startColumnNumber = DEFAULT_START_COLUMN_NUMBER;
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void createExcelSheet(XSSFWorkbook xssfWorkbook, String sheetName,
                                  List<?> rowList) {

        try {
            Class excelDtoClass = null;
            if(rowList != null && rowList.size() > 0) {
                excelDtoClass = rowList.get(0).getClass();
            }
            createExcelSheet(xssfWorkbook, sheetName, rowList, excelDtoClass);
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void createExcelSheet(XSSFWorkbook xssfWorkbook, String sheetName,
                                 List<?> rowList, Class excelDtoClass) {

        try {

            XSSFSheet sheet = xssfWorkbook.createSheet(sheetName);
            if(excelDtoClass == null){
                return;
            }
            Field[] fields = excelDtoClass.getDeclaredFields();
            int startRowNumber = DEFAULT_START_ROW_NUMBER;
            int startColumnNumber = DEFAULT_START_COLUMN_NUMBER;

            // header make
            XSSFRow headerRow = sheet.createRow(startRowNumber++);

            XSSFCellStyle headerRowStyle = xssfWorkbook.createCellStyle();
            headerRowStyle.setBorderBottom(BorderStyle.MEDIUM);
            headerRowStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            headerRowStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerRowStyle.setAlignment(HorizontalAlignment.CENTER);
            headerRowStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            XSSFFont headerRowFont = xssfWorkbook.createFont();
            headerRowFont.setBold(true);
            headerRowStyle.setFont(headerRowFont);
            headerRow.setHeight(DEFAULT_HEADER_ROW_HEIGHT);

            for(Field field : fields) {
                field.setAccessible(true);
                boolean existExcelColumnInfoAnnotation = field.isAnnotationPresent(ExcelColumnInfo.class);
                String columnName = "";
                int columnWidth = DEFAULT_COLUMN_WIDTH;
                if(existExcelColumnInfoAnnotation) {
                    ExcelColumnInfo excelColumnInfo = field.getAnnotation(ExcelColumnInfo.class);
                    columnName = excelColumnInfo.columnName();
                    columnWidth = excelColumnInfo.columnWidth();
                } else {
                    columnName = field.getName();
                }
                sheet.setColumnWidth(startColumnNumber, columnWidth);
                Cell cell = headerRow.createCell(startColumnNumber++);
                cell.setCellValue(columnName);
                cell.setCellStyle(headerRowStyle);
            }

            startColumnNumber = DEFAULT_START_COLUMN_NUMBER;

            if(rowList == null || rowList.size() == 0) {
                return;
            }

            for(Object rowInfo : rowList) {
                Row dataRow = sheet.createRow(startRowNumber++);
                for(Field field : fields) {
                    Object fieldValue = field.get(rowInfo);
                    Cell cell = dataRow.createCell(startColumnNumber++);
                    if(fieldValue instanceof String) {
                        cell.setCellValue((String)fieldValue);
                    } else if(fieldValue instanceof Integer) {
                        cell.setCellValue((Integer)fieldValue);
                    } else if(fieldValue instanceof Double) {
                        cell.setCellValue((Double)fieldValue);
                    } else {
                        cell.setCellValue(fieldValue.toString());
                    }
                }
                startColumnNumber = DEFAULT_START_COLUMN_NUMBER;
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void downloadExcelFile(HttpServletRequest request, HttpServletResponse response,
                                  XSSFWorkbook xssfWorkbook, String fileName) {
        try
        {

            // 여기서부터는 각 브라우저에 따른 파일이름 인코딩작업
            String browser = request.getHeader("User-Agent");
            if (browser.indexOf("MSIE") > -1) {
                fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
            } else if (browser.indexOf("Trident") > -1) {       // IE11
                fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
            } else if (browser.indexOf("Firefox") > -1) {
                fileName = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1") + "\"";
            } else if (browser.indexOf("Opera") > -1) {
                fileName = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1") + "\"";
            } else if (browser.indexOf("Chrome") > -1) {
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < fileName.length(); i++) {
                    char c = fileName.charAt(i);
                    if (c > '~') {
                        sb.append(URLEncoder.encode("" + c, "UTF-8"));
                    } else {
                        sb.append(c);
                    }
                }
                fileName = sb.toString();
            } else if (browser.indexOf("Safari") > -1){
                fileName = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1")+ "\"";
            } else {
                fileName = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1")+ "\"";
            }

            response.setContentType("application/download;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
            response.setHeader("Content-Transfer-Encoding", "binary");

            OutputStream out = response.getOutputStream();
            xssfWorkbook.write(out);
            out.close();
            xssfWorkbook.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
