package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ma;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import pages.*;
import utilities.BrowserUtils;
import utilities.Driver;

import java.util.List;
import java.util.Map;


public class Guru99Steps {

    WebDriver driver = Driver.getDriver();
    Guru99LoginPage loginPage = new Guru99LoginPage();
    Guru99HomePage homePage = new Guru99HomePage();
    Guru99PaymentGatewayPage paymentGatewayPage = new Guru99PaymentGatewayPage();
    Guru99PaymentProcessPage paymentProcessPage = new Guru99PaymentProcessPage();
    Guru99PaymentPage paymentPage = new Guru99PaymentPage();
    Guru99TelecomProjectPage telecomProjectPage = new Guru99TelecomProjectPage();
    Guru99AddCustomerPage addCustomerPage = new Guru99AddCustomerPage();

    @When("User enters username {string} and password {string}")
    public void user_enters_username_and_password(String username, String password) {
    loginPage.emailBox.sendKeys(username);
    loginPage.passwordBox.sendKeys(password);
    }

    @When("When user click on login")
    public void when_user_click_on_login() {
    loginPage.submitBtn.click();
    }
    @Then("Then user validates success message {string}")
    public void then_user_validates_success_message(String expectedMessage) {
    String actualMessage = homePage.actualMessage.getText();
        Assert.assertEquals(expectedMessage,actualMessage);
    }
    @And("User click on {string}  section")
    public void userClickOnSection(String section) {
        if(section.equals("Payment Gateway Project")){
            homePage.sectionPayment.click();
        }
        if(section.equals("Telecom Project")){
            homePage.telecomSection.click();
        }

    }
    @When("User clicks on BUY NOW button for {string} toy")
    public void user_clicks_on_buy_now_button_for_toy(String quantity) {
        BrowserUtils.selectDropDownByValue(paymentGatewayPage.quantityDropDown,quantity);
        paymentGatewayPage.buyNowBtn.click();
    }
    @Then("User validates {string} in new opened page")
    public void userValidatesInNewOpenedPage(String expectedMessage) {
        SoftAssert softAssert = new SoftAssert();
        String actualMessage = paymentProcessPage.actualMessage.getText();
        softAssert.assertEquals(expectedMessage,actualMessage);
        softAssert.assertAll();
    }

    @When("User provides random payment information and clicks on PAY button")
    public void user_provides_random_payment_information_and_clicks_on_pay_button(DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        Map<String,String> data = dataTable.asMap(String.class, Object.class);
        for(Object object : data.values()){
            System.out.println(object);
        }
        paymentGatewayPage.cardNumberBox.sendKeys(data.get("Card Number"));
        BrowserUtils.selectDropDownByValue(paymentGatewayPage.monthDropDown,data.get("Expiration Month"));
        BrowserUtils.selectDropDownByValue(paymentGatewayPage.yearDropDown, data.get("Expiration Year"));
        paymentGatewayPage.cvvBox.sendKeys(data.get("CVV Code"));
        paymentGatewayPage.buyNowBtn.click();

    }
    @Then("user validates success message {string}")
    public void user_validates_success_message(String expectedMessage) {
       String actualMessage = paymentPage.actualMessage.getText();
       Assert.assertEquals(expectedMessage,actualMessage);

    }

    @When("User clicks on Add Customer button")
    public void user_clicks_on_add_customer_button() {
        telecomProjectPage.addCustomerBtn.click();
        addCustomerPage.radioDoneBtn.click();

    }

    @When("User creates customer with random data and clicks submit button")
    public void user_creates_customer_with_random_data_and_clicks_submit_button(DataTable dataTable) {
      List<List<String>> data = dataTable.cells();
        addCustomerPage.radioDoneBtn.click();
        addCustomerPage.firstNameBox.sendKeys(data.get(0).get(0));
        addCustomerPage.lastNameBox.sendKeys(data.get(0).get(1));
        addCustomerPage.emailBox.sendKeys(data.get(0).get(2));
        addCustomerPage.addressBox.sendKeys(data.get(0).get(3));
        addCustomerPage.mobileNumberBox.sendKeys(data.get(0).get(4));
        addCustomerPage.submitBtn.click();

    }

    @Then("User validates customer is created and Customer ID is generated")
    public void user_validates_customer_is_created_and_customer_id_is_generated() {
        boolean customerID = addCustomerPage.customerId.isDisplayed();
        Assert.assertTrue(customerID);

    }

    @And("User creates customer with random data and clicks reset button")
    public void userCreatesCustomerWithRandomDataAndClicksResetButton(DataTable dataTable) {
        List<List<String>> data = dataTable.cells();
        addCustomerPage.radioDoneBtn.click();
        addCustomerPage.firstNameBox.sendKeys(data.get(0).get(0));
        addCustomerPage.lastNameBox.sendKeys(data.get(0).get(1));
        addCustomerPage.emailBox.sendKeys(data.get(0).get(2));
        addCustomerPage.addressBox.sendKeys(data.get(0).get(3));
        addCustomerPage.mobileNumberBox.sendKeys(data.get(0).get(4));
        addCustomerPage.resetBtn.click();
    }
    @Then("User validates all input fields are cleared")
    public void userValidatesAllInputFieldsAreCleared() {
        Assert.assertEquals(false,addCustomerPage.radioDoneBtn.isSelected());


    }

    @Then("User validate error alert message {string}")
    public void userValidateErrorAlertMessage(String exceptedMessage) {
        Alert alert = driver.switchTo().alert();
        String actualMessage = alert.getText();
        System.out.println(actualMessage);
        Assert.assertEquals(exceptedMessage,actualMessage);


    }
}
