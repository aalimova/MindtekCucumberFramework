package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.LogisticsPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.sql.*;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class LogisticsRestAPIProject {
    LogisticsPage logisticsPage = new LogisticsPage();
    Response response;
    String company_id;
    long mc_number;
    long dot_number;
    WebDriver driver = Driver.getDriver();
    @Given("user creates company with Post call")
    public void user_creates_company_with_post_call() {
        Random random = new Random();
        mc_number=(long)(Math.random()*Math.pow(10,10));
        dot_number=(long)(Math.random()*Math.pow(10,10));

        response = given().baseUri("http://3.141.30.63/en-us/api/v2/")
                .and().header("Authorization", "Token 304ac80fb73abd62bfbcfcfac9d56ab405248062")
                .and().header("Content-Type", "application/json")
                .and().body("{\n" +
                        "    \"company_name\": \"Elinest\",\n" +
                        "    \"company_type\": \"broker company\",\n" +
                        "    \"status\": \"active\",\n" +
                        "    \"mc_number\": \""+mc_number+"\",\n" +
                        "    \"dot_number\": \""+dot_number+"\",\n" +
                        "    \"ifta\": false,\n" +
                        "    \"address\": \"5363 n Navi\",\n" +
                        "    \"apt_suite_company_co\": null,\n" +
                        "    \"city\": \"Chicago\",\n" +
                        "    \"state\": \"IL\",\n" +
                        "    \"zip_code\": \"60621\",\n" +
                        "    \"insurance\": \"bbbb\",\n" +
                        "    \"producer_address\": \"6573 n Reverse\",\n" +
                        "    \"producer_apt_suite_company_co\": null,\n" +
                        "    \"producer_city\": \"Chicago\",\n" +
                        "    \"producer_state\": \"IL\",\n" +
                        "    \"producer_zip_code\": \"60614\",\n" +
                        "    \"policy_effective_day\": null,\n" +
                        "    \"policy_expiration\": null,\n" +
                        "    \"type_of_insurance\": null,\n" +
                        "    \"automobile_liability\": \"\",\n" +
                        "    \"num_of_truck_insured\": null,\n" +
                        "    \"policy_number\": null,\n" +
                        "    \"employer_id_num\": \"45-4545674\",\n" +
                        "    \"billing_address\": null,\n" +
                        "    \"bank_name\": null,\n" +
                        "    \"routing_number\": null,\n" +
                        "    \"account_number\": null,\n" +
                        "    \"president_full_name\": null,\n" +
                        "    \"trucks_in_fleet\": null,\n" +
                        "    \"scac_code\": \"\",\n" +
                        "    \"other_licenses\": false,\n" +
                        "    \"license_name\": null,\n" +
                        "    \"incorporated_in\": null,\n" +
                        "    \"notes\": null,\n" +
                        "    \"company_picture\": [],\n" +
                        "    \"company_documents\": [],\n" +
                        "    \"contacts\": [\n" +
                        "        {\n" +
                        "            \"phone\": \"657-576-5765\",\n" +
                        "            \"ext\": \"\",\n" +
                        "            \"contact_name\": \"\",\n" +
                        "            \"email\": \"sfsf@gmail.com\",\n" +
                        "            \"fax\": \"\",\n" +
                        "            \"producer_phone\": \"465-465-6565\",\n" +
                        "            \"producer_phone_ext\": \"\",\n" +
                        "            \"producer_contact_name\": \"\",\n" +
                        "            \"producer_email\": \"dhfkjshd@gmail.com\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"phone_number\": [\n" +
                        "        {\n" +
                        "            \"phone\": \"657-576-5765\",\n" +
                        "            \"ext\": \"\",\n" +
                        "            \"contact_name\": \"\",\n" +
                        "            \"email\": \"sfsf@gmail.com\",\n" +
                        "            \"fax\": \"\",\n" +
                        "            \"producer_phone\": \"465-465-6565\",\n" +
                        "            \"producer_phone_ext\": \"\",\n" +
                        "            \"producer_contact_name\": \"\",\n" +
                        "            \"producer_email\": \"dhfkjshd@gmail.com\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"fax_number\": [\n" +
                        "        {\n" +
                        "            \"fax\": \"\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"email_number\": [\n" +
                        "        {\n" +
                        "            \"email\": \"sfsf@gmail.com\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"producer_email_number\": [\n" +
                        "        {\n" +
                        "            \"producer_email\": \"dhfkjshd@gmail.com\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"producer_phone_number\": [\n" +
                        "        {\n" +
                        "            \"producer_phone\": \"465-465-6565\",\n" +
                        "            \"producer_phone_ext\": \"\",\n" +
                        "            \"producer_contact_name\": \"\"\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}")
                .when().post("/companies/");
        response.then().log().all();
        company_id = response.body().jsonPath().getString("id");

    }

    @When("user updates company name with patch call")
    public void user_updates_company_name_with_patch_call() {
        response = given().baseUri("http://3.141.30.63/en-us/api/v2/")
                .and().header("Authorization", "Token 304ac80fb73abd62bfbcfcfac9d56ab405248062")
                .and().header("Content-Type", "application/json")
                .and().body("{\n" +
                        "    \"id\": 21,\n" +
                        "    \"company_name\": \"Mortal\",\n" +
                        "    \"company_type\": \"broker company\",\n" +
                        "    \"status\": \"active\",\n" +
                        "    \"mc_number\": \""+mc_number+"\",\n" +
                        "    \"dot_number\": \""+dot_number+"\",\n" +
                        "    \"ifta\": false,\n" +
                        "    \"address\": \"5363 n Navi\",\n" +
                        "    \"apt_suite_company_co\": null,\n" +
                        "    \"city\": \"Chicago\",\n" +
                        "    \"state\": \"IL\",\n" +
                        "    \"zip_code\": \"60621\",\n" +
                        "    \"insurance\": \"aaa\",\n" +
                        "    \"producer_address\": \"6573 n Reverse\",\n" +
                        "    \"producer_apt_suite_company_co\": null,\n" +
                        "    \"producer_city\": \"Chicago\",\n" +
                        "    \"producer_state\": \"IL\",\n" +
                        "    \"producer_zip_code\": \"60614\",\n" +
                        "    \"policy_effective_day\": null,\n" +
                        "    \"policy_expiration\": null,\n" +
                        "    \"type_of_insurance\": null,\n" +
                        "    \"automobile_liability\": \"\",\n" +
                        "    \"num_of_truck_insured\": null,\n" +
                        "    \"policy_number\": null,\n" +
                        "    \"billing_address\": null,\n" +
                        "    \"bank_name\": null,\n" +
                        "    \"routing_number\": null,\n" +
                        "    \"account_number\": null,\n" +
                        "    \"president_full_name\": null,\n" +
                        "    \"trucks_in_fleet\": null,\n" +
                        "    \"scac_code\": \"\",\n" +
                        "    \"other_licenses\": false,\n" +
                        "    \"license_name\": null,\n" +
                        "    \"incorporated_in\": null,\n" +
                        "    \"notes\": null,\n" +
                        "    \"contacts\": [\n" +
                        "        {\n" +
                        "            \"phone\": \"773-456-3277\",\n" +
                        "            \"contact_name\": \"\",\n" +
                        "            \"ext\": \"\",\n" +
                        "            \"email\": \"sfsf@gmail.com\",\n" +
                        "            \"fax\": \"\",\n" +
                        "            \"producer_phone\": \"338-839-3999\",\n" +
                        "            \"producer_contact_name\": \"\",\n" +
                        "            \"producer_phone_ext\": \"\",\n" +
                        "            \"producer_email\": \"dhfkjshd@gmail.com\",\n" +
                        "            \"id\": 21\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}")
                .when().patch("/companies/"+company_id+"/");
            response.then().log().all();

    }
    @Then("user validates status code {int}")
    public void user_validates_status_code(Integer expectedCodeStatus) {
        Assert.assertEquals((int)expectedCodeStatus, response.statusCode());
        System.out.println(response.statusCode());

    }
    @Then("user validates name is updated in Database")
    public void user_validates_name_is_updated_in_database() throws SQLException {
        Connection connection = DriverManager.getConnection(ConfigReader.getProperty("LogisticsDBURL"),
                ConfigReader.getProperty("LogisticsDBUSER"),
                ConfigReader.getProperty("LogisticsDBPassword"));
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from core_company where company_name = 'Mortal'");
        resultSet.next();
        Assert.assertEquals("Mortal",resultSet.getString("company_name"));

    }

    @And("user validates name is updated in UI")
    public void userValidatesNameIsUpdatedInUI() {
        driver.get(ConfigReader.getProperty("LogisticsURL"));
        logisticsPage.username.sendKeys(ConfigReader.getProperty("LogisticsUsername"));
        logisticsPage.password.sendKeys(ConfigReader.getProperty("LogisticsPassword"));
        logisticsPage.submitBtn.click();

        List<WebElement> list = logisticsPage.listOfCompany;
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).getText());
        }

    }
}
