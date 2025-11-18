
package testcases;

import Common.Constant.Constant;
import PageObjects.ChangePasswordPage;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ChangePasswordTest
{
    @BeforeMethod
    public void beforeMethod()
    {
        System.out.println("Pre-condition");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @Test
    public void TC09()
    {
        final String oldPassword = "1234@abc";
        final String newPassword = "NewPass" + System.currentTimeMillis();

        HomePage homePage = new HomePage();

        // Khối try-finally đảm bảo password luôn được khôi phục dù test thất bại
        try {
            // Step 1: Navigate to QA Railway Website
            homePage.open();

            // Step 2: Login với mật khẩu GỐC
            LoginPage loginPage = homePage.gotoLoginPage();
            homePage = loginPage.login("wpotmaster@gmail.com", oldPassword);

            // Step 3: Click on "Change Password" tab (FIX: Sửa cú pháp khai báo PageObject)
            ChangePasswordPage changePasswordPage = homePage.gotoChangePasswordPage();

            // Step 4: Enter valid value into all fields (FIX: Gọi phương thức trên đối tượng)
            changePasswordPage.changePassword(oldPassword, newPassword, newPassword);

            // Step 5: Verify message "Your password has been updated" appears
            WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));

            WebElement successMsgElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(changePasswordPage.getSuccessMessageLocator())
            );

            String actualMsg = successMsgElement.getText().trim();
            String expectedMsg = "Your password has been updated!";

            Assert.assertEquals(actualMsg, expectedMsg, "Password change message is incorrect!");

            Constant.PASSWORD = newPassword;

        } finally {
            // Mật khẩu hiện tại là giá trị cuối cùng của Constant.PASSWORD
            String currentPassword = Constant.PASSWORD;

            // Chỉ chạy Revert nếu mật khẩu đã bị thay đổi
            if (!currentPassword.equals(oldPassword)) {

                System.out.println("INFO: Reverting password...");

                // 1. Đăng nhập lại bằng mật khẩu MỚI
                homePage.open();
                LoginPage loginPage = new LoginPage();
                homePage = loginPage.login(Constant.USERNAME, currentPassword);

                // 2. Thay đổi Mật khẩu về giá trị GỐC
                ChangePasswordPage changePasswordPage = homePage.gotoChangePasswordPage();
                changePasswordPage.changePassword(currentPassword, oldPassword, oldPassword);

                // 3. Khôi phục lại Constant.PASSWORD về giá trị ban đầu.
                Constant.PASSWORD = oldPassword;
                System.out.println("INFO: Password successfully reverted to original value.");
            }
        }
        /*
        System.out.println("TC09 - User can change password successfully");

        String oldPassword = "1234@abc";
        String newPassword = "NewPwd" + System.currentTimeMillis();

        System.out.println(" Chuỗi Mật khẩu MỚI được tạo: " + newPassword);

        // Step 1: Navigate to QA Railway Website
        HomePage homePage = new HomePage();
        homePage.open();

        // Step 2: Login with valid account
        LoginPage loginPage = homePage.gotoLoginPage();
        homePage = loginPage.login("wpotmaster@gmail.com", "1234@abc");

        // Step 3, 4: Click on "Change Password" tab and Enter valid value into all fields
        ChangePasswordPage changePasswordPage = homePage.gotoChangePasswordPage();

        changePasswordPage.changePassword(oldPassword, newPassword, newPassword);

        // Verify
        String actualMsg = changePasswordPage.getSuccessMessage();
        String expectedMsg = "Your password has been updated";

        Assert.assertEquals(actualMsg, expectedMsg, "Password update success message is incorrect.");

        // 5. CẬP NHẬT TRẠNG THÁI: Ghi đè mật khẩu cũ bằng mật khẩu mới
        Constant.PASSWORD = newPassword;
        System.out.println("Password updated to: " + newPassword);*/
    }

    @AfterMethod
    public void afterMethod()
    {
        System.out.println("Post-condition");
        Constant.WEBDRIVER.quit();
    }
}
