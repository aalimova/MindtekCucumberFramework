package utilities;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.*;
import java.util.Random;
import java.util.UUID;

public class BrowserUtils {

    /**
     * This method will accept WebElement of dropdown
     * and String value of dropdown
     * provided value in dropdown
     */
    private static FileInputStream inputStream;
    private static FileOutputStream outputStream;
    private static Workbook workbook;
    private static Sheet sheet;
    private static String path;

    public static void openExcelFile(String fileName, String sheetName){
        path = System.getProperty("user.dir") + ""+fileName+".xlsx";

        try {
            inputStream = new FileInputStream(path);
            workbook = new XSSFWorkbook(inputStream);
            sheet = workbook.getSheet(sheetName);
        } catch (FileNotFoundException e) {
            System.out.println("Excel spreadsheet path is invalid" + path);
        }
        catch (IOException e){
            System.out.println("Couldn't open Excel");
        }
    }

    //This method will return data from provided row and cell number
    public static String getValue(int rowNum, int cellNum){
        return sheet.getRow(rowNum).getCell(cellNum).toString();
    }

    public static void setValue(int rowNum, int cellNum, String value){
        if(sheet.getPhysicalNumberOfRows() <= rowNum){
            sheet.createRow(rowNum).createCell(cellNum).setCellValue(value);
        }
        else if(sheet.getRow(rowNum).getPhysicalNumberOfCells() <= cellNum){
            sheet.getRow(rowNum).createCell(cellNum).setCellValue(value);
        }
        else {
            sheet.getRow(rowNum).getCell(cellNum).setCellValue(value);
        }
        try {
            outputStream = new FileOutputStream(path);
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            System.out.println("Excel spreadsheet path is invalid" + path);
        }
        catch (IOException e){
            System.out.println("Couldn't save changes to Excel");
        }
        finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                System.out.println("Couldn't close fileOutputStream object");
            }
        }
    }
    public static void selectDropDownByValue(WebElement element, String value){
        Select select = new Select(element);
        select.selectByValue(value);
    }

    /*
    This method takes screenshots
       Ex:
        takeScreenshot("SauceDemo Test")
     */

    public static void takeScreenshot(String testName) throws IOException {
        WebDriver driver = Driver.getDriver();
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String path = "src/test/resources/screenshots/"+ testName+".png";
        File file = new File(path);

        FileUtils.copyFile(screenshot, file);
    }

    /*
    This method generated random emails
            Ex:
            getRandomMethods() returns testUser123@gmail.com

     */

    public static String getRandomMethods(){
        String username = "testUser";
        Random random = new Random();
        int number = random.nextInt(999_99);
        return username + number+"@gmail.com";
    }

    /*
    This method generated very strong password
      Ex: getRandomStrongPassword
     */

    public static String getRandomStrongPassword(){
      UUID uuid = UUID.randomUUID();
      String uuidStr = uuid.toString();
      return uuidStr;
    }
}
