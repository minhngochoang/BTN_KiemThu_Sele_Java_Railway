package PageObjects;

import org.openqa.selenium.By;

public class LoginPage extends GeneralPage {
    private final By lblWelcomeUser = By.xpath("//div[@class='account']"); // Locator cho chữ Welcome [email]

    private final By txtUsername = By.xpath("//input[@id ='username']");
    private final By txtPassword = By.xpath("//input[@id ='password']");
    private final By btnLogin = By.xpath("//input[contains(@value, 'Login')]");
    private final By lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");

//input[contains@value, 'Login']"
}
