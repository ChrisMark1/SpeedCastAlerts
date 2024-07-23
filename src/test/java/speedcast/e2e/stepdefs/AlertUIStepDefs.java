package speedcast.e2e.stepdefs;

import org.junit.Assert;
import org.openqa.selenium.Alert;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import speedcast.e2e.ui.pageobjects.SpeedcastPageObjects;
import speedcast.e2e.ui.pages.CommonUI;
import speedcast.e2e.ui.pages.NavigationBase;

public class AlertUIStepDefs extends NavigationBase {

    CommonUI commonUI = new CommonUI();

    @Given("The user is navigating on the JavaScript alerts page")
    public void TheUserIsNavigatingOnTheJavascriptAlertsPage() {
        commonUI.openJavascriptAlertPage();    }

    @When("The user triggers the JavaScript {string} Button")
    public void theUserTriggersTheJavascriptButton(String scenario) {
        switch (scenario) {
            case "Alert":
                click(SpeedcastPageObjects.BUTTON_JS_ALERT);
                break;
            case "Confirm":
                click(SpeedcastPageObjects.BUTTON_JS_CONFIRM);
                break;
            case "Prompt":
                click(SpeedcastPageObjects.BUTTON_JS_PROMPT);

                break;
            default:
                throw new IllegalArgumentException("Invalid scenario: " + scenario);
        }
    }

    @And("The user {string} the pop up alert")
    public void TheUserAcceptsThePopUpAlert(String action) {
        Alert alert = getDriver().switchTo().alert();
        switch (action) {
            case "accepts":
                alert.accept();
                break;
            case "dismisses":
                alert.dismiss();
                break;
            default:
                throw new IllegalArgumentException("Invalid action: " + action);
        }
    }

    @And("The user dismisses the pop up alert")
    public void TheUserADismissesThePopUpAlert() {
        Alert alert = getDriver().switchTo().alert();
        alert.dismiss();
    }

    @And("The user verifies the result message as {string} with color green")
    public void TheUserVerifiesTheResultMessageAs(String expectedMessage) {
        String actualMessage = getDriver().findElement(SpeedcastPageObjects.RESULT).getText();
        String actualColor = getDriver().findElement(SpeedcastPageObjects.RESULT).getCssValue("color");
        Assert.assertEquals(expectedMessage, actualMessage);

        if (!expectedMessage.isEmpty()) {
            Assert.assertEquals("rgba(0, 128, 0, 1)", actualColor);
        }
    }

    @When("The user enters input {string} into the prompt")
    public void TheUserEntersInputIntoThePrompt(String input) {
        Alert promptAlert = getDriver().switchTo().alert();
        promptAlert.sendKeys(input);
    }

    @Then("The user verifies the pop message as {string}")
    public void theUserVerifiesThePopUpMessageAs(String expectedMessage) {
        Alert alert = getDriver().switchTo().alert();
        Assert.assertEquals(expectedMessage, alert.getText());
    }

    @And("The user verifies the header text as {string} with tag {string}")
    public void theUserVerifiesTheHeaderTextAsWithTag(String text, String tag) {
        String actualText = getDriver().findElement(By.tagName(tag)).getText();
        Assert.assertEquals(text, actualText);
    }

    @And("The user verifies the basic body of the page")
    public void theUserVerifiesTheBasicBodyOfThePage() {
        String actualText = getDriver().findElement(SpeedcastPageObjects.P_BASIC_PAGE_BODY).getText();
        Assert.assertEquals("Here are some examples of different JavaScript alerts which can be troublesome for automation.\n" +
                "Click each button and the outcome of the action will be logged to the result section. Create tests for all use cases", actualText);
    }

    @And("the user verifies the existence and characteristics of buttons")
    public void theUserVerifiesTheExistenceOfButtons() {
        // Verify the existence of the buttons
        Assert.assertTrue(getDriver().findElement(SpeedcastPageObjects.BUTTON_JS_ALERT).isDisplayed());
        Assert.assertTrue(getDriver().findElement(SpeedcastPageObjects.BUTTON_JS_CONFIRM).isDisplayed());
        Assert.assertTrue(getDriver().findElement(SpeedcastPageObjects.BUTTON_JS_PROMPT).isDisplayed());

        // Assert the background color of the buttons
        String expectedBackgroundColor = "rgba(67, 111, 146, 1)"; // Equivalent RGBA value of #436f92
        String alertButtonBackgroundColor = getDriver().findElement(SpeedcastPageObjects.BUTTON_JS_ALERT).getCssValue("background-color");
        String confirmButtonBackgroundColor = getDriver().findElement(SpeedcastPageObjects.BUTTON_JS_CONFIRM).getCssValue("background-color");
        String promptButtonBackgroundColor = getDriver().findElement(SpeedcastPageObjects.BUTTON_JS_PROMPT).getCssValue("background-color");

        Assert.assertEquals(expectedBackgroundColor, alertButtonBackgroundColor);
        Assert.assertEquals(expectedBackgroundColor, confirmButtonBackgroundColor);
        Assert.assertEquals(expectedBackgroundColor, promptButtonBackgroundColor);

        // Assert the text color of the buttons
        String expectedTextColor = "rgba(255, 255, 255, 1)"; // Equivalent RGBA value of white (#ffffff)
        String alertButtonTextColor = getDriver().findElement(SpeedcastPageObjects.BUTTON_JS_ALERT).getCssValue("color");
        String confirmButtonTextColor = getDriver().findElement(SpeedcastPageObjects.BUTTON_JS_CONFIRM).getCssValue("color");
        String promptButtonTextColor = getDriver().findElement(SpeedcastPageObjects.BUTTON_JS_PROMPT).getCssValue("color");

        Assert.assertEquals(expectedTextColor, alertButtonTextColor);
        Assert.assertEquals(expectedTextColor, confirmButtonTextColor);
        Assert.assertEquals(expectedTextColor, promptButtonTextColor);
    }
}