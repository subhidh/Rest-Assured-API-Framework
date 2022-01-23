package files;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {
    public static String extractValuefromJson(String s, String key) {
        JsonPath js = new JsonPath(s);
        return (js.getString(key));
    }
}
