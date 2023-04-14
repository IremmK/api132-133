package homeworks12;

import io.restassured.response.Response;
import org.junit.Test;

import static homeworks12.HomeworkAuthenticationGmiBank.gmiBankToken;
import static io.restassured.RestAssured.given;

public class Homework12State extends  GmiBankBaseUrl {

    @Test
    public void postState (){
        spec.pathParams("first","api","second","tp-states");

        Response response = given(spec).
                headers("Authorization", "Bearer " + gmiBankToken()).
                post("{first}/{second}");
        response.prettyPrint();
    }

}
