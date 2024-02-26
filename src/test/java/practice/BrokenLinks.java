package practice;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrokenLinks {

	@Test
	public void brokenLinksTest() throws IOException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.ajio.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println(links.size());
		
		for (WebElement link : links) {
			String linkURL = link.getAttribute("href");
			if(linkURL==null || linkURL.isBlank() || !linkURL.contains("http")) {
				System.err.println(linkURL+" - Broken link");
				continue;
			}
			URL url = new URL(linkURL);
			HttpURLConnection httpUrlConnection = (HttpURLConnection) (url.openConnection());
			httpUrlConnection.setConnectTimeout(8000);
			httpUrlConnection.connect();
			
			if(httpUrlConnection.getResponseCode() != 200)
				System.err.println(linkURL+" - "+httpUrlConnection.getResponseCode()+" - "+httpUrlConnection.getResponseMessage());
		
			httpUrlConnection.disconnect();
		}
		
		driver.quit();
		
	}
}
