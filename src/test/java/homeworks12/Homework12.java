package homeworks12;

import base_urls.GmiBankBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static homeworks12.HomeworkAuthenticationGmiBank.gmiBankToken;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Homework12 extends GmiBankBaseUrl {
    /*
    //Type an automation test that creates a "country" which includes at least 3 "states"
    using the document //https://app.swaggerhub.com/apis/yasinaniltechpro/GmiBank/0.0.1.
     */
    @Test
    public void homework12(){
        //Set the Url
        spec.pathParams("first","api","second","tp-countries");

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).
                headers("Authorization", "Bearer " + gmiBankToken()).
                get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        response.then().statusCode(200);
        JsonPath json = response.jsonPath();

        List<String> states = json.getList("states");
        System.out.println("states = " + states);
        assertTrue(states.size()>=3);


    }
}