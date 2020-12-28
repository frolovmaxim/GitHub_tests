


public class ExcelUtilsTest {

    public static void main(String[] args) {

        ExcelUtils excel = new ExcelUtils("./data/TestData.xlsx", "Sheet1");

        excel.getRowCount();
        excel.getCellData(1, 0);
    }
}

