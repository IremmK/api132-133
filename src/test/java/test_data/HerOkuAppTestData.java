package test_data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.Map;
@JsonIgnoreProperties(ignoreUnknown = true)

public class HerOkuAppTestData {

    public Map<String, String> bookingdatesMapMethod(String checkin, String checkout) {

        Map<String, String> bookingdatesMap = new HashMap<>();
        if(checkin!= null){
            bookingdatesMap.put("checkin",checkin);
        }
        if(checkout!= null){
            bookingdatesMap.put("checkout",checkout);
        }

        return bookingdatesMap;
    }

    public Map<String, Object> expectedDataMethod(String firstname, String lastname, Integer totalprice, Boolean depositpaid, Map<String, String> bookingdatesMap, String additionalneeds) {

        Map<String, Object> expectedData = new HashMap<>();

        if(firstname!= null){
            expectedData.put("firstname",firstname);
        }
        if(lastname!= null){
            expectedData.put("lastname",lastname);
        }
        if(totalprice!= null){
            expectedData.put("totalprice",totalprice);
        }
        if(depositpaid!= null){
            expectedData.put("depositpaid",depositpaid);
        }
        if(bookingdatesMap!= null){
            expectedData.put("bookingdates",bookingdatesMap);
        }
        if(additionalneeds!= null){
            expectedData.put("additionalneeds",additionalneeds);
        }



        return expectedData;
    }
}