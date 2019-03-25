package UserReg.Selenium;


import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class UserAccount {
	
	WebDriver driver = new ChromeDriver();
	String emailId="";
	String password="stepStone1234@";
	String firstName ="Test";
	String lastName="Interview";

	public static void main(String[] args) throws Throwable {
		System.setProperty("webdriver.chrome.driver", "D:\\Applications\\chromedriver_win32\\chromedriver.exe");
		UserAccount user = new UserAccount();
		user.driver.get("http://www.stepstone.com/");
        user.driver.manage().window().maximize();
		//The code is written to test different scenarios while registering, like valid details, invalid email and password scenarios.
		//The data driven testing can be achieved through excel but here I have chosen to use a Switch case.
		
		//SCenario 1 : Entering all Valid details
        
        
		user.RunIterations(1);
		
		//Scenario 2 : Entering Invalid Email
		user.RunIterations(2);
		
		//Scenario 3 : ENtering Invalid Password and thereby Verifying length of Password
		user.RunIterations(3);
		
		//Scenario 4 : Leaving first name blank to verify mandatory field message
		user.RunIterations(4);

	}
	
	public void RunIterations(int iteration) throws Throwable
	{
		
		switch (iteration)
		{
			
			
			case 1 :    System.out.print("Case 1 : Entering Valid details");
						emailId = generateEmailId();
						
						CreateProfile(emailId, password, firstName, lastName);		
						UploadPhoto();		
						if(ValidateLoginSuccess()&&VerifyPhotoUploadSuccess()&& VerifyPersonalCredentials(emailId, firstName+""+lastName))
						{
							//Assert can be set up to indicate success and failure
							System.out.print("Test Passed");
						}
						else
						{
							//Assert can be set up to indicate success and failure
							System.out.print("Test failed");
						}
						
						break;
						
						
						
			case 2 : System.out.print("Enter an invalid email id");
					 emailId = generateEmailId();
					 CreateProfile(emailId+"@#", password, firstName, lastName);
						if(!VerifyWarningMessage())
						{
							System.out.print("Email id entered id invalid");
						}
						
						break;
						
						
			case 3 : System.out.print("Enter Invalid Password");
			 
					CreateProfile(emailId, "1234", firstName, lastName);
				    if(!VerifyPasswordLengthMessage())
				    {
					 System.out.print("Invalid Password and Password lenght message is displayed");
				    }
				   
				    break;
				    
			case 4 : System.out.println("Entering all details except first name");
					 emailId = generateEmailId();
					 CreateProfile(emailId, password, "", lastName);
					 if(!VerifyMandatoryFieldMessage())
					 {
						 System.out.println("Mandatory field warning message is displayed");
					 }
					 break;
		}
		driver.close();
	}
	
	
//---------------------Action Section-------------------------------------------------------
	public void CreateProfile(String emailId,String password,String firstName,String lastName) throws Throwable

	{
		
		//Step 2: CLick on "Join Us" menu
				driver.findElement(By.id("menu-item-214")).click();
				Thread.sleep(2000);
				
		//Step 3: Click on Login menu
				driver.findElement(By.xpath("//*[@id=\"nav_head\"]/div[2]/div/ul/li[4]/a")).click();
				Thread.sleep(2000);
				
		//STep 4: Click on register link to register a new profile
				driver.findElement(By.xpath("//*[@id=\"LoginInfoBlock_pageBlock\"]/div/div/a")).click();
				Thread.sleep(2000);
				
		//Step 5: Enter Email.
				try
				{
				driver.findElement(By.xpath("//*[@id=\"ProfileRegister_newregister_username\"]")).sendKeys(emailId);
				Thread.sleep(4000);
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
	
	public String  generateEmailId()
	{
        return "TempEmail-" + UUID.randomUUID().toString() + "@Interview.com";

	}
	
	public void UploadPhoto() throws Throwable
	{ 
		WebElement profileIcon = driver.findElement(By.id("WelcomeInfoBlock_MyDetails_formRow")); 
		if(profileIcon.isDisplayed())
		{
			profileIcon.click();
			Thread.sleep(2000);
			WebElement photosection = driver.findElement(By.className("messageTextContainer"));
			photosection.click();
			Thread.sleep(2000);
			WebElement profilepicIcon = driver.findElement(By.xpath("//*[@id=\"eArcuProfileImage\"]/div[1]"));
			profilepicIcon.click();
			Thread.sleep(2000);
			
			StringSelection ss = new StringSelection("C:\\Users\\Suhas\\OneDrive\\Pictures\\2-1024x660.jpg");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
			
			Robot robot = new Robot();

			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			
			
			driver.findElement(By.id("buttonSubmit_ajaxSave")).submit();
			Thread.sleep(2000);
			
		}
		
		
	}
	
//----------------Verification Section-------------------------------------------------------
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



