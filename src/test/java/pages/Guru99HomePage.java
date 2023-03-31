package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class Guru99HomePage {

    public Guru99HomePage(){
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }
    @FindBy(tagName = "h3")
    public WebElement actualMessage;

    @FindBy(linkText = "Payment Gateway Project")
    public WebElement sectionPayment;

    @FindBy(linkText = "Telecom Project")
    public WebElement telecomSection;

}
