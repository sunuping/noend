package com.sunup.noend.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class ExcelUtil {

    public static Workbook getWorkbook(MultipartFile file) throws IOException {
        Workbook workbook = null;
        InputStream in = file.getInputStream();
        String fileExtension = file.getName().substring(file.getName().lastIndexOf("."));
        if (".xls".equals(fileExtension)) {
            workbook = new HSSFWorkbook(in);
        } else if (".xlsx".equals(fileExtension)) {
            workbook = new XSSFWorkbook(in);
        }
        return workbook;
    }

    public static String[][] read(MultipartFile file) throws IOException {
        String[][] table = null;
        Sheet sheet = getWorkbook(file).getSheetAt(0);
        //获取总行数
        int rows = sheet.getPhysicalNumberOfRows();
        int cols = 0;
        Row row = null;
        Cell cell = null;
        for (int i = 1; i < rows; i++) {
            row = sheet.getRow(i);
            if (row!=null){
                //获取总列数
                cols = 3;

                if (table == null) {
                    table = new String[rows][cols];
                }

                for (int j = 0; j < cols; j++) {
                    cell = row.getCell(j);
                    table[i][j] = cell != null ? cell.toString() : "";
                }
            }

        }
        return table;
    }

}
