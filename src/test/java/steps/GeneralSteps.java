package steps;

import context.Utility;
import cucumber.api.java.en.Given;
import helpers.Driver;
import org.openqa.selenium.WebDriver;

public class GeneralSteps {
    private Utility context;

    public GeneralSteps(Utility context) {
        this.context = context;
    }

    private Driver webBrowser = Driver.getInstance();
    private WebDriver driver = webBrowser.openBrowser();

    @Given("^I'm on \"([^\"]*)\" page$")
    public void iOpenPage(String url) {
        driver.get(url);
    }
}
