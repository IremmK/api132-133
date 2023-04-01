package post_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02 extends HerOkuAppBaseUrl {

     /*
   Given
       1) https://restful-booker.herokuapp.com/booking
       2) {
            "firstname": "John",
            "lastname": "Doe",
            "totalprice": 11111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2021-09-09",
                "checkout": "2021-09-21"
             }
          }
   When
       I send POST Request to the Url
   Then
       Status code is 200
       And response body should be like {
                                           "bookingid": 5315,
                                           "booking": {
                                               "firstname": "John",
                                               "lastname": "Doe",
                                               "totalprice": 11111,
                                               "depositpaid": true,
                                               "bookingdates": {
                                                   "checkin": "2021-09-09",
                                                   "checkout": "2021-09-21"
                                               }
                                           }
                                        }
*/

    @Test
    public void post02 (){
        //Set the URL
        spec.pathParams("first","booking");

        //Set the expected data
        Map<String, String> bookingDatesMap = new HerOkuAppTestData().bookingdatesMapMethod("2021-09-09","2021-09-21");
        Map<String, Object> expectedData = new HerOkuAppTestData().expectedDataMethod("John","Doe",11111,true,bookingDatesMap,null);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).body(expectedData).when().post("{first}");
        response.prettyPrint();

        //Do assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);



        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("firstname"),((Map)actualData.get("booking")).get("firstname"));
        assertEquals(expectedData.get("lastname"),((Map)actualData.get("booking")).get("lastname"));
        assertEquals(expectedData.get("totalprice"),((Map)actualData.get("booking")).get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),((Map)actualData.get("booking")).get("depositpaid"));
        assertEquals(bookingDatesMap.get("checkin"),      ( (Map)((Map)actualData.get("booking")).get("bookingdates")) .get("checkin")    );
        assertEquals(bookingDatesMap.get("checkout"),      ( (Map)((Map)actualData.get("booking")).get("bookingdates")) .get("checkout")    );
        //NOTE : Since the value data type is "Object" in actualData Map, all values will return as Object data type
        //To use map methods from an Object container, we need to do type casting


////        Assert.assertEquals(expectedData.get("lastname"),actualData.get("booking.lastname"));
////        Assert.assertEquals(expectedData.get("totalprice"),actualData.get("booking.totalprice"));
////        Assert.assertEquals(expectedData.get("depositpaid"),actualData.get("booking.depositpaid"));
////        Assert.assertEquals(expectedData.get("bookingdates.checkin"),actualData.get("booking.bookingdates.checkin"));
////        Assert.assertEquals(expectedData.get("bookindates.checkout"),actualData.get("booking.bookingdates.checkout"));


        //In that issue I mean if you have firstname inside the booking body you cannot use that way "booking.firstname" because it is not working
        //But if you use type casting I mean "((Map) actualData.get("booking")).get("firstname")" it is working it is recommended
        //Your way is fail :(  If you use your own way you can get fail sorry about that :(






    }



}
