package pages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import utils.PropertiesLoader;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static pages.LeftMenu.actionsAdd;

public class EngagementPage extends BasePage {


    private static final Log LOG = LogFactory.getLog(EngagementPage.class);

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
        $(actionsAdd).waitUntil(appear, 5000).waitUntil(visible, 5000).click();
        $(engagementNameField).waitUntil(appear, 5000).waitUntil(visible, 5000).setValue(engagementName);
        $(descriptionField).setValue(engagementDescription);
        $(submitButtom).click();
        $(submitButtom).waitUntil(disappear, 5000);
        LOG.info("Engagement with name '" + engagementName + "' was created");
    }

}
