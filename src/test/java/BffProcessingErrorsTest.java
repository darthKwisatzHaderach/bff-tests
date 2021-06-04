import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"pretty", "json:target/BffProcessingErrors.json", "html:target/BffProcessingErrors.html"},
        glue = "stepdefs",
        features = {"src/test/resources/features/BffProcessingErrors.feature"})
public class BffProcessingErrorsTest extends AbstractTestNGCucumberTests {
}
