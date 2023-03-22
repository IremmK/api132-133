package get_requests;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestAndResponse {

    /*
    1) Postman it used for manual API testing
    2) We used REST ASSURED library for API Automation testing
    3) To type Automation Script follow these steps:
       1. Step : Understand the requirement
       2. Step : Type test cases
                 To type the test cases we use 'Gherkin Language'
                 The keywords are : a) Given: It is used for pre-condition
                                    b) When : It is used for action (Requests...)
                                    c) Then : It is used for output (Assertion...)
                                    d) And : It is used for multiple usage of Given, When and Then

       3.Step : Starts to type Automation Script
                i) Set the URL
                ii) Set the expected data
                iii) Send the request and get the response
                iv) Do assertion
     */
    /*
        Given
            https://restful-booker.herokuapp.com/booking/10
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
     */
    public static void main(String[] args) {

        String url = "https://restful-booker.herokuapp.com/booking/10";

        //User sends a GET Request to the url
        Response response = given().when().get(url);
        response.prettyPrint();

        //HTTP Status Code should be "200"
        System.out.println(response.statusCode());

        //Content Type should be "JSON"
        System.out.println(response.contentType());

        //Status Line should be "HTTP/1.1 200 OK"
        System.out.println(response.statusLine());

        //How to see "Header" on console:
        System.out.println(response.header("Connection"));

        //How to see "Headers" on console:
        System.out.println();
        System.out.println(response.headers());

        //How to see "Time" on console
        System.out.println(response.time());
    }
}