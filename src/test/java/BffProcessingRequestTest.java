import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"pretty", "json:target/BffProcessingRequest.json", "html:target/BffProcessingRequest.html"},
        glue = "stepdefs",
        features = {"src/test/resources/features/BffProcessingRequests.feature"})
public class BffProcessingRequestTest extends AbstractTestNGCucumberTests {
}
