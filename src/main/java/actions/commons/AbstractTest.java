package actions.commons;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.openqa.jetty.log.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;

public class AbstractTest {

	WebDriver driver;

	protected final Log log;

	protected AbstractTest() {
		log = LogFactory.getLog(getClass());
	}

	protected synchronized WebDriver openMultiBrowsers(String browser, String version) {
		if (browser.equalsIgnoreCase("chrome")) {
//			ChromeDriverManager.getInstance().version(version).setup();
			System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("start-maximized");
			chromeOptions.addArguments("disable-infobars");
			driver = new ChromeDriver(chromeOptions);
		} else if (browser.equalsIgnoreCase("firefox")) {
			FirefoxDriverManager.getInstance().version(version).setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("chromeheadless")) {
			ChromeDriverManager.getInstance().version(version).setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1400,800");
			log.info("-----------Open chrome browser------------");
			driver = new ChromeDriver(options);
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

	public boolean verifyPassed(boolean condition, String messagePass, String messageFail) {
		boolean result = true;
		try {
			if (condition == true) {
				log.info(messagePass);
			} else {
				log.info(messageFail);
			}
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			result = false;
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return result;
	}

	public boolean verifyFailed(boolean condition, String messagePass, String messageFail) {
		boolean result = true;
		try {
			if (condition == false) {
				log.info(messagePass);
			} else {
				log.info(messageFail);
			}
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			result = false;
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return result;
	}

	public boolean verifyEqual(Object actual, Object expected, String messagePass, String messageFail) {
		boolean result = true;
		try {
			if (actual.equals(expected)) {
				log.info(messagePass);
			} else {
				log.info(messageFail);
			}
			Assert.assertEquals(actual, expected);
		} catch (Throwable e) {
			result = false;
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return result;
	}

	public String randomEmail() {
		Random random = new Random();
		int number = random.nextInt(999999);
		String randomEmail = "automation" + number + "@gmail.com";
		return randomEmail;
	}
}
