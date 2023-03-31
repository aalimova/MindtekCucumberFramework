package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import utilities.ConfigReader;

import java.sql.*;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class LogisticsRestAPISteps {
    Response response;
    String company_id;
    ResultSet resultSet;
    Statement statement;
    List<String> companies;
    @When("user sends get company api call")
    public void user_sends_get_company_api_call() {
         response = given().baseUri(ConfigReader.getProperty("LogisticsBaseURI"))
                .and().header("Authorization","Token 304ac80fb73abd62bfbcfcfac9d56ab405248062")
                .when().get("/companies/" + company_id );
        System.out.println(response.statusCode());
        System.out.println(response.body().asString());
        response.then().log().all();
    }

    @Then("user validates {int} status code")
    public void user_validates_status_code(Integer expectedStatusCode) {
        Assert.assertEquals(expectedStatusCode,new Integer(response.statusCode()));
    }

    @Given("user creates company with post call")
    public void userCreatesCompanyWithPostCall() {

        Random random = new Random();
        long mc_number=999999999+random.nextInt(100000000);
        long dot_number=999999999+random.nextInt(100000000);
        response = given().baseUri(ConfigReader.getProperty("LogisticsBaseURI"))
                .and().header("Authorization","Token 304ac80fb73abd62bfbcfcfac9d56ab405248062")
                .and().header("Content-Type", "application/json")
                .and().body("{\n" +
                        "    \"company_name\": \"meaajajn\",\n" +
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
        System.out.println(company_id);

    }

    @When("user connect to database")
    public void userConnectToDatabase() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://avapromindtekdatabase.cjmvgob8jlhc.us-east-1.rds.amazonaws.com:5432/mindtek_qa_db",
                "mindtek",
                "gwkr52lw");
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        resultSet = statement.executeQuery("select * from core_company where id =" + company_id);
        resultSet.next();


    }

    @Then("user validates created company is persisted in database")
    public void userValidatesCreatedCompanyIsPersistedInDatabase() throws SQLException {
        Assert.assertEquals(company_id,resultSet.getString("id"));
    }

    @When("user sends get company api call with limit {int}")
    public void userSendsGetCompanyApiCallWithLimit(int limit) {
        response = given().baseUri(ConfigReader.getProperty("LogisticsBaseURI"))
                .and().header("Authorization","Token 304ac80fb73abd62bfbcfcfac9d56ab405248062")
                .and().header("Accept","application/json")
                .and().queryParam("limit",limit)
                .when().get("/companies/");

        response.then().log().all();

    }

    @And("user validates that response has {int} companies")
    public void userValidatesThatResponseHasCompanies(int limit) {

        companies = response.body().jsonPath().getList("results.company_name");

        Assert.assertEquals(limit,companies.size());
    }

    @Then("user validates response limit matches with database first {int} companies")
    public void userValidatesResponseLimitMatchesWithDatabaseFirstCompanies(int limit) throws SQLException {
        resultSet = statement.executeQuery("select company_name from core_company\n" +
                "order by company_name\n" +
                "limit "+limit);

        for(int i = 0; i < companies.size(); i++){
            resultSet.next();
            Assert.assertEquals(resultSet.getString("company_name"),companies.get(i));
        }
    }
}
