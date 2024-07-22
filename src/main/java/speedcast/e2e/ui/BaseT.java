package speedcast.e2e.ui;

import io.cucumber.java.Scenario;
import speedcast.e2e.ui.utils.browsers.*;
import speedcast.e2e.ui.utils.config.EnvDataConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;

public class BaseT {

    public Logger logger = LogManager.getLogger(BaseT.class.getName());

    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    private static ThreadLocal<WebDriverWait> threadLocalDriverWait = new ThreadLocal<>();
    public EnvDataConfig envDataConfig = new EnvDataConfig();


    public synchronized static void setDriverWait(WebDriverWait driverWait) {
        threadLocalDriverWait.set(driverWait);
    }

    public synchronized static WebDriverWait getDriveWait() {
        return threadLocalDriverWait.get();
    }

    public synchronized static void setDriver(WebDriver driver) {
        threadLocalDriver.set(driver);
    }

    public synchronized static WebDriver getDriver() {
        return threadLocalDriver.get();
    }


    public void openBrowser() {
        String browser = envDataConfig.getBrowser();
        if ("chrome".equals(browser)) {
            Chrome chrome = new Chrome();
            setDriver(chrome.start());
        }
        assert getDriver() != null;

        setDriverWait(new WebDriverWait(getDriver(), Duration.ofMillis(Long.parseLong(envDataConfig.getTimeout()))));
    }

    public void closeBrowser() {
//        getDriver().quit();
//        killDriver();
    }

    private void killDriver() {
        String browser = envDataConfig.getBrowser();
        String os = System.getProperty("os.name");
        if (os.toLowerCase().contains("windows")) {
            try {
                switch (browser) {
                    case "chrome":
                        Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
