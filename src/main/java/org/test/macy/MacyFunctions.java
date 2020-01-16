package org.test.macy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.test.help.AutoTestHelper;
import org.test.login.Registration;

public class MacyFunctions {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		WebDriver driver = AutoTestHelper.getChromeDriver();
		
		try {
			
			driver.navigate().to("https://www.macys.com");
		
			Thread.sleep(5000);
			
			driver.findElement(By.xpath("//*[@id='closeButton']")).click();
			driver.manage().deleteAllCookies();			
			
			Thread.sleep(2000);

			driver.findElement(By.xpath("//span[text()='Sign Up Now']")).click();
			
			Thread.sleep(3000);
			
			AutoTestHelper.handleWindow(driver);
			
			Registration reg = new Registration();
			Row regRow = reg.readRegistrationDetails();
			
			driver.findElement(By.xpath("//input[@id='fname']")).sendKeys(regRow.getCell(1).getStringCellValue());
			driver.findElement(By.xpath("//input[@id='lname']")).sendKeys(regRow.getCell(2).getStringCellValue());
			driver.findElement(By.xpath("//input[@id='verifyemail']")).sendKeys(regRow.getCell(3).getStringCellValue());
			
			
			Select select =new Select(driver.findElement(By.xpath("//select[@id='country']")));
			
			select.selectByValue("IN");
			
			driver.findElement(By.xpath("//input[@id='optIn']")).click();
			driver.findElement(By.xpath("//input[@id='submitBtn']")).click();
			
			AutoTestHelper.switchToParentsWindow(driver);
			
			driver.findElement(By.xpath("//input[@id='globalSearchInputField']")).sendKeys("Alfany",Keys.ENTER);
			
			Thread.sleep(2000);
			
			driver.manage().deleteAllCookies();
			
			driver.findElement(By.xpath("//a[@href='/shop/product/alfani-mens-stretch-performance-solid-slim-fit-suit-separates-created-for-macys?ID=2638291&CategoryID=17788']")).click();

			driver.manage().deleteAllCookies();
			
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			
			WebElement element = driver.findElement(By.xpath("//li[contains(text(),'36R')]"));
			
			jse.executeScript("arguments[0].scrollIntoView(true);",element);
			
			driver.findElement(By.xpath("//li[contains(text(),'36R')]")).click();
			
			
			driver.findElement(By.xpath("//button[@id='bag-add-2637438']")).click();
			
			driver.manage().deleteAllCookies();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[@id='atbIntViewBagAndCheckout']")).click();
			Thread.sleep(2000);
			
			File fWrite=new File("D:\\PurchasedProduct.xlsx");
			FileOutputStream fos = new FileOutputStream(fWrite);
			Workbook wb = new XSSFWorkbook();
			Sheet s= wb.createSheet("Products Details");
			Row r = s.createRow(2);
			Cell c1 = r.createCell(1);
			Cell c2 = r.createCell(2);
			
			c1.setCellValue(driver.findElement(By.xpath("//a[contains(text(),'Stretch Performance Slim-Fit Jacket, Created for Macy')]")).getText());
			c2.setCellValue(driver.findElement(By.xpath("//div[@class='NO_LABEL large-11 medium-12 minimedium-12 small-11 end']")).getText());
			wb.write(fos);
			
		} finally {

			Thread.sleep(4000);
			driver.quit();
		}

	}

}
