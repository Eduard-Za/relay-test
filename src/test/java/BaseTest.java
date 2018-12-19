import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import drivers.ChromeWebDriver;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.PropertiesLoader;

import java.net.MalformedURLException;
import java.net.URI;

public class BaseTest {


    protected Logger logger = LoggerFactory.getLogger("Test");

    protected WebDriver driver;
    private String url = PropertiesLoader.loadProperty("url.main");

    @BeforeMethod
    @Parameters({"browser", "browserVersion"})
    public void setUp(@Optional String browser, @Optional String browserVersion) throws MalformedURLException {
        logger.info("Set driver");
        if (browser != null || browserVersion != null) {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setBrowserName(browser);
            desiredCapabilities.setVersion(browserVersion);
            desiredCapabilities.setCapability("enableVNC", true);
            desiredCapabilities.setCapability("enableVideo", false);
            driver = new RemoteWebDriver(URI.create(PropertiesLoader.loadProperty("url.to.server")).toURL(), desiredCapabilities);
        } else {
            driver = ChromeWebDriver.getWebDriverInstance();
        }
        WebDriverRunner.setWebDriver(driver);
        Selenide.open(url);
        driver.manage().window().maximize();

    }

    @AfterMethod
    public void tearDown() {
        logger.info(("close driver"));
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

}
