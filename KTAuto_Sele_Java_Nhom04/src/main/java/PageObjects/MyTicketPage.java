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

    // TC16
    public void cancelTicketByID(String ticketID) {

        String cancelLinkXpath = String.format(
                "//table[@class='MyTable']//input[@value='Cancel' and contains(@onclick, '%s')]",
                ticketID
        );

        By cancelLocator = By.xpath(cancelLinkXpath);

        // 1. Wait và Click nút "Cancel"
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(cancelLocator)).click();

        // 2. Xử lý Alert Confirmation ("Are you sure?")
        Constant.WEBDRIVER.switchTo().alert().accept();
    }

    //Kiểm tra vé có còn hiển thị bằng cách tìm nút Cancel của nó.

    public boolean isTicketDisplayed(String ticketID) {
        // Sử dụng lại logic tìm kiếm trong onclick để xác định sự tồn tại
        String checkXpath = String.format(
                "//table[@class='MyTable']//input[@value='Cancel' and contains(@onclick, '%s')]",
                ticketID
        );
        By ticketLocator = By.xpath(checkXpath);

        // Nếu size > 0 nghĩa là element (nút Cancel) vẫn còn tồn tại
        return Constant.WEBDRIVER.findElements(ticketLocator).size() > 0;
    }

}




