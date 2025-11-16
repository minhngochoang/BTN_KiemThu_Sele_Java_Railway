package PageObjects;

import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ChangePasswordPage extends GeneralPage {

    private WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));

    private final By lblPageHeader = By.xpath("//div[@id='content']/h1"); //Tieu de trang Change Password

    public boolean isPageTitleDisplayed() {
        try {
            // Chờ cho tiêu đề (ví dụ: <h1>Change Password</h1>) xuất hiện
            return wait.until(ExpectedConditions.visibilityOfElementLocated(lblPageHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
