package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class EtsyAppHomePage {

    public EtsyAppHomePage(){
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "global-enhancements-search-query")
    public WebElement serchBox;

    @FindBy(id="catnav-primary-link-10855")
    public WebElement jewelryAndAccessories;

    @FindBy(id = "catnav-primary-link-10923")
    public WebElement clothingAndShoes;

    @FindBy(id = "catnav-primary-link-891")
    public WebElement  homeAndLiving;

    @FindBy(xpath = "//span[@id='catnav-primary-link-10983']")
    public WebElement weddingAndParty;

    @FindBy(id = "catnav-primary-link-11049")
    public WebElement toysAndEnt;

    @FindBy(id = "catnav-primary-link-66")
    public WebElement artAndColl;

    @FindBy(id = "catnav-primary-link-562")
    public WebElement craftAndSupp;

    @FindBy(id = "catnav-primary-link--10")
    public WebElement giftsAndCards;


}
