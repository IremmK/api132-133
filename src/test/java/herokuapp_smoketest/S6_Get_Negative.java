package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static herokuapp_smoketest.S1_Post.bookingId;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.replaceFiltersWith;
import static org.junit.Assert.assertEquals;

public class S6_Get_Negative extends HerOkuAppBaseUrl {
    /*
    Given
         https://restful-booker.herokuapp.com/booking/{id}
    When
         Send get request
    Then
         Status code should be like 404
    And
         Response body should be like "Not Found"
     */

    @Test
    public void getNegativeTest (){
        //Set the URL
        spec.pathParams("first","booking","second",bookingId);

        //Set the expected data
        String expectedData = "Not Found";

        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        assertEquals(404,response.statusCode());
        assertEquals(expectedData,response.asString());
    }
}
