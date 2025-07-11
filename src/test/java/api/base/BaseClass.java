package api.base;

import api.utilities.ExtentReportManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import io.restassured.RestAssured;

public class BaseClass {

    public ExtentTest test;
    public ExtentReports extentReports;
    public Properties endpoints;

    @BeforeSuite
    public void beforeSuite_setup() throws IOException {
        extentReports = new ExtentReportManager().extentReports();
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/endPointes.properties");
       endpoints = new Properties();
        endpoints.load(fileInputStream);
        RestAssured.baseURI = endpoints.getProperty("baseUrl");
    }

    @BeforeMethod
    public void initializeExtentTest(Method method) {
        String testName = method.getName(); // Gets the test method name
        test = new ExtentReportManager().extentTest(extentReports, testName);
    }

    @AfterSuite
    public void afterSuite_setup(){
        extentReports.flush();
    }
}
