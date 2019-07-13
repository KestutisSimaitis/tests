package cucumberTests;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestStepDefinitions {

    ReadFromFile read = new ReadFromFile();

    private double profitLossValue = 0.00;

    private WebDriver driver = WebDriverHolder.getWebDriver();

    private double getProfitLoss() {
        return Double.valueOf(driver.findElement(
                By.xpath("//strong[text()='Profit/Loss'][@class='win-disposable']/.././/span")
        ).getText().substring(1));
    }

    @Given("^I open the website$")
    public void goToWebSite() {
        driver.navigate().to("https://app.plus500.com/");
    }

    @When("^I login to the demo part of the page$")
    public void loginButton() throws IOException {
        driver.findElement(By.id("demoMode")).click();
        WebElement accountNameField = driver.findElement(By.xpath("//input[@type='email']"));
        WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']"));
        accountNameField.click();
        accountNameField.sendKeys(read.getUserCredentials().get(0));
        passwordField.click();
        passwordField.sendKeys(read.getUserCredentials().get(1));
        driver.findElement(By.className("login-button")).click();
    }

    @Then("^the page is opened in (demo|real) mode selected$")
    public void pageIsOpenedInDemoModeSelected(String expectedPageMode) {
        assertThat(expectedPageMode.equals(driver.findElement(By.id("footerSwitchMode")).getAttribute("class")),
                is(true));
    }

    @Then("^Trade option is selected in navigation menu$")
    public void tradeOptionIsSelectedInNavigationMenu() {
        assertThat(driver.findElement(By.id("tradeNav")).getAttribute("class").contains("active"),is(true));
    }

    @When("^I click (Sell|Buy) button$")
    public void clickSellButton(String buttonName) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(String.format("//button[@class='buySellButton'][text()='%s']", buttonName))
        )).click();
    }

    @Then("^side-bar is opened$")
    public void sideBarIsOpened() {
        assertThat(driver.findElement(By.className("sidebar-content")).isDisplayed(),
                is(true));
    }

    @Then("^instrument graph and user controls menu are displayed$")
    public void instrumentGraphAndUserControlsAreDisplayed() {
        assertThat(driver.findElement(By.className("ciq-chart")).isDisplayed()
                && driver.findElement(By.className("ciq-menu-section")).isDisplayed(), is(true));
    }

    @When("^I click close button for the instrument I am selling$")
    public void clickCloseButtonForTheInstrument() {
        driver.findElement(By.xpath("//button[contains(@class,'close-position')]")).click();
    }

    @And("^I click sell button in the sidebar$")
    public void clickSellButtonInTheSidebar() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[@id='trade-button'][@class='sell']")
        )).click();
        profitLossValue = getProfitLoss();
    }

    @And("^I click Close Position button in the sidebar$")
    public void clickClosePositionButtonInTheSidebar() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[@id='closePositionBtn']")
        )).click();
    }

    @Then("^Profit/Loss value has changed to (\\d)$")
    public void profitLossValueIs(int expectedValue) {
        double convertedExpectedValue = (double) expectedValue;
        double newProfitLossValue = getProfitLoss();
        assertThat(convertedExpectedValue != profitLossValue && newProfitLossValue == convertedExpectedValue, is(true));
    }
}
