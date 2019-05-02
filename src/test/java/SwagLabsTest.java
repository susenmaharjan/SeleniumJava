import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import swaglabs.Checkout;
import swaglabs.LoginPage;
import swaglabs.SortProducts;
import swaglabs.UpdateProductsToCart;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SwagLabsTest {
    static WebDriver _driver;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\Susen\\chromedriver_win32\\chromedriver.exe");
        _driver = new ChromeDriver();
        _driver.manage().window().maximize();
        WebDriverHelper.loadNewWindow("https://www.saucedemo.com", _driver);
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
    public void loginTest() {
        LoginPage page = new LoginPage(_driver);
        page.setUsername("standard_user");
        //page.setUsername("problem_user");
        page.setPassword("secret_sauce");
        page.loginClick();
        WebDriverHelper.wait(_driver);
    }

    @Test
    @Order(2)
    public void checkIfImagesAreLoaded() {
        List<WebElement> images = _driver.findElements(By.xpath("//img[contains(@class,'inventory_item_img')]"));

        for (WebElement image : images) {
            if(WebDriverHelper.verifyIfImageIsActive(image)){
                System.out.println("Image is active");
            }else{
                System.out.println("Image is inactive");
            }
        }
    }

    @Test
    @Order(3)
    public void sortPrice() {
        SortProducts page = new SortProducts(_driver);

        page.selectItemByVisibleText("Name (Z to A)");
        WebDriverHelper.wait(_driver);

        page.selectItemByIndexId(3);
        WebDriverHelper.wait(_driver);
    }

    @Test
    @Order(4)
    public void addProductsToCart() {
        UpdateProductsToCart page = new UpdateProductsToCart(_driver);
        page.addProductToCartByIndex(1);
        page.addProductToCartByIndex(2);
        WebDriverHelper.wait(_driver);
    }

    @Test
    @Order(5)
    public void removeProductsFromCart() {
        UpdateProductsToCart page = new UpdateProductsToCart(_driver);
        page.removeProductFromCartByIndex(1);
        WebDriverHelper.wait(_driver);
    }

    @Test
    @Order(6)
    public void redirectToShoppingCart() {
        _driver.findElement(By.className("shopping_cart_link ")).click();
        WebDriverHelper.wait(_driver);
    }

    @Test
    @Order(7)
    public void checkoutCart() {
        Checkout page = new Checkout(_driver);

        page.redirectToStepOne();

        WebDriverHelper.wait(_driver);
    }

    @Test
    @Order(8)
    public void checkoutStepOne() {
        Checkout page = new Checkout(_driver);
        WebDriverHelper.wait(_driver);
        page.setupStepOne();

        page.updateStepOne("Susen", "Maharjan", "977");

        WebDriverHelper.wait(_driver);

        page.submitStepOne();

        WebDriverHelper.wait(_driver);
    }

    @Test
    @Order(9)
    public void checkoutStepTwo() {
        Checkout page = new Checkout(_driver);

        page.redirectToStepTwo();

        //setup
        page.setupStepTwo();

        //get sauce card
        String sauceCard = page.getSauceCard();
        String sauceCardId = sauceCard.substring(sauceCard.lastIndexOf("#") + 1);

        //check item total
        String itemTotal = page.getItemTotal();
        String itemTotalValue = itemTotal.substring(itemTotal.lastIndexOf("$") + 1);

        //check tax
        String tax = page.getTax();
        String taxValue = tax.substring(tax.lastIndexOf("$") + 1);

        //check total
        String total = page.getTotal();
        String totalValue = total.substring(total.lastIndexOf("$") + 1);


        assertEquals("31337", sauceCardId);
        assertEquals("15.99", itemTotalValue);
        assertEquals("1.28", taxValue);
        assertEquals("17.27", totalValue);

        WebDriverHelper.wait(_driver);
    }


    @Test
    @Order(10)
    public void completeCheckout() {
        Checkout page = new Checkout(_driver);
        String result = page.completeCheckout();

        assertEquals("THANK YOU FOR YOUR ORDER", result);

        WebDriverHelper.wait(_driver);
    }
}
