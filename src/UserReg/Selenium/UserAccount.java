package UserReg.Selenium;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UserAccount {
	WebDriver driver = new ChromeDriver();
	
	String emailId="";
	String password="stepStone1234@";
	String firstName ="Test";
	String lastName="Interview";
	
	Actions action = new Actions();
	Verifications verify = new Verifications();

	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver", "D:\\Applications\\chromedriver_win32\\chromedriver.exe");
		UserAccount user = new UserAccount();
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
	
	public void RunIterations(int iteration)
	{
		driver.get("http://www.stepstone.com/");
		driver.manage().window().maximize();
		switch (iteration)
		{
			
			
			case 1 :    System.out.print("Case 1 : Entering Valid details");
						emailId = action.generateEmailId();					
						action.CreateProfile(emailId, password, firstName, lastName);		
						action.UploadPhoto();		
						if(verify.ValidateLoginSuccess()&&verify.VerifyPhotoUploadSuccess()&& verify.VerifyPersonalCredentials(emailId, firstName+""+lastName))
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
					 emailId = action.generateEmailId();
					 action.CreateProfile(emailId+"@#", password, firstName, lastName);
						if(!verify.VerifyWarningMessage())
						{
							System.out.print("Email id entered id invalid");
						}
						
						break;
						
						
			case 3 : System.out.print("Enter Invalid Password");
			 
					action.CreateProfile(emailId, "1234", firstName, lastName);
				    if(!verify.VerifyPasswordLengthMessage())
				    {
					 System.out.print("Invalid Password and Password lenght message is displayed");
				    }
				   
				    break;
				    
			case 4 : System.out.println("Entering all details except first name");
					 emailId = action.generateEmailId();
					 action.CreateProfile(emailId, password, "", lastName);
					 if(!verify.VerifyMandatoryFieldMessage())
					 {
						 System.out.println("Mandatory field warning message is displayed");
					 }
					 break;
		}
		driver.close();
	}
	

}
