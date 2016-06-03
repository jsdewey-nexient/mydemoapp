package com.nexient.controller;

import com.nexient.MyDemoAppApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * @author Joel Dewey
 *         Date: 6/3/2016
 */
public class IndexControllerSeleniumTest {
	public static final String RALPH_STRING = "My cat's breath smells like " +
			"cat food!";

	private int serverPort = 8083;
	private String appUrl;
	private RemoteWebDriver driver;

	@Before
	public void setUp() {
		this.appUrl = String.format("http://localhost:%d/", serverPort);
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		try {
			this.driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			fail("Something is wrong with the RemoteWebDriver URL!");
		}
		this.driver.get(this.appUrl);
	}

	@Test
	public void testRalphStringIsPresent() {
		String pageText = this.driver.findElement(By.id("main-p")).getText();
		assertEquals(RALPH_STRING, pageText);
	}

	@Test
	public void testRalphStringIsntPresent() {
		String pageText = this.driver.findElement(By.id("main-p")).getText();
		assertNotEquals(RALPH_STRING, pageText);
	}

	@After
	public void tearDown() {
		this.driver.close();
	}
}
