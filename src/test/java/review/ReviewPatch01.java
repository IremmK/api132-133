package review;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ReviewPatch01 extends JsonPlaceHolderBaseUrl {

    /*
      Given
          1) https://jsonplaceholder.typicode.com/todos/198
          2) {
               "title": "Read the books"
             }
      When
           I send PATCH Request to the Url
      Then
            Status code is 200
            And response body is like   {
                                      "userId": 10,
                                      "title": "Read the books",
                                      "completed": true,
                                      "id": 198
                                     }
   */

    @Test
    public void patch01 (){
        //Set the URL
        spec.pathParams("first","todos","second",198);

        //Set the expected data
        Map<String,Object> expectedData = new JsonPlaceHolderTestData().expectedDataMapMethod(10,"Read the books",true);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).when().body(expectedData).patch("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);


        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals(expectedData.get("userId"),actualData.get("userId"));
        Assert.assertEquals(expectedData.get("title"),actualData.get("title"));
        Assert.assertEquals(expectedData.get("completed"),actualData.get("completed"));



    }




}
