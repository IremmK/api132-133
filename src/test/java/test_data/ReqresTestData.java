package test_data;

import java.util.HashMap;
import java.util.Map;

public class ReqresTestData {
    //This method will create a map for payload
    public Map<String, Object> reqresUsersSetUp(String job, String name) {
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("name", name);
        expectedData.put("job",job);

        return expectedData;
    }
}
