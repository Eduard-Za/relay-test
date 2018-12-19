package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

/**
 * Class that describes Confirmation Window.
 *
 * @author Eduard Zaretski
 */
public class ConfirmationWindow extends BasePage {
    public ConfirmationWindow(WebDriver driver) {
        super(driver);
    }

    private static SelenideElement window = $(".modal-content");
    public static SelenideElement submit = window.$(By.xpath("//button[@class='btn btn-primary']"));
}
