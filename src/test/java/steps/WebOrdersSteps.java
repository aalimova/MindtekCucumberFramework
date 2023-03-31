package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import pages.WebOrdersHomePage;
import pages.WebOrdersLoginPage;
import pages.WebOrdersOrderModulePage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

public class WebOrdersSteps {

    WebDriver driver = Driver.getDriver();
    WebOrdersLoginPage loginPage = new WebOrdersLoginPage();
    WebOrdersHomePage homePage = new WebOrdersHomePage();
    WebOrdersOrderModulePage modulePage = new WebOrdersOrderModulePage();



    @When("User enters username {string} and password {string} and click on login button")
    public void user_enters_username_and_password_and_click_on_login_button(String username, String password) {
        loginPage.username.sendKeys(username);
        loginPage.password.sendKeys(password);
        loginPage.loginButton.click();
    }

    @Then("User validate application is logged in")
    public void user_validate_application_is_logged_in() {

        String expectedTitle = "Web Orders";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);

    }

    @Then("User validate an error message {string}")
    public void userValidateAnErrorMessage(String errorMessage) {
        String actualMessage = loginPage.errorMessage.getText();
        Assert.assertEquals(actualMessage, errorMessage);

    }
    @When("User clicks on Order module")
    public void user_clicks_on_order_module() {
        homePage.orderModuleBtn.click();

    }

    @When("User selects {string} with {int}")
    public void userSelectsWithQuantity(String product, int quantity) {
        BrowserUtils.selectDropDownByValue(modulePage.productDropDown,product);
        modulePage.quantityBox.sendKeys(Keys.CONTROL +"A");
        modulePage.quantityBox.sendKeys(Keys.BACK_SPACE);
        modulePage.quantityBox.sendKeys(quantity+"" + Keys.ENTER);
    }

    @Then("User validates total is calculated properly for {int}")
    public void user_validates_total_is_calculated_properly_for_quantity(int quantity) {
        int total = 0;

        String pricePerUnit = modulePage.priceBox.getAttribute("value");
        String discountAmount = modulePage.discountBox.getAttribute("value");

        int pricePerUnitInt = Integer.parseInt(pricePerUnit);
        int discountAmmountInt =Integer.parseInt(discountAmount);
    }

    @When("User creates  order with data")
    public void user_creates_order_with_data(DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
//        List<Map<String,String>> listData = dataTable.asMaps(String.class,String.class);
//        System.out.println(listData.get(0).get("zip"));
//        System.out.println(listData.toString());

        Map<String,String> data = dataTable.asMap(String.class, String.class);
        System.out.println(data.toString());

        BrowserUtils.selectDropDownByValue(modulePage.productDropDown, data.get("product"));
        modulePage.quantityBox.sendKeys(data.get("quantity"));
        modulePage.nameBox.sendKeys(data.get("name"));
        modulePage.streetBox.sendKeys(data.get("street"));
        modulePage.cityBox.sendKeys(data.get("city"));
        modulePage.stateBox.sendKeys(data.get("state"));
        modulePage.zipCode.sendKeys(data.get("zip"));
        modulePage.visaRadioBtn.click();
        modulePage.ccBox.sendKeys(data.get("cc"));
        modulePage.expireDate.sendKeys(data.get("expire date"));
        modulePage.processBtn.click();

    }

    @Then("User validates success message {string}")
    public void userValidatesSuccessMessage(String expectedMessage) {

//        String actualMessage = modulePage.successMessage.getText();
//        Assert.assertEquals(expectedMessage, actualMessage);
    }
}