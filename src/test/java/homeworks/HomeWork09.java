package homeworks;

import base_urls.PetStoreBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HomeWork09 extends PetStoreBaseUrl {
    //Homework9:

//Using the https://petstore.swagger.io/ document, write an automation test that finds the number of "pets" with a status of
// "available" and asserts that those are more than 100.

   @Test
    public void homeWork09 (){

       //Set the URL
       spec.pathParams("first","pet","second","findByStatus").queryParam("status","available");

       //Set the expected data


       //Send the request and get the response
      Response response = given(spec).when().get("{first}/{second}");
      response.prettyPrint();

      //Do assertion
       JsonPath json = response.jsonPath();
       List<String> status = json.getList("status");
       System.out.println("status = " + status);
       assertTrue(status.size()>100);
       assertEquals(200,response.statusCode());



   }




}
