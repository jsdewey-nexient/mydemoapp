package com.nexient;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class SeleniumSetup {
	public static WebDriver getDriver(DesiredCapabilities capabilities) {
		Properties properties = SeleniumSetup.loadProperties();
		String useSeleniumGrid = properties.getProperty("selenium.grid.enable");
		String hubUrl = properties.getProperty("selenium.grid.huburl");

		if (useSeleniumGrid.toLowerCase().equals("true")) {
			return SeleniumSetup.getRemoteWebDriver(hubUrl, capabilities);
		}

		return SeleniumSetup.getLocalWebDriver(properties, capabilities);
	}

	private static Properties loadProperties() {
		InputStream stream = SeleniumSetup.class
				.getClassLoader()
				.getResourceAsStream("application.properties");
		Properties properties = new Properties();

		try {
			properties.load(stream);
		} catch (IOException e) {
			System.out.print(
					"The application.properties file is missing from the " +
							"CLASSPATH; check src/test/resources."
			);
			System.exit(1);
		}

		return properties;
	}

	private static RemoteWebDriver getRemoteWebDriver(
			String hubUrl,
			DesiredCapabilities capabilities
	) {
		try {
			return new RemoteWebDriver(new URL(hubUrl), capabilities);
		} catch (MalformedURLException e) {
			System.out.print(
					String.format(
							"Selenium Grid URL is malformed. Stack " +
									"trace: \n %s",
							e.getMessage()
					)
			);
			System.exit(1);
		}
		return null; // To get rid of the annoying red squiggle.
	}

	private static WebDriver getLocalWebDriver(
			Properties properties,
			DesiredCapabilities capabilities
	) {
		String browser = capabilities.getBrowserName();
		WebDriver driver;

		switch (browser) {
			case BrowserType.CHROME:
				System.setProperty(
						"webdriver.chrome.driver",
						properties.getProperty("selenium.local.chromedriver.path")
				);
				driver = new ChromeDriver();
				break;
			case BrowserType.FIREFOX:
				driver = new FirefoxDriver();
				break;
			case BrowserType.IE:
				System.setProperty(
						"webdriver.ie.driver",
						properties.getProperty("selenium.local.iedriver.path")
				);
				driver = new InternetExplorerDriver();
				break;
			case BrowserType.EDGE:
				System.setProperty(
						"webdriver.edge.driver",
						properties.getProperty("selenium.local.edgedriver.path")
				);
				driver = new EdgeDriver();
				break;
			default:
				throw new IllegalArgumentException("WebDriver not recognized.");
		}

		return driver;
	}
}
