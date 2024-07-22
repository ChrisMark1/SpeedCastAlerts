package speedcast.e2e.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import speedcast.e2e.ui.BaseT;

public class NavigationBase extends BaseT {

    public NavigationBase click(By by) {
        waitForElementToBeClickable(by);
        getDriver().findElement(by).click();
        logger.debug("Pressed {}", by);
        return this;
    }

    public NavigationBase waitUntilPageLoads() {
        ExpectedCondition<Boolean> expectation =
                driver -> {
                    assert driver != null;
                    return ((JavascriptExecutor) driver)
                            .executeScript("return document.readyState").toString().equals("complete");
                };
        try {
            Thread.sleep(500);
            getDriveWait().until(expectation);
            Thread.sleep(500);

        } catch (Exception ex) {
            Thread.currentThread().interrupt();
        }
        return this;
    }

    public void waitForElementToBeClickable(By by) {
        if (by != null) {
            getDriveWait().until(ExpectedConditions.elementToBeClickable(by));
            logger.debug("Element IS clickable.");
        } else {
            logger.debug("ERROR Element is NOT clickable.");
        }
    }
}
