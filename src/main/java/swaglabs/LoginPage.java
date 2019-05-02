package swaglabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver _driver;

    WebElement act,_username,_password;


    public LoginPage(WebDriver driver){
        this._driver = driver;

        act = _driver.findElement(By.className("btn_action"));
        _username=_driver.findElement(By.id("user-name"));
        _password=_driver.findElement(By.id("password"));
    }

    public void setUsername(String username){
        this._username.sendKeys(username);
    }

    public  void setPassword(String password){
        this._password.sendKeys(password);
    }

    public  void loginClick(){
        this.act.click();
    }
}
