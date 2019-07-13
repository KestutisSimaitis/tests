package cucumberTests;

import org.openqa.selenium.WebDriver;

public class WebDriverHolder {
    private static WebDriver webDriver;

    public static WebDriver getWebDriver() {
        return webDriver;
    }

    public static void setWebDriver(WebDriver webDriver) {
        WebDriverHolder.webDriver = webDriver;
    }
}
