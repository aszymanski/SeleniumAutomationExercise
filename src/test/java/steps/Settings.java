package steps;


import com.cucumber.listener.Reporter;
import context.Utility;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import helpers.Driver;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static helpers.CaptureScreenshot.capture;

public class Settings {

    private Utility context;

    public Settings(Utility context) {
        this.context = context;
    }
    private Driver webBrowser = Driver.getInstance();
    private WebDriver driver = webBrowser.openBrowser();
    public Scenario scenario;

    //This is initial setting for test execution
    //In this example I've used this section to set Capturing screenshots for reports and clearing instance of a driver after test

    @Before
    public void before(Scenario scenario)
    {
        this.scenario = scenario;
        //context.addComponent(IBettingPage.class, BettingPageDesktop.class);

    }

    @After
    public void tearDown() throws IOException{

        Reporter.addScreenCaptureFromPath(capture(scenario));
        driver.close();
        webBrowser.clearInstance();
            }
}
