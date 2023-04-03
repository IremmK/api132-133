package get_requests;

import base_urls.GorestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class Get12 extends GorestBaseUrl {
    /*
    Given
        https://gorest.co.in/public/v1/users
    When
        User send GET Request
    Then
        The value of "pagination limit" is 10
    And
        The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
    And
        The number of users should  be 10
    And
        We have at least one "active" status
    And
        "Gov. Vrinda Panicker", "Sen. Devika Embranthiri" and "Rev. Jay Shukla" are among the users
    And
        The female users are less than or equals to male users
*/

    @Test
    public void get12 (){
        //Set the URL
        spec.pathParam("first","users");

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();

        //Do assertion
        //The value of "pagination limit" is 10
        int paginationLimit = response.jsonPath().getInt("meta.pagination.limit");
        System.out.println("paginationLimit = " + paginationLimit);
        assertEquals(10,paginationLimit);

        // The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        String currentLink = response.jsonPath().getString("meta.pagination.links.current");
        assertEquals("https://gorest.co.in/public/v1/users?page=1",currentLink);

        response.then().body("meta.pagination.limit",equalTo(10),
                "meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1"),
                "data",hasSize(10),"data.status",hasItem("active"),
                "data.name",hasItems("Mr. Upendra Mehrotra","Radha Dwivedi","Rajendra Dhawan"));


    }


}
