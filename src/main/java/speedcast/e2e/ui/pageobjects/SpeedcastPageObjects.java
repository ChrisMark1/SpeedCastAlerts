package speedcast.e2e.ui.pageobjects;

import org.openqa.selenium.By;

public class SpeedcastPageObjects {

    public static final By BUTTON_JS_ALERT = By.xpath("//button[text()='Click for JS Alert']");
    public static final By BUTTON_JS_CONFIRM = By.xpath("//button[text()='Click for JS Confirm']");
    public static final By BUTTON_JS_PROMPT = By.xpath("//button[text()='Click for JS Prompt']");
    public static final By P_BASIC_PAGE_BODY = By.xpath("//*[@id=\"content\"]/div/p[1]");
    public static final By RESULT = By.id("result");
}
