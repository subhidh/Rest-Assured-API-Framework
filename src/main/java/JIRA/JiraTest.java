package JIRA;

import files.ReusableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import org.testng.Assert;

import java.io.File;

import static io.restassured.RestAssured.*;

public class JiraTest {
    public static void main(String[] x) {
        RestAssured.baseURI = "http://localhost:8080/";

        //sign-in
        SessionFilter sessionFilter = new SessionFilter();
        String sessionID = given().header("Content-Type", "application/json")
                .body(payload.JiraAuthentication("subhidhagarwal", "Subhidh@28"))
                .filter(sessionFilter)
                .when().post("rest/auth/1/session")
                .then().assertThat().statusCode(200).extract().response().asString();

        //add-comment
        String expectedMessage = "Updated comment from Framework";
        String addResponse = given().pathParam("IssueID", "10003")
                .header("Content-Type", "application/json")
                .body(payload.JiraUpdateComment(expectedMessage))
                .filter(sessionFilter)
                .when().post("rest/api/2/issue/{IssueID}/comment")
                .then().assertThat().statusCode(201).extract().response().asString();

        String commentID = ReusableMethods.extractStringfromJson(addResponse, "id");
        //attachments
        given().header("X-Atlassian-Token","no-check").filter(sessionFilter)
                .pathParam("key", "10003")
                .header("Content-Type", "multipart/form-data")
                .multiPart("file", new File("C:\\RestAPI-Framework\\src\\main\\java\\JIRA\\jira.txt"))
                .when().post("rest/api/2/issue/{key}/attachments")
                .then().log().all().assertThat().statusCode(200);

        //validating comment
        String issueDetails = given().filter(sessionFilter).pathParam("IssueID", "10003")
                .queryParam("fields","comment")
                .when().log().all().get("/rest/api/2/issue/{IssueID}")
                .then().extract().response().asString();
        int countComments = ReusableMethods.extractIntfromJson(issueDetails, "fields.comment.comments.size()");

        for(int i=0;i<countComments;i++) {
            String commentIDissued = ReusableMethods.extractStringfromJson(issueDetails, "fields.comment.comments["+i+"].id");
            if(commentIDissued.equalsIgnoreCase(commentID)) {
                String Actualmessage = ReusableMethods.extractStringfromJson(issueDetails, "fields.comment.comments[" + i + "].body");
                Assert.assertEquals(Actualmessage, "Updated comment from Framework");
                break;
            }
        }


    }
}
