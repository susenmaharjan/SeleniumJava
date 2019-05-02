import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    public static  boolean verifyIfImageIsActive(WebElement image){
        try{
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet((image.getAttribute("src")));
            HttpResponse response = client.execute(request);

            client.close();

            if(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                return true;
            }

        }catch(Exception ex){
            return  false;
        }

        return false;
    }
}
