package framework.driver;

import framework.baseutils.ConfigManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class DriverSingleton {
    private static WebDriver driver = null;

    private DriverSingleton() {
        driver = BrowserFactory.createBrowser(ConfigManager.readerStr("browserName", "configdata.json"));
    }

    public static WebDriver getDriver(){
        if (driver == null){
            new DriverSingleton();
        }
        return driver;
    }

    public static void clearSingleton(){
        driver.quit();
        driver = null;
    }

    public static void getUrl(String url) {
        driver.get(url);
    }

    public static void close(){
        driver.close();
    }

    public static void quit(){
        driver.quit();
    }

    public static void switchTo(){
        driver.switchTo();
    }

    public static void minimize(){
        driver.manage().window().minimize();
    }

    public static void maximize(){
        driver.manage().window().maximize();
    }

    public static void addCookie(String key, String value){
        driver.manage().addCookie(new Cookie(key, value));
    }

    public static void executeScript(String script){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script);
    }

    public static void refresh(){
        driver.navigate().refresh();
    }

    public static void back(){
        driver.navigate().back();
    }

    public static void forward(){
        driver.navigate().forward();
    }

    public static String getPageSource(){
        return driver.getPageSource();
    }

    public static String getWindowHandle(){
        return driver.getWindowHandle();
    }

    public static Set<String> getWindowHandles(){
        return driver.getWindowHandles();
    }

    public static void switchToWindowHandle(String originalWindow){
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }
}
