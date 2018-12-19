package drivers;

import exceptions.UnknownOsException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utils.PropertiesLoader;

/**
 * Class sets the chromedriver path and returns an instance of ChromeDriver object.
 *
 * @author Eduard Zaretski
 */
public class ChromeWebDriver {

    private static final String OS = "os.name";
    private static final String DRIVER = "webdriver.chrome.driver";

    /**
     * Static method checks for the system name and sets the system chromedriver properties.
     *
     * @return new proper ChromeDriver instance.
     */
    public static WebDriver getWebDriverInstance() {
        String os = System.getProperty(OS);
        String chromeDriverPath = null;
        if (os.contains(Drivers.WINDOWS.getOsName())) {
            chromeDriverPath = PropertiesLoader.loadProperty(Drivers.WINDOWS.getPath());
        } else if (os.contains(Drivers.MAC.getOsName())) {
            chromeDriverPath = PropertiesLoader.loadProperty(Drivers.MAC.getPath());
        } else if (os.contains(Drivers.Linux.getOsName())) {
            chromeDriverPath = PropertiesLoader.loadProperty(Drivers.Linux.getPath());
        } else {
            throw new UnknownOsException("Unsupported OS");
        }
        System.setProperty(DRIVER, chromeDriverPath);
        return new ChromeDriver();
    }
}
