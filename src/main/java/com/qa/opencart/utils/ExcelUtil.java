package com.qa.opencart.utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtil {
    private static final String TEST_DATA_SHEET_PATH = "./src/test/resources/TestData/Registration Data.xlsx";
    private static Workbook book;// This is workbook reference which is used to read data from excel
    private static Sheet sheet;

    public static Object[][] getTestData(String sheetName) throws FileNotFoundException {
        Object data [][] = null;
        // TODO Auto-generated method stub
        FileInputStream ip= new FileInputStream(TEST_DATA_SHEET_PATH);
        try {
            book=WorkbookFactory.create(ip);
            sheet=book.getSheet(sheetName);
            data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()]; // this will count the number of rows and columns

            for (int i = 0; i < sheet.getLastRowNum(); i++) {
                for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
                    data[i][j]=sheet.getRow(i+1).getCell(j).toString();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;


    }
}
