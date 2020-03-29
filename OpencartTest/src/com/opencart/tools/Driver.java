package com.opencart.tools;

import com.opencart.data.ConstantVariables;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {

    private static WebDriver driver = null;

    private Driver() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            if (ConstantVariables.BROWSER_NAME.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                options.addArguments("headless");
                options.addArguments("start-maximized");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);
            }
        }
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get(ConstantVariables.URL);
        return driver;
    }

    public static void quit() {
        driver.quit();
        driver = null;
    }

    public static WebDriver getAdminDriver() {
        driver = getDriver();
        driver.get(ConstantVariables.ADMIN_URL);
        return driver;
    }

    public static void clearCookies() {
        driver.manage().deleteAllCookies();
    }
}