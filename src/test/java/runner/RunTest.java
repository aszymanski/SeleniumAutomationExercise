package runner;

import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions(   format = { "pretty", "html:target/cucumber"},
                    plugin = {"com.cucumber.listener.ExtentCucumberFormatter:output/report.html"},
                    glue = "steps",
                    features = "src/test/resources/features")
public class RunTest {
    @AfterClass
    public static void teardown() {
        //Besic setup for extentreport
        //Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
        Reporter.setSystemInfo("User", System.getProperty("user.name"));
        Reporter.setSystemInfo("Operating System", System.getProperty("os.name"));
        Reporter.setTestRunnerOutput("This is only a sample message");
    }
}
