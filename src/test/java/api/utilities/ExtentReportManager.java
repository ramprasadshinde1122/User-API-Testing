package api.utilities;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public  class ExtentReportManager {

    public ExtentReports extentReports(){
        String date = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("testReports/apiTestReport_"+date+".html");
        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("Environment","QA");
        extentReports.setSystemInfo("Project","API Automation");
        extentReports.setSystemInfo("Tester","Ramprasad Shinde");
        return extentReports;
    }

    public ExtentTest extentTest(ExtentReports extentReports, String testName){
        ExtentTest extentTest = extentReports.createTest(testName);
        extentTest.assignAuthor("Ramprasad Shinde");
        extentTest.assignCategory("Unit Testing");
        extentTest.assignDevice("Swagger tests");
        return extentTest;
    }


}
