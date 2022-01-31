package OAuth2;

import static io.restassured.RestAssured.*;

import files.ReusableMethods;
import io.restassured.RestAssured;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

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

        String url = "https://rahulshettyacademy.com/getCourse.php?state=asfddbf&code=4%2F0AX4XfWh2_EbgJ9CvdmiEaRC-l4bGmey4PtTUMN-gX0ogqF4py_E4evodHo8sfk8t0p67zg&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=5&prompt=consent#";
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

        String response = given().queryParam("access_token",access_token)
                .when().get("https://rahulshettyacademy.com/getCourse.php")
                .asString();
        System.out.println(response);
    }
}
