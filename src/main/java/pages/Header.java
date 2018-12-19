package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class Header extends BasePage {
    public Header(WebDriver driver) {
        super(driver);
    }

    /* locators*/
    private final static String dataManagment = "#r42-profiles";
    private final static String engagementTo = "#r42-profiles-engagements";
    private final static By audiences = By.xpath("//span[contains(text(),'Audiences')]/..");

    public static EngagementPage toEngagements() {
        $(engagementTo).click();
        return new EngagementPage(driver);
    }

    public static AudiencePage toDataManagment(WebDriver driver) {
        $(dataManagment).waitUntil(Condition.appear, 3000)
                .waitUntil(visible, 5000)
                .waitUntil(Condition.enabled, 3000).click();
        return new AudiencePage(driver);
    }


    public static AudiencePage toAudiences(WebDriver driver) {
        $(audiences).waitUntil(appear, 5000).click();
        $(audiences)
                .waitUntil(visible, 3000)
                .waitUntil(enabled, 3000);
        return new AudiencePage(driver);
    }
}
