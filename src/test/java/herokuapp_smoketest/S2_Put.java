package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.replaceFiltersWith;

public class S2_Put extends HerOkuAppBaseUrl {
    /*
    Given
         https://restful-booker.herokuapp.com/booking/1
    And
         {
    "firstname" : "Mark",
    "lastname" : "Twain",
    "totalprice" : 111,
    "depositpaid" : false,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Extra Pillow"
}
    When
        Send put request
    Then
        Status code should be 200
    And
        Body should be like{
    "firstname": "James",
    "lastname": "Brown",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}

     */

    @Test
    public void putTest (){
        //Set the URL
        spec.pathParams("first","booking","second",1);

        //Set the expected data
        BookingDatesPojo bookingdatesPojo = new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo expectedData = new BookingPojo("Mark","Twain",111,false,bookingdatesPojo,"Extra pillow");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).put("{first}/{second}");
        response.prettyPrint();

        //Do assertion


    }


}
