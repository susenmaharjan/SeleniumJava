package swaglabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Checkout {
    private WebDriver _webDriver;

    private WebElement checkOutStepOneBtn, checkOutStepTwoBtn;
    private WebElement _firstName, _lastName, _postalCode;
    private WebElement _sauceCard, _itemTotal, _tax, _total;

    public Checkout(WebDriver webDriver){
        this._webDriver = webDriver;
    }

    public void redirectToStepOne(){
        this._webDriver.findElement(By.className("checkout_button")).click();
    }

    public void setupStepOne(){
        this._firstName = this._webDriver.findElement(By.id("first-name"));
        this._lastName = this._webDriver.findElement(By.id("last-name"));
        this._postalCode = this._webDriver.findElement(By.id("postal-code"));

        this.checkOutStepOneBtn = this._webDriver.findElement(By.className("cart_button"));
    }

    public void updateStepOne(String firstName, String lastName, String postalCode){
        this._firstName.sendKeys(firstName);
        this._lastName.sendKeys(lastName);
        this._postalCode.sendKeys(postalCode);
    }

    public void submitStepOne(){
        this._postalCode.click();
    }

    public void redirectToStepTwo(){
        this._webDriver.findElement(By.className("cart_button")).click();
    }

    public void setupStepTwo(){
        this._sauceCard = this._webDriver.findElement(By.className("summary_value_label"));
        this._itemTotal = this._webDriver.findElement(By.className("summary_subtotal_label"));
        this._tax = this._webDriver.findElement(By.className("summary_tax_label"));
        this._total = this._webDriver.findElement(By.className("summary_total_label"));
    }

    public String getSauceCard(){
        return this._sauceCard.getAttribute("textContent");
    }

    public String getItemTotal(){
        return  this._itemTotal.getAttribute("textContent");
    }

    public String getTax(){
        return this._tax.getAttribute("textContent");
    }

    public String getTotal(){
        return  this._total.getAttribute("textContent");
    }

    public String completeCheckout(){
        this._webDriver.findElement(By.className("cart_button")).click();

        return this._webDriver.findElement(By.className("complete-header")).getAttribute("textContent");
    }
}
