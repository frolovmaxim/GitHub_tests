import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public class ExcelUtils {
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;

    public ExcelUtils(String excelPath, String sheetName){

        try {
            workbook = new XSSFWorkbook(excelPath);
            sheet = workbook.getSheet(sheetName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getCellData(int rowNum, int colNum){
            String value = sheet.getRow(1).getCell(0).getStringCellValue();
            System.out.println(value);

            DataFormatter formatter = new DataFormatter();
            Object value2 = formatter.formatCellValue(sheet.getRow(rowNum).getCell(colNum));
    }

    public void getRowCount(){
            int rowCount = sheet.getPhysicalNumberOfRows();
            System.out.println("No of rows: " + rowCount);
    }
}
