package cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features",plugin = "json:target/jsonReports/cucumber-report.json",glue = {"StepDefinitions"})
public class TestRunner {

}
