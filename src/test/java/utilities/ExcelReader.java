package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelReader {

    public static List<Map<String, String>> getExcelData(String filePath, String sheetName) {
        List<Map<String, String>> dataList = new ArrayList<>();
        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0);
            int columnCount = headerRow.getLastCellNum();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Start from row 1 (skip headers)
                Row row = sheet.getRow(i);
                Map<String, String> dataMap = new HashMap<>();

                for (int j = 0; j < columnCount; j++) {
                    Cell headerCell = headerRow.getCell(j);
                    Cell valueCell = row.getCell(j);
                    String header = headerCell.getStringCellValue();
                    String value = (valueCell == null) ? "" : valueCell.toString();
                    dataMap.put(header, value);
                }
                dataList.add(dataMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public static Map<String, String> getTestData(String filePath, String sheetName, String testCase) {
        List<Map<String, String>> dataList = getExcelData(filePath, sheetName);
        for (Map<String, String> data : dataList) {
            if (data.get("testcase").equalsIgnoreCase(testCase)) {
                return data;
            }
        }
        return null; // If test case not found
    }
}