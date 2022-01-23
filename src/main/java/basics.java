import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class basics {
    public static void main(String[] x){
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(payload.AddPlace())
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.18 (Ubuntu)").extract().asString();
        
        JsonPath js=new JsonPath(response); //for parsing Json
        String place_id = js.getString("place_id");
        System.out.println(place_id);

        given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
                .body(payload.UpdatePlace(place_id))
                .when().put("/maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));

        given().log().all().queryParam("key", "qaclick123").queryParam("place_id",place_id)
                .when().get("maps/api/place/add/json")
                .then().assertThat().log().all().
    }
}


/*given → all input data
when → submit api -- resource, http method
then → validate response
 */