package OAuth2;

import static io.restassured.RestAssured.*;

import POJO.Api;
import POJO.GetCourse;
import POJO.WebAutomation;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OAuth20 {
    public static void main(String[] args) throws InterruptedException {

        /*System.setProperty("webdriver.edge.driver","C:\\RestAPI-Framework\\src\\main\\resources\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=asfddbf");
        WebElement emailKey = driver.findElement(By.cssSelector("input[type='email']"));
        emailKey.sendKeys("contact.subhidh");
        emailKey.sendKeys(Keys.ENTER);
        Thread.sleep(10000);
        WebElement passwordKeys = driver.findElement(By.cssSelector("input[type='password']"));
        passwordKeys.sendKeys("Subhidh@28");
        passwordKeys.sendKeys(Keys.ENTER);
        Thread.sleep(3000);*/

        String[] courseTitles= { "Selenium Webdriver Java","Cypress","Protractor"};

        String url = "https://rahulshettyacademy.com/getCourse.php?state=asfddbf&code=4%2F0AX4XfWjoPUfvCTuPh9VMODFRd0Obt19dTs-reZ3SPwKuByPFPOxAg-o701gJht5bbZRmZQ&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=5&prompt=none#";
        String code = url.split("code=")[1].split("&scope")[0];
        System.out.println(code);

        String access_token = given()
                .urlEncodingEnabled(false)
                .queryParam("code",code)
                .queryParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .queryParam("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
                .queryParam("grant_type","authorization_code")
                .when().post("https://www.googleapis.com/oauth2/v4/token")
                .asString();
        access_token = ReusableMethods.extractStringfromJson(access_token,"access_token");

        GetCourse response = given().queryParam("access_token",access_token)
                .expect().defaultParser(Parser.JSON)
                .when().get("https://rahulshettyacademy.com/getCourse.php")
                .as(GetCourse.class);
        System.out.println(response.getLinkedIn());
        System.out.println(response.getCourses().getApi().get(0).getCourseTitle());

        List<Api> apiCourse = response.getCourses().getApi();
        for(int i=0; i<apiCourse.size();i++)
        {
            if(apiCourse.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
            {
                apiCourse.get(i).getPrice();
            }
        }

        //Get the course names of WebAutomation
        ArrayList<String> a= new ArrayList<String>();


        List<WebAutomation> w=response.getCourses().getWebAutomation();

        for(int j=0;j<w.size();j++)
        {
            a.add(w.get(j).getCourseTitle());
        }

        List<String> expectedList=	Arrays.asList(courseTitles);

        Assert.assertTrue(a.equals(expectedList));


    }
}
