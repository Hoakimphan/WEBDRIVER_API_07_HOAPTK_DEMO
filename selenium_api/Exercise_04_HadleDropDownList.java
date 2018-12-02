package selenium_api;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.RenderingHints.Key;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Exercise_04_HadleDropDownList {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor javaExcutor;
	String month1;
	String month2;
	String month3;
	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", ".\\libs\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 30);
		javaExcutor = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	@Test
	public void TestScript_01_Handle_HTML_Dropdownlist() throws InterruptedException
	{
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		//initialize Select variable
		Select select = new Select(driver.findElement(By.xpath("//select[@id='job1']")));
		//Kiểm tra dropdown Job Role 01 không hỗ trợ thuộc tính multi-select
		Assert.assertFalse(select.isMultiple());
		//Chọn giá trị Automation Tester trong dropdown bằng phương thức selectVisible
		select.selectByVisibleText("Automation Tester");
		Thread.sleep(3000);
		//Kiểm tra giá trị đã được chọn thành công(gia tri duoc chon se nam o vi tri dau tien)
		Assert.assertEquals("Automation Tester", select.getFirstSelectedOption().getText());
		//Chọn giá trị Manual Tester trong dropdown bằng phương thức selectValue
		select.selectByValue("manual");
		Thread.sleep(3000);
		//Kiểm tra giá trị đã được chọn thành công
		Assert.assertEquals("Manual Tester", select.getFirstSelectedOption().getText());
		//Chọn giá trị Mobile Tester trong dropdown bằng phương thức selectIndex
		select.selectByIndex(3);
		Thread.sleep(3000);
		//Kiểm tra giá trị đã được chọn thành công
		Assert.assertEquals("Mobile Tester", select.getFirstSelectedOption().getText());
		//Kiểm tra dropdown có đủ 5 giá trị
		Assert.assertEquals(5, select.getOptions().size());
	}
//	@Test
//	public void TestScript_02_Handle_Custom_Dropdownlist() throws InterruptedException
//	{
//		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
//		selectItemInCustomDropDown("//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']", "19");
//		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='19']")).isDisplayed());
//		Thread.sleep(3000);
		//
//		driver.get("https://material.angular.io/components/select/examples");
//		selectItemInCustomDropDown("//span[@aria-owns='color_listbox']","//span[@aria-owns='color_listbox']//span[@class='k-icon k-i-arrow-60-down']", "//div[@id='color-list']//ul[@class='k-list k-reset']//li[@role='option']", "Grey");
//		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='19']")).isDisplayed());
//		Thread.sleep(3000);
//		driver.get("https://mikerodham.github.io/vue-dropdowns/");
//		selectItemInCustomDropDown("//div[@class='btn-group']", "//li[@class='dropdown-toggle']//span[@class='caret']", "//ul[@class='dropdown-menu']//li", "Second Option");
//		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(text(),'Second Option')]")).isDisplayed());
//		Thread.sleep(3000);
		
//		driver.get("http://indrimuska.github.io/jquery-editable-select/");
//		driver.findElement(By.xpath("//div[@id='default-place']//input")).sendKeys("Audi");
//		Thread.sleep(3000);
//		//press TAB to get value in suggestion dropdown into edit textbox
//		driver.findElement(By.xpath("//div[@id='default-place']//input")).sendKeys(Keys.ENTER);
//		Thread.sleep(3000);
//		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='default-place']//li[text()='Audi']")).getAttribute("class"), "es-visible selected");
//		Thread.sleep(3000);
		//
//		driver.get("http://indrimuska.github.io/jquery-editable-select/");
//		driver.findElement(By.xpath("//div[@id='default-place']//input")).sendKeys("Fiat");
//		driver.findElement(By.xpath("//div[@id='default-place']//input")).sendKeys(Keys.ENTER);
//		Thread.sleep(3000);
//		//verify Fiat is selected
//		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='default-place']//li[text()='Fiat']")).getAttribute("class"), "es-visible selected");
//	}
	@Test
	public void TC_03_CustomDropdown_Diff()
	{
		driver.get("http://wenzhixin.net.cn/p/multiple-select/docs/");
		selectItemInCustomDropDown("//p[@id='e1_t']//button[@class='ms-choice']", "//p[@id='e1_t']//button[@class='ms-choice']//div", "//p[@id='e1_t']//div//ul//li//label", "March");
		System.out.println("March");
		driver.findElement(By.xpath("//p[@id='e1_t']//button[@class='ms-choice']")).click();
		selectItemInCustomDropDown("//p[@id='e1_t']//button[@class='ms-choice']", "//p[@id='e1_t']//button[@class='ms-choice']//div", "//p[@id='e1_t']//div//ul//li//label", "April");
		driver.findElement(By.xpath("//p[@id='e1_t']//button[@class='ms-choice']")).click();
		selectItemInCustomDropDown("//p[@id='e1_t']//button[@class='ms-choice']", "//p[@id='e1_t']//button[@class='ms-choice']//div", "//p[@id='e1_t']//div//ul//li//label", "May");
		driver.findElement(By.xpath("//p[@id='e1_t']//button[@class='ms-choice']")).click();
		selectItemInCustomDropDown("//p[@id='e1_t']//button[@class='ms-choice']", "//p[@id='e1_t']//button[@class='ms-choice']//div", "//p[@id='e1_t']//div//ul//li//label", "June");
		driver.findElement(By.xpath("//p[@id='e1_t']//button[@class='ms-choice']")).click();
		String actual = countSelected().size() +" " + "of 12 selected";
		Assert.assertEquals(actual, "4 of 12 selected");
		//select < 4
		selectItemInCustomDropDown("//p[@id='e1_t']//button[@class='ms-choice']", "//p[@id='e1_t']//button[@class='ms-choice']//div", "//p[@id='e1_t']//div//ul//li//label", "April");
		driver.findElement(By.xpath("//p[@id='e1_t']//button[@class='ms-choice']")).click();
		selectItemInCustomDropDown("//p[@id='e1_t']//button[@class='ms-choice']", "//p[@id='e1_t']//button[@class='ms-choice']//div", "//p[@id='e1_t']//div//ul//li//label", "May");
		driver.findElement(By.xpath("//p[@id='e1_t']//button[@class='ms-choice']")).click();
		selectItemInCustomDropDown("//p[@id='e1_t']//button[@class='ms-choice']", "//p[@id='e1_t']//button[@class='ms-choice']//div", "//p[@id='e1_t']//div//ul//li//label", "June");
		driver.findElement(By.xpath("//p[@id='e1_t']//button[@class='ms-choice']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='e1_t']//button[@class='ms-choice']")).getText(), "April, May, June");
		//select all
		selectItemInCustomDropDown("//p[@id='e1_t']//button[@class='ms-choice']", "//p[@id='e1_t']//button[@class='ms-choice']//div", "//p[@id='e1_t']//div//ul//li//label", "[Select all]");
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='e1_t']//button[@class='ms-choice']")).getText(), "All selected");
	}
	public List<WebElement> countSelected()
	{
		return driver.findElements(By.xpath("//p[@id='e1_t']//div//ul//li[@class='selected']"));
	}
	/*Click vào dropdown
	Wait để tất cả phần tử trong dropdown được hiển thị
	Get tất cả item trong dropdown vào 1 list element (List <WebElement>)
	Dùng vòng lặp for duyệt qua từng phần tử sau đó getText
	Nếu actual text = expected text thì click vào phần tử đó và break khỏi vòng lặp*/
	public void selectItemInCustomDropDown(String scrollXpath, String parentXpath, String childXpath, String expectedItem)
	{
		javaExcutor.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.xpath(scrollXpath)));
		WebElement element = driver.findElement(By.xpath(parentXpath));
		//scroll
		javaExcutor.executeScript("arguments[0].scrollIntoView(true)", element);
		element.click();
		//get list
		List<WebElement> childList = driver.findElements(By.xpath(childXpath));
		//wait
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(childList));
		//loop
		for(WebElement child:childList)
		{
			String textItem = child.getText().trim();
			System.out.println("Text in dropdown: "+textItem);
			if(textItem.equals(expectedItem))
			{
				javaExcutor.executeScript("arguments[0].scrollIntoView()", child);
				child.click();
				break;
			}
		}
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
