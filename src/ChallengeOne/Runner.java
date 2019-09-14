package ChallengeOne;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Runner extends Base {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		
		//Initiating the driver
		System.setProperty("webdriver.chrome.driver", "D:\\Web Drivers For Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 20);
		
		
		//Open the page
		driver.get("https://en.wikipedia.org/wiki/Selenium");

		
		//Verify that the external links in “External links“ section work
		WebElement externalLinks = driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div/ul[2]"));
		List<WebElement> eLinks = externalLinks.findElements(By.tagName("a"));
		System.out.println("Total links are " + eLinks.size());
		System.out.println("List of the Links:");
		for (int i = 0; i < eLinks.size(); i++) {
			WebElement element = eLinks.get(i);
			String url = element.getAttribute("href");
			verifyLink(url);
		}
		
		
		//Click on the “Oxygen” link on the Periodic table at the bottom of the page
		driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div/div[19]/table/tbody/tr[2]/td/div/table/tbody/tr[3]/td[7]/a/span")).click();
		
		
		//Verify that it is a “featured article”
		try {
			driver.findElement(By.xpath("//*[@id=\"mw-indicator-featured-star\"]"));
			System.out.println("This(Oxygen) is a featured article in wikipedia");
		}catch(Exception e) {
			System.out.println("This(Oxygen) is not a featured article in wikipedia");
		}
		
		
		//Take a screenshot of the right hand box that contains element properties
		WebElement box = driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div/table[1]/tbody"));
		File screenshot = ((TakesScreenshot)box).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir")+"\\src\\ChallengeOne\\Screenshot.jpg"));
		
		
		//Count the number of links in “References“
		WebElement referencesLinks = driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div/div[47]"));
		List<WebElement> rLinks = referencesLinks.findElements(By.tagName("a"));
		System.out.println("Total number of Links in Reference sections: " + rLinks.size());
		
		
		//In the search bar on top right enter “pluto” and verify that the 2nd suggestion is “Plutonium”
		driver.findElement(By.xpath("//*[@id=\"searchInput\"]")).sendKeys("pluto");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"searchInput\"]")).sendKeys(Keys.DOWN);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"searchInput\"]")).sendKeys(Keys.DOWN);
        String text=driver.findElement(By.xpath("//*[@id=\"searchInput\"]")).getText();
        if(text.equalsIgnoreCase("Plutonium")) {
        	System.out.println("Suggestion is Plutonium");
        }else {
        	System.out.println("Suggestion is not Plutonium");
        }
	}
}
