package testcases;

import Common.Constant.Constant;
import PageObjects.HomePage;
import PageObjects.RegisterPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterTest {
    @BeforeMethod
    public void beforeMethod()
    {
        System.out.println("Pre-condition");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @Test
    public void TC10() {
        // Step 1. Navigate to QA Railway Website
        HomePage homePage = new HomePage();
        homePage.open();

        // Step 2. Click on "Register" tab
        RegisterPage registerPage = homePage.gotoRegisterPage();

        // Step 3. Enter valid information into all fields except "Confirm password" is not the same with "Password"
        String email = "rappergb@gmail.com";
        String password = "1234@abcd";
        String confirmPassword = "1234@abc"; // Khong khop
        String pid = "123456789";

        registerPage.register(email, password, confirmPassword, pid);

        // Verify
        String actualErrorMsg = registerPage.getRegisterErrorMessage();
        String expectedErrorMsg = "There're errors in the form. Please correct the errors and try again.";

        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed correctly!");
    }

    /*@Test
    public void TC11() throws InterruptedException {
        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.gotoRegisterPage();
        Thread.sleep(3000);

        String email = "ngan03305@gmail.com";
        registerPage.register(email, "", "", "");

        // Verify: General error message
        WebElement generalError = registerPage.getGeneralErrorMessage();
        Assert.assertTrue(generalError.isDisplayed(), "General error message not displayed");
        Assert.assertEquals(generalError.getText(),
                "There're errors in the form. Please correct the errors and try again.",
                "General error message text is incorrect");

        // Verify: Password error message
        WebElement passwordError = registerPage.getPasswordErrorMessage();
        Assert.assertTrue(passwordError.isDisplayed(), "Password error message not displayed");
        Assert.assertEquals(passwordError.getText(), "Invalid password length",
                "Password error message is incorrect");

        // Verify: PID error message
        WebElement pidError = registerPage.getPIDErrorMessage();
        Assert.assertTrue(pidError.isDisplayed(), "PID error message not displayed");
        Assert.assertEquals(pidError.getText(), "Invalid ID length",
                "PID error message is incorrect");

        System.out.println("TC11 Passed");
    }*/
    
    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        Constant.WEBDRIVER.quit();
    }
}
