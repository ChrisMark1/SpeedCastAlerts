package speedcast.e2e.ui.pages;

public class CommonUI extends NavigationBase {

    public void openJavascriptAlertPage() {
        logger.debug("Navigating to {}", envDataConfig.getURL());
        getDriver().get(envDataConfig.getURL());
        waitUntilPageLoads();
        logger.debug("page opened");
    }
}
