package POJO_serialization;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

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
        RequestSpecification req =new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON).build();

        ResponseSpecification resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        RequestSpecification res=given().spec(req)
                .body(add);
        Response response =res.when().post("/maps/api/place/add/json").
                then().spec(resspec).extract().response();
        String r = response.asString();

    }
}
