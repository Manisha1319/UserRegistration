package UserReg.Selenium;

import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Actions extends UserAccount{
	
	
	
	public String  generateEmailId()
	{
        return "TempEmail-" + UUID.randomUUID().toString() + "@Interview.com";

	}
		
	public void CreateProfile(String emailId,String password,String firstName,String lastName)
	{
		
		//Step 2: CLick on "Join Us" menu
				driver.findElement(By.id("menu-item-214")).click();
				
		//Step 3: Click on Login menu
				driver.findElement(By.xpath("//*[@id=\"nav_head\"]/div[2]/div/ul/li[4]/a")).click();
				
		//STep 4: Click on register link to register a new profile
				driver.findElement(By.xpath("//*[@id=\"LoginInfoBlock_pageBlock\"]/div/div/a")).click();
				
		//Step 5: Enter Email.
				try
				{
				driver.findElement(By.id("ProfileRegister_newregister_username")).sendKeys(emailId);
				}
				catch(Throwable ex)
				{
					//throws an exception if email field was not present or could not be entered.
					System.out.print("Exception:"+ex.getMessage());
				}
				
		//Step 6: Enter password
				try
				{
				driver.findElement(By.id("ProfileRegister_newregister_password")).sendKeys(password);
				}
				catch(Throwable ex)
				{
					System.out.print("Exception:"+ex.getMessage());
				}
				
		//Step 7: Enter Confirm Password
				try
				{
				driver.findElement(By.id("ProfileRegister_newregister_confirmpassword")).sendKeys(password);
				}
				catch(Throwable ex)
				{
					System.out.print("Exception:"+ex.getMessage());
				}
				
				
		//Step 8: Enter First Name
				try
				{
				driver.findElement(By.id("ProfileRegister_newregister_firstname")).sendKeys(firstName);
				}
				catch(Throwable ex)
				{
					System.out.print("Exception:"+ex.getMessage());
				}
				
		//Step 9: Enter Last Name
				try{
				driver.findElement(By.id("ProfileRegister_newregister_surname")).sendKeys(lastName);
				}
				catch(Throwable ex)
				{
					System.out.print("Exception:"+ex.getMessage());
				}
				
		//Step 10: Click on Create Account Button
				try
				{
				driver.findElement(By.id("buttonSubmit_register")).click();
				}
				catch(Throwable ex)
				{
					System.out.print("Exception:"+ex.getMessage());
				}
				
	}
	
	public void UploadPhoto()
	{
		WebElement profileIcon = driver.findElement(By.id("WelcomeInfoBlock_MyDetails_formRow")); 
		if(profileIcon.isDisplayed())
		{
			profileIcon.click();
			WebElement uploadPhotoIcon = driver.findElement(By.name("uploadprofileimagehiddenfiletag"));
			uploadPhotoIcon.sendKeys("C:\\Users\\Manisha\\Documents\\Image5.jpg");
			driver.findElement(By.id("buttonSubmit_ajaxSave")).click();	
		}
		
		
	}

}
