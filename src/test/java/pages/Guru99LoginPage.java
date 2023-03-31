package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class Guru99LoginPage {

    public Guru99LoginPage(){
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "email")
    public WebElement emailBox;

    @FindBy(id = "passwd")
    public WebElement passwordBox;

    @FindBy(id = "SubmitLogin")
    public WebElement submitBtn;
}
