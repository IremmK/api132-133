package test_data;

import java.util.HashMap;
import java.util.Map;

public class PetStoreTestData {
    public Map<String,String> categoryMapMethod (String name){
        Map<String,String> categoryMap = new HashMap<>();
        categoryMap.put("name",name);
        return categoryMap;
    }
    public Map<String,Object> expectedDataMapMethod (Integer id,String name,String status,Map<String,String> categoryMap){

        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("id",id);
        expectedData.put("name",name);
        expectedData.put("status",status);
        expectedData.put("category",categoryMap);

        return expectedData;

    }



}
