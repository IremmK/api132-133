package homeworks;

import base_urls.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.ReqresTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class HomeWork07 extends ReqresBaseUrl {

    //Homework7:


  /*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }
     */

    @Test
    public void homeWork07() {
        //Set the URL
        spec.pathParam("first", "users");

        //Set the expected data
        Map<String, Object> expectedData = new ReqresTestData().reqresUsersSetUp("leader", "morpheus");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        //Do assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(201, response.getStatusCode());
        assertEquals(expectedData.get("name"), actualData.get("name"));
        assertEquals(expectedData.get("job"), actualData.get("job"));



    }

    @Test
    public void post01() {
        spec.pathParam("first", "users");
        ReqresTestData reqresTestData = new ReqresTestData();
        Map<String, Object> expectedData = reqresTestData.reqresUsersSetUp("leader","morpheus");
        System.out.println("expectedData = " + expectedData);


        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        Map<String, String> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(201, response.getStatusCode());
        Assert.assertEquals(expectedData.get("name"), actualData.get("name"));
        Assert.assertEquals(expectedData.get("job"), actualData.get("job"));

    }
}
