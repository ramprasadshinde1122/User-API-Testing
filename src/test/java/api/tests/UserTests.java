package api.tests;

import api.base.BaseClass;
import api.payloads.User;
import api.utilities.ExtentReportManager;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class UserTests extends BaseClass {

    @Test
    public void createUser(){
        test = new ExtentReportManager().extentTest(extentReports,"Create new user");
        User user = new User();
        user.setId(2);
        user.setUsername("laptop_dell");
        user.setFirstName("Laptop");
        user.setLastName("Dell");
        user.setEmail("laptopdell@gmail.com");
        user.setPhone("6789456787");
        user.setPassword("password123");
        user.setUserStatus(0);
        test.info("Payload : "+user);
        Response response = given()
                .contentType("application/json")
                .body(user)
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

    @Test(dependsOnMethods = "createUser")
    public void getUser(){
        test = new ExtentReportManager().extentTest(extentReports,"Get user");
        Response response = given()
                .pathParam("username","laptop_dell")
                .log().uri()
                .when()
                .get(endpoints.getProperty("getApiGetUser"));
        test.info("Response : "+response.asString());
        try {
            response.then().assertThat().statusCode(200);
            test.pass("Get user successful");
        } catch (AssertionError e) {
            test.fail("Status code validation failed: " + e.getMessage());
            Assert.fail("API Response validation failed");
        }
    }

    @Test
    public void createUserList() throws IOException {
        test = new ExtentReportManager().extentTest(extentReports,"Create user list");
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/apiResources/excelData/createUserDetails.xlsx");
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheet("Sheet1");
        List<User> userlist = new ArrayList<>();
        for (Row row: sheet){
            if(row.getRowNum() == 0) continue;
            User user = new User();
            user.setId((int) row.getCell(0).getNumericCellValue());
            user.setUsername(row.getCell(1).getStringCellValue());
            user.setFirstName(row.getCell(2).getStringCellValue());
            user.setLastName(row.getCell(3).getStringCellValue());
            user.setEmail(row.getCell(4).getStringCellValue());
            user.setPhone(String.valueOf(row.getCell(5).getNumericCellValue()));
            user.setPassword(row.getCell(6).getStringCellValue());
            user.setUserStatus((int) row.getCell(7).getNumericCellValue());
            userlist.add(user);
        }
        test.info("Payload : "+ userlist);
        Response response = given()
                .contentType("application/json")
                .body(userlist)
                .when()
                .post(endpoints.getProperty("postApiCreateUserList"));
        response.then().log().all();
        test.info("Response : "+response.asString());
        try {
            response.then().assertThat().statusCode(200);
            test.pass("User List Created Successfully");
        } catch (AssertionError e) {
            test.fail("Status code validation failed: " + e.getMessage());
            Assert.fail("API Response validation failed");
        }
    }
}
