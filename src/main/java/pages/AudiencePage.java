package pages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import utils.PropertiesLoader;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static pages.LeftMenu.actionsAdd;
import static ui_methods.JsDragAndDrop.dragAndDrop;

public class AudiencePage extends BasePage {

    private static final Log LOG = LogFactory.getLog(AudiencePage.class);

    /*data from property file*/
    public static final String audienceNameValue = PropertiesLoader.loadProperty("audience.name");
    public static final String engagementName = PropertiesLoader.loadProperty("engagement.name");
    public static final String audienceDescriptionValue = PropertiesLoader.loadProperty("audience.description");


    /* locators*/
    private String audienceName = "#segment-name";
    private String audienceDescription = "#segment-description";
    private String next = "a[class='btn btn-primary ng-binding']";

    private String dragFrom = String.format("a[data-name='%s']", engagementName);
    private String dragTo = "#dropTargetAnd";

    private String apiIdentifier = ".col-md-3 > p:last-child";


    public AudiencePage(WebDriver driver) {
        super(driver);
    }

    public void createAudience() {
        $(actionsAdd).waitUntil(visible, 5000).click();
        $(audienceName).waitUntil(visible, 3000).setValue(audienceNameValue);
        $(audienceDescription).waitUntil(enabled, 5000).setValue(audienceDescriptionValue);
        $(dragFrom).waitUntil(visible, 5000);
        dragAndDrop(driver, $(dragFrom), $(dragTo));
        $(next).waitUntil(visible, 1000).click();
        $(next).waitUntil(visible, 1000).click();
        $(next).waitUntil(appear, 4000)
                .waitUntil(visible, 4000)
                .waitUntil(enabled, 5000).click();
        LOG.info("Audience with name '" + audienceNameValue + "' and description '" + audienceDescriptionValue + "' was created");
    }

    public String getApiName() {
        return $(apiIdentifier)
                .waitUntil(appear, 5000)
                .waitUntil(exist, 5000)
                .waitUntil(visible, 5000).getText();
    }
}
