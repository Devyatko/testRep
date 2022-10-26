package framework.baseelements;

import framework.driver.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.not;

public class BaseElement {
    protected By byLocator;
    protected String nameElement;

    public BaseElement(By byLocator, String nameElement){
        this.byLocator = byLocator;
        this.nameElement = nameElement;
    }

    private WebElement findElement(){
        return DriverSingleton.getDriver().findElement(byLocator);
    }

    public Integer getSizeFindElements(){
        List<WebElement> listWebEl = DriverSingleton.getDriver().findElements(byLocator);
        return listWebEl.size();
    }

    protected List<WebElement> findElements(){
        return DriverSingleton.getDriver().findElements(byLocator);
    }

    public String getText(){
        WebDriverWait waitTime = new WebDriverWait(DriverSingleton.getDriver(), Duration.ofSeconds(10));
        WebElement wEl = waitTime.until(ExpectedConditions.presenceOfElementLocated(byLocator));
        return wEl.getText();
    }

    public String getAttribute(String attributeName){
        WebDriverWait waitTime = new WebDriverWait(DriverSingleton.getDriver(), Duration.ofSeconds(10));
        WebElement wEl = waitTime.until(ExpectedConditions.presenceOfElementLocated(byLocator));
        return wEl.getAttribute(attributeName);
    }

    public void click() {
        WebDriverWait waitTime = new WebDriverWait(DriverSingleton.getDriver(), Duration.ofSeconds(20));
        WebElement wEl = waitTime.until(ExpectedConditions.elementToBeClickable(byLocator));
        wEl.click();
    }

    public boolean isPresent(){
        boolean flag = false;
        try {
            WebDriverWait waitTime = new WebDriverWait(DriverSingleton.getDriver(), Duration.ofSeconds(20));
            WebElement wEl = waitTime.until(ExpectedConditions.presenceOfElementLocated(byLocator));
            flag = true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isVisible(){
        boolean flag = false;
        try {
            WebDriverWait waitTime = new WebDriverWait(DriverSingleton.getDriver(), Duration.ofSeconds(20));
            Boolean value = waitTime.until(not(ExpectedConditions.visibilityOfElementLocated(byLocator)));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            flag = true;
        }
        return flag;
    }

    public void clickElementUnderNumber(int numberElement){
        WebDriverWait waitTime = new WebDriverWait(DriverSingleton.getDriver(), Duration.ofSeconds(20));
        WebElement wEl = waitTime.until(ExpectedConditions.elementToBeClickable(byLocator));
        List<WebElement> list1 = findElements();
        list1.get(numberElement).click();
    }

    public String getAttributeUnderNumberFromList(int numberElement, String attributeName){
        WebDriverWait waitTime = new WebDriverWait(DriverSingleton.getDriver(), Duration.ofSeconds(10));
        WebElement wEl = waitTime.until(ExpectedConditions.presenceOfElementLocated(byLocator));
        List<WebElement> list1 = findElements();
        return list1.get(numberElement).getAttribute(attributeName);
    }
}
