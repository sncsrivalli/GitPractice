package practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
//This is the comment by user1
public class HighLightedText {
	
	@Test
	public void textCheckTest() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demo.actitime.com/login.do");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement title = driver.findElement(By.id("headerContainer"));
		System.out.println(title.getCssValue("backgroundColor"));
		
		Thread.sleep(3000);
		Actions a = new Actions(driver);
		a.doubleClick(title).perform();
		
		System.out.println(title.getCssValue("backgroundColor"));
//		driver.findElement(By.xpath("//*[name()='svg'][@class='_close_11p8l_1 _black_11p8l_26']")).click();
//		if(title.getCssValue("background-color").equals(""))
	}

}
