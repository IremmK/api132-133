package homeworks;

import base_urls.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.PetStoreTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class HomeWork08 extends PetStoreBaseUrl {
     /*
    Type automation code to create a 'user' by using "https://petstore.swagger.io/v2" documantation.
   */
    /*
    {
    "id": 989898,
    "category": {
        "id": 0,
        "name": "cow"
    },
    "name": "sarikiz",
    "photoUrls": [
        "string"
    ],
    "tags": [
        {
            "id": 0,
            "name": "string"
        }
    ],
    "status": "available"
}
     */

    @Test
    public void homework08(){

        //Set the URL
        spec.pathParam("first","pet").queryParam("id",989898);

        //Set the expected data
        Map<String,String> categoryMap = new PetStoreTestData().categoryMapMethod("cow");
        Map<String,Object> expectedData = new PetStoreTestData().expectedDataMapMethod(989898,"sarikiz","available",categoryMap);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).when().body(expectedData).contentType(ContentType.JSON).post("{first}");
        response.prettyPrint();

        //Do assertion
        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(expectedData.get("id"),actualData.get("id"));
        Assert.assertEquals(expectedData.get("name"),actualData.get("name"));
        Assert.assertEquals(expectedData.get("status"),actualData.get("status"));
        Assert.assertEquals(     ( (Map)  expectedData.get("category")).get("name")     ,    ((Map)   actualData.get("category")).get("name")       );



    }



}
