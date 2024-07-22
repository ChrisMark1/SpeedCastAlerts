package speedcast.e2e.ui.utils.browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import speedcast.e2e.ui.utils.config.EnvDataConfig;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class Chrome {

    public Logger logger = LogManager.getLogger(Chrome.class.getName());
    EnvDataConfig envDataConfig = new EnvDataConfig();

    public WebDriver start() {
        String remoteURL = envDataConfig.getRemoteURL();
        if (!StringUtils.isEmpty(remoteURL)) {
            try {
                RemoteWebDriver driver = new RemoteWebDriver(new URL(remoteURL), getChromeOptions());
                driver.setFileDetector(new LocalFileDetector());
                return driver;
            } catch (MalformedURLException e) {
                logger.error(e.getStackTrace());
                return null;
            }
        } else {
            return new ChromeDriver(getChromeOptions());
        }
    }

    private ChromeOptions getChromeOptions() {
        String remoteURL = envDataConfig.getRemoteURL();
        if (StringUtils.isEmpty(remoteURL)) {
            WebDriverManager.chromedriver().setup();
        }

        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("safebrowsing.enabled", "true");
        chromePrefs.put("download.directory_upgrade", "true");
        chromePrefs.put("download.prompt_for_download", "false");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("lang=en");
        options.addArguments("start-maximized");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--incognito"); // Correct way to enable incognito mode
        return options;
    }
}
