package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_02_Login {
	WebDriver driver;

	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", ".\\libs\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void Verify_URL_and_Title() {
		driver.get("http://live.guru99.com");
		String hometitle = driver.getTitle();
		Assert.assertEquals(hometitle, "Home page");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//div[@class='buttons-set']//a[@title='Create an Account']")).click();
		driver.navigate().back();
		String URL1 = driver.getCurrentUrl();
		Assert.assertEquals(URL1, "http://live.guru99.com/index.php/customer/account/login/");
		driver.get("http://live.guru99.com/index.php/customer/account/create/");
		String URL2 = driver.getCurrentUrl();
		Assert.assertEquals(URL2, "http://live.guru99.com/index.php/customer/account/create/");
	}

	// Test Script 02: Login empty
	public void Login_Empty() throws InterruptedException {
		driver.get("http://live.guru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("send2")).click();// click login btn
		Thread.sleep(3000);
		String err_email_msg = driver.findElement(By.id("advice-required-entry-email")).getText();
		Assert.assertEquals(err_email_msg, "This is a required field.");
		String err_pwd_msg = driver.findElement(By.id("advice-required-entry-pass")).getText();
		Assert.assertEquals(err_pwd_msg, "This is a required field.");
	}

	// Test Script 03: Login with Email invalid
	public void Login_with_Email_invalid() {
		driver.get("http://live.guru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("123434234@12312.123123");
		driver.findElement(By.id("send2")).click();// click login btn
		String err_email_msg = driver.findElement(By.id("advice-validate-email-email")).getText();
		Assert.assertEquals(err_email_msg, "Please enter a valid email address. For example johndoe@domain.com.");

	}

	// Test Script 04: Login with Password incorrect
	public void Login_with_Password_incorrect() {
		driver.get("http://live.guru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123");
		driver.findElement(By.id("send2")).click();// click login btn
		String err_pwd_msg = driver.findElement(By.id("advice-validate-password-pass")).getText();
		Assert.assertEquals(err_pwd_msg, "Please enter 6 or more characters without leading or trailing spaces.");
	}
	// Test Script 05: Create an account

	public void Create_an_account() throws InterruptedException {
		String firstName = "Apple", middleName = "Peach", lastName = "Pie",
				email = "Apple" + randomEmail() + "@gmail.com", passwod = "123123";
		driver.get("http://live.guru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//div[@class='buttons-set']//a[@title='Create an Account']")).click();
		driver.findElement(By.xpath("//input[@id = 'firstname']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id = 'middlename']")).sendKeys(middleName);
		driver.findElement(By.xpath("//input[@id = 'lastname']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id = 'email_address']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id = 'password']")).sendKeys(passwod);
		driver.findElement(By.xpath("//input[@id = 'confirmation']")).sendKeys(passwod);
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		String success_msg = driver
				.findElement(By.xpath("//span[contains(text(),'Thank you for registering with Main Website Store.')]"))
				.getText();
		Assert.assertEquals(success_msg, "Thank you for registering with Main Website Store.");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='links']//a[@title='Log Out']")).click();
		Thread.sleep(5000);
		String URL_homepage = driver.getCurrentUrl();
		Assert.assertEquals(URL_homepage, "http://live.guru99.com/index.php/");
	}

	// Test Script 04: Login with Password < 6 character
	public void Password_Less_Than_6() {
		driver.get("http://live.guru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//input[@id = 'email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id = 'pass']")).sendKeys("123123123");
		driver.findElement(By.xpath("//button[@id = 'send2']")).click();
		String err_mess = driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).getText();
		Assert.assertEquals(err_mess, "Invalid login or password.");
	}

	public int randomEmail() {
		Random random = new Random();
		int number = random.nextInt(99999);
		System.out.println("Random number: =" + number);
		return number;
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
