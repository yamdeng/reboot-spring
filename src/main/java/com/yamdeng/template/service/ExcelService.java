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

@Service
public class ExcelService {

    private static final int DEFAULT_START_ROW_NUMBER = 1;
    private static final int DEFAULT_START_CELL_NUMBER = 1;
    private static final int DEFAULT_COLUMN_WIDTH = 2500;
    private static final short DEFAULT_HEADER_ROW_HEIGHT = 500;

    private void crateExcelSheet(XSSFWorkbook xssfWorkbook, String sheetName,
                                 List<?> rowList, Class excelDtoClass) {
        try {

            XSSFSheet sheet = xssfWorkbook.createSheet(sheetName);
            Field[] fields = excelDtoClass.getDeclaredFields();
            int startRowNumber = DEFAULT_START_ROW_NUMBER;
            int startCellNumber = DEFAULT_START_CELL_NUMBER;

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
                sheet.setColumnWidth(startCellNumber, columnWidth);
                Cell cell = headerRow.createCell(startCellNumber++);
                cell.setCellValue(columnName);
                cell.setCellStyle(headerRowStyle);
            }

            startCellNumber = DEFAULT_START_CELL_NUMBER;

            if(rowList == null || rowList.size() == 0) {
                return;
            }

            for(Object rowInfo : rowList) {
                Row dataRow = sheet.createRow(startRowNumber++);
                for(Field field : fields) {
                    Object fieldValue = field.get(rowInfo);
                    Cell cell = dataRow.createCell(startCellNumber++);
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
                startCellNumber = 1;
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void downloadExcelFile(HttpServletRequest request, HttpServletResponse response, String fileName) {
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
        crateExcelSheet(xssfWorkbook, "test1", list, ExcelDto.class);
        crateExcelSheet(xssfWorkbook, "test2", null, ExcelDto.class);


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

            //
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
