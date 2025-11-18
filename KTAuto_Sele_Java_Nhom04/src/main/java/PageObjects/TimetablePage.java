
package PageObjects;

import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TimetablePage extends GeneralPage {

    // TC15
    public BookTicketPage clickBookTicketForRoute(String departStation, String arriveStation) {

        // 1. Xây dựng Dynamic XPath (Bộ lọc hàng ổn định nhất)
        // Tìm dòng (tr) chứa cả hai Ga đi và Ga đến, sau đó nhắm mục tiêu vào link 'Book ticket'.
        String dynamicXpath = String.format(
                //"//table//tr[td[normalize-space(text())='%s'] and td[normalize-space(text())='%s']]//a[contains(text(),'Book ticket')]",
                "//table//tr[td[contains(text(),'%s')] and td[contains(text(),'%s')]]//a[contains(text(),'Book ticket')]",
                departStation,
                arriveStation
        );

        By bookTicketLink = By.xpath(dynamicXpath);

        // 2. Chờ đợi rõ ràng (Explicit Wait) và Click
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(bookTicketLink)).click();

        return new BookTicketPage();
    }
}
/**
     * Tìm và click liên kết "Book ticket" cho một tuyến đường cụ thể.
     * Sử dụng following-sibling để xác định vị trí tương đối giữa Ga đi và Ga đến.
     *//*

    public BookTicketPage bookTicketForRoute(String departStation, String arriveStation) {

        // 1. Dựng XPath Dynamic
        String dynamicXpath = String.format(
                // Bắt đầu từ Ga đi (cột 2), sử dụng normalize-space() để an toàn với khoảng trắng
                "//td[normalize-space(text())='%s']" +
                        // Sau đó đi đến ô Ga đến (cột 3), là sibling ngay sau nó
                        "/following-sibling::td[normalize-space(text())='%s']" +
                        // Từ đó, đi tiếp đến nút 'Book ticket' nằm trong các sibling sau đó (cột 7)
                        "/following-sibling::td//a[text()='Book ticket']",
                departStation,
                arriveStation
        );

        By bookTicketLink = By.xpath(dynamicXpath);

        // 2. Thực hiện Wait và Click chuẩn
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(bookTicketLink)).click();

        return new BookTicketPage();
    }
}
*/
