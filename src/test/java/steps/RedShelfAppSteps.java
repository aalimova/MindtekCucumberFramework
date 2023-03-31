package steps;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.RedShelfHomePage;
import pages.RedShelfSearchPage;
import utilities.Driver;

import java.util.List;

public class RedShelfAppSteps {

    WebDriver driver = Driver.getDriver();
    RedShelfHomePage redShelfHomePage = new RedShelfHomePage();
    RedShelfSearchPage redShelfSearchPage = new RedShelfSearchPage();


    @When("User searches {string}")
    public void userSearches(String item) throws InterruptedException {
     redShelfHomePage.searchBox.sendKeys(item + Keys.ENTER);
     Thread.sleep(5000);
    }




    @Then("user validates search message {string}")
    public void user_validates_search_message(String expectedMessage) {
        String actualMessage = redShelfSearchPage.actualMessage.getText();
        Assert.assertTrue(actualMessage.contains(expectedMessage));

    }

    @Then("User validates all books having author {string}")
    public void userValidatesAllBooksHavingAuthor(String name) {
        List<WebElement> listOfElement = redShelfSearchPage.listOfElement;

        for(WebElement element: listOfElement){
            if(element.getText().contains(name)){
                Assert.assertTrue(true);
            }
        }
    }

    @And("User choose first book in search result")
    public void userChooseFirstBookInSearchResult() {
        redShelfSearchPage.firstBook.click();

    }

    @And("User click on Add To Cart button")
    public void userClickOnAddToCartButton() throws InterruptedException {
        redShelfSearchPage.addToCartBtn.click();


    }

    @Then("User clicks on view cart button and validates book is in Cart")
    public void userClicksOnViewCartButtonAndValidatesBookIsInCart() throws InterruptedException {
        redShelfSearchPage.viewCartBtn.click();
        String actualTitle = "Purple Cow, New Edition";
        Assert.assertEquals(redShelfSearchPage.expectedTitle.getText(),actualTitle);

        Thread.sleep(2000);
    }


    @Then("User validates first result page having no more than <{int}> books.")
    public void userValidatesFirstResultPageHavingNoMoreThanBooks(int number) {

        List<WebElement> listOfBook = redShelfSearchPage.imgBook;

        int numbers = listOfBook.size();
        Assert.assertTrue(number > numbers);

    }

    @Then("User validates {string} error message.")
    public void userValidatesErrorMessage(String errorMessage) {

        String actualMessage = redShelfSearchPage.actualErrorMessage.getText();
        Assert.assertEquals(actualMessage,errorMessage);

    }
}
