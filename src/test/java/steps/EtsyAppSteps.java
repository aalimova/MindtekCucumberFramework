package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.EtsyAppHomePage;
import pages.EtsyAppSearchResultsPage;
import pages.EtsyAppTitlePages;
import utilities.Driver;

import java.util.*;

public class EtsyAppSteps {

    WebDriver driver = Driver.getDriver();
    EtsyAppHomePage etsyhomePage = new EtsyAppHomePage();
    EtsyAppSearchResultsPage searchResultsPage = new EtsyAppSearchResultsPage();
    EtsyAppTitlePages etsyAppTitlePages = new EtsyAppTitlePages();

    @When("User searches for {string}")
    public void user_searches_for(String item) {
            etsyhomePage.serchBox.sendKeys(item + Keys.ENTER);

    }
    @Then("User validate search result items name contains keyword {string}")
    public void user_validate_search_result_items_contains_keyword(String keyword) {
        List<WebElement> myItemsList = searchResultsPage.listofItemsTitle;

        for (WebElement element: myItemsList){
            if (element.getText().contains(keyword)) {
                Assert.assertTrue("Item doesn't contain" + keyword + element.getText(),
                        element.getText().toLowerCase().contains(keyword));
            }
        }

    }

    @And("User applies price filter {string} dollars")
    public void userAppliesPriceFilterOverDollars(String filterRange) {
        searchResultsPage.allFiltersBtn.click();

        if(filterRange.equals("over 1500")) {
            searchResultsPage.filterRadioButtonOver1500.click();
        }
        else if(filterRange.equals("under 250")){
            searchResultsPage.filterRadioButtonUnder250.click();
        }
        else if(filterRange.equals("250 to 750")){
            searchResultsPage.filterRadioButton250to750.click();
        }
        else if(filterRange.equals("500 to 1000")){
            searchResultsPage.filterRadioButton500to100.click();
        }
        searchResultsPage.applyBtn.click();
    }

    @Then("User validates that item prices are over {string} dollars")
    public void userValidatesThatItemPricesAreOverDollars(String filterRange ) throws InterruptedException{

        if (filterRange.equals("over 1500")) {
            Thread.sleep(3000);
            List<WebElement> prices = searchResultsPage.listOfPrice;
            for (WebElement element : prices) {
                System.out.println(element.getText());
                //String = "2,299.00" --> double = 2299.00
                String priceStr = element.getText().replace(",", ""); //"1920.00"
                double actualPriceDbl = Double.parseDouble(priceStr);//1920.00
                System.out.println(actualPriceDbl);
                String expectedPrice = filterRange.substring(filterRange.indexOf(" ") + 1);
                double expectedPriceDbl = Double.parseDouble(expectedPrice);

                Assert.assertTrue(actualPriceDbl >= expectedPriceDbl); //1920.00 >= 1500.00
            }
        } else if (filterRange.equals("under 250")) {
            Thread.sleep(3000);
//            List<WebElement> prices = searchResultsPage.listOfPrice;
//            for (WebElement element : prices) {
//                System.out.println(element.getText());
//                //String = "2,299.00" --> double = 2299.00
//                String priceStr = element.getText().replace(",", ""); //"1920.00"
//                double actualPriceDbl = Double.parseDouble(priceStr);//1920.00
//                System.out.println(actualPriceDbl);
//                String expectedPrice = filterRange.substring(filterRange.indexOf(" ") + 1);
//                double expectedPriceDbl = Double.parseDouble(expectedPrice);
//                Assert.assertTrue(actualPriceDbl < expectedPriceDbl); //1920.00 >= 250.00
//            }
            List<WebElement> prices = searchResultsPage.listOfPrice;
            for (WebElement ele : prices){
                System.out.println(ele.getText());
            }
            System.out.println("-----------------");
           List<WebElement> pricesDiscount = searchResultsPage.listOfPriceDiscount;
            for (WebElement element : pricesDiscount){
                System.out.println(element.getText());
            }
            System.out.println("----------------");
            List<WebElement> merged = new ArrayList<>();
           merged.addAll(prices);
           merged.addAll(pricesDiscount);
           for (WebElement el : merged){
               System.out.println(el.getText());
           }
            System.out.println("Set Hash Set");
           Set<WebElement> setList = new HashSet<>(merged);
            for (WebElement element : setList) {
                System.out.println(element.getText());
               //String = "2,299.00" --> double = 2299.00
               String priceStr = element.getText().replace(",", ""); //"1920.00"
               double actualPriceDbl = Double.parseDouble(priceStr);//1920.00
                System.out.println("Actual Price : " + actualPriceDbl);
               String expectedPrice = filterRange.substring(filterRange.indexOf(" ") + 1);
              double expectedPriceDbl = Double.parseDouble(expectedPrice);
               Assert.assertTrue(actualPriceDbl < expectedPriceDbl); //1920.00 >= 250.00
            }

        } else if(filterRange.equals("250 to 750")){
            Thread.sleep(3000);
            List<WebElement> prices = searchResultsPage.listOfPrice;;
            for (WebElement element : prices) {
                System.out.println(element.getText());
                //String = "2,299.00" --> double = 2299.00
                String priceStr = element.getText().replace(",", ""); //"1920.00"
                double actualPriceDbl = Double.parseDouble(priceStr);//1920.00
                System.out.println(actualPriceDbl);
                String expectedPrice1 = filterRange.substring(0,filterRange.indexOf(" "));//250
                double expectedPriceDbl1 = Double.parseDouble(expectedPrice1);
                String expectedPrice2 = filterRange.substring(filterRange.lastIndexOf(" ")+1);//750
                double expectedPriceDbl2 = Double.parseDouble(expectedPrice2);
                Assert.assertTrue(actualPriceDbl >= expectedPriceDbl1 && actualPriceDbl<=expectedPriceDbl2); //1920.00 >= 250.00
            }
        } else if(filterRange.equals("500 to 1000")){
            Thread.sleep(3000);
            List<WebElement> prices = searchResultsPage.listOfPrice;;
            for (WebElement element : prices) {
                System.out.println(element.getText());
                //String = "2,299.00" --> double = 2299.00
                String priceStr = element.getText().replace(",", ""); //"1920.00"
                double actualPriceDbl = Double.parseDouble(priceStr);//1920.00
                System.out.println(actualPriceDbl);
                String expectedPrice1 = filterRange.substring(0,filterRange.indexOf(" "));//250
                double expectedPriceDbl1 = Double.parseDouble(expectedPrice1);
                String expectedPrice2 = filterRange.substring(filterRange.lastIndexOf(" ")+1);//750
                double expectedPriceDbl2 = Double.parseDouble(expectedPrice2);
                Assert.assertTrue(actualPriceDbl >= expectedPriceDbl1 && actualPriceDbl<=expectedPriceDbl2); //1920.00 >= 250.00
            }
        }
    }
    @When("User clicks on {string} section")
    public void user_clicks_on_section(String section) {
        if(section.equals("Jewelry & Accessories")) {
            etsyhomePage.jewelryAndAccessories.click();
        }
        else if(section.equals("Clothing & Shoes")){
            etsyhomePage.clothingAndShoes.click();
        }
        else if(section.equals("Home & Living")){
            etsyhomePage.homeAndLiving.click();
        }
        else if(section.equals("Wedding & Party")){
            etsyhomePage.weddingAndParty.click();
        }
        else if(section.equals("Toys & Entertainment")){
            etsyhomePage.toysAndEnt.click();
        }
        else if(section.equals("Art & Collectibles")){
            etsyhomePage.artAndColl.click();
        }
        else if(section.equals("Craft Supplies")){
            etsyhomePage.craftAndSupp.click();
        }
        else if(section.equals("Gifts & Gift Cards")){
            etsyhomePage.giftsAndCards.click();
        }
    }

    @Then("User validates the title is {string} and the header is {string}")
    public void user_validates_the_title_is_and_the_header_is(String title, String header) {
        String actualHeader = "";
        String actualTitle = "";
        if (header.equals("Jewelry & Accessories")) {
            actualHeader = etsyAppTitlePages.jaHeader.getText();
            actualTitle = driver.getTitle();
        } else if (header.equals("Clothing & Shoes")) {
            actualHeader = etsyAppTitlePages.csHeader.getText();
            actualTitle = driver.getTitle();
        } else if (header.equals("Home & Living")) {
            actualHeader = etsyAppTitlePages.hlHeader.getText();
            actualTitle = driver.getTitle();
        } else if (header.equals("Wedding & Party")) {
            actualHeader = etsyAppTitlePages.wpHeader.getText();
            actualTitle = driver.getTitle();
        } else if (header.equals("Toys & Entertainment")) {
            actualHeader = etsyAppTitlePages.teHeader.getText();
            actualTitle = driver.getTitle();
        } else if (header.equals("Art & Collectibles")) {
            actualHeader = etsyAppTitlePages.acHeader.getText();
            actualTitle = driver.getTitle();
        } else if (header.equals("Craft Supplies & Tools")) {
            actualHeader = etsyAppTitlePages.cstHeader.getText();
            actualTitle = driver.getTitle();
        } else if (header.equals("The Etsy Gift Guide")) {
            actualHeader = etsyAppTitlePages.ggHeader.getText();
            actualTitle = driver.getTitle();
        }


        Assert.assertEquals(title, actualTitle);
        Assert.assertEquals(header, actualHeader);
    }


}
