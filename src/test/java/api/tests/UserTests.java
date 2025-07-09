package api.tests;

import api.base.BaseClass;
import api.utilities.ExtentReportManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;


public class UserTests extends BaseClass {

    @Test
    public void createUser(){
        test = new ExtentReportManager().extentTest(extentReports,"Create new user");
        HashMap<String, Object> user = new HashMap<>();
        user.put("id", 1);
        user.put("username", "pavanshinde");
        user.put("firstName", "Pavan");
        user.put("lastName", "Shinde");
        user.put("email", "pavanshinde@gmail.com");
        user.put("password", "pass@123");
        user.put("phone", "9234566787");
        user.put("userStatus", 0);
        List<HashMap<String, Object>> userList = new ArrayList<>();
        userList.add(user);
        test.info("Payload : "+userList);

        Response response = given()
                .contentType("application/json")
                .body(userList)
                .when()
                .post("https://petstore.swagger.io/v2/user/createWithList");
        response.then().log().all();
        test.info("Response : "+response.asString());
        try {
            response.then().assertThat().statusCode(200);
            test.pass("User Created Successfully");
        } catch (AssertionError e) {
            test.fail("Status code validation failed: " + e.getMessage());
            Assert.fail("API Response validation failed");
        }
    }
}
