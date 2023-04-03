package homeworks;

import base_urls.AutomationExerciseBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;

import java.util.List;


import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class HomeWork10 extends AutomationExerciseBaseUrl {
    //Homework10:

/*
    Given
        https://automationexercise.com/api/productsList
    When
        User sends Get request
        Note: use prettyPrint like: response.jsonPath().prettyPrint()
    Then
        Assert that number of "Women" usertype is 12

*/

    @Test
    public void homeWork10  (){
        //Set the URL
        //  String url = "https://automationexercise.com/api/productsList";
        spec.pathParams("first","api","second","productsList");

        //Set the expected data
        //Send the request and get the data
        Response response = given(spec).when().contentType(ContentType.JSON).get("{first}/{second}");
        response.jsonPath().prettyPrint();

        //Do assertion
        JsonPath json = response.jsonPath();
        List<String> usertype = json.getList("products.category.usertype.usertype");
        System.out.println("usertype = " + usertype);



        List<String> women = new ArrayList<>();
        for(int i=0; i< usertype.size();i++){
            if(usertype.get(i).equals("Women")){
                women.add(usertype.get(i));
            }
        }

        System.out.println("women = " + women);
        assertEquals(12,women.size());

        int womenSize=0;
        for (int i =0 ; i<usertype.size();i++){
            if(usertype.get(i).equals("Women")){
                womenSize++;
            }
        }
        assertEquals(12,womenSize);

    }


}
