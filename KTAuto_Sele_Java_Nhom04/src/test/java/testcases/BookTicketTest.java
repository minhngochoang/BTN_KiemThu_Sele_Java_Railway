package testcases;

import Common.Constant.Constant;
import PageObjects.BookTicketPage;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class BookTicketTest {

    @BeforeMethod
    public void beforeMethod() {
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @Test
    public void TC14() {

        System.out.println("TC14 - User can book 1 ticket at a time");

        // Step 1. Navigate to QA Railway Website
        HomePage homePage = new HomePage();
        homePage.open();

        // Step 2. Login with a valid account
        LoginPage loginPage = homePage.gotoLoginPage();
        homePage = loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        // Step 3. Click on "Book ticket" tab
        BookTicketPage bookTicketPage = homePage.gotoBookTicketPage();

        // Step 4. Select a "Depart date" from the list
        String departDate = Common.Common.DateUtils.getFutureDateForRailway(5);
        System.out.println("Ngày test: " + departDate);

        // Step 5. Select "Sài Gòn" for "Depart from" and "Nha Trang" for "Arrive at".
        String departFrom = "Sài Gòn";
        String arriveAt = "Nha Trang";

        // Step 6. Select "Soft bed with air conditioner" for "Seat type"
        String seatType = "Soft bed with air conditioner";

        // Step 7. Select "1" for "Ticket amount"
        String ticketAmount = "1";

        // Step 8. Click on "Book ticket" button
        bookTicketPage.bookTicket(departDate, departFrom, arriveAt, seatType, ticketAmount);

        // Verify message
        String actualMsg = bookTicketPage.getSuccessMessage();
        Assert.assertEquals(actualMsg, "Ticket booked successfully!", "Success message is incorrect.");

        // Verify
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(bookTicketPage.getTicketDetails("Depart Date: "), departDate, "Depart Date mismatch!");
        softAssert.assertEquals(bookTicketPage.getTicketDetails("Depart Station: "), departFrom, "Depart Station mismatch!");
        softAssert.assertEquals(bookTicketPage.getTicketDetails("Arrive Station: "), arriveAt, "Arrive Station mismatch!");
        softAssert.assertEquals(bookTicketPage.getTicketDetails("Seat Type: "), seatType, "Seat Type mismatch!");
        softAssert.assertEquals(bookTicketPage.getTicketDetails("Amount: "), ticketAmount, "Ticket Amount mismatch!");
        softAssert.assertAll();
    }

    @AfterMethod
    public void afterMethod() {
        Constant.WEBDRIVER.quit();
    }
}
