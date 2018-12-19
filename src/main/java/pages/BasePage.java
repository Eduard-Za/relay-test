package pages;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasePage {


    protected static Logger logger = LoggerFactory.getLogger("Test");

    protected static WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

}
