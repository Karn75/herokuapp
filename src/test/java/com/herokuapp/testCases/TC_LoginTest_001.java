package com.herokuapp.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {
	
	@Test
	public void loginTest() throws IOException {
		
		logger.info("url entered");
		
		LoginPage lp = new LoginPage(driver);
		
		lp.setUsername(usename);
		logger.info("username entered");
		
		lp.setPassword(password);
		logger.info("password entered");
		
		lp.clickLogin();
		logger.info("login button clicked");
		
		String title = driver.getTitle();
		System.out.println("Title is: "+title);
		
		if(title.equals("The Internet")) {
			Assert.assertTrue(true);
			logger.info("login passed");
		}
		else {
			captureScreen(driver, "loginTest");
			Assert.assertTrue(false);
			logger.info("login failed");
		}
	}

}
