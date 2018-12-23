package drivers;

import exceptions.UnknownOsException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.LoggerFactory;
import utils.PropertiesLoader;

/**
 * Class sets the driver path and returns an instance of Driver object.
 *
 * @author Eduard Zaretski
 */
public class WebdriverInstance {

    private static final String OS = "os.name";
    private static final Log LOG = LogFactory.getLog(WebdriverInstance.class);

    /**
     * Static method checks for the system name and sets the system driver properties.
     *
     * @return new proper Driver instance.
     */
    public static WebDriver getWebDriverInstance() {
        LOG.info(WebdriverInstance.class.getSimpleName() + " Set driver");
        String os = System.getProperty(OS);
        String driverProperty = null;
        String driverPath = null;
        WebDriver driver = null;
        if (os.contains("Windows") ) {
            driverPath = PropertiesLoader.loadProperty(Drivers.WINDOWS.getPath());
            driverProperty = "webdriver.chrome.driver";
            System.setProperty(driverProperty, driverPath);
            driver = new ChromeDriver();
        }else if(os.contains("Mac")){
            driverPath = PropertiesLoader.loadProperty(Drivers.MAC.getPath());
            driverProperty = "webdriver.chrome.driver";
            System.setProperty(driverProperty, driverPath);
            driver = new ChromeDriver();
        } else if (os.contains("Linux")) {
            driverProperty = "webdriver.gecko.driver";
            driverPath = PropertiesLoader.loadProperty(Drivers.Linux.getPath());
            System.setProperty(driverProperty, driverPath);
            driver = new FirefoxDriver();
        } else {
            throw new UnknownOsException("Unsupported OS");
        }
        LOG.info(driver.getClass().getSimpleName() + " was set");
        return driver;
    }
}
