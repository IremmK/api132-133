package practice;

import base_urls.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class Q04 extends PetStoreBaseUrl {
     /*
    Given
    https://petstore.swagger.io/v2/pet/findByStatus?status=available
    When
    User sens Get request
    Then
    Assert that number of pets whose status is "available" is more than 100
            */

    @Test
    public void q04(){
        //Set the URL
        spec.pathParams("first","pet","second","findByStatus").queryParam("status","available");

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        int availablePetNum = response.jsonPath().getList("id").size();
        assertTrue(availablePetNum>100);

    }


}
