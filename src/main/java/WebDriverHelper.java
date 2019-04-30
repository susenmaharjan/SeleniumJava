import org.openqa.selenium.WebDriver;

public class WebDriverHelper {
    public static void loadNewWindow(String url, WebDriver driver){
        if(driver!=null)
            driver.get(url);
    }

    public static void navigateBack(WebDriver driver){
        driver.navigate().back();
    }

    public static  void wait(WebDriver driver){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
