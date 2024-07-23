package speedcast.e2e.ui.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import speedcast.e2e.ui.BaseT;

public class CommonUIStepDefs extends BaseT {

    @Before("@UI")
    public void beforeTestUI() {
        logger.debug("beforeTestUI");
        openBrowser();
    }
    @After("@UI")
    public void afterTest() {
        closeBrowser();
    }
}
