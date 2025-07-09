package api.base;

import api.utilities.ExtentReportManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseClass {

    public ExtentTest test;
    public ExtentReports extentReports;

    @BeforeSuite
    public void beforeSuite_setup(){
        extentReports = new ExtentReportManager().extentReports();
    }

    @AfterSuite
    public void afterSuite_setup(){
        extentReports.flush();
    }
}
