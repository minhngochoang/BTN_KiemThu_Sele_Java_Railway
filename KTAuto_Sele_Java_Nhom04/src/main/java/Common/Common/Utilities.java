package Common.Common;
import org.openqa.selenium.WebDriver;
public class Utilities
{
    // Dừng tạm vài giây (dùng khi cần quan sát)
    public static void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    // Mở URL và phóng to trình duyệt
    public static void openUrl(WebDriver driver, String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }
}

