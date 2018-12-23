package pages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.PropertiesLoader;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {


    /* locators*/
    private static By usernameField = By.cssSelector("input[name=username]");
    private static By passwordField = By.cssSelector("input[name=password]");
    private static By signInBtn = By.cssSelector("button[type=submit]");

    private static final Log LOG = LogFactory.getLog(LoginPage.class);


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public static void login() {
        String login = PropertiesLoader.loadProperty("credentials.username");
        String pass = PropertiesLoader.loadProperty("credentials.password");
        $(usernameField).setValue(login);
        $(passwordField).setValue(pass);
        $(signInBtn).submit();
        LOG.info(LoginPage.class.getSimpleName() + " - Successfull login");
    }


}
