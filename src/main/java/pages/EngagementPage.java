package pages;

import org.openqa.selenium.WebDriver;
import utils.PropertiesLoader;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;
import static pages.LeftMenu.actionsAdd;

public class EngagementPage extends BasePage {

    /*data from property file*/
    public static final String engagementName = PropertiesLoader.loadProperty("engagement.name");
    public static final String engagementDescription = PropertiesLoader.loadProperty("engagement.value");

    /* locators*/
    private final static String engagementNameField = "#data-interaction-mainFieldValue";
    private final static String descriptionField = "#data-interaction-description";
    private final static String submitButtom = "#r42-profiles-interactions-interactionform-edit-submit";

    public EngagementPage(WebDriver driver) {
        super(driver);
    }

    public static void createEngagement() {
        $(actionsAdd).click();
        $(engagementNameField).setValue(engagementName);
        $(descriptionField).setValue(engagementDescription);
        $(submitButtom).click();
        $(submitButtom).waitUntil(disappear, 5000);
    }

}
