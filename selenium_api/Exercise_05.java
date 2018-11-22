package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Exercise_05 {
	WebDriver driver;
	JavascriptExecutor javaExecutor;
	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", ".\\libs\\chromedriver.exe");
		driver = new ChromeDriver();
		javaExecutor = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	@Test
	public void TC_01()
	{
		driver.get("http://live.guru99.com/");
		 WebElement myAccount = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
		clickElementByJavascript(myAccount);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
		WebElement createAnAccount = driver.findElement(By.xpath("//a[@title='Create an Account']"));
		clickElementByJavascript(createAnAccount);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
		
	}
	@Test
	public void TC_02() throws InterruptedException
	{
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		WebElement checkbox1 = driver.findElement(By.xpath("//label[contains(text(),'Dual-zone air conditioning')]/preceding-sibling::input"));
		clickElementByJavascript(checkbox1);
		Thread.sleep(3000);
		Assert.assertTrue(checkbox1.isSelected());
		clickElementByJavascript(checkbox1);
		Assert.assertFalse(checkbox1.isSelected());
	}
	@Test
	public void TC_03() throws InterruptedException
	{
		driver.get("https://demos.telerik.com/kendo-ui/styling/radios");
		WebElement radio_btn = driver.findElement(By.xpath("//label[contains(text(),'2.0 Petrol, 147kW')]/preceding-sibling::input"));
		clickElementByJavascript(radio_btn);
		Thread.sleep(3000);
		Assert.assertTrue(radio_btn.isSelected());
		WebElement radio_btn_diff = driver.findElement(By.xpath("//label[contains(text(),'1.8 Petrol, 118kW')]//preceding-sibling::input"));
		clickElementByJavascript(radio_btn_diff);
		Assert.assertFalse(radio_btn.isSelected());
		Thread.sleep(3000);
		clickElementByJavascript(radio_btn);
		Assert.assertTrue(radio_btn.isSelected());
		Thread.sleep(3000);
	}
	@Test
	public void TC_04() throws InterruptedException
	{
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		WebElement alert_btn = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
		//scroll to alert btn
		javaExecutor.executeScript("arguments[0].scrollIntoView(true)", alert_btn);
		Thread.sleep(3000);
		//click on alert btn
		clickElementByJavascript(alert_btn);
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		Thread.sleep(3000);
		String textOnAlert = alert.getText();
		Assert.assertEquals(textOnAlert, "I am a JS Alert");
		alert.accept();
		Thread.sleep(3000);
		Assert.assertEquals(driver.findElement(By.xpath("//p[text()='You clicked an alert successfully ']")).getText().trim(), "You clicked an alert successfully");
	}
	@Test
	public void TC_05() throws InterruptedException
	{
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		WebElement js_confirm = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
		//scroll to alert btn
		javaExecutor.executeScript("arguments[0].scrollIntoView(true)", js_confirm);
		Thread.sleep(3000);
		//click on alert btn
		clickElementByJavascript(js_confirm);
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		Thread.sleep(3000);
		String textOnAlert = alert.getText();
		Assert.assertEquals(textOnAlert, "I am a JS Confirm");
		alert.dismiss();;
		Thread.sleep(3000);
		Assert.assertEquals(driver.findElement(By.xpath("//p[text()='You clicked: Cancel']")).getText().trim(), "You clicked: Cancel");
	}
	@Test
	public void TC_06() throws InterruptedException
	{
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		WebElement js_prompt = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));
		//scroll to alert btn
		javaExecutor.executeScript("arguments[0].scrollIntoView(true)", js_prompt);
		Thread.sleep(3000);
		//click on alert btn
		clickElementByJavascript(js_prompt);
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		Thread.sleep(3000);
		String textOnAlert = alert.getText();
		Assert.assertEquals(textOnAlert, "I am a JS prompt");
		String key = "Automation";
		alert.sendKeys(key);
		alert.accept();
		Thread.sleep(3000);
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText().trim(), "You entered:" + " " + key);
	}
	@Test
	public void TC_07() throws InterruptedException
	{
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		Assert.assertEquals(driver.findElement(By.xpath("//h3[text()='Basic Auth']//following-sibling::p")).getText().trim(), "Congratulations! You must have the proper credentials.");
	}
	
	public void clickElementByJavascript(WebElement element)
	{
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].click();", element);
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
