package speedcast.e2e.runners.e2e.ui.linear;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeTest;

@CucumberOptions(plugin = {"pretty", "json:target/cucumberLinearUI.json", "junit:target/cucumber-junit-reportLinearUI.xml","html:target/ReportLinearAlertUI.html", "timeline:target/timeline"},
        features = {"src/test/resources/features/e2e/ui/linear"},
        tags = "@UI and @Linear",
        glue = {"speedcast.e2e.stepdefs"},
        monochrome = true)
public class LinearUIRunner extends AbstractTestNGCucumberTests {

    @BeforeTest
    public void setup() {
    }
}
