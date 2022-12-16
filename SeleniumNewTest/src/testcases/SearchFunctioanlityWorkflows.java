package testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class SearchFunctioanlityWorkflows {
	WebDriver driver;
	WebElement Element;
  @Test (description="search keyword text is available or not",priority=0)
  public void VerifySearchKeywordisAvailable(){
	  System.out.println("Testcase  : Verify search keyword is present ");
		
		try {
			driver.get("https://global.hitachi-solutions.com/");
			WebDriverWait wait = new WebDriverWait(driver,10);
			Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("open-global-search"))); 
			Element.click();
			Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("site-search-keyword"))); 
			Element.isDisplayed();
			System.out.println("Testcase : Passed Search keyword text available in the application");
			 
			
		}catch(Exception e) {
			System.out.println("Testcase : Failed Search keyword text not available in the application");
			Assert.assertTrue(false); 
		}
  }
  @Test (description="search with valid keyword like Dynamic 365 and search results is displayed",priority=1)
  @Parameters("serachString")
  public void SearchResultsDisplayedForKeyword(String serachString){
	  System.out.println("Testcase  : Display serach results for search keywoprd (Dynamics 365) entered ");
		
		try {
			driver.get("https://global.hitachi-solutions.com/");
			WebDriverWait wait = new WebDriverWait(driver,10);
			Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("open-global-search"))); 
			Element.click();
			Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("site-search-keyword"))); 
			Element.sendKeys(serachString);
			driver.findElement(By.xpath("//button[@role='submit']")).submit();
			Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("results"))); 
			if (Element.getText().contains(serachString)) {
				driver.findElement(By.xpath("//div[@class='search-result']/p/a[1]")).isDisplayed();
				System.out.println("Testcase : Passed Search results displayed in the application");
			}else {
				System.out.println("Testcase : failed Search results not displayed in the application");
				Assert.assertTrue(false);
			}
				
		}catch(Exception e) {
			System.out.println("Testcase : Failed Search results not displayed in the application with exception "+e.getMessage());
			Assert.assertTrue(false);
		}
  }
  @Test (description="search results are displayed as per search keyword",priority=2)
  public void VerifyAllSearchResultsDisplayedForKeyword(){
	  System.out.println("Testcase  : Verify search results should contain the keyword entered ");
		
		try {
			driver.get("https://global.hitachi-solutions.com/");
			WebDriverWait wait = new WebDriverWait(driver,10);
			Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("open-global-search"))); 
			Element.click();
			Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("site-search-keyword"))); 
			Element.sendKeys("Dynamics 365");
			driver.findElement(By.xpath("//button[@role='submit']")).submit();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("results"))); 
			List<WebElement> allLinks = driver.findElements(By.xpath("//div[@class='search-result']/p/a"));
			boolean bln =false;
			//Code to check dynamics 365 in links present in search results
			for (WebElement link :allLinks) {
				if (link.getText().contains("Dynamics 365")) {
					bln =true;
				}else {
					bln =false;
					break;
				}
			}
			if (bln) {
				
				System.out.println("Testcase : Passed Search results displayed sucessdully with all links contain keyword in the application");
			}else {
				System.out.println("Testcase : failed Search results not displayed sucessfully with all links contain keyword entered in the application");
				Assert.assertTrue(false);
			}
				
		}catch(Exception e) {
			System.out.println("Testcase : Failed Search results not displayed in the application with exception "+e.getMessage());
			Assert.assertTrue(false); 
		}
  }
  @Test (description="Search displayed error when searched with special character like $@t",priority=3)
  public void SearchResultsWithInvalidKeyword(){
	  System.out.println("Testcase  : Error display when search with invalid keyword (@$t) entered ");
		
		try {
			driver.get("https://global.hitachi-solutions.com/");
			WebDriverWait wait = new WebDriverWait(driver,10);
			Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("open-global-search"))); 
			Element.click();
			Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("site-search-keyword"))); 
			Element.sendKeys("@$t");
			driver.findElement(By.xpath("//button[@role='submit']")).submit();
			Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("results"))); 
			if (Element.getText().contains("search didn't return any results")) {
				
				System.out.println("Testcase : Passed Error displayed");
			}else {
				System.out.println("Testcase : failed Error not displayed");
				Assert.assertTrue(false);
			}
				
		}catch(Exception e) {
			System.out.println("Testcase : Failed Search results not displayed in the application with exception "+e.getMessage());
			Assert.assertTrue(false);
		}
  }
  @Test (description="earch with empty keyword and result should display",priority=4)
  public void SearchResultsWithEmptyKeyword(){
	  System.out.println("Testcase  : Display serach results with empty keywoprd ");
		
		try {
			driver.get("https://global.hitachi-solutions.com/");
			WebDriverWait wait = new WebDriverWait(driver,10);
			Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("open-global-search"))); 
			Element.click();
			Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("site-search-keyword"))); 
			driver.findElement(By.xpath("//button[@role='submit']")).submit();
			Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("results"))); 
			if (Element.getText().contains("Search results")) {
				driver.findElement(By.xpath("//div[@class='search-result'][1]/p/a")).isDisplayed();
				System.out.println("Testcase : Passed Search results displayed in the application");
			}else {
				System.out.println("Testcase : failed Search results not displayed in the application");
				Assert.assertTrue(false);
			}
				
		}catch(Exception e) {
			System.out.println("Testcase : Failed Search results not displayed in the application with exception "+e.getMessage());
			Assert.assertTrue(false); 
		}
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.setProperty("webdriver.chrome.driver", "C:\\config\\chromedriver.exe");
		 driver = new ChromeDriver();
		 driver.manage().window().maximize();
  }

  @AfterMethod
  public void afterMethod() {
	  driver.close();
  }

  @AfterTest
  public void afterTest() {
  }

}
