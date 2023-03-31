package utilities;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelTest {
    public static void main(String[] args) throws IOException {

        String path = System.getProperty("testdata/Book1.xlsx");
        FileInputStream fileInputStream = new FileInputStream(path);

        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet1 = workbook.getSheet("sheet1");

        String firstName = sheet1.getRow(1).getCell(0).toString();
        System.out.println(firstName);
    }
}
