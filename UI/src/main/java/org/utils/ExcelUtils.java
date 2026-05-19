package org.utils;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {
    public static Object[][] getTestData(String fileName, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream("src" + File.separator + "test" + File.separator + "resources" + File.separator + fileName + ".xlsx");
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        //for maximum row: for object[][] size
        int maxRows = sheet.getPhysicalNumberOfRows();
        int maxCols = sheet.getRow(0).getLastCellNum();

        //{{"user1", "pwd1"}, {"user2", "pwd2"}}
        Object[][] testData = new Object[maxRows][maxCols];

        for (int i = 0; i < maxRows; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < maxCols; j++) {
                Cell cell = row.getCell(j);
                testData[i][j] = cell.toString();
            }
        }
        return testData;
    }
}
