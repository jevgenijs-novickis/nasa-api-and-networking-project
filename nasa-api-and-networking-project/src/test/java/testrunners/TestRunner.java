package testrunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features", 
    glue = "stepdefinitions",                     
    plugin = { "pretty", "html:target/cucumber-reports.html" },
    monochrome = true,
    tags = "@NasaTest or @NetworkTests"
)
public class TestRunner {
}
