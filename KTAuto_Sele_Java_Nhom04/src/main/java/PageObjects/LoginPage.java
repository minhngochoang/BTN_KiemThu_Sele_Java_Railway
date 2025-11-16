package PageObjects;

import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends GeneralPage {

    private WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));

    private final By txtUsername = By.xpath("//input[@id = 'username']");
    private final By txtPassword = By.xpath("//input[@id = 'password']");
    private final By btnLogin = By.xpath("//input[contains(@value, 'Login')]");
    private final By lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");

    // Element
    public WebElement getTxtUsername() {
        return Constant.WEBDRIVER.findElement(txtUsername);
    }

    public WebElement getTxtPassword() {
        return Constant.WEBDRIVER.findElement(txtPassword);
    }

    public WebElement getBtnLogin() {
        return Constant.WEBDRIVER.findElement(btnLogin);
    }

    public WebElement getLbLoginErrorMsg() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(lblLoginErrorMsg));
    }


    public String getLoginErrorMsg(){
        return this.getLbLoginErrorMsg().getText();
    }

    public HomePage login(String username, String password) {
        getTxtUsername().clear();
        getTxtUsername().sendKeys(username);

        getTxtPassword().clear();
        getTxtPassword().sendKeys(password);

        //Cuộn lên
        WebElement loginButton = getBtnLogin();
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("arguments[0].click();", loginButton);

        return new HomePage();
    }
}
