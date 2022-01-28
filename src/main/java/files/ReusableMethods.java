package files;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {
    public static String extractStringfromJson(String s, String key) {
        JsonPath js = new JsonPath(s);
        return (js.getString(key));
    }
    public static JsonPath rawToJson(String s) {
        JsonPath js = new JsonPath(s);
        return js;
    }

    public static int extractIntfromJson(String s, String key) {
        JsonPath js = new JsonPath(s);
        return (js.getInt(key));
    }
}
