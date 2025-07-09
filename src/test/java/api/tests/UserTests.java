package api.tests;

import api.base.BaseClass;
import api.payloads.User;
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
        User user = new User();
        user.setId(2);
        user.setFirstName("Laptop");
        user.setLastName("Dell");
        user.setEmail("laptopdell@gmail.com");
        user.setPhone("6789456787");
        user.setPassword("password123");
        user.setUserStatus(0);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        test.info("Payload : "+userList);

        Response response = given()
                .contentType("application/json")
                .body(userList)
                .when()
                .post(endpoints.getProperty("postApiCreateUser"));
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
