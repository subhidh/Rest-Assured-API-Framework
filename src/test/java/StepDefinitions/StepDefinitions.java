package StepDefinitions;
import static org.junit.Assert.*;
import POJO_serialization.Address;
import POJO_serialization.Location;
import Resources.TestDataBuild;
import Resources.Utils;
import files.ReusableMethods;
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

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StepDefinitions extends Utils {
    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;
    @Given("Add Place Payload")
    public void add_place_payload() throws FileNotFoundException {
        TestDataBuild data = new TestDataBuild();
        res=given().spec(requestSpecification())
                .body(data.addPlacePayLoad());
    }
    @When("user calls {string} with Post http request")
    public void user_calls_with_post_http_request(String string) {
        resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        response =res.when().post("/maps/api/place/add/json").
                then().spec(resspec).extract().response();
    }
    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1) {
        assertEquals(response.getStatusCode(),200);
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String ExpectedValue) {
        String resp = response.asString();
        assertEquals(ReusableMethods.extractStringfromJson(resp, keyValue), ExpectedValue);
    }
}
