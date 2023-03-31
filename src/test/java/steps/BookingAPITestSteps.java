package steps;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import pojos.Booking;
import pojos.Bookingdates;

import java.awt.print.Book;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class BookingAPITestSteps {

    Response response;
    String bookingId;
    Booking booking;

    @Given("user creates booking with Post API call with data")
    public void user_creates_booking_with_post_api_call_with_data(DataTable dataTable) {
        Map<String,Object> data = dataTable.asMap(String.class, Object.class);

        booking = new Booking();
        Bookingdates bookingdates = new Bookingdates();
        booking.setFirstname(data.get("firstname").toString());
        booking.setLastname(data.get("lastname").toString());
        booking.setTotalprice(Integer.valueOf(data.get("totalprice").toString()));
        booking.setDepositpaid(Boolean.valueOf(data.get("depositpaid").toString()));
        booking.setAdditionalneeds(data.get("additionalneeds").toString());
        bookingdates.setCheckin(data.get("checkin").toString());
        bookingdates.setCheckout(data.get("checkout").toString());
        booking.setBookingdates(bookingdates);

        response = given().baseUri("https://restful-booker.herokuapp.com")
                .and().header("Authorization"," Token 0c35e6c58d0f260a1a312e4e26a3beef395182c2")
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().body(booking)
                .when().post("/booking");
        response.then().log().all();
        bookingId = response.body().jsonPath().getString("bookingid");


    }

    @When("user sends get booking api call")
    public void user_sends_get_booking_api_call() {
        response = given().baseUri("https://restful-booker.herokuapp.com")
                .and().header("Accept", "application/json")
                .when().get("/booking/"+bookingId);
        response.then().log().all();
    }
    @Then("user validate status code {int}")
    public void user_validate_status_code(Integer statusCode) {
        response.then().statusCode(statusCode);

    }
    @Then("user validates data matches with created data")
    public void user_validates_data_matches_with_created_data() {
        Booking responseBody = response.body().as(Booking.class);
        Assert.assertEquals(booking.toString(), responseBody.toString());

    }


    @When("user deletes booking")
    public void userDeletesBooking() {
        response = given().baseUri("https://restful-booker.herokuapp.com")
                .and().header("cookie", "token="+generateToken())
                .when().delete("/booking/"+bookingId);
        response.then().log().all();
    }
    public  String generateToken(){
        response = given().baseUri("https://restful-booker.herokuapp.com")
                .and().contentType(ContentType.JSON)
                .and().body("{\n" +
                        "    \"username\" : \"admin\",\n" +
                        "    \"password\" : \"password123\"\n" +
                        "}")
                .when().post("/auth");
        response.then().log().all();
        return response.body().jsonPath().getString("token");
    }

    @When("user updates booking")
    public void userUpdatesBooking(DataTable dataTable) {
        Map<String,Object> data = dataTable.asMap(String.class, Object.class);

        booking = new Booking();
        Bookingdates bookingdates = new Bookingdates();
        booking.setFirstname(data.get("firstname").toString());
        booking.setLastname(data.get("lastname").toString());
        booking.setTotalprice(Integer.valueOf(data.get("totalprice").toString()));
        booking.setDepositpaid(Boolean.valueOf(data.get("depositpaid").toString()));
        booking.setAdditionalneeds(data.get("additionalneeds").toString());
        bookingdates.setCheckin(data.get("checkin").toString());
        bookingdates.setCheckout(data.get("checkout").toString());
        booking.setBookingdates(bookingdates);

        response = given().baseUri("https://restful-booker.herokuapp.com")
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .and().header("Cookie", "token="+generateToken())
                .and().body(booking)
                .when().put("/booking/"+bookingId);
        response.then().log().all();
    }

    @And("user validates response body matches with update data")
    public void userValidatesResponseBodyMatchesWithUpdateData() {
        Booking responseBooking = response.body().as(Booking.class);
        Assert.assertEquals(booking.toString(),responseBooking.toString());
    }
}
