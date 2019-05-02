package swaglabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SortProducts {
    WebDriver _webDriver;

    Select _dropDownList;

    public SortProducts(WebDriver _webDriver){
        this._webDriver=_webDriver;

        this._dropDownList= new Select(this._webDriver.findElement(By.className("product_sort_container")));
    }

    public void selectItemByVisibleText(String text){
        _dropDownList.selectByVisibleText(text);
    }

    public void selectItemByIndexId(int index){
        _dropDownList.selectByIndex(index);
    }
}
