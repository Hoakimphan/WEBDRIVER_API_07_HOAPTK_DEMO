package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Exercise_08 {
	WebDriver driver;
	String new_first_name, new_middle_name, new_last_name, new_email_address, new_pwd, new_confirm_pwd;
	JavascriptExecutor javaExecutor;
	By first_name = By.xpath("//input[@id = 'firstname']");
	By middle_name = By.xpath("//input[@id = 'middlename']");
	By last_name = By.xpath("//input[@id = 'lastname']");
	By email_address = By.xpath("//input[@id = 'email_address']");
	By pwd = By.xpath("//input[@id = 'password']");
	By confirm_pwd = By.xpath("//input[@id = 'confirmation']");
	By submit_btn = By.xpath("//button[@title='Register']");
	@BeforeTest
	public void setUp() {
		//System.setProperty("webdriver.chrome.driver", ".\\libs\\chromedriver.exe");
		driver = new FirefoxDriver();
		javaExecutor = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//input data
				new_first_name = "Automation";
				new_middle_name = "ad";
				new_last_name = "Demo";
				new_email_address = "Auto" + randomEmail() + "@gmail.com";
				new_pwd = "Ap1234";
				new_confirm_pwd = "Ap1234";
	}
//	@Test
//	public void TC_01() throws InterruptedException
//	{
//		driver.get("http://live.guru99.com/");
//		//
//		//Step 02 - Sử dụng JE để get domain của page
//		String domainName = (String) executeForBrowser("return document.domain");
//		Assert.assertEquals(domainName, "live.guru99.com");
//		//Step 03 - Sử dụng JE để get URL của page
//		String page_URL = (String) executeForBrowser("return document.URL");
//		Assert.assertEquals(page_URL, "http://live.guru99.com/");
//		//Step 04 - Open MOBILE page (Sử dụng JE)
//		WebElement mobileLink = driver.findElement(By.xpath("//a[text()='Mobile']"));
//		clickToElementByJS(mobileLink);
//		Thread.sleep(2000);
//		//Step 05 - Add sản phẩm SAMSUNG GALAXY vào Cart (click vào ADD TO CART button bằng JE)
//		WebElement add_To_Cart_btn = driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//button[@title='Add to Cart']"));
//		clickToElementByJS(add_To_Cart_btn);
//		Thread.sleep(2000);
//		//Step 06 - Verify message được hiển thị:  Samsung Galaxy was added to your shopping cart.
//		String msg_mobile_added_success = (String) executeForBrowser("return document.documentElement.innerText;");
//		System.out.println("success 01");
//		Assert.assertTrue(msg_mobile_added_success.contains("Samsung Galaxy was added to your shopping cart."));
//		//Step 07 - Open PRIVACY POLICY page (Sử dụng JE)
//		scrollToBottomPage();
//		Thread.sleep(2000);
//		WebElement privacy_policy = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
//		clickToElementByJS(privacy_policy);
//		Thread.sleep(2000);
//		//Verify title của page = Privacy Policy (Sử dụng JE)
//		String Privacy_Policy_URL = (String) executeForBrowser("return document.title");
//		Assert.assertEquals(Privacy_Policy_URL, "Privacy Policy");
//		//Step 08 - Srcoll xuống cuối page
//		scrollToBottomPage();
//		Thread.sleep(2000);
//		//Step 09 - Verify dữ liệu có hiển thị với chỉ 1 xpath: 
//		WebElement wish_list = driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td[text()='The number of items in your Wishlist.']"));
//		Assert.assertTrue(wish_list.isDisplayed());
//		//Step 10 - Navigate tới domain: http://demo.guru99.com/v4/  (Sử dụng JE)
//		navigateToUrlByJS("http://demo.guru99.com/v4/");
//		Thread.sleep(2000);
//		String new_Page_domain = (String) executeForBrowser("return document.domain");
//		Assert.assertEquals(new_Page_domain, "demo.guru99.com");
//	}
	@Test
	public void TC_02() throws InterruptedException
	{
		driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
		//switc iframe
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='iframeResult']")));
		//
		WebElement first_name = driver.findElement(By.xpath("//input[@name='fname']"));
		WebElement last_name = driver.findElement(By.xpath("//input[@name='lname']"));
		WebElement submit = driver.findElement(By.xpath("//input[@value='Submit']"));
		removeAttributeInDOM(last_name, "disabled");
		//Step 03 - Sendkey vào field Last name
		sendkeyToElementByJS(first_name, "Automation");
		sendkeyToElementByJS(last_name, "Testing");
		//step 04: click submit
		clickToElementByJS(submit);
		//step 05:
		WebElement input_result = driver.findElement(By.xpath("//div[contains(text(),'fname')]"));
		Assert.assertTrue(input_result.getText().contains("Automation") && input_result.getText().contains("Testing"));
		Thread.sleep(5000);
	}
//	@Test
//	public void TC_03() throws InterruptedException
//	{
//		driver.get("http://live.guru99.com/");
//		//Step 02 - Click vào link "My Account" để tới trang đăng nhập (Sử dụng JE)
//		WebElement account_link = driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']"));
//		clickToElementByJS(account_link);
//		Thread.sleep(3000);
//		//Step 03 - Click CREATE AN ACCOUNT button để tới trang đăng kí tài khoản (Sử dụng JE)
//		WebElement create_an_account = driver.findElement(By.xpath("//span[text()='Create an Account']"));
//		clickToElementByJS(create_an_account);
//		Thread.sleep(3000);
//		//Step 04 - Nhập thông tin hợp lệ vào tất cả các field: First Name/ Last Name/ Email Address/ Password/ Confirm Password (Sử dụng JE)
//		driver.findElement(first_name).clear();
//		sendkeyToElementByJS(driver.findElement(first_name), new_first_name);
//		driver.findElement(middle_name).clear();
//		sendkeyToElementByJS(driver.findElement(middle_name), new_middle_name);
//		driver.findElement(last_name).clear();
//		sendkeyToElementByJS(driver.findElement(last_name), new_last_name);
//		driver.findElement(email_address).clear();
//		sendkeyToElementByJS(driver.findElement(email_address), new_email_address);
//		driver.findElement(pwd).clear();
//		sendkeyToElementByJS(driver.findElement(pwd), new_pwd);
//		driver.findElement(confirm_pwd).clear();
//		sendkeyToElementByJS(driver.findElement(confirm_pwd), new_confirm_pwd);
//		Thread.sleep(3000);
//		clickToElementByJS(driver.findElement(submit_btn));
//		Thread.sleep(3000);
//		//Step 05 - Verify message xuất hiện khi đăng kí thành công: Thank you for registering with Main Website Store.
//		String register_success = (String) executeForBrowser("return document.documentElement.innerText;");
//		Assert.assertTrue(register_success.contains("Thank you for registering with Main Website Store."));
//		//Step 06 - Logout khỏi hệ thống (Sử dụng JE)
//		WebElement account_logout = driver.findElement(By.xpath("//div[@class='skip-links']//span[text()='Account']"));
//		clickToElementByJS(account_logout);
//		Thread.sleep(3000);
//		//
//		WebElement logout_btn = driver.findElement(By.xpath("//a[text()='Log Out']"));
//		clickToElementByJS(logout_btn);
//		Thread.sleep(2000);
//		//Step 07 - Kiểm tra hệ thống navigate về Home page sau khi logout thành công (Sử dụng JE)
//		//navigateToUrlByJS("http://live.guru99.com/index.php/");
////		String guru99_Home_Page = (String) executeForBrowser("return document.URL");
////		Assert.assertEquals(guru99_Home_Page, "http://live.guru99.com/index.php/");
//		Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'This is demo site for')]")).isDisplayed());
//	}
	public void highlightElement(WebElement element)
	{
		javaExecutor.executeScript("arguments[0].style.border='6px groove red'", element);
	}
	public Object executeForBrowser(String javaScript)
	{
		try
		{
			return javaExecutor.executeScript(javaScript);
		}
		catch (Exception e) {
			e.getMessage();
            return null;
		}
	}
	public Object clickToElementByJS(WebElement element)
	{
		try
		{
			return javaExecutor.executeScript("arguments[0].click();", element);
		}
		catch (Exception e) {
			e.getMessage();
            return null;
		}
	}
	public Object sendkeyToElementByJS(WebElement element, String value)
	{
		try
		{
			return javaExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
		}
		catch (Exception e) {
			e.getMessage();
            return null;
		}
	}
	public Object removeAttributeInDOM(WebElement element, String attribute) {
        try {
            return javaExecutor.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
	public Object scrollToBottomPage() {
        try {          
            return javaExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
	public Object navigateToUrlByJS(String url) {
        try {
            return javaExecutor.executeScript("window.location = '" + url + "'");
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
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
