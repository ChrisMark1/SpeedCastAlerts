package speedcast.e2e.stepdefs;

import org.junit.Assert;
import org.openqa.selenium.Alert;

import io.cucumber.java.en.*;
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

    @And("The user verifies the result message as {string}")
    public void TheUserVerifiesTheResultMessageAs(String expectedMessage) {
        String actualMessage = getDriver().findElement(SpeedcastPageObjects.RESULT).getText();
        Assert.assertEquals(expectedMessage, actualMessage);
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
}