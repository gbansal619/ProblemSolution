package testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class WebsiteUrlNavigationWorkflow {
	WebDriver driver;
	WebElement Element;
	@BeforeMethod
	  public void beforeMethod() {
		  System.setProperty("webdriver.chrome.driver", "C:\\config\\chromedriver.exe");
			 driver = new ChromeDriver();
	  }
  @Test (description="Sucessful navigation with correct url",priority=0)
  public void Testcase_SuccessfulNavigation() {
	  System.out.println("Testcase  : Successful Navigation with correct url https://global.hitachi-solutions.com/ ");
		
		try {
			driver.get("https://global.hitachi-solutions.com/");
			WebDriverWait wait = new WebDriverWait(driver,10);
			Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Business Solutions']"))); 
			Element.isDisplayed();
			System.out.println("Testcase : Passed Navigated to the application successfully");
			 
			
		}catch(Exception e) {
			System.out.println("Testcase : Failed not Navigated to the application successfully");
			 Assert.assertTrue(false);
		}
		 
  }
  @Test(description="Successful navigation to incorrect url which is not matched with correct url",priority=1)
  public void TestCase_IncorrectUrlNavigation() {
	  System.out.println("Testcase  : Navigation with incorrect url https://hitachi-solutions.com/ ");
		 driver.get("https://hitachi-solutions.com/");
		 try {
				WebDriverWait wait = new WebDriverWait(driver,10);
				Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Business Solutions']"))); 
				Element.isDisplayed();
				System.out.println("Testcase: Failed as Navigated to the application sucessfully");
				Assert.assertTrue(false);
				
			}catch(Exception e) {
				System.out.println("Testcase: Passed as it is not Navigated to the actual application ");
				Assert.assertTrue(true); 
			}
			 
		 
  }
  @Test(description="Unsucessful navigation with partial url",priority=2)
  public void TestCase_PartialUrlNavigation() {
	  System.out.println("Testcase  : Navigation with partial url https://global.hitachi.com/ ");
		 
		 try {
			 	driver.get("https://global.hitachi.com/");
				System.out.println("Testcase: Failed as Navigated to the application sucessfully");
				 Assert.assertTrue(false);
				
			}catch(Exception e) {
				System.out.println("Testcase: Passed as it is not Navigated to the application with partial url ");
				 Assert.assertTrue(true); 
			}
		
		 
  }
  @Test(description="Unsucessful navigation with invalid url",priority=3)
  public void TestCase_InvalidUrlNavigation() {
	  System.out.println("Testcase  : Navigation with partial url https://global.hitachi-solutions/ ");
		 
		 try {
			 	driver.get("https://global.hitachi-solutions/");
				System.out.println("Testcase: Failed as Navigated to the application sucessfully");
				 Assert.assertTrue(false);
				
			}catch(Exception e) {
				System.out.println("Testcase: Passed as it is not Navigated to the application with partial url ");
				 Assert.assertTrue(true); 
			}
		
		 
  }
  

  @AfterMethod
  public void afterMethod() {
	  driver.close();
  }

  @AfterTest
  public void afterTest() {
  }

}
