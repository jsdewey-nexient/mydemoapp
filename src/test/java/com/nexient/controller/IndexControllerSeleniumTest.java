package com.nexient.controller;

import com.nexient.MyDemoAppApplication;
import com.nexient.SeleniumSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
	private WebDriver driver;

	@Before
	public void setUp() {
		this.appUrl = String.format("http://54.200.157.72:%d/", serverPort);
	}

	@Test
	public void testRalphStringIsPresentInChrome() {
		assertEquals(
				RALPH_STRING,
				this.getMainPText(DesiredCapabilities.chrome())
		);
	}

	@Test
	public void testRalphStringIsntPresentInChrome() {
		assertNotEquals(
				RALPH_STRING,
				this.getMainPText(DesiredCapabilities.chrome())
		);
	}

	@Test
	public void testRalphStringIsPresentInFirefox() {
		assertEquals(
				RALPH_STRING,
				this.getMainPText(DesiredCapabilities.firefox())
		);
	}

	@Test
	public void testRalphStringIsntPresentInFirefox() {
		assertNotEquals(
				RALPH_STRING,
				this.getMainPText(DesiredCapabilities.firefox())
		);
	}

	@Test
	public void testRalphStringIsPresentInInternetExplorer() {
		assertEquals(
				RALPH_STRING,
				this.getMainPText(DesiredCapabilities.internetExplorer())
		);
	}

	@Test
	public void testRalphStringIsntPresentInInternetExplorer() {
		assertNotEquals(
				RALPH_STRING,
				this.getMainPText(DesiredCapabilities.internetExplorer())
		);
	}

	@Test
	public void testRalphStringIsPresentInEdge() {
		assertEquals(
				RALPH_STRING,
				this.getMainPText(DesiredCapabilities.edge())
		);
	}

	@Test
	public void testRalphStringIsntPresentInEdge() {
		assertNotEquals(
				RALPH_STRING,
				this.getMainPText(DesiredCapabilities.edge())
		);
	}

	@After
	public void tearDown() {
		this.driver.close();
	}

	private String getMainPText(DesiredCapabilities desiredCapabilities) {
		this.driver = SeleniumSetup.getDriver(desiredCapabilities);
		this.driver.get(this.appUrl);
		return this.driver.findElement(By.id("main-p")).getText();
	}
}
