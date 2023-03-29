package practice;

import base_urls.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.platform.engine.support.discovery.SelectorResolver;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Q01 extends ReqresBaseUrl {

      /*
       Given
              https://reqres.in/api/unknown/
       When
            I send GET Request to the URL
       Then

            1)Status code is 200
            2)Print all pantone_values
            3)Print all ids greater than 3 on the console
              Assert that there are 3 ids greater than 3
            4)Print all names whose ids are less than 3 on the console
              Assert that the number of names whose ids are less than 3 is 2
    */

    @Test
    public void q01 (){
        //Set the URL
        spec.pathParams("first","unknown");

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //Do assertion
        response.then().statusCode(200);
        JsonPath json = response.jsonPath();//Take the json and convert it to Java --> De-Serialization
        List<String> pantoneValues = json.getList("data.pantone_value");
        System.out.println("pantoneValues = " + pantoneValues);


        List<String> idsGreaterThan3Ids = json.getList("data.findAll{it.id>3}.id");
        System.out.println("idsGreaterThan3Ids = " + idsGreaterThan3Ids);
        assertEquals(3,idsGreaterThan3Ids.size());

        List<String> idsLessThan3Names = json.getList("data.findAll{it.id<3}.name");
        System.out.println("idsLessThan3Names = " + idsLessThan3Names);
        assertEquals(2,idsLessThan3Names.size());


    }
}
