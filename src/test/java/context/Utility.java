package context;

import helpers.Driver;
import org.openqa.selenium.WebDriver;
import pages.IBettingPage;
import pages.desktop.BettingPageDesktop;
import pages.mobile.BettingPageMobile;
//This class contains values for injection to step definition class by picocontainer plugin
public class Utility {

    private Driver webBrowser = Driver.getInstance();
    private WebDriver driver = webBrowser.openBrowser();

    public double bet;
    public String eventID;
    public String betId;
    public double chanceA;
    public double chanceB;
    public String home;
    public String away;

    //This is a dummy workaround for creating an instance of the right page
    //Picocontainer has no configuration option and this is one of the recommended solutions
    public IBettingPage getBettingPage(){
        IBettingPage page;
        if(System.getenv("Mobile")==null) {
            page = new BettingPageDesktop(driver);
        } else {
            page = new BettingPageMobile(driver);
        }
    return page;
    }
}
