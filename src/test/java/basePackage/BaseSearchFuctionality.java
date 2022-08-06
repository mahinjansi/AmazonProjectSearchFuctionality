package basePackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utility.TimeUtils;

public class BaseSearchFuctionality {
	public static Properties prop = new Properties();
	public static WebDriver driver;

	public BaseSearchFuctionality() {
		try {
			// #1 exception
			FileInputStream file = new FileInputStream(
					"C:\\Users\\ra\\Desktop\\QA Training\\Programming\\Java\\SearchFunctionality\\src\\test\\java\\environmentVariables\\config.properties");
			prop.load(file);
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// #2 exception
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static void browserinitiate() {
		String browsername = prop.getProperty("browser");
		if (browsername.equals("Firefox")) {

			System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
			System.out.println("Initializing Firefox Driver...");
			driver = new FirefoxDriver();
		} else if (browsername.equals("chrome")) {

			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			System.out.println("Initializing Chrome Driver...");
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(TimeUtils.timepage, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
	}

	public static void screenshots(String filename) {

		System.out.println("Screenshot will be taken");
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// ((TakeScreenshots)driver) ==== is to convert driver to take screenshot
		// .getScreenshotAs(OutputType.FILE) ======= is used to create screenshot
		// to save screenshot in file format use File(class name) file (object name)

		try {
			FileUtils.copyFile(file, new File(
					"C:\\Users\\ra\\Desktop\\QA Training\\Programming\\Java\\SearchFunctionality\\src\\test\\java\\screenshots\\Screenshots"
							+ filename + ".jpg"));
			// new File === passing parameters in file
			// whenever handling file use IOException
			// FileUtils.copyFile( === is used to copy screenshot file at desired location
		}

		catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Screenshot has been taken");

	}
}
