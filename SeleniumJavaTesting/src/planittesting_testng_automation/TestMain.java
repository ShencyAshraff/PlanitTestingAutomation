package planittesting_testng_automation;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class TestMain {

	
	WebDriver driver = null;
		
	@BeforeMethod
	public void initialSetUp() {
			System.out.println("Initial Driver Setup...");
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\91940\\Downloads\\chromedriver_win32\\chromedriver.exe"); 
			driver=new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
			
	@Test
	public void TestCase1()
	{
			System.out.println("TEST CASE 1... EXECUTION STARTING...");
			System.out.println("VALIDATING: CONTACT PAGE MANDATORY ERROR MESSAGES");
			driver.get("http://jupiter.cloud.planittesting.com");
			driver.manage().window().maximize();
			System.out.println("WINDOW MAXIMISATION SUCCESSFUL....");
			System.out.println("HOME PAGE LOADED SUCCESSFULLY....");
				
			driver.findElement(By.xpath("//li[@id='nav-contact']/a")).click();
			System.out.println("CONTACT LINK CLICKED...");
				
			driver.findElement(By.xpath("//a[text()='Submit']")).click();
			System.out.println("CONTACT PAGE SUBMIT BUTTON CLICKED...");
				
			System.out.println("STARTING - CONTACT PAGE ERROR VERIFICATION...");
				
			String headerMandatoryMessage = driver.findElement(By.xpath("//div[@class='alert alert-error ng-scope']")).getText();
			System.out.println("Mandatory field to be filled header Message: "+headerMandatoryMessage);
				
			String forenameMandatoryErrorMsg = driver.findElement(By.xpath("//span[@id='forename-err']")).getText();
			if (forenameMandatoryErrorMsg.equals("Forename is required")) {
				System.out.println("Verified forename is required message - PASS");
			}
			else
				System.out.println("Verified forename is required message - FAIL");
				
			String emailMandatoryErrorMsg = driver.findElement(By.xpath("//span[@id='email-err']")).getText();
			if (emailMandatoryErrorMsg.equals("Email is required")) {
				System.out.println("Verified E-Mail is required message - PASS");
			}
			else
				System.out.println("Verified E-Mail is required message - FAIL");
				
			String messageMandatoryErrorMsg = driver.findElement(By.xpath("//span[@id='message-err']")).getText();
			if (messageMandatoryErrorMsg.equals("Message is required")) {
				System.out.println("Verified Message is required - PASS");
			}
			else
				System.out.println("Verified Message is required - FAIL");	
				
			System.out.println("CONTACT PAGE ERROR VERIFICATION COMPLETED...");

			System.out.println("POPULATING MANDATORY FIELDS IN CONTACT PAGE...");
			driver.findElement(By.xpath("//input[@id='forename']")).sendKeys("John");
			driver.findElement(By.xpath("//input[@id='email']")).sendKeys("john.example@planit.net.au");
			driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("Message Field Text Test");
			System.out.println("MANDATORY FIELDS ENTERED - FORENAME, EMAIL AND MESSAGE...");
				
			System.out.println("START VALIDATING ERRORS ARE GONE WHEN MANDATORY FIELDS ARE ENTERED.. ");
			if( (driver.findElements(By.xpath("//span[@id='forename-err']")).size()>0)|| 
					(driver.findElements(By.xpath("//span[@id='email-err']")).size()>0)||
					(driver.findElements(By.xpath("//span[@id='message-err']")).size()>0))
				{
					System.out.println("VALIDATE ERRORS GONE ON ENTERING MANDATORY FIELDS - FAIL");
				}
			else {
						System.out.println("VALIDATE ERRORS GONE ON ENTERING MANDATORY FIELDS - PASS");
				}
				
			}	
		
	@Test
	public void TestCase2()
	{
			System.out.println("TEST CASE 2... EXECUTION STARTING...");
			System.out.println("VALIDATING: SUCCESSFUL CONTACT PAGE SUBMISSION MESSAGE");
			driver.get("http://jupiter.cloud.planittesting.com");
			driver.manage().window().maximize();
			System.out.println("WINDOW MAXIMISATION SUCCESSFUL....");
			System.out.println("HOME PAGE LOADED SUCCESSFULLY....");
			
			driver.findElement(By.xpath("//li[@id='nav-contact']/a")).click();
			System.out.println("CONTACT LINK CLICKED...");

			System.out.println("POPULATING MANDATORY FIELDS IN CONTACT PAGE...");
			driver.findElement(By.xpath("//input[@id='forename']")).sendKeys("John");
			driver.findElement(By.xpath("//input[@id='email']")).sendKeys("john.example@planit.net.au");
			driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("Message Field Test Fest");
			System.out.println("MANDATORY FIELDS ENTERED - FORENAME, EMAIL AND MESSAGE...");

			driver.findElement(By.xpath("//a[text()='Submit']")).click();
			System.out.println("CONTACT PAGE SUBMIT BUTTON CLICKED...");

			String textSuccessfulMsg = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
			System.out.println("SUBMIT BUTTON CLICKED SUCCESSFUL MESSAGE VERIFIED: "+textSuccessfulMsg);
			
		}
		
	@Test	
	public void TestCase3() throws Exception{
			System.out.println("TEST CASE 3... EXECUTION STARTING...");
			System.out.println("VALIDATING: CART PAGE PRICE, QUANTITY AND SUBTOTALS");
			driver.get("http://jupiter.cloud.planittesting.com");
			driver.manage().window().maximize();
			System.out.println("WINDOW MAXIMISATION SUCCESSFUL....");
			System.out.println("HOME PAGE LOADED SUCCESSFULLY....");
			driver.findElement(By.xpath("//a[text()='Start Shopping »']")).click();
			System.out.println("START SHOPPING BUTTON CLICKED....");
			
			for (int click1=0;click1<2;click1++) {
				driver.findElement(By.xpath("//h4[text()='Stuffed Frog']//parent::div//a[text()='Buy']")).click();
			}
			
			for (int click2=0;click2<5;click2++) {
				driver.findElement(By.xpath("//h4[text()='Fluffy Bunny']//parent::div//a[text()='Buy']")).click();
			}
			for (int click3=0;click3<3;click3++) {
				driver.findElement(By.xpath("//h4[text()='Valentine Bear']//parent::div//a[text()='Buy']")).click();
			}
			System.out.println("ITEMS ADDED SUCCESSFULLY TO CART....");
			
			driver.findElement(By.xpath("//a[@href ='#/cart']")).click();
			System.out.println("CART PAGE LOADED....");
			Thread.sleep(3000);
			
			String delim="$";
			final DecimalFormat df = new DecimalFormat("0.00");
			
			
			//****************************STUFFED FROG****************************************
			
			float StuffedFrogPrice = Float.parseFloat(driver.findElement(By.xpath("//td[text()=' Stuffed Frog']//following-sibling::td[1]")).getText().replace(delim,""));
			float StuffedFrogQuantity = Float.parseFloat(driver.findElement(By.xpath("//td[text()=' Stuffed Frog']//following-sibling::td[2]/input")).getAttribute("value"));
			float StuffedFrogSubTotal = Float.parseFloat(driver.findElement(By.xpath("//td[text()=' Stuffed Frog']//following-sibling::td[3]")).getText().replace(delim,""));
					
			float StuffedFrogSubTotal1 = StuffedFrogPrice*StuffedFrogQuantity;
			float StuffedFrogCalculate = Float.parseFloat(df.format(StuffedFrogSubTotal1));
			System.out.println(StuffedFrogCalculate);
			        
			System.out.println("Stuffed Frog Price: "+StuffedFrogPrice);
			System.out.println("Stuffed Frog Quantity: "+StuffedFrogQuantity);
			System.out.println("Stuffed Frog SubTotal: "+StuffedFrogSubTotal);
			
			//STUFFED FROG : PRODUCT * QUANTITY = SUBTOTAL
			if(StuffedFrogCalculate==StuffedFrogSubTotal){
				System.out.println("SUBTOTAL FOR STUFFED FROG PRODUCT IS CORRECT: Price * Quantity = SubTotal - PASS");
			}
			else {
				System.out.println("SUBTOTAL FOR STUFFED FROG PRODUCT IS INCORRECT - FAIL");
			}
			
			//**************************** FLUFFY BUNNY ****************************************
			
			float FluffyBunnyPrice = Float.parseFloat(driver.findElement(By.xpath("//td[text()=' Fluffy Bunny']//following-sibling::td[1]")).getText().replace(delim,""));
			float FluffyBunnyQuantity = Float.parseFloat(driver.findElement(By.xpath("//td[text()=' Fluffy Bunny']//following-sibling::td[2]/input")).getAttribute("value"));
			float FluffyBunnySubTotal = Float.parseFloat(driver.findElement(By.xpath("//td[text()=' Fluffy Bunny']//following-sibling::td[3]")).getText().replace(delim,""));
			
			float FluffyBunnySubTotal2 = FluffyBunnyPrice*FluffyBunnyQuantity;
			float FluffyBunnyCalculate = Float.parseFloat(df.format(FluffyBunnySubTotal2));
	        System.out.println(FluffyBunnyCalculate);
	        
			System.out.println("Fluffy Bunny Price: "+FluffyBunnyPrice);
			System.out.println("Fluffy Bunny Quantity: "+FluffyBunnyQuantity);
			System.out.println("Fluffy Bunny SubTotal: "+FluffyBunnySubTotal);
			
			//FLUFFY BUNNY : PRODUCT * QUANTITY = SUBTOTAL
			if(FluffyBunnyCalculate==FluffyBunnySubTotal) {
				System.out.println("SUBTOTAL FOR FLUFFY BUNNY PRODUCT IS CORRECT: Price * Quantity = SubTotal - PASS");
			}
			else {
				System.out.println("SUBTOTAL FOR FLUFFY BUNNY PRODUCT IS INCORRECT - FAIL");
			}
			
			//**************************** VALENTINE BEAR ****************************************
			
			//VALENTINE BEAR : PRODUCT * QUANTITY = SUBTOTAL
			float ValentineBearPrice = Float.parseFloat(driver.findElement(By.xpath("//td[text()=' Valentine Bear']//following-sibling::td[1]")).getText().replace(delim,""));
			float ValentineBearQuantity = Float.parseFloat(driver.findElement(By.xpath("//td[text()=' Valentine Bear']//following-sibling::td[2]/input")).getAttribute("value"));
			float ValentineBearSubTotal = Float.parseFloat(driver.findElement(By.xpath("//td[text()=' Valentine Bear']//following-sibling::td[3]")).getText().replace(delim,""));

			float ValentineBearSubTotal3 = ValentineBearPrice*ValentineBearQuantity;
			float ValentineBearCalculate = Float.parseFloat(df.format(ValentineBearSubTotal3));
	        System.out.println(ValentineBearCalculate);
			
			System.out.println("Valentine Bear Price: "+ValentineBearPrice);
			System.out.println("Valentine Bear Quantity: "+ValentineBearQuantity);
			System.out.println("Valentine Bear SubTotal: "+ValentineBearSubTotal);
			
			if(ValentineBearCalculate==ValentineBearSubTotal) {
				System.out.println("SUBTOTAL FOR VALENTINE BEAR PRODUCT IS CORRECT: Price * Quantity = SubTotal - PASS");
			}
			else {
				System.out.println("SUBTOTAL FOR VALENTINE BEAR PRODUCT IS INCORRECT - FAIL");
			}
			
			// VERIFICATION : TOTAL = SUM(SUB TOTALS)
			String delim2="Total: ";
			Float totalCartValue = Float.parseFloat(driver.findElement(By.xpath("//tfoot//strong")).getText().replace(delim2,""));
			System.out.println("TOTAL CART VALUE: "+ totalCartValue);
			System.out.println("SUM (SUB TOTALS): "+ (StuffedFrogSubTotal+FluffyBunnySubTotal+ValentineBearSubTotal));
			if((StuffedFrogSubTotal+FluffyBunnySubTotal+ValentineBearSubTotal)==totalCartValue) {
				System.out.println("VERIFIED TOTAL = SUM(SUB TOTALS) - PASS");
			}
			else {
				System.out.println("VERIFIED TOTAL = SUM(SUB TOTALS) - MISMATCH IN VALUE : FAIL");
			}
			
		}
		
	@AfterMethod
	public void tearDownValidation() {
			System.out.println("INITIALISING DRIVER CLOSING...");
			driver.close();
			System.out.println("TESTING COMPLETED SUCCESSFULLY...");
	}

}
