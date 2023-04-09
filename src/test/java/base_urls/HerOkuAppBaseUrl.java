package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import utils.AuthenticationHerOkuApp;

public class HerOkuAppBaseUrl {
    protected RequestSpecification spec;

    @Before//This method will run before each @Test methods.
    public void setUp() {

        spec = new RequestSpecBuilder().
                setBaseUri("https://restful-booker.herokuapp.com").
                build().
                headers("Cookie", "token="+ AuthenticationHerOkuApp.generateToken()).
                contentType(ContentType.JSON);

    }
}
