package homeworks.homework11;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import test_data.HerOkuAppTestData;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Post11 extends HerOkuAppBaseUrl {
    /*
Using the document "https://restful-booker.herokuapp.com/apidoc/index.html";
Type an automation test that creates a booking, updates that booking and then deletes it. Add positive and negative tests that validate these steps.
{
    {
    "bookingid": 6328,
    "booking": {
        "firstname": "Jim",
        "lastname": "Brown",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"
    }
}
}
*/

    @Test
    public void post11() {

        //Set the Url
        spec.pathParam("first", "booking");

        //Set the expected Data
        Map<String, String> bookingdatesMapMethod = new HerOkuAppTestData().bookingdatesMapMethod("2018-01-01", "2019-01-01");
        System.out.println("bookingdatesMapMethod = " + bookingdatesMapMethod);
        Map<String, Object> exceptedData = new HerOkuAppTestData().expectedDataMethod("Jim", "Brown", 111,
                true, bookingdatesMapMethod, "Breakfast");
        System.out.println("exceptedData = " + exceptedData);

        //Send the request get the response
        Response response = given(spec).body(exceptedData).post("/{first}");
        response.prettyPrint();


        //Do Assert

        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);
        Assert.assertEquals(exceptedData.get("booking.firstname"), actualData.get("booking.firstname"));
        Assert.assertEquals(exceptedData.get("booking.lastname"), actualData.get("booking.lastname"));
        Assert.assertEquals(exceptedData.get("booking.totalprice"), actualData.get("booking.totalprice"));
        Assert.assertEquals(exceptedData.get("booking.depositpaid"), actualData.get("booking.depositpaid"));
        Assert.assertEquals(bookingdatesMapMethod.get("checkin"), ((Map) ((Map) actualData.get("booking")).get("bookingdates")).get("checkin"));
        Assert.assertEquals(bookingdatesMapMethod.get("checkout"), ((Map) ((Map) actualData.get("booking")).get("bookingdates")).get("checkout"));
        Assert.assertEquals(exceptedData.get("booking.additionalneeds"), actualData.get("booking.additionalneeds"));


    }

}
