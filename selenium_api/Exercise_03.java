package selenium_api;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Exercise_03 {
	WebDriver driver;
	By email_user = By.xpath("//input[@name='user_email']");
	By pwd = By.xpath("//input[@name='user_pass']");
	By age_under_18 = By.xpath("//input[@id='under_18']");
	By education = By.xpath("//textarea[@id='edu']");
	By biography = By.xpath("//textarea[@id='bio']");
	By job_role_1 = By.xpath("//select[@id='job1']");
	By job_role_2 = By.xpath("//select[@id='job2']");
	By interests_developer = By.xpath("//input[@id='development']");
	By slider_1 = By.xpath("//input[@id='slider-1']");
	By btn_disabled = By.xpath("//button[@id='button-disabled']");
	By btn_enabled = By.xpath("//button[@id='button-enabled']");
	By slider_2 = By.xpath("//input[@id='slider-2']");
	By radio_btn_disabled = By.xpath("//input[@id='radio-disabled']");
	By checkbox_disabled = By.xpath("//input[@id='check-disbaled']");

	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", ".\\libs\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	@Test
	public void TestScript_01()
	{
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		if(isControlDisplayed(email_user))
		{
			driver.findElement(email_user).sendKeys("Automation Testing");
		}
		if(isControlDisplayed(education))
		{
			driver.findElement(education).sendKeys("Automation Testing");
		}
		if(isControlDisplayed(age_under_18))
		{
			driver.findElement(age_under_18).click();
		}
	}
	@Test
	public void TestScript_02()
	{
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		//check enabled
		assertTrue(isControlEnabled(email_user));
		assertTrue(isControlEnabled(age_under_18));
		assertTrue(isControlEnabled(education));
		assertTrue(isControlEnabled(job_role_1));
		assertTrue(isControlEnabled(interests_developer));
		assertTrue(isControlEnabled(slider_1));
		assertTrue(isControlEnabled(btn_enabled));
		//check disabled
		assertFalse(isControlEnabled(pwd));
		assertFalse(isControlEnabled(radio_btn_disabled));
		assertFalse(isControlEnabled(biography));
		assertFalse(isControlEnabled(job_role_2));
		assertFalse(isControlEnabled(checkbox_disabled));
		assertFalse(isControlEnabled(slider_2));
		assertFalse(isControlEnabled(btn_disabled));
		
	}
	@Test
	public void TestScript_03()
	{
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		driver.findElement(age_under_18).click();
		driver.findElement(interests_developer).click();
		assertTrue(isControlSelected(age_under_18));
		assertTrue(isControlSelected(interests_developer));
		System.out.println("check case radio & checkbox is selected or not!");
		if(!isControlSelected(age_under_18))
		{
			driver.findElement(age_under_18).click();
		}
		if(!isControlSelected(interests_developer))
		{
			driver.findElement(interests_developer).click();
		}
	}
	public boolean isControlDisplayed(By by)
	{
		WebElement element = driver.findElement(by);
		if(element.isDisplayed())
		{
			System.out.println("Element: "+by+" is displayed!");
			return true;
		}
		else
		{
			System.out.println(("Element + "+by+"is NOT displayed!"));
			return false;
		}
	}
	public boolean isControlEnabled(By by)
	{
		WebElement element = driver.findElement(by);
		if(element.isEnabled())
		{
			System.out.println("Element: "+by+" is enabled!");
			return true;
		}
		else
		{
			System.out.println(("Element + "+by+"is NOT enabled!"));
			return false;
		}
	}
	public boolean isControlSelected(By by)
	{
		WebElement element = driver.findElement(by);
		if(element.isSelected())
		{
			System.out.println("Element: "+by+" is selected!");
			return true;
		}
		else
		{
			System.out.println(("Element + "+by+"is NOT selected!"));
			return false;
		}
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
