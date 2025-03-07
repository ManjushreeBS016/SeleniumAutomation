package ecommerce.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ecommercepageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public  WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initialiseDriver() throws IOException
	{
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/ecommerce/resources/Global.properties");
		Properties prop=new Properties();
		prop.load(fis);
		
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");
		//String broserName=prop.getProperty("browser");
		
		if(browserName.contains("chrome"))
		{
			ChromeOptions option=new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if (browserName.contains("headless"))
			{
			option.addArguments("headless");
			}
			driver = new ChromeDriver(option);
			driver.manage().window().setSize(new Dimension(1440,900));
		}
		if(browserName.contains("firefox"))
		{
		driver = new FirefoxDriver();
		}

		if(browserName.contains("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		return driver;
	}
	@BeforeMethod
	public LandingPage launchApplication() throws IOException {
		driver=initialiseDriver();
	landingPage = new LandingPage(driver);
		landingPage.gotoUrl();
		return landingPage;
	}
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException
	{
		//read json to string
	String jsonContent = FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
	
	//String to HashMap- Jackson Datbind
	
	ObjectMapper mapper = new ObjectMapper();
	  List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
      });
	  return data;
	
	//{map, map}
	}
	
	public String getScreenshot(String testcaseName,WebDriver driver) throws IOException
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest=new File(System.getProperty("user.dir")+"//Reports"+testcaseName+".png");
		FileUtils.copyFile(src, dest);
		return System.getProperty("user.dir")+"//Reports"+testcaseName+".png";
	}
}
