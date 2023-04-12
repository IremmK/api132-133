package get_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class Get13 extends DummyRestApiBaseUrl {
    /*
Given
https://dummy.restapiexample.com/api/v1/employees
When
User sends Get Request to the Url
Then
Status code is 200
And
There are 24 employees
And
"Tiger Nixon" and "Garrett Winters" are among the employees
And
The greatest age is 66
And
The name of the lowest age is "[Tatyana Fitzpatrick]"
And
Total salary of all employees is 6,644,770
*/
    @Test
    public void get13() {
        //Set the url
        spec.pathParam("first", "employees");

        //Set the expected data

        //Send the request and get response
        Response response = given(spec).get("{first}");
        response.prettyPrint();

        //Do assertion
        response.
                then().
                statusCode(200).
                body("data", hasSize(24),
                "data.employee_name",hasItems("Tiger Nixon","Garrett Winters"));
        JsonPath json = response.jsonPath();
        List<Integer> ages = json.getList("data.employee_age");

        Collections.sort(ages);
        System.out.println("ages = " + ages);

        int greatestAge = ages.get(ages.size()-1);

        assertEquals(66,greatestAge);

        String name = json.getString("data.find{it.employee_age=="+ages.get(0)+"}.employee_name");
        System.out.println("name = " + name);
        assertEquals("Tatyana Fitzpatrick",name);

        //1.Way:
        List < Integer> salaries = json.getList("data.employee_salary");
        System.out.println(salaries);

        int totalSalary = 0;
        for(Integer w : salaries){
            totalSalary+=w;
        }
        System.out.println(totalSalary);
        assertEquals(6644770,totalSalary);

        //2.Way: Functional Programming ==> RECOMMENDED WAY
        int totalSalariesLambda =salaries.stream().reduce(0,Math:: addExact);
        assertEquals(6644770,totalSalariesLambda);


    }
}
