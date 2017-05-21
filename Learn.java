package Learnhard;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Learn {
	
	WebDriver driver;
	
	@BeforeTest
	public void setup(){
	 
	  System.setProperty("webdriver.firefox.bin","/home/em3it/Documents/firefox/firefox");
	  driver = new FirefoxDriver();
	}
	
  @Test
  public void f() throws Exception {
	 
	  
	  driver.get("http://www.seleniumframework.com/Practiceform/");
	  
	  String winHandleBefore = driver.getWindowHandle();
	  
	  driver.findElement(By.id("button1")).click();
	  
	  for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  	wait.until(
		        ExpectedConditions.visibilityOfElementLocated(By.id("mobile-menu")));
		
	  JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
		
	  WebElement element1 = driver.findElement(By.linkText("Setup and First Script"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		
	  Thread.sleep(7000);
		
	  // Close the new window, if that window no more required
	  driver.close();

	  // Switch back to original browser (first window)
	  driver.switchTo().window(winHandleBefore);
	  
	  driver.findElement(By.xpath("//*[@id='content']/div[2]/div[2]/div/div[1]/div/p[3]/button")).click();
	  
	  for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
	  
	  String body = driver.findElement(By.tagName("body")).getText();
	  System.out.println(body);
	  
	  // Close the new window, if that window no more required
	  driver.close();

	  // Switch back to original browser (first window)
	  driver.switchTo().window(winHandleBefore);
	  
	  driver.findElement(By.xpath("//*[@id='content']/div[2]/div[2]/div/div[1]/div/p[4]/button")).click();
	  
	  for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		
	  // Close the new window, if that window no more required
	  driver.close();
	  
	  // Switch back to original browser (first window)
      driver.switchTo().window(winHandleBefore);
	  
	  driver.findElement(By.id("alert")).click();
	  driver.switchTo().alert().accept();
	  
	  driver.findElement(By.id("timingAlert")).click();
	  new WebDriverWait(driver, 60)
      .ignoring(NoAlertPresentException.class)
      .until(ExpectedConditions.alertIsPresent());
	  driver.switchTo().alert().accept();
	  
	  Actions action = new Actions(driver);
	  action.moveToElement(driver.findElement(By.id("doubleClick"))).doubleClick().perform();
	  
	  WebElement element5 = driver.findElement(By.id("draga"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element5);
	  
	  WebElement source = driver.findElement(By.id("draga"));
	  WebElement destination = driver.findElement(By.id("dragb"));
	  
	  (new Actions(driver)).dragAndDrop(source, destination).perform();
	  
	  WebElement textarea = driver.findElement(By.id("vfb-10"));
	  Helper.highLightElement(driver, textarea);
      textarea.click();
      textarea.clear();
      textarea.sendKeys(body);
      
	  WebElement text = driver.findElement(By.id("vfb-9"));
	  Helper.highLightElement(driver, text);
	  text.clear();
	  text.sendKeys("Body");
	  
	  driver.findElement(By.id("vfb-6-1")).click();
	  
	  driver.findElement(By.id("vfb-7-1")).click();
	  
	  WebElement date = driver.findElement(By.id("vfb-8"));
	  Helper.highLightElement(driver, date);
	  date.sendKeys("05/20/2017");
	  
	  Select option = new Select(driver.findElement(By.id("vfb-12")));
	  option.selectByValue("Option 1");
	  
	  driver.findElement(By.id("vfb-3")).sendKeys("45");
	  driver.findElement(By.id("vfb-4")).submit();
	  
	  Thread.sleep(5000);
	  
	  driver.close();
	  
	driver.quit();
	  
  }
  @AfterMethod 
  public void takeScreenShotOnFailure(ITestResult testResult) throws IOException { 
  	if (testResult.getStatus() == ITestResult.FAILURE) { 
  		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
  		FileUtils.copyFile(scrFile, new File("errorScreenshots\\" + testResult.getName() + "-" 
  				+ Arrays.toString(testResult.getParameters()) +  ".jpg"));
  	} 
  }

}
