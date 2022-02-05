package StepDefinitions;
import static org.junit.Assert.*;
import POJO_serialization.Address;
import POJO_serialization.Location;
import Resources.APIRecources;
import Resources.TestDataBuild;
import Resources.Utils;
import com.sun.imageio.plugins.gif.GIFImageMetadata;
import files.ReusableMethods;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeTest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StepDefinitions extends Utils {

    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;
    static String place_id;
    @Given("Add Place Payload with {string} {string} {string} {string}")
    public void add_place_payload(String name, String language, String address, String phone) throws IOException {
        TestDataBuild data = new TestDataBuild();
        res=given().spec(requestSpecification())
                .body(data.addPlacePayLoad(name, language, address, phone));
    }
    @When("user calls {string} with {string} http request")
    public void user_calls_with_post_http_request(String resource, String method) {
        APIRecources API = APIRecources.valueOf(resource);
        System.out.println(API.getResource());
       // resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        if(method.equalsIgnoreCase("POST"))
            response =res.when().post(API.getResource());
        else if(method.equalsIgnoreCase("GET"))
            response =res.when().get(API.getResource());
    }
    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1) {
        assertEquals(response.getStatusCode(),200);
    }
    @And("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String ExpectedValue) {
        assertEquals(ReusableMethods.extractStringfromJson(response.asString(), keyValue), ExpectedValue);
    }

    @And("verify place_id created using {string}")
    public void verifyPlace_idCreatedMapsToUsing(String arg0) throws IOException {
        place_id = ReusableMethods.extractStringfromJson(response.asString(),"place_id");
        res = given().spec(requestSpecification()).queryParam("place_id",place_id);
    }

    @Given("DeletePlace Payload")
    public void deleteplacePayload() throws IOException {
        res = given().spec(requestSpecification()).body(deletePlacePayload(place_id));
    }
}
