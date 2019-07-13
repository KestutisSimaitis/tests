package cucumberTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public abstract class Page {
    protected final WebDriver webDriver;

    public Page(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private WebDriver driver;

    @BeforeClass
    void setUp() {
        System.setProperty("webdriver.gecko.driver", "sources\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        WebDriverHolder.setWebDriver(driver);
    }

    @AfterClass
    void tearDown() {
        driver.quit();
    }
}
