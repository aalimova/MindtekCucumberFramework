package steps;

import io.cucumber.java.an.Y;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.it.Ma;
import io.cucumber.java.sl.In;
import io.restassured.response.Response;
import org.junit.Assert;
import pojos.Yard;
import utilities.ConfigReader;


import java.util.Map;

import static io.restassured.RestAssured.given;

public class ProjectAPIcallsSteps {
    Yard yard;
    Response response;
    String yard_id;

    @Given("user creates yard with post api call with data")
    public void user_creates_yard_with_post_api_call_with_data(DataTable dataTable) {

        Map<String, Object> data = dataTable.asMap(String.class, Object.class);
        yard = new Yard();
        yard.setId(Integer.valueOf(data.get("id").toString()));
        yard.setContacts(new Object[0]);
        yard.setLocation(data.get("location").toString());
        yard.setName(data.get("name").toString());
        yard.setStatus(data.get("status").toString());
        yard.setAddress(data.get("address").toString());
        yard.setApt_suite(Integer.valueOf(data.get("apt_suite_company_co").toString()));
        yard.setCity(data.get("city").toString());
        yard.setState(data.get("state").toString());
        yard.setZip_code(Integer.valueOf(data.get("zip_code").toString()));
        yard.setSpots(data.get("spots").toString());


        response = given().baseUri(ConfigReader.getProperty("LogisticsBaseURI"))
                .and().header("Authorization"," Token 0c35e6c58d0f260a1a312e4e26a3beef395182c2")
                .and().header("Content-Type", "application/json")
                .and().header("Accept","application/json")
                .and().body(yard)
                .when().post("/yards/");

                response.then().log().all();
                yard_id = response.body().jsonPath().getString("id");
        System.out.println(yard_id);



    }

    @When("user sends get yard api call")
    public void user_sends_get_yard_api_call() {
        response = given().baseUri("http://3.141.30.63/en-us/api/v2")
                .and().header("Authorization"," Token 0c35e6c58d0f260a1a312e4e26a3beef395182c2")
                .and().header("Content-Type", "application/json")
                .and().header("Accept","application/json")
                .when().get("/yards/"+yard_id);
        response.then().log().all();

    }
    @Then("user verify status code {int}")
    public void user_verify_status_code(int statusCode) {
       Assert.assertEquals(statusCode,response.statusCode());
    }


    @Given("user updates yard with patch api call with data")
    public void userUpdatesYardWithPatchApiCallWithData(DataTable dataTable) {
        Map<String, String> map = dataTable.asMap(String.class, String.class);
        map.get("location");

        response = given().baseUri("http://3.141.30.63/en-us/api/v2")
                .and().header("Authorization"," Token 0c35e6c58d0f260a1a312e4e26a3beef395182c2")
                .and().header("Content-Type", "application/json")
                .and().header("Accept","application/json")
                .and().body(map)
                .when().patch("/yards/"+yard_id);
        response.then().log().all();

    }

    @And("user validates updated location is displayed in get response body")
    public void userValidatesUpdatedLocationIsDisplayedInGetResponseBody() {

    }
}
