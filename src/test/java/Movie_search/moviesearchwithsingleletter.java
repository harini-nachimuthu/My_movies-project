package Movie_search;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class moviesearchwithsingleletter {
	protected String url="https://neon-kitsune-9ab441.netlify.app/";
	WebDriver driver=null;
	
	 @Test
	    public void searchAndCheckMovie() throws IOException, InterruptedException {
	        System.out.println("Title of the page: " + driver.getTitle());
	        System.out.println("URL of the page: " + driver.getCurrentUrl());
	        
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
	        WebElement searchbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[class=\"form-control me-2\"]")));
	        
	        String movieName = "A";
	        searchbox.sendKeys(movieName);
	        //searchbox.submit();
	        
	        List<WebElement> lists=driver.findElements(By.xpath("//html//body//div//div//section//div//div//div//h5[1]"));
	        System.out.println("total movies with Statring letter "+movieName+": "+lists.size());
	        
	        for(WebElement list:lists) {
	        	System.out.println(list.getText());
	        }
	        
	        Thread.sleep(3000);
	        
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        if (js.executeScript("return document.readyState").equals("complete")) {
	            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	            FileUtils.copyFile(file, new File("C:\\Users\\harin\\eclipse-workspace\\My_movies\\Screen_Shot\\movie_search_withsingle.jpg"));
	        } else {
	            System.out.println("Page did not load completely.");
	        }
	 }

  
  @BeforeSuite
  public void beforsuites() {
	  WebDriverManager.chromedriver().setup();
	  driver=new ChromeDriver();
	  driver.manage().window().maximize();;
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
  }
  
  @BeforeClass
  public void beforclas() {
	 driver.get(url); 
  }
  
  @AfterSuite
  public void aftersuites() {
	  driver.close();
  }
}

