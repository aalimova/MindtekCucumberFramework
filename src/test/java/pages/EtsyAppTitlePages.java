package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class EtsyAppTitlePages {

    public EtsyAppTitlePages(){
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1[@class=\"wt-text-heading-01 wt-display-inline wt-mb-xs-2 wt-overflow-hidden\"]")
    public WebElement jaHeader;

    @FindBy(xpath = "//h1[contains(text(),'Clothing & Shoes')]")
    public WebElement csHeader;

    @FindBy(xpath = "//h1[contains(text(),'Home & Living')]")
    public WebElement hlHeader;

    @FindBy(xpath = "//h1[contains(text(),'Wedding & Party')]")
    public WebElement wpHeader;

    @FindBy(xpath = "//h1[contains(text(),'Toys & Entertainment')]")
    public WebElement teHeader;

    @FindBy(xpath = "//h1[contains(text(),'Art & Collectibles')]")
    public WebElement acHeader;

    @FindBy(xpath = "//h1[contains(text(),'Craft Supplies & Tools')]")
    public WebElement cstHeader;

    @FindBy(xpath = "//h1[contains(text(),'The Etsy Gift Guide')]")
    public WebElement ggHeader;
}
