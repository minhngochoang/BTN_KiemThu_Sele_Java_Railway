package PageObjects;

import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyTicketPage extends GeneralPage{

    private WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));

    // Bạn cần tìm XPath của tiêu đề trang "My ticket"
    private final By lblPageHeader = By.xpath("//div[@id='content']/h1"); // Tieu de trang My ticket

    public boolean isPageTitleDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(lblPageHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
