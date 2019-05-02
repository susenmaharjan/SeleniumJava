package swaglabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UpdateProductsToCart {
    WebDriver _webDriver;
    List<WebElement> ProductsBtn;

    public UpdateProductsToCart(WebDriver driver){
        this._webDriver = driver;
        this.ProductsBtn = this._webDriver.findElements(By.className("btn_inventory"));
    }

    public void addProductToCartByIndex(int id){
        this.ProductsBtn.get(id).click();
    }

    public void removeProductFromCartByIndex(int id){
        this.ProductsBtn.get(id).click();
    }
}
