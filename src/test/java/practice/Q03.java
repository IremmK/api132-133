package practice;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.JsonPlaceHolderPojo;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.replaceFiltersWith;
import static org.junit.Assert.assertEquals;

public class Q03 extends HerOkuAppBaseUrl {
    /*
   Given
           https://restful-booker.herokuapp.com/booking/555
   When
           I send GET Request to the URL
   Then
           Status code is 200
                   {
                       "firstname": "Jane",
                       "lastname": "Doe",
                       "totalprice": 111,
                       "depositpaid": true,
                       "bookingdates": {
                           "checkin": "2018-01-01",
                           "checkout": "2019-01-01"
                       },
                       "additionalneeds":  "Extra pillows please"
                   }
*/

   @Test
    public void q03 () throws IOException {
       //Set the URL
       spec.pathParams("first","booking","second",147);

       //Set the expected data
       BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01","2019-01-01");
       BookingPojo expectedData = new BookingPojo("Jane","Doe",111,true,bookingDates,"Extra pillows please");
       System.out.println("expectedData = " + expectedData);

       //Send the request and get the response
       Response response = given(spec).body(expectedData).get("/{first}/{second}");
       response.prettyPrint();

       //Do assertion
       BookingPojo actualData = new ObjectMapper().readValue(response.asString(),BookingPojo.class);
       System.out.println("actualData = " + actualData);


       SoftAssert softAssert = new SoftAssert();
       softAssert.assertEquals(response.getStatusCode(),200);
       softAssert.assertEquals(actualData.getFirstname(),expectedData.getFirstname());
       softAssert.assertEquals(actualData.getLastname(),expectedData.getLastname());
       softAssert.assertEquals(actualData.getTotalprice(),expectedData.getTotalprice());
       softAssert.assertEquals(actualData.getDepositpaid(),expectedData.getDepositpaid());
       softAssert.assertEquals(actualData.getBookingdates().getCheckin(),bookingDates.getCheckin());
       softAssert.assertEquals(actualData.getBookingdates().getCheckout(),bookingDates.getCheckout());
       softAssert.assertEquals(actualData.getAdditionalneeds(),expectedData.getAdditionalneeds());

       softAssert.assertAll();

   }



}
