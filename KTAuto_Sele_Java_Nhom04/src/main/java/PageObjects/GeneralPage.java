package PageObjects;

import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GeneralPage {
    private WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));

    // Locators
    private final By tabLogin = By.xpath("//div[@id='menu']//a[contains(@href, '/Account/Login')]");
    private final By tabLogout = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");
    private final By lblWelcomeUser= By.xpath("//div[@class='account']");

    private final By tabBookTicket = By.xpath("//div[@id='menu']//a[contains(@href, 'BookTicket')]");
    private final By lblPageHeader = By.xpath("//div[@id='content']/h1"); //Tiêu đề trang Login

    // Elements
    protected WebElement getTabLogin(){
        // Đợi đến khi tab Login hiển thị
        return wait.until(ExpectedConditions.visibilityOfElementLocated(tabLogin));
    }

    protected WebElement getTabLogout(){
        // Đợi đến khi tab Logout hiển thị
        return wait.until(ExpectedConditions.visibilityOfElementLocated(tabLogout));
    }

    protected WebElement getLbWelcomeUser(){
       // Đợi text hiển thị
        return wait.until(ExpectedConditions.visibilityOfElementLocated(lblWelcomeUser));
    }

    protected WebElement getTabBookTicket() {
        return wait.until(ExpectedConditions.elementToBeClickable(tabBookTicket));
    }

    // Methods
    public String getWelcomeUser(){
        return this.getLbWelcomeUser().getText();
    }

    public LoginPage gotoLoginPage(){
        getTabLogin().click();
        return new LoginPage();
    }

    // Run TC04
    public LoginPage gotoBookTicketPage_ExpectLoginPage() {
        getTabBookTicket().click();
        return new LoginPage();
    }
    public String getPageHeaderText() {
        WebElement headerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(lblPageHeader));
        return headerElement.getText();
    }


}
