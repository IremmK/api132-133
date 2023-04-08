package homeworks.homework11;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class Put11 extends HerOkuAppBaseUrl {
      /*
Using the document "https://restful-booker.herokuapp.com/apidoc/index.html";
Type an automation test that creates a booking, updates that booking and then deletes it. Add positive and negative tests that validate these steps.
*/

    //  https://restful-booker.herokuapp.com/booking

    @Test
    public void put11() {

        //Set the Url
        spec.pathParams("first", "booking","second",12363);

        //Set the expected Data
        Map<String, String> bookingdatesMapMethod = new HerOkuAppTestData().bookingdatesMapMethod("2018-01-01", "2019-01-01");
        System.out.println("bookingdatesMapMethod = " + bookingdatesMapMethod);
        Map<String, Object> exceptedData = new HerOkuAppTestData().expectedDataMethod("Jim", "Brown", 111,
                true, bookingdatesMapMethod, "Breakfast");
        System.out.println("exceptedData = " + exceptedData);


        Response response = given(spec).body(exceptedData).put("/{first}/{second}");
        response.prettyPrint();
        response.then().statusCode(403);


    }
}



