package speedcast.e2e.ui.utils.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Enumeration;
import java.util.Properties;

import static java.nio.charset.StandardCharsets.UTF_8;

public class EnvDataConfig {

    ResourcesConfig resourcesConfig = new ResourcesConfig();

    public String getURL() { return getEnvProperties().getProperty("URL"); }

    public String getBrowser() {
        return getEnvProperties().getProperty("BROWSER");
    }

    public String getRemoteURL() {
        return getEnvProperties().getProperty("DRIVER.REMOTE.URL");
    }

    public String getTimeout() { return getEnvProperties().getProperty("TIMEOUT"); }
    private static final Logger logger = LogManager.getLogger(EnvDataConfig.class);


    private Properties getEnvProperties() {
        return getProperties(loadProperties(resourcesConfig.getEnvironmentProperties()));
    }

    public static Properties loadProperties(String testDataFile) {
        Properties prop = new Properties();
        try (InputStream inputStream = new FileInputStream(testDataFile); Reader reader = new InputStreamReader(inputStream, UTF_8)) {
            prop.load(reader);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }
        return prop;
    }

    public static Properties getProperties(Properties params) {
        Properties result = new Properties();
        Enumeration<?> names = params.propertyNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            result.put(name, params.get(name));
        }
        return result;
    }
}
