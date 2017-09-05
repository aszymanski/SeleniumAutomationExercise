package pages.desktop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.IBettingPage;

import static helpers.Helpers.*;
import static helpers.Helpers.waitUntilWebElementPresent;

public class BettingPageDesktop extends AbstractPageDesktop implements IBettingPage {

    private String sport = "//ul[@data-toggle-receive='all-sports']/li/a[@title='%s']";
    private String event = "//section[@data-panel='panel-pre-match']//div[text()='%s v %s']";
    private String eventID = "//ancestor::div[@class='event']";
    private String betButton = "//button[@data-event='%s' and @data-name='%s']";
    private String betSlipBet = "//div[contains(span,'%s v %s')]//input[contains(@class,'betslip-selection__stake-input')]";
    private String returnValue = "//span[contains(@id,'%s')]/span";

    public BettingPageDesktop(WebDriver driver){
        super(driver);
    }

    public BettingPageDesktop selectSport(String sportName) {
        handleOverlay();
        getWebElement(sport,sportName).click();
        return new BettingPageDesktop(driver);
    }

    public BettingPageDesktop selectEvent(String eventHome, String eventAway) {
        handleOverlay();
        waitUntilWebElementPresent(getXpath(event,eventHome,eventAway),20);
        getWebElement(event,eventHome,eventAway).click();
        return new BettingPageDesktop(driver);
    }

    public String getEventID(String eventHome, String eventAway){
        return getWebElement(event+eventID,eventHome,eventAway).getAttribute("data-entityid");
    }

    public WebElement pickWinner(String eventId, String winner){
        handleOverlay();
        WebElement selection = getWebElement(betButton,eventId,winner);
        selection.click();
        return selection;
    }

    public int getChanceA(WebElement betButton) {
        return Integer.parseInt(betButton.getAttribute("data-num"));
    }

    public int getChanceB(WebElement betButton) {
        return Integer.parseInt(betButton.getAttribute("data-denom"));
    }

    public BettingPageDesktop placeBet(String eventHome, String eventAway, double value)
    {
        handleOverlay();
        waitUntilWebElementPresent(getXpath(betSlipBet,eventHome,eventAway),20);
        getWebElement(betSlipBet,eventHome,eventAway).sendKeys(Double.toString(value));
        return new BettingPageDesktop(driver);
    }

    public String getBetID(WebElement betButton){
        return betButton.getAttribute("data-entityid");
    }

    public double getReturnValue(String betId) {
        String betReturnValue = getWebElement(returnValue,betId).getText();
        return Double.parseDouble(betReturnValue);
    }
}
