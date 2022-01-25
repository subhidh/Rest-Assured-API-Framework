package JIRA;

import files.ReusableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

import java.io.File;

import static io.restassured.RestAssured.*;

public class JiraTest {
    public static void main(String[] x) {
        RestAssured.baseURI = "http://localhost:8080/";

        SessionFilter sessionFilter = new SessionFilter();
        String sessionID = given().header("Content-Type", "application/json")
                .body(payload.JiraAuthentication("subhidhagarwal", "Subhidh@28"))
                .filter(sessionFilter)
                .when().post("rest/auth/1/session")
                .then().assertThat().statusCode(200).extract().response().asString();
        String value = ReusableMethods.extractValuefromJson(sessionID, "session.value");
        String name = ReusableMethods.extractValuefromJson(sessionID, "session.name");


        given().pathParam("IssueID", "10003")
                .log().all()
                .header("Content-Type", "application/json")
                .body(payload.JiraUpdateComment("Updated comment from Framework"))
                .filter(sessionFilter)
                .when().post("rest/api/2/issue/{IssueID}/comment")
                .then().log().all().assertThat().statusCode(201);

        given().header("X-Atlassian-Token","no-check").filter(sessionFilter)
                .pathParam("key", "10003")
                .header("Content-Type", "multipart/form-data")
                .multiPart("file", new File("C:\\RestAPI-Framework\\src\\main\\java\\JIRA\\jira.txt"))
                .when().post("rest/api/2/issue/{key}/attachments")
                .then().log().all().assertThat().statusCode(200);

    }
}
