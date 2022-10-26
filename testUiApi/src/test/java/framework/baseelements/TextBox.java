package framework.baseelements;

import framework.driver.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TextBox extends BaseElement{
    public TextBox(By byLocator, String nameElement){
        super(byLocator, nameElement);
    }

    public void clearAndInputText(String text){
        TextBox textBox = new TextBox(byLocator, nameElement);
        WebDriverWait waitTime = new WebDriverWait(DriverSingleton.getDriver(), Duration.ofSeconds(10));
        if(textBox.isPresent()){
            WebElement inputField = waitTime.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
            inputField.clear();
            inputField.sendKeys(text);
        }
    }
}
