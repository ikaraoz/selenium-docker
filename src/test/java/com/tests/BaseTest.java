package com.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    //    @BeforeTest
//    public void setup() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    //  }
    @BeforeTest
    public void setupDriver(ITestContext ctx) throws MalformedURLException {
        // BROWSER => chrome / firefox  //Suppose someone will give a variable for browser
        // HUB_HOST => localhost / 10.0.1.3 / hostname  //And again assume that someone will pass the hub variable, what if no pass those information?
        //So, we will create a default
        String host = "localhost";
        DesiredCapabilities dc;

        if (System.getProperty("BROWSER") != null &&
                System.getProperty("BROWSER").equalsIgnoreCase("firefox")) {
            dc = DesiredCapabilities.firefox();
        } else {
            dc = DesiredCapabilities.chrome();
        }

        if (System.getProperty("HUB_HOST") != null) {
            host = System.getProperty("HUB_HOST");
        }

        String testName = ctx.getCurrentXmlTest().getName();

        String completeUrl = "http://" + host + ":4444/wd/hub";
        dc.setCapability("name", testName);
        this.driver = new RemoteWebDriver(new URL(completeUrl), dc); //first of the parameters looks for where to find the remote driver
    }

    @AfterTest
    public void quitDriver() {
        this.driver.quit();
    }


}
