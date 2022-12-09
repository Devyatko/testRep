package tests;

import framework.baseutils.ConfigManager;
import framework.driver.DriverSingleton;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    @BeforeTest
    public void beforeTest(){
        DriverSingleton.getDriver();
        DriverSingleton.maximize();
    }

    @BeforeMethod
    public void beforeMethod(){
        DriverSingleton.getUrl(ConfigManager.readerStr("url", "configdata.json"));
    }

    @AfterTest
    public void afterTest(){
        DriverSingleton.clearSingleton();
    }
}
