package pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import constants.Type;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;

public class LeftMenu extends BasePage {


    private static SelenideElement leftMenu = $("#left");
    static String actionsAdd = ".actions";

    private static final Log LOG = LogFactory.getLog(LeftMenu.class);


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


        LOG.info("Delete '" + name + "' with type " + type + " after test");

        By elementToDelete = By.xpath(String.format("//*[contains(text(), '%s')]", name));
        By delete = By.xpath("//button[@title='Delete " + type.getTab() + "']");
        try {
            leftMenu.$(elementToDelete).waitUntil(enabled, 5000)
                    .click();
            $(delete).waitUntil(enabled, 5000)
                    .click();
            ConfirmationWindow.submit
                    .waitUntil(enabled, 5000).click();

            LOG.info(type + " with name '" + name + "' successfully deleted");

        } catch (ElementNotFound elementNotFound) {
            LOG.info(elementNotFound.getClass().getSimpleName() + "    "
                    + type + "' with name '" + name + "' already deleted!");
            elementNotFound.getMessage();
        }
    }
}
