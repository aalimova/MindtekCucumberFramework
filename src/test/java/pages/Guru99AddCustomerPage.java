package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class Guru99AddCustomerPage {

    public Guru99AddCustomerPage(){
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//div[@class='2u 12u$(small)']/label)[1]")
    public WebElement radioDoneBtn;

    @FindBy(xpath = "//input[@name='fname']")
    public WebElement firstNameBox;

    @FindBy(xpath = "//input[@name='lname']")
    public WebElement lastNameBox;

    @FindBy(xpath = "//input[@name='emailid']")
    public WebElement emailBox;

    @FindBy(xpath = "//textarea[@name='addr']")
    public WebElement addressBox;

    @FindBy(xpath = "//input[@name='telephoneno']")
    public WebElement mobileNumberBox;

    @FindBy(xpath = "//input[@type='submit']")
    public WebElement submitBtn;

    @FindBy(xpath = "//table[@class='alt access']//tr/td/h3")
    public WebElement customerId;

    @FindBy(xpath = "//input[@type='reset']")
    public WebElement resetBtn;
}
