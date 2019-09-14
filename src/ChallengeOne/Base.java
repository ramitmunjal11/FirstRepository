package ChallengeOne;

import java.net.HttpURLConnection;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {
	public static WebDriver driver = null;
	static WebDriverWait wait = null;

	public static void verifyLink(String urlLink) {
		try {
			URL link = new URL(urlLink);
			HttpURLConnection httpConn = (HttpURLConnection) link.openConnection();
			httpConn.connect();
			if (httpConn.getResponseCode() == 200) {
				System.out.println(urlLink + " - " + httpConn.getResponseMessage());
			} else {
				System.out.println(urlLink + "- Not working");
			}
		} catch (Exception e) {
			System.out.println("Exception is coming: " + e.getMessage());
		}
	}
}
