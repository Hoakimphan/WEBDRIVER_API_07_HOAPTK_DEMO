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

public class Exercise_04 {
	WebDriver driver;
	String newName, newDob, newAddress, newCity, newState, newPin, newPhone, newEmail, password;
	String editAddress, editCity, editState, editPin, editPhone, editEmail;
	String CustomerID;
	
	By nameByTxt = By.xpath("//input[@name ='name']");
	By dobbyTxt = By.xpath("//input[@id='dob']");
	By addressByTxt = By.xpath("//textarea[@name='addr']");
	By cityByTxt = By.xpath("//input[@name='city']");
	By stateByTxt = By.xpath("//input[@name='state']");
	By pinByTxt = By.xpath("//input[@name='pinno']");
	By mobileByTxt = By.xpath("//input[@name='telephoneno']");
	By emailByTxt = By.xpath("//input[@name='emailid']");
	By pwdByTxt = By.xpath("//input[@name='password']");
	By submitByBtn = By.xpath("//input[@name='sub']");
//	By btn_enabled = By.xpath("//button[@id='button-enabled']");
//	By slider_2 = By.xpath("//input[@id='slider-2']");
//	By radio_btn_disabled = By.xpath("//input[@id='radio-disabled']");
//	By checkbox_disabled = By.xpath("//input[@id='check-disbaled']");
	
	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", ".\\libs\\chromedriver.exe");
		driver = new ChromeDriver();
		//driver.get("http://demo.guru99.com/v4/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//input data
		newName = "Automation Demo";
		newDob = "2000-10-01";
//		newDob = "01-01-2000";
		newAddress = "Seatle";
		newCity = "LA";
		newState = "America";
		newPin = "123456";
		newPhone = "012345677";
		newEmail = "Auto" + randomEmail() + "@gmail.com";
		password = "123123";
		//
		editAddress = "Las Vegas";
		editCity = "Hollywood";
		editState = "LV";
		editPin = "321321";
		editPhone = "023456789";
		editEmail = "Testing" + randomEmail() + "@gmail.com";
	}
	@Test
	public void TC_01_New_Customer() throws InterruptedException
	{
		//get ID and pwd
		driver.get("http://demo.guru99.com/");
		//input email
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys("hoakimphan1996@gmail.com");
		//
		driver.get("http://demo.guru99.com/v4");
		//login
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr162169");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("qEburym");
		//click on submit
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		//click on New customer tab
		driver.findElement(By.xpath("//a[text() = 'New Customer']")).click();
		//input NEW data
		driver.findElement(nameByTxt).clear();
		driver.findElement(nameByTxt).sendKeys(newName);
//		driver.findElement(dobbyTxt).clear();
		driver.findElement(dobbyTxt).sendKeys(newDob);
		driver.findElement(addressByTxt).clear();
		driver.findElement(addressByTxt).sendKeys(newAddress);
		driver.findElement(cityByTxt).clear();
		driver.findElement(cityByTxt).sendKeys(newCity);
		driver.findElement(stateByTxt).clear();
		driver.findElement(stateByTxt).sendKeys(newState);
		driver.findElement(pinByTxt).clear();
		driver.findElement(pinByTxt).sendKeys(newPin);
		driver.findElement(mobileByTxt).clear();
		driver.findElement(mobileByTxt).sendKeys(newPhone);
		driver.findElement(emailByTxt).clear();
		driver.findElement(emailByTxt).sendKeys(newEmail);
		driver.findElement(pwdByTxt).clear();
		driver.findElement(pwdByTxt).sendKeys(password);
		driver.findElement(submitByBtn).click();
		//
		Thread.sleep(5000);
		//get information
		//get Customer ID
		CustomerID = driver.findElement(By.xpath("//td[text()='Customer ID']//following-sibling::td")).getText();
		//
		//compare the result
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']//following-sibling::td")).getText(), newName);
		//Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), newDob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), newAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), newCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), newState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), newPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), newPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), newEmail);
	}
	@Test
	public void TC_02_Edit_Customer()
	{
		// Edit customer
		driver.findElement(By.xpath("//a[text() = 'Edit Customer']")).click();
		//input cus ID
		driver.findElement(By.xpath("//input[@name = 'cusid']")).sendKeys(CustomerID);
		//click on submit
		driver.findElement(By.xpath("//input[@name = 'AccSubmit']")).click();
		//verify Customer Name and Address
		Assert.assertEquals(driver.findElement(nameByTxt).getAttribute("value"), newName);
		Assert.assertEquals(driver.findElement(addressByTxt).getText(), newAddress);
		//edit customer
		driver.findElement(addressByTxt).clear();
		driver.findElement(addressByTxt).sendKeys(editAddress);
		driver.findElement(cityByTxt).clear();
		driver.findElement(cityByTxt).sendKeys(editCity);
		driver.findElement(stateByTxt).clear();
		driver.findElement(stateByTxt).sendKeys(editState);
		driver.findElement(pinByTxt).clear();
		driver.findElement(pinByTxt).sendKeys(editPin);
		driver.findElement(mobileByTxt).clear();
		driver.findElement(mobileByTxt).sendKeys(editPhone);
		driver.findElement(emailByTxt).clear();
		driver.findElement(emailByTxt).sendKeys(editEmail);
		//click on submit
		driver.findElement(submitByBtn).click();
		//compare the result
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), editPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), editEmail);
	}
	public int randomEmail()
	{
		Random random = new Random();
		int number = random.nextInt(99999);
		return number;
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
