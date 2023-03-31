package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    private static WebDriver driver;

    /**
      This method sets up WebDriver type based on
      provided browser type in Configuration properties file.
      @return WebDriver
     */

    public static WebDriver getDriver(){
        String browser = ConfigReader.getProperty("browser");
        if(driver==null || ((RemoteWebDriver)driver).getSessionId()==null){
            switch (browser){
                case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
                case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                case "ie":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
            }
        }
            else {
                return driver;
            }


        return driver;
    }


}
