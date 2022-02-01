package POJO_serialization;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

public class MapsAPI {
    public static void main(String[] x) {

        Address add = new Address();
        add.setAccuracy(50);
        add.setAddress("Bajla Chowk, Castiers Town");
        Location loc = new Location();
        loc.setLat(-45.356);
        loc.setLng(-56.312);
        add.setLocation(loc);
        add.setName("Frontline House");
        add.setPhone_number("456456465");
        List<String> types = new ArrayList<>();
        types.add("shoe");
        types.add("shop");
        add.setTypes(types);
        add.setWebsite("google.com");
        add.setLanguage("English");

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        Response response = given().log().all().queryParam("key", "qaclick123")
                .body(add)
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).extract().response();
        String r = response.asString();

    }
}
