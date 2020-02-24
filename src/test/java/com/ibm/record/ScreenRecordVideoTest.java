package com.ibm.record;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.ibm.util.ScreenRecorderUtil;

public class ScreenRecordVideoTest {

	@Test(description = "Record Video", enabled = true)
	public void recordVideo() throws Exception {
	
		System.setProperty("webdriver.chrome.driver", "C:\\WorkSpace\\Eclipse_WorkSpace\\JobPortals\\libs//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com");
		driver.manage().window().maximize();
		ScreenRecorderUtil.startRecord("GoogleTestRecording");//Starting point of video recording
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Selenium"); 
		Thread.sleep(3000);
		driver.findElement(By.name("btnK")).click();
        Thread.sleep(3000);
        ScreenRecorderUtil.stopRecord();//End point of video recording
        driver.quit();
	}

}
