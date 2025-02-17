package ecommerce.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
public static ExtentReports Reports() {
	String filepath=System.getProperty("user.dir")+"//Reports//index.html";
ExtentSparkReporter reporter=new ExtentSparkReporter(filepath);
reporter.config().setReportName("Web Automation Results");
reporter.config().setDocumentTitle("Test Results");

ExtentReports extent =new ExtentReports();
extent.attachReporter(reporter);
extent.setSystemInfo("Tester", "Manjushree");
return extent;
}
}
