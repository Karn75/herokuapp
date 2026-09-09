package com.herokuapp.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {
	
	public ExtentSparkReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	public void onStart(ITestContext testContext) {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName = System.getProperty("user.dir")+ "/test-output/" + "TestReport-" + timeStamp + ".html";
		
		htmlReporter = new ExtentSparkReporter(repName);
		htmlReporter.config().setDocumentTitle("Test Automation Report");
		htmlReporter.config().setReportName("Test Execution Report");
		htmlReporter.config().setTheme(Theme.STANDARD);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host", "Local-Host");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User", "Karn");
	  }
	
	  public void onTestSuccess(ITestResult tr) {
	   
		  logger = extent.createTest(tr.getName());
		  logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	  }

	  public void onTestFailure(ITestResult tr) {
		  
		  logger = extent.createTest(tr.getName());
		  logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		  
		  String filePath = System.getProperty("user.dir")+ "/screenshots/" + tr.getName() + ".png";
		  
		  File f = new File(filePath);
		  if(f.exists()) {
			  try {
				  logger.fail("The screenshot is: "+ logger.addScreenCaptureFromPath(filePath));
			  }catch(Exception e) {
				  e.printStackTrace();
			  }	
		  }
	  }
	  
	  public void onTestSkipped(ITestResult tr) {
		  
		  logger = extent.createTest(tr.getName());
		  logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	  }
	  
	  public void onFinish(ITestContext testContext) {
		  
		  extent.flush();
	  }

}
