package testcases;

import Common.Constant.Constant;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest
{
    @BeforeMethod
    public void beforeMethod()
    {
        System.out.println("Pre-condition");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @Test
    public void TC01()
    {
        System.out.println("TC01 - User can log into Railway with valid username and password");

        // Step 1. Navigate to QA Railway Website
        HomePage homePage = new HomePage();
        homePage.open();

        // Step 2. Click on "Login" tab
        LoginPage loginPage = homePage.gotoLoginPage();

        // Step 3. Enter valid Email and Password
        HomePage actualHomePage = loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        // Verify
        String actualMsg = actualHomePage.getWelcomeUser().trim();
        String expectedMsg = "Welcome " + Constant.USERNAME;

        Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed correctly!");
    }

    @Test
    public void TC02()
    {
        System.out.println("TC02 - User can't login with blank Username textbox");

        // Step 1. Navigate to QA Railway Website
        HomePage homePage = new HomePage();
        homePage.open();

        // Step 2. Click on "Login" tab
        LoginPage loginPage = homePage.gotoLoginPage();

        // Step 3. User doesn't type any words into "Username" textbox but enter valid information into "Password" textbox
        loginPage.getTxtUsername().sendKeys("");
        loginPage.getTxtPassword().sendKeys(Constant.PASSWORD);

        // Step 4. Click on "Login" button
        WebElement loginButton = loginPage.getBtnLogin();
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("arguments[0].click();", loginButton);

        // Verify
        String actualMessage =  loginPage.getLoginErrorMsg();
        String expectedMessage = "There was a problem with your login and/or errors exist in your form.";
        Assert.assertEquals(actualMessage, expectedMessage, "Error message should be displayed");
    }

    @Test
    public void TC03() {

        System.out.println("TC03 - User cannot log into Railway with invalid password");

        //Step 1. Navigate to QA Railway Website
        HomePage homePage = new HomePage();
        homePage.open();

        // Step 2. Click on "Login" tab
        LoginPage loginPage = homePage.gotoLoginPage();

        // Step 3. Enter valid Email and invalid Password
        loginPage.getTxtUsername().sendKeys(Constant.USERNAME);
        loginPage.getTxtPassword().sendKeys("invalid_password");


        // Step 4. Click on "Login" button
        WebElement loginButton = loginPage.getBtnLogin();
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("arguments[0].click();", loginButton);

        // Verify
        String actualMessage =  loginPage.getLoginErrorMsg();
        String expectedMessage = "There was a problem with your login and/or errors exist in your form.";
        Assert.assertEquals(actualMessage, expectedMessage, "Error message should be displayed");
    }

    @Test
    public void TC04() {
        System.out.println("TC04 - Login page displays when un-logged User clicks on Book ticket tab");

        // Step 1. Navigate to QA Railway Website
        HomePage homePage = new HomePage();
        homePage.open();

        // Step 2. Click on "Book ticket" tab
        LoginPage loginPage = homePage.gotoBookTicketPage_ExpectLoginPage();

        // Verify
        String actualTitle = loginPage.getPageHeaderText();
        String expectedTitle = "Login Page";

        Assert.assertEquals(actualTitle, expectedTitle, "Login page was not displayed correctly");
    }

    @Test
    public void TC05() {
        System.out.println("TC05 - System shows message when user enters wrong password several times");

        // Step 1. Navigate to QA Railway Website
        HomePage homePage = new HomePage();
        homePage.open();

        // Step 2. Click on "Login" tab
        LoginPage loginPage = homePage.gotoLoginPage();

        // Step 3, 4, 5: Lặp lại chính xác 4 LẦN
        for (int i = 1; i <= 4; i++) {
            System.out.println("Đang nhap sai lan: " + i);

            loginPage.attemptLogin(Constant.USERNAME, "wrongPassword");
        }

        // Vefiry
        String actualMessage = loginPage.getLoginErrorText();
        String expectedMessage = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";

        Assert.assertEquals(actualMessage, expectedMessage, "Error message content is not correct after 4 attempts!");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        Constant.WEBDRIVER.quit();
    }
}
