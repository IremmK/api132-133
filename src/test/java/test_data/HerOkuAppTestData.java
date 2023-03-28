package test_data;

import java.util.HashMap;
import java.util.Map;

public class HerOkuAppTestData {

    //This method will create a map for payload
    public Map<String, Object> expectedDataMapMethod(Integer totalPrice, String firstName, String lastName,String checkin,String checkout
            , Boolean depositpaid, String additionalneeds,Map<String,String> bookingdatesMap) {
        bookingdatesMap.put("checkin",checkin);
        bookingdatesMap.put("checkout",checkout);

        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("firstname",firstName);
        expectedData.put("lastname",lastName);
        expectedData.put("totalprice",totalPrice);
        expectedData.put("depositpaid",depositpaid);
        expectedData.put("bookingdates",bookingdatesMap);
        expectedData.put("additionalneeds",additionalneeds);

        return expectedData;
    }


}
