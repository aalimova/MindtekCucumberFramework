package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.PizzaAppPage;
import utilities.BrowserUtils;
import utilities.Driver;

import java.util.List;
import java.util.Map;

public class PizzaAppSteps {

    String costValue = "";

    WebDriver driver = Driver.getDriver();
    PizzaAppPage pizzaAppPage = new PizzaAppPage();


    @When("User creates pizza order with data")
    public void user_creates_pizza_order_with_data(DataTable dataTable) {
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.

        Map<String,String> data = dataTable.asMap(String.class, Object.class);
        for(Object object : data.values()){
            System.out.println(object);
        }

        BrowserUtils.selectDropDownByValue(pizzaAppPage.pizzaDropDown, data.get("Pizza"));
        BrowserUtils.selectDropDownByValue(pizzaAppPage.toppings1DropDown, data.get("Topping1"));
        BrowserUtils.selectDropDownByValue(pizzaAppPage.toppings2DropDown, data.get("Topping2"));
        pizzaAppPage.pizzaQuantity.clear();
        pizzaAppPage.pizzaQuantity.sendKeys(data.get("Quantity"));
        costValue = pizzaAppPage.pizzaCostBox.getAttribute("value");
        pizzaAppPage.nameBox.sendKeys(data.get("Name"));
        pizzaAppPage.emailBox.sendKeys(data.get("Email"));
        pizzaAppPage.phoneBox.sendKeys(data.get("Phone"));
        pizzaAppPage.cashOnPickupRadioBtn.click();
        if(data.get("Payment").equals("Cash on Pickup")) {
            pizzaAppPage.placeOrderBtn.click();
        }
        else {
            pizzaAppPage.creditCardRadioButton.click();
        }
    }



    @Then("User validates that order is created with message {string} {string} {string}")
    public void userValidatesThatOrderIsCreatedWithMessage(String success, String quantity, String pizza) {
        String expectedMessage = success + costValue + " "+pizza;
        String actualMessage = pizzaAppPage.dialogMessage.getText();
        System.out.println(actualMessage);

        Assert.assertEquals(expectedMessage,actualMessage);
    }
}
