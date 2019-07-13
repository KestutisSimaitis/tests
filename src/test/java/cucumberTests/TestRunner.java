package cucumberTests;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = {"src/test/java/cucumberTests"}, glue = "StepDefinitions", plugin = {"pretty",
        "html:target/cucumber-html-report", "json:target/cucumber.json"})
public class TestRunner extends AbstractTestNGCucumberTests {
}
