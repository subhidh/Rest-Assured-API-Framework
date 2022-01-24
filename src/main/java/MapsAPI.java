import files.ReusableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class MapsAPI {
    public static void main(String[] x) throws IOException {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        //add new place to maps
        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                //.body(payload.AddPlace())
                .body(new String(Files.readAllBytes(Paths.get("C:\\RestAPI-Framework\\src\\main\\resources\\AddPlace.json"))))
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.18 (Ubuntu)").extract().asString();

        //store place ID
        JsonPath js=new JsonPath(response); //for parsing Json
        String place_id = js.getString("place_id");
        System.out.println(place_id);


        String new_address = "Jharkhand, India";
        //update place
        given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
                .body(payload.UpdatePlace(place_id, new_address))
                .when().put("/maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));

        //Check if new place added is correct or not
        String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id",place_id)
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200).extract().response().asString();


        String gotAddress = ReusableMethods.extractValuefromJson(getPlaceResponse, "address");

        Assert.assertEquals(gotAddress, new_address);
    }
}


/*given → all input data
when → submit api -- resource, http method
then → validate response
 */