package genericlibs;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

//Class which contains the Generic Utility Functions 
public class FrameworkUtility implements AutoConstants {
	
	public static void sleepInSeconds(long seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static String getScreenshot(WebDriver driver, String testCaseName) {
		
		String timeStamp = LocalDateTime.now().toString().replace(':', '-');
		String path = IMG_PATH+timeStamp+testCaseName+".png";
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		File tempFile=ts.getScreenshotAs(OutputType.FILE);
		File destFile=new File(path);
		
		try {
			FileUtils.copyFile(tempFile, destFile);
			return path;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
