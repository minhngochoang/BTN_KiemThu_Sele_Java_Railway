package testcases;

import Common.Constant.Constant;
import PageObjects.HomePage;
import PageObjects.RegisterPage;
import org.openqa.selenium.chrome.ChromeDriver;
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

        // 4. Nhập thông tin và click Register
        registerPage.register(email, password, confirmPassword, pid);

        // 5. Lấy thông báo lỗi chung
        String actualErrorMsg = registerPage.getRegisterErrorMessage();
        String expectedErrorMsg = "There're errors in the form. Please correct the errors and try again.";
    }

    
    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        Constant.WEBDRIVER.quit();
    }
}
