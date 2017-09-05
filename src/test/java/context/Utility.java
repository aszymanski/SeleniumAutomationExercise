package context;

import helpers.Driver;
import org.openqa.selenium.WebDriver;
import pages.IBettingPage;
import pages.desktop.BettingPageDesktop;
import pages.mobile.BettingPageMobile;

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
