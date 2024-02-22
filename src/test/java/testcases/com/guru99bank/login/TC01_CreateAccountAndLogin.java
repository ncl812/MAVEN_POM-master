package testcases.com.guru99bank.login;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import actions.commons.AbstractTest;
import actions.commons.TestListener;
import actions.pages.HomePage;
import actions.pages.LoginPage;
import actions.pages.RegisterPage;

@Listeners({TestListener.class})
public class TC01_CreateAccountAndLogin extends AbstractTest {
	WebDriver driver;
	String url = "https://demo.guru99.com/V4";
	String email, user, pass;

	@Parameters({ "browser", "version" })
	@BeforeClass
	public void beforeClass(String browser, String version) {
		driver = openMultiBrowsers(browser, version);
		email = randomEmail();

	}

	@Test
	public void TC01_CreateAnAccount() {
		// Login Page
		System.out.println("Step 01 - Navigate to Login page");
		loginPage = new LoginPage(driver);
		loginPage.openLoginPage(url);

		System.out.println("Step 02 - Click Here");
		loginPage.clickHere();

		// Register page
		System.out.println("Step 03 - Enter email");
		registerPage = new RegisterPage(driver);
		registerPage.enterEmailAddress(email);

		System.out.println("Step 04 - Click Submit");
		registerPage.clickSubmit();

		System.out.println("Step 05 - Get User and Password");
		user = registerPage.getUserInfo();
		pass = registerPage.getPassInfo();
	}

	@Test
	public void TC02_Login() {
		System.out.println("Step 01 - Navigate to Login page");
		loginPage = new LoginPage(driver);
		loginPage.openLoginPage(url);

		System.out.println("Step 02 - Enter user and password");
		loginPage.inputUserIdAndPassword(user, pass);

		System.out.println("Step 03 - Click Login");
		loginPage.clickLogin();

		System.out.println("Step 04 - Verify Home is displayed");
		homePage = new HomePage(driver);
		homePage.verifyHomePageIsDisplayed(user);
	}

	@AfterClass
	public void afterClass() {
//		driver.quit();
	}

	private LoginPage loginPage;
	private RegisterPage registerPage;
	private HomePage homePage;

}
