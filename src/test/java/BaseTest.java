import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import drivers.WebdriverInstance;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.Header;
import pages.LeftMenu;
import utils.PropertiesLoader;

import java.net.MalformedURLException;
import java.net.URI;

import static constants.Type.AUDIENCES;
import static constants.Type.ENGAGEMENTS;
import static pages.AudiencePage.audienceNameValue;
import static pages.EngagementPage.engagementName;

public class BaseTest {


    protected WebDriver driver;
    private String url = PropertiesLoader.loadProperty("url.main");
    private static final Log LOG = LogFactory.getLog(BaseTest.class);


    @BeforeMethod
    @Parameters({"browser", "browserVersion"})
    public void setUp(@Optional String browser, @Optional String browserVersion) throws MalformedURLException {
        if (browser != null || browserVersion != null) {
            LOG.info("Method called with args {}" + browser + " and " + browserVersion + " for Selenoid");
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setBrowserName(browser);
            desiredCapabilities.setVersion(browserVersion);
            desiredCapabilities.setCapability("enableVNC", true);
            desiredCapabilities.setCapability("enableVideo", false);
            LOG.info("Desired capabilities {} " + desiredCapabilities + " for driver for Selenoid");
            driver = new RemoteWebDriver(URI.create(PropertiesLoader.loadProperty("url.to.server")).toURL(), desiredCapabilities);
        } else {
            driver = WebdriverInstance.getWebDriverInstance();
        }
        LOG.info(driver.getClass().getSimpleName() + " was created");
        WebDriverRunner.setWebDriver(driver);
        Selenide.open(url);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        try {
            deleteTestDataAfterTestViaUI();
        } finally {
            if (driver != null) {
                driver.close();
                driver.quit();
            }
        }
        LOG.info(driver.getClass().getSimpleName() + " was closed");
    }

    private void deleteTestDataAfterTestViaUI() {
        Header.toAudiences(driver);
        LeftMenu.deleteIfPresent(audienceNameValue, AUDIENCES);
        Header.toEngagements();
        LeftMenu.deleteIfPresent(engagementName, ENGAGEMENTS);
    }

}
