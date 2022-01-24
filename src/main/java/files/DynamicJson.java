package files;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DynamicJson {

    @Test(dataProvider = "BookDetail")
    public void addBook(String isbn, String aisle) {
        RestAssured.baseURI = "http://216.10.245.166";

        //add book
        String response = given().log().all().header("Content-Type", "application/json").
                body(payload.AddBook(isbn, aisle)).
                when().post("Library/Addbook.php")
                .then().assertThat().statusCode(200).extract().response().asString();

        String id = ReusableMethods.extractValuefromJson(response, "ID");

        //delete book
        given().log().all().header("Content-Type", "application/json").
                body(payload.DeleteBook(id)).
                when().post("Library/DeleteBook.php")
                .then().log().all().assertThat().statusCode(200);


    }

    @DataProvider(name="BookDetail")
    public Object[][] getData() {
        return new Object[][]{{"afaf", "54456"},{"eqgdf","46546"},{"sfseg","54645"}} ;
    }
}
