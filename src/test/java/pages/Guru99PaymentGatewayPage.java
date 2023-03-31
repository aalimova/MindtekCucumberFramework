package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class Guru99PaymentGatewayPage {

    public Guru99PaymentGatewayPage(){
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }
    @FindBy(name = "quantity")
    public WebElement quantityDropDown;

    @FindBy(xpath = "//input[@type='submit']")
    public WebElement buyNowBtn;

    @FindBy(id = "card_nmuber")
    public WebElement cardNumberBox;

    @FindBy(id = "month")
    public WebElement monthDropDown;

    @FindBy(id = "year")
    public WebElement yearDropDown;

    @FindBy(id = "cvv_code")
    public WebElement cvvBox;

    @FindBy(xpath = "//input[@type='submit']")
    public WebElement submitBtn;
}
