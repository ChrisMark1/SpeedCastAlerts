package speedcast.e2e.ui.utils.config;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.file.Paths;

@Slf4j
public class ResourcesConfig {

    public String getEnvironmentProperties() {
        String prop;
        if (System.getProperty("environment") == null) {
            prop = EnvDataConfig.getProperties(EnvDataConfig.loadProperties(getApiProperties())).getProperty("ENV");
        } else {
            prop = System.getProperty("environment");
        }
        if (System.getProperty(prop + ".properties") == null) {
            return getResourcesPath() + "/" + prop + ".properties";
        } else {
            return getAbsolutePath() + System.getProperty(prop + ".properties");
        }
    }

    public String getApiProperties() {
        if (System.getProperty("env.properties") == null)
            return getResourcesPath() + "/env.properties";
        else
            return getAbsolutePath() + System.getProperty("env.properties");
    }

    private String getResourcesPath() {
        return getResourcesPath("main");
    }

    private String getResourcesPath(String packageName) {
        String filePathString = getAbsolutePath() + "src/" + packageName + "/resources";
        File f = new File(filePathString);
        if (!f.exists())
            filePathString = getAbsolutePath();
        return filePathString;
    }

    public String getAbsolutePath() {
        String absPath = Paths.get(".").toAbsolutePath().normalize().toString().replace("\\", "/");

        String modulePath = this.getClass().getClassLoader().getResource(".").getPath();
        modulePath = modulePath.replace("\\", "/");
        modulePath = modulePath.replace("/target/test-classes", "");
        modulePath = modulePath.replace("/target/classes", "");
        modulePath = modulePath.replace(absPath, "");
        modulePath = modulePath.replace("//", "/");

        return absPath + modulePath;
    }

}
