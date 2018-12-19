package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.PropertiesLoader;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.dismiss;

public class LoginPage extends BasePage {


    /* locators*/
    private static By usernameField = By.cssSelector("input[name=username]");
    private static By passwordField = By.cssSelector("input[name=password]");
    private static By signInBtn = By.cssSelector("button[type=submit]");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public static void login() {
        logger.info("Login");
        String login = PropertiesLoader.loadProperty("credentials.username");
        String pass = PropertiesLoader.loadProperty("credentials.password");
        $(usernameField).setValue(login);
        $(passwordField).setValue(pass);
        $(signInBtn).submit();
    }


}
