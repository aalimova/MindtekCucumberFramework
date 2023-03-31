package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class RedShelfSearchPage {

    public RedShelfSearchPage(){
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='product-search-result-author']")
    public List<WebElement> listOfElement;

    @FindBy(xpath = "//h1[@class='mdc-typography--headline4']")
    public WebElement actualMessage;

    @FindBy(xpath = "//a[@id='ember68']")
    public WebElement firstBook;

    @FindBy(xpath = "//span[@class='mdc-button__ripple']")
    public WebElement addToCartBtn;

    @FindBy(xpath = "//span[@class='mdc-button__ripple']")
    public WebElement viewCartBtn;

    @FindBy(xpath = "//a[@class='title']")
    public WebElement expectedTitle;

    @FindBy(xpath = "//div[@class='product-search-result-title']")
    public List<WebElement> imgBook;

    @FindBy(xpath = "//div[@class='search-page-empty__text-content mdc-typography--body1']")
    public WebElement actualErrorMessage;


}
