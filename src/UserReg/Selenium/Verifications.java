package UserReg.Selenium;

import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TakesScreenshot;

public class Verifications extends UserAccount {
	
	
	public boolean ValidateLoginSuccess()
	{
		String loginmessage = driver.findElement(By.xpath("//*[@id=\"EarcuHeadingTextBlockTitle\"]/h1")).getAttribute("innerHTML");
		if(loginmessage.contains("myHome"))
		{
			return true;
		}
		else
		{
			return false;
		}
		
				
	}
	
	public boolean VerifyPhotoUploadSuccess()
	{
		if(driver.findElement(By.className("imageContainer imageContainer_CustomImage")).isDisplayed())
		{
			
			System.out.print("Photo upload Successful");
			return true;
		}
		else
		{
			System.out.print("Photo upload failed");
			return false;
		}
		
	}

	public boolean VerifyWarningMessage()
	{
		if(driver.findElement(By.className("actionMessage actionMessageWarning")).isDisplayed())
		{
			System.out.print("Some of the information you have entered on this page is invalid or missing");
			return false;
		}
		return true;
	}

	public boolean VerifyPasswordLengthMessage()
	{
		if(driver.findElement(By.className("usernameErrors")).getAttribute("innerHTML").contains("The password entered is too short. The minimum length is 6"))
		{
			return false;
	
		}
		else
		{
			return true;
		}
	}
	
	public boolean VerifyMandatoryFieldMessage() {
		
		if(driver.findElement(By.className("controlError")).getAttribute("innerHTML").contains("This field is mandatory."))
			return false;
		else
			return true;
		
	}
	
	public boolean VerifyPersonalCredentials(String emailId,String name)
	{
		WebElement nameInfo = driver.findElement(By.className("iconRowValue iconRowValue_name"));
		String nameValue = nameInfo.findElement(By.className("iconRowValueData")).getAttribute("innerHTML");
		
		WebElement emailInfo = driver.findElement(By.className("iconRowValue iconRowValue_email"));
		String emailValue = emailInfo.findElement(By.className("iconRowValueData")).getAttribute("innerHTML");
		
		TakeScreenshot(emailInfo);
		
		if(nameValue.contentEquals(name) && emailValue.contentEquals(emailId))
		{
			System.out.println("Information recorded is correct");
			return true;
		}
		else 
		{
			return false;
		}
		
		
	}
	
	public void TakeScreenshot(WebElement webElement)
	{
		TakesScreenshot screenshot = (TakesScreenshot)(webElement);
		File image = screenshot.getScreenshotAs(OutputType.FILE);
	}

}
