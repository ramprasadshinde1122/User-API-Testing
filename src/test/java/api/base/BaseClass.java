package api.base;

import api.utilities.ExtentReportManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import java.io.FileInputStream;
import java.io.IOException;
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

    @AfterSuite
    public void afterSuite_setup(){
        extentReports.flush();
    }
}
