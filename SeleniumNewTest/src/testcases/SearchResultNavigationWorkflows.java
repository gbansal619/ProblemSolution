package testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class SearchResultNavigationWorkflows {
	WebDriver driver;
	WebElement Element;
  
	
	  @Test(description="first Search result link opened and validated sucessfully") 
	  
	  public void Open_SearchResult(){
		  System.out.println("Testcase  : Able to open serach result ");
	  
		  try { 
			  driver.get("https://global.hitachi-solutions.com/"); 
			  WebDriverWait wait = new WebDriverWait(driver,10);
			  Element =wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("open-global-search")));
			  Element.click(); 
			  Element =wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("site-search-keyword"))); Element.sendKeys("Dynamics 365");
			  driver.findElement(By.xpath("//button[@role='submit']")).submit(); 
			  Element =wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("results"))); 
			  if (Element.getText().contains("Dynamics 365")) {
				  String ExpectedlinkText =driver.findElement(By.xpath("//div[@class='search-result']/p/a[1]")).getText();
				  driver.findElement(By.xpath("//div[@class='search-result']/p/a[1]")).click();
				  Element =wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("post-title"))); String ActualLinkText = Element.getText();
				  Assert.assertEquals(ExpectedlinkText, ActualLinkText);
				  
				  System.out.println("Testcase : Able to open search result successfully");
			  }else {
				  System.out.println("Testcase : Not Able to open search result successfully");
				  Assert.assertTrue(false); 
				  }
		  
		  }catch(Exception e) { 
			  System.out.println("Testcase : Failed Not Able to open search result successfully with theth exception "+e.getMessage()); Assert.assertTrue(false); 
		  } 
	  }
	  
	  @Test(description="Validate application does not store previous page and display same page for next search result") 
	  public void SearchResults_Navigations(){ 
		  System.out.println("Testcase  : Validate other results are not displaying same page");
	  
		  try { 
			  driver.get("https://global.hitachi-solutions.com/");
			  WebDriverWait wait= new WebDriverWait(driver,10); 
			  Element =wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("open-global-search"))); 
			  Element.click(); 
			  Element =wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("site-search-keyword"))); 
			  Element.sendKeys("Dynamics 365");
			  driver.findElement(By.xpath("//button[@role='submit']")).submit(); 
			  Element =wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("results")));
			  if (Element.getText().contains("Dynamics 365")) {
			  
				  String ExpectedlinkText =driver.findElement(By.xpath("//div[@class='search-result'][1]/p/a")).getText();
				  driver.findElement(By.xpath("//div[@class='search-result']/p/a[1]")).click();
				  driver.navigate().back(); 
				  Element =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='search-result'][2]/p/a")));
				  
				  Element.click(); 
				  Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("post-title"))); 
				  String ActualLinkText = Element.getText();
				  Assert.assertNotEquals(ExpectedlinkText, ActualLinkText);
			  
				  System.out.println("Testcase Passed : Other results are not taking to same page ");
		  
			  }else {
				  System.out.println("Testcase Failed : search did not happened successfully");
				  Assert.assertTrue(false); 
				  }
		  
	  }catch(Exception e) { 
		  System.out.println("Testcase : Failed Other results are taking to same pagey with theth exception "+e.getMessage()); 
		  Assert.assertTrue(false); 
		  } 
	}
	 
  @Test (description="Validate all search results get opened or not",priority=2)
  
  public void Open_AllSearchResults_ForKeyword(){
	  System.out.println("Testcase  : Verify User can successfully open all search results ");
		
	  try {
			driver.get("https://global.hitachi-solutions.com/");
			WebDriverWait wait = new WebDriverWait(driver,10);
			Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("open-global-search"))); 
			Element.click();
			Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("site-search-keyword"))); 
			Element.sendKeys("Dynamics 365");
			driver.findElement(By.xpath("//button[@role='submit']")).submit();
			Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("results"))); 
			if (Element.getText().contains("Dynamics 365")) {
				
				List<WebElement> allLinks = driver.findElements(By.xpath("//div[@class='search-result']/p/a"));
				for (int i=1;i<allLinks.size();i++) {
					WebElement ele = driver.findElement(By.xpath("//div[@class='search-result']["+i+"]/p/a"));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);

					((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);

					Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='post-title' or @class='resource-title']" ))); 
					
					Element.isDisplayed();
					driver.navigate().back();
					
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					
				}
				
					System.out.println("Testcase : All serach results opened successfully");
				
				
				
			}else {
				System.out.println("Testcase : Not Able to open all search result successfully");
				Assert.assertTrue(false);
			}
				
		}catch(Exception e) {
			System.out.println("Testcase : Failed Not Able to open search result successfully with theth exception "+e.getMessage());
			Assert.assertTrue(false); 
		}
  }
  @Test (description="validate all search results opened with their respective page only",priority=3)
  
  public void Verify_AllSearchResults_ForKeyword(){
	  System.out.println("Testcase  : Verify all search results opened their respective page ");
		
	  try {
			driver.get("https://global.hitachi-solutions.com/");
			WebDriverWait wait = new WebDriverWait(driver,10);
			Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("open-global-search"))); 
			Element.click();
			Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("site-search-keyword"))); 
			Element.sendKeys("Dynamics 365");
			driver.findElement(By.xpath("//button[@role='submit']")).submit();
			Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("results"))); 
			if (Element.getText().contains("Dynamics 365")) {
				boolean bln= false;
				List<WebElement> allLinks = driver.findElements(By.xpath("//div[@class='search-result']/p/a"));
				for (int i=1;i<allLinks.size();i++) {
					WebElement ele = driver.findElement(By.xpath("//div[@class='search-result']["+i+"]/p/a"));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);

					String ExpectedlinkText = ele.getText();
					//ele.click();
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);

					Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='post-title' or @class='resource-title']" ))); 
					String ActualLinkText = Element.getText();
					if (ExpectedlinkText.contains(ActualLinkText)) {
						bln = true;
					}else {
						bln = false;
						break;
					}
					
					driver.navigate().back();
					
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					
				}
				if (bln){
					System.out.println("Testcase : All serach results validated successfully");
				}else {
					System.out.println("Testcase : All serach results not validated successfully");
					Assert.assertTrue(false);
				}
				
				
				
			}else {
				System.out.println("Not Able to open search result successfully");
				Assert.assertTrue(false);
			}
				
		}catch(Exception e) {
			System.out.println("Testcase : Failed serach results not validated successfully with theth exception "+e.getMessage());
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
