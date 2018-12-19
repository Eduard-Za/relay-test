package drivers;

/**
 * Enum - stores names of OS and property names with paths to Drivers
 *
 * @author Eduard Zaretski
 */

public enum Drivers {

    WINDOWS("Windows", "chromedriver.path.windows"),
    MAC("Mac", "chromedriver.path.mac"),
    Linux("Linux", "chromedriver.path.linux");

    private final String path;
    private final String osName;

    Drivers(String osName, String path) {
        this.path = path;
        this.osName = osName;
    }

    public String getOsName() {
        return osName;
    }

    public String getPath() {
        return path;
    }
}