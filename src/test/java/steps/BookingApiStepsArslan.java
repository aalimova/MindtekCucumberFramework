package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.it.Ma;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojosArslan.Booking;
import pojosArslan.BookingDates;
import utilities.ConfigReader;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BookingApiStepsArslan {

    Response response;


    @Given("user creates booking with post api call with data")
    public void user_creates_booking_with_post_api_call_with_data(DataTable dataTable) {

        Map<String, Object> map = dataTable.asMap(String.class, Object.class);
        Booking booking = new Booking();
        BookingDates bookingDates = new BookingDates();
        booking.setFirstname(map.get("firstname").toString());
        booking.setLastname(map.get("lastname").toString());
        booking.setTotalprice(Integer.valueOf(map.get("totalprice").toString()));
        booking.setDepositpaid(Boolean.valueOf(map.get("depositpaid").toString()));
        booking.setAdditionalneeds(map.get("additionalneeds").toString());
        bookingDates.setCheckin(map.get("checkin").toString());
        bookingDates.setCheckout(map.get("checkout").toString());
        booking.setBookingDates(bookingDates);
      response = given().baseUri(ConfigReader.getProperty("BookingApiBaseURL"))
              .and().accept(ContentType.JSON)
              .and().contentType(ContentType.JSON)
              .and().body(booking)
              .when().post("/booking");

    }

    @When("user get created booking with get api call")
    public void user_get_created_booking_with_get_api_call() {

    }
    @Then("user validates {int} status")
    public void user_validates_status(Integer int1) {

    }

}
