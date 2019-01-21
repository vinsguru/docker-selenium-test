package com.testautomationguru.container.test;

import com.google.common.util.concurrent.Uninterruptibles;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;

public class BaseTest {

    protected WebDriver driver;

    @BeforeSuite
    public void initialDelay(){
        //intentionally added this as chrome/firefox containers take few ms to register
        //to hub - test fails saying hub does not have node!!
        //very rare
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
    }
    
    @BeforeTest
    public void setUp() throws MalformedURLException {
        
        DesiredCapabilities dc = DesiredCapabilities.chrome();

        if (System.getProperty("browser").equals("firefox"))
            dc = DesiredCapabilities.firefox();

        String host = System.getProperty("seleniumHubHost");
        
        driver = new RemoteWebDriver(new URL("http://" + host + ":4444/wd/hub"), dc);
        
    }

    @AfterTest
    public void tearDown() throws InterruptedException {
        driver.quit();
    }  
}
