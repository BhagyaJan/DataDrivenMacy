package org.test.help;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AutoTestHelper {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<String> setToArrayList(Set set) {
		
		List<String> list = new ArrayList<String>();
		list.addAll(set);
		return list;   
	}
	
	public static void handleWindow(WebDriver driver) {
		
		Set<String> allWindow1 = driver.getWindowHandles();
		System.out.println(allWindow1);

		driver.switchTo().window(AutoTestHelper.setToArrayList(allWindow1).get(1));
	}
	public static void switchToParentsWindow(WebDriver driver) {
		
		Set<String> allWindow1 = driver.getWindowHandles();
		System.out.println(allWindow1);

		driver.switchTo().window(AutoTestHelper.setToArrayList(allWindow1).get(0));
	}
	
	public static Alert isAlertPresent(WebDriver driver) 
	{ 
		Alert a = null;
	    try 
	    { 
	    	
	        a = driver.switchTo().alert(); 
	        System.out.println(a.getText());
	        return a; 
	    }   // try 
	    catch (NoAlertPresentException Ex) 
	    { 
	        return a; 
	    }   // catch 
	}   // isAlertPresent()

	public static void handleSimpleAlert(WebDriver driver) 
	{
		Alert a=isAlertPresent(driver);
		
		if(a!=null)
		{
			a.accept();	
		}
	}
	public static void handleConfirmAlert(WebDriver driver,int input) 
	{
		Alert a=isAlertPresent(driver);
		
		if(a!=null)
		{
			if(input==1)
				a.accept();	
			else
				a.dismiss();
		}
	}
	public static void handlePromptAlert(WebDriver driver,int input,String text) 
	{
		Alert a=isAlertPresent(driver);
		
		if(a!=null)
		{
			if(input==1)
			{
				a.sendKeys(text);
				a.accept();	
			}
			else
				a.dismiss();
		}
	}

	public static void handleFrames(WebDriver driver, String matchString) {
		
		int size = driver.findElements(By.tagName("iframe")).size();
		
		System.out.println("size : " + size);
		
		for(int i=0; i<size; i++){
			
			driver.switchTo().frame(i);
			List<WebElement> elementFrames = driver.findElements(By.xpath("" + matchString + ""));
			System.out.println(elementFrames.size());
			if(elementFrames.size()==1)
			{
					System.out.println("Frame Index : " + i);
					break;
			}
			driver.switchTo().defaultContent();
			}
		
	}
	
	public static WebDriver getFirefoxDriver()
	{
		System.setProperty("webdriver.gecko.driver", "D:\\BrowserDrivers\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		return driver;
	}
	
	public static WebDriver getChromeDriver()
	{
		System.setProperty("webdriver.chrome.driver", "D:\\BrowserDrivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}

}
