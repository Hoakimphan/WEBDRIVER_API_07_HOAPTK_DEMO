package selenium_api;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Exercise_07 {
	WebDriver driver;
	JavascriptExecutor javaExcutor;
	@BeforeTest
	public void setUp() {
		//System.setProperty("webdriver.chrome.driver", ".\\libs\\chromedriver.exe");
		driver = new FirefoxDriver();
		javaExcutor = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	@Test
	public void TC_01() throws InterruptedException
	{
		//Issue 01: pop up random, nếu sử dụng findElement thì nếu k tìm thấy pop up sẽ đánh fail TC và k chạy tiếp.
		//sử dụng findElements để nếu k tìm thấy phần tử, sẽ trả ra list rỗng nhưng vẫn tiếp tục chạy các TC khác chứ k đánh fail 
		//(iframe của pop up cần close xuất hiện khi pop up xuất hiện
		System.out.println("--------------STEP 01---------------");
		driver.get("http://www.hdfcbank.com/");
		System.out.println("--------------STEP 02---------------");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List <WebElement> listIframe = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		if(listIframe.size()>0)//nếu list có từ 1 phần tử trở lên
		{
			driver.switchTo().frame(listIframe.get(0));//thao tác với phần tử iframe đầu tiên
			WebElement closePopUp =  driver.findElement(By.xpath("//div[@id='div-close']"));
			Assert.assertTrue(closePopUp.isDisplayed());
			clickElementByJavascript(closePopUp);
			Assert.assertFalse(closePopUp.isDisplayed());
			System.out.println("click pop up success");
			//Issue 02: sau khi switch iframe đầu tiên, phải quay lại Top window để tìm iframe kế tiếp cần thao tác
			driver.switchTo().defaultContent();
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("--------------STEP 03---------------");
		//k nên lấy id có số vì sẽ đổi khi reload lại page
		WebElement iframe1 = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
		driver.switchTo().frame(iframe1);
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='messageText']")).getText(), "What are you looking for?");
		Thread.sleep(3000);
		System.out.println("--------------STEP 04---------------");
		driver.switchTo().defaultContent();
		WebElement imageIframe = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
		driver.switchTo().frame(imageIframe);
		List <WebElement> imagesBanner = driver.findElements(By.xpath("//div[@id='productcontainer']//img[@class='bannerimage']"));
		Assert.assertEquals(imagesBanner.size(), 6);
		//
		System.out.println("--------------STEP 05---------------");
		driver.switchTo().defaultContent();
		//flipper banner hien thi va co 8 item
		List <WebElement> flipperBanner = driver.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='flipBanner']")).isDisplayed());
		Assert.assertEquals(flipperBanner.size(), 8);
		System.out.println("flipper banner: "+ flipperBanner.size());
		//8 item duoc hien thi
		for(WebElement flipper: flipperBanner)
		{
			System.out.println("flipper banner: "+ flipper.isDisplayed());
		}
	}
	@Test
	public void TC_02() throws InterruptedException
	{
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		WebElement clickHere = driver.findElement(By.xpath("//a[text()='Click Here']"));
		javaExcutor.executeScript("arguments[0].scrollIntoView(true)", clickHere);
		clickHere.click();
		Thread.sleep(3000);
		String parentWindow = driver.getWindowHandle();
		switchToChildWindow(parentWindow);
		Thread.sleep(3000);
		System.out.println("Already to switch");
		String URL1 = driver.getTitle();
		Assert.assertEquals(URL1, "Google");
		driver.close();
		System.out.println("parent");
	//Step 05 - Switch về parent window
		driver.switchTo().window(parentWindow);
		System.out.println("switch back to parent");
		Thread.sleep(3000);
		String URL2 = driver.getCurrentUrl();
		Assert.assertEquals(URL2, "https://daominhdam.github.io/basic-form/index.html");
	}
	@Test
	public void TC_03() throws InterruptedException
	{
		driver.get("http://www.hdfcbank.com/");
		//Kiểm tra và close quảng cáo nếu có xuất hiện
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List <WebElement> listIframeOnBank = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		if(listIframeOnBank.size()>0)//nếu list có từ 1 phần tử trở lên
		{
			driver.switchTo().frame(listIframeOnBank.get(0));//thao tác với phần tử iframe đầu tiên
			WebElement closePopUp =  driver.findElement(By.xpath("//div[@id='div-close']"));
			Assert.assertTrue(closePopUp.isDisplayed());
			clickElementByJavascript(closePopUp);
			Assert.assertFalse(closePopUp.isDisplayed());
			System.out.println("click pop up success");
			//Issue 02: sau khi switch iframe đầu tiên, phải quay lại Top window để tìm iframe kế tiếp cần thao tác
			driver.switchTo().defaultContent();
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[text()='Agri']")).click();
		String angriWindow = driver.getWindowHandle();
		switchToChildWindow(angriWindow);
		Thread.sleep(3000);
		//
		driver.findElement(By.xpath("//ul[@class='grid_list clearfix']//li//a//div//p[text()='Account Details']")).click();
		Thread.sleep(3000);
		switchToWindowByTitle("Welcome to HDFC Bank NetBanking");
		Thread.sleep(3000);
		System.out.println("switch to Welcome to HDFC Bank NetBanking");
		Thread.sleep(3000);
		//frame
		WebElement footerFrame = driver.findElement(By.xpath("//frame[@name='footer']"));
		driver.switchTo().frame(footerFrame);
		System.out.println("switch frame success");
		//driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//form[@name='frmFooter']//p//a[text()='Privacy Policy']")).click();
		Thread.sleep(3000);
		switchToWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
		Thread.sleep(3000);
		System.out.println("switch to HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
		//Step 06- Click CSR link on Privacy Policy page
		driver.findElement(By.xpath("//li//a[text()='CSR']")).click();
		Thread.sleep(3000);
		//back to parent window
		String parent_Window = driver.getWindowHandle();
		closeAllWithoutParentWindows(parent_Window);
		Thread.sleep(3000);
		System.out.println("switch back to parent");
	}
	public boolean closeAllWithoutParentWindows(String parentWindow)
	{
		Set <String> allWindows = driver.getWindowHandles();
		for(String runWindows : allWindows)
		{
			if(!runWindows.equals(parentWindow))
			{
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		if(driver.getWindowHandles().size() == 1)
			return true;
			else
				return false;
	}
	public void switchToChildWindow(String parent)
	{
		Set<String> allWindows = driver.getWindowHandles();
		for(String runWindow : allWindows)
		{
			if(!runWindow.equals(parent))
			{
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}
	public void switchToWindowByTitle(String title)//usin for more than 2 page + title is unique
	{
		Set <String> allWindow = driver.getWindowHandles();
		for(String runWindows : allWindow)
		{
			driver.switchTo().window(runWindows);
			String currentWindow = driver.getTitle().trim();
			if(currentWindow.equals(title))
			{
				break;
			}
		}
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
