package pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import constants.Type;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;

public class LeftMenu extends BasePage {


    private static SelenideElement leftMenu = $("#left");
    static String actionsAdd = ".actions";

    public LeftMenu(WebDriver driver) {
        super(driver);
    }

    /**
     * Delete audience or engagement if present via UI.
     *
     * @param name - name of engagement or audience
     * @param type - type of entity for deleting
     */
    public static void deleteIfPresent(String name, Type type) {

        logger.info("Delete if present");
        By elementToDelete = By.xpath(String.format("//*[contains(text(), '%s')]", name));
        By delete = By.xpath("//button[@title='Delete " + type.getTab() + "']");
        try {
            leftMenu.$(elementToDelete).waitUntil(enabled, 5000)
                    .click();
            $(delete).waitUntil(enabled, 5000)
                    .click();
            ConfirmationWindow.submit
                    .waitUntil(enabled, 5000).click();
        } catch (ElementNotFound elementNotFound) {
            logger.info("Already deleted");
            elementNotFound.getMessage();
        }
    }
}
