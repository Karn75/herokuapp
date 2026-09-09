package com.herokuapp.testCases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.google.common.io.Files;
import com.herokuapp.utilities.ReadConfig;

public class BaseClass {
	
	ReadConfig rc = new ReadConfig();
	public String url = rc.getURL();
	public String usename = rc.getUsername();
	public String password = rc.getPassword();
	
	public Logger logger = LogManager.getLogger();
	
	public WebDriver driver;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br) {
		
		if(br.contains("chrome")) {
			driver = new ChromeDriver();
		}
		else if(br.contains("edge")) {
			driver = new EdgeDriver();
		}
		else if(br.contains("firefox")) {
			driver = new FirefoxDriver();
		}
		else {
			System.out.println("Invalid browser name");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(url);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File trg = new File(System.getProperty("user.dir")+ "/screenshots/" + tName + ".png");
		Files.copy(src, trg);
		System.out.println("Screenshot taken");
	}

}
