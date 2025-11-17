
package testcases;

import Common.Constant.Constant;
import PageObjects.ChangePasswordPage;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChangePasswordTest
{
    @BeforeMethod
    public void beforeMethod()
    {
        System.out.println("Pre-condition");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    /*@Test
    public void TC09()
    {
        System.out.println("TC09 - User can change password successfully");

        String oldPassword = Constant.PASSWORD;
        String newPassword = "NewPwd" + System.currentTimeMillis();

        System.out.println(" Chuỗi Mật khẩu MỚI được tạo: " + newPassword);

        // Step 1: Navigate to QA Railway Website
        HomePage homePage = new HomePage();
        homePage.open();

        // Step 2: Login with valid account
        LoginPage loginPage = homePage.gotoLoginPage();
        homePage = loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        // Step 3, 4: Click on "Change Password" tab and Enter valid value into all fields
        ChangePasswordPage changePasswordPage = homePage.gotoChangePasswordPage();

        changePasswordPage.changePassword(oldPassword, newPassword, newPassword);

        // Verify
        String actualMsg = changePasswordPage.getSuccessMessage();
        String expectedMsg = "Your password has been updated";

        Assert.assertEquals(actualMsg, expectedMsg, "Password update success message is incorrect.");

        // 5. CẬP NHẬT TRẠNG THÁI: Ghi đè mật khẩu cũ bằng mật khẩu mới
        Constant.PASSWORD = newPassword;
        System.out.println("Password updated to: " + newPassword);
    }*/

    @AfterMethod
    public void afterMethod()
    {
        System.out.println("Post-condition");
        Constant.WEBDRIVER.quit();
    }
}
