import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;


public class ExcelUtils {
    //public XSSFWorkbook workbook;
    //public XSSFSheet sheet;


 /*   public ExcelUtils(String excelPath, String sheetName) {
        try {
            workbook = new XSSFWorkbook(excelPath);
            sheet = workbook.getSheet(sheetName);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/

    public Account getCellData(int rowNumber){

        try {
            String excelFilePath = ".\\users\\users.xlsx";
            FileInputStream file = new FileInputStream(new File(excelFilePath));

            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int columnCount = 0;
            Row row = sheet.getRow(rowNumber);
            Cell cell = row.getCell(columnCount++);
            String username = cell.getStringCellValue();
            Cell cell1 = row.getCell(columnCount++);
            String email = cell1.getStringCellValue();
            Cell cell2 = row.getCell(columnCount);
            String pwd = cell2.getStringCellValue();
            file.close();
            return new Account(email, username, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

/*    public void getRowCount(){
        int rowCount = sheet.getPhysicalNumberOfRows();
        System.out.println("Num of rows: " + rowCount);
    }*/

    public void setCellData(String username, String email, String password){

        String excelFilePath = ".\\users\\users.xlsx";
        try {
            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getLastRowNum() + 1;
            //Sheet newSheet = workbook1.createSheet("Comments2");
            //int rowCount = 0;
            int columnCount = 0;
            Row row = sheet.createRow(rowCount);
            Cell cell = row.createCell(columnCount);
            cell.setCellValue(username);
            Cell cell1 = row.createCell(++columnCount);
            cell1.setCellValue(email);
            Cell cell2 = row.createCell(++columnCount);
            cell2.setCellValue(password);

            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
