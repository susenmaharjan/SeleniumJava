import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class HerokuAppTest {

    static WebDriver _driver;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\Susen\\chromedriver_win32\\chromedriver.exe");
        _driver = new ChromeDriver();
    }

    @AfterAll
    public static void tearDown() {
        if (_driver != null) {
            _driver.close();
            _driver.quit();
        }
    }

    @Test
    @Order(1)
    public void loadHerokuSite(){
        WebDriverHelper.loadNewWindow("http://the-internet.herokuapp.com", _driver);
        WebDriverHelper.wait(_driver);
    }

    @Test
    @Order(2)
    public void loadAbTesting(){
        WebElement element = _driver.findElement(By.linkText("A/B Testing"));
        element.click();
        WebDriverHelper.wait(_driver);
        WebDriverHelper.navigateBack(_driver);
    }

    @Test
    @Order(3)
    public void addOrRemoveElement(){
        WebElement addOrRemoveElement = _driver.findElement(By.linkText("Add/Remove Elements"));
        WebDriverHelper.wait(_driver);
        addOrRemoveElement.click();

        _driver.findElement(By.cssSelector("button")).click();
        WebDriverHelper.wait(_driver);

        _driver.findElement(By.cssSelector("button")).click();
        WebDriverHelper.wait(_driver);

        _driver.findElement(By.cssSelector("button")).click();
        WebDriverHelper.wait(_driver);

        _driver.findElement(By.cssSelector("button")).click();
        WebDriverHelper.wait(_driver);

        List<WebElement> addedButtons = _driver.findElements(By.className("added-manually"));

        assertEquals(addedButtons.size(),4);

        for (WebElement deleteBtn : addedButtons) {
            WebDriverHelper.wait(_driver);
            deleteBtn.click();
        }

        List<WebElement> afterDeleting = _driver.findElements(By.className("added-manually"));
        assertEquals(afterDeleting.size(),0);

        WebDriverHelper.wait(_driver);
        WebDriverHelper.navigateBack(_driver);
    }

    @Test
    @Order(4)
    public void basicAuthentication(){
        String username="admin";
        String password="admin";

        String url = "http://" + username + ":" + password + "@the-internet.herokuapp.com/basic_auth";
        _driver.get(url);

        WebDriverHelper.wait(_driver);
        WebDriverHelper.navigateBack(_driver);
    }

    @Test
    @Order(5)
    public void checkBoxTest(){
        WebDriverHelper.wait(_driver);
        _driver.findElement(By.linkText("Checkboxes")).click();

        List<WebElement> checkBoxes = _driver.findElements(By.xpath("//input[@type='checkbox']"));

        for(WebElement checkBox: checkBoxes) {
            WebDriverHelper.wait(_driver);
            checkBox.click();
        }

        WebDriverHelper.wait(_driver);
        WebDriverHelper.navigateBack(_driver);
    }

    @Test
    @Order(6)
    public void dropdownList(){
        _driver.findElement(By.linkText("Dropdown")).click();

        Select dropdowns = new Select(_driver.findElement(By.id("dropdown")));

        WebDriverHelper.wait(_driver);
        dropdowns.selectByVisibleText("Option 1");

        WebDriverHelper.wait(_driver);
        dropdowns.selectByIndex(2);

        List<WebElement> elements= dropdowns.getAllSelectedOptions();

        for(WebElement element : elements) {
            System.out.println(element.getAttribute("value"));
        }
        WebDriverHelper.wait(_driver);
    }
}
