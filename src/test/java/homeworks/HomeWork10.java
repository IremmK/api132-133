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
//        List<String> womenList =  json.getList("products.category.usertype.findAll{usertype=='Women'}.usertype");
//        System.out.println("womenList = " + womenList);


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

    @Test
    public void automationExerciseTest(){
        spec.pathParam("first","productsList");
        Response response = given(spec).get("{first}");
        response.jsonPath().prettyPrint();
        JsonPath jsonPath = response.jsonPath();
        //Assert that number of "Women" usertype is 12
        int numOfWomanUser = jsonPath.getList("products.findAll{it.category.usertype.usertype=='Women'}").size();
        assertEquals(12,numOfWomanUser);
    }


}
