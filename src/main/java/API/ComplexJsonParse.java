package API;/*
{
"dashboard": {
"purchaseAmount": 910,
"website": "rahulshettyacademy.com"
},
"courses": [
{
"title": "Selenium Python",
"price": 50,
"copies": 6
},
{
"title": "Cypress",
"price": 40,
"copies": 4
},
{
"title": "RPA",
"price": 45,
"copies": 10
}]}
1. Print No of courses returned by API
2.Print Purchase Amount
3. Print Title of the first course
4. Print All course titles and their respective Prices
5. Print no of copies sold by RPA Course
6. Verify if Sum of all Course prices matches with Purchase Amount
* */

import files.payload;
import io.restassured.path.json.JsonPath;
import static org.junit.Assert.*;

public class ComplexJsonParse {
    public static void main(String[] x) {

        JsonPath js = new JsonPath(payload.ComplexJson());

        System.out.println("No of Courses = "+js.getInt("courses.size()")+"\n"+
                "Total Amount = "+js.getInt("dashboard.purchaseAmount")+"\n"+
                "First Course = "+js.getString("courses[0].title"));

        for(int i=0 ;i<js.getInt("courses.size()");i++) {
            System.out.println(js.getString("courses["+i+"].title"));
            System.out.println(js.get("courses["+i+"].price").toString());
        }

        for(int i=0; i<js.getInt("courses.size()");i++) {
            if((js.get("courses["+i+"].title").toString()).equalsIgnoreCase("RPA")) {
                System.out.println(js.getString("courses[" + i + "].copies"));
                break;
            }
        }

        int sum = 0;
        for(int i=0; i<js.getInt("courses.size()");i++) {
            sum+=(js.getInt("courses["+i+"].price") * js.getInt("courses["+i+"].copies"));
        }
        assertEquals(sum, js.getInt("dashboard.purchaseAmount"));

    }
}
