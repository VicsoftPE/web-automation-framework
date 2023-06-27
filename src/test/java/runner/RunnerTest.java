package runner;

import io.cucumber.junit.Cucumber;

import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import pages.BasePage;

@RunWith(Cucumber.class)

@CucumberOptions(
    features = "src/test/resources/features",
        glue = "steps",
        monochrome = true,
        plugin = {"pretty", "json:target/cucumber-reports/cucumber.json", "json:target/cucumber-reports/cucumberreport.html", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        tags = "@regression"


)

public class RunnerTest {
    @AfterClass
    public static void cleanDriver(){
        BasePage.closeBrowser();
    }

}