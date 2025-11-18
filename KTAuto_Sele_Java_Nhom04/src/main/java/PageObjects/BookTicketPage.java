package PageObjects;

import Common.Common.DateUtils;
import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BookTicketPage extends GeneralPage{

    private WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));

    // Locators
    private final By ddlDepartDate = By.xpath("//select[@name='Date']");
    private final By ddlDepartFrom = By.xpath("//select[@name='DepartStation']");
    private final By ddlArriveAt = By.xpath("//select[@name='ArriveStation']");
    private final By ddlSeatType = By.xpath("//select[@name='SeatType']");
    private final By ddlTicketAmount = By.xpath("//select[@name='TicketAmount']");

    private final By btnBookTicket = By.xpath("//input[@type='submit' and @value='Book ticket']");
    private final By lblSuccessHeader = By.xpath("//div[@id='content']/h1");



    //Methods
    // TC14
    public void selectOption(String nameAttribute, String value) {
        String dynamicXpath = String.format("//select[@name='%s']", nameAttribute);
        WebElement element = Constant.WEBDRIVER.findElement(By.xpath(dynamicXpath));
        new Select(element).selectByVisibleText(value);
    }

    public void bookTicket(String date, String depart, String arrive, String seat, String amount) {
        selectOption("Date", date);
        selectOption("DepartStation", depart);
        selectOption("ArriveStation", arrive);
        selectOption("SeatType", seat);
        selectOption("TicketAmount", amount);

        WebElement btnBook = wait.until(ExpectedConditions.elementToBeClickable(btnBookTicket));
        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].click();", btnBook);
    }

    public String getSuccessMessage() {
        return Constant.WEBDRIVER.findElement(lblSuccessHeader).getText();
    }

    public String getTicketDetails(String columnName) {
        int index = 0;

        if (columnName.equals("Depart Date")) {
            index = 1;
        }else if (columnName.equals("Depart Station")) {
            index = 2;
        }else if (columnName.equals("Arrive Station")) {
            index = 3;
        }else if (columnName.equals("Seat Type")) {
            index = 4;
        }else if (columnName.equals("Amount")) {
            index = 5;
        }

        String xpath = String.format("//table[@class='MyTable']//tr[last()]/td[%d]", index);

        return Constant.WEBDRIVER.findElement(By.xpath(xpath)).getText().trim();
    }
}



