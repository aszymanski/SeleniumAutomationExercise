package pages.mobile;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.IBettingPage;

import static helpers.Helpers.*;

public class BettingPageMobile extends AbstractPageMobile implements IBettingPage {

    private String sport = "//p[@class='contextual-nav__title' and text()='%s']";
    private String event = "//div[@class='btmarket__content']//a[@title='%s vs %s']/div";
    private String eventID = "//ancestor::div[@class='event']";
    private String betButton = "//button[@data-event='%s' and @data-name='%s']";
    private String betSlipBet = "//div[contains(span,'%s v %s')]//input[contains(@class,'betslip-selection__stake-input')]";
    private String returnValue = "//span[contains(@id,'%s')]/span";
    private String betSlipToolBar = "//*[@class='toggle-betslip']";

    public BettingPageMobile(WebDriver driver){
        super(driver);
    }

    public BettingPageMobile selectSport(String sportName) {
        handleOverlay();
        getWebElement(sport,sportName).click();
        return new BettingPageMobile(driver);
    }

    public BettingPageMobile selectEvent(String eventHome, String eventAway) {
        handleOverlay();
        waitUntilWebElementPresent(getXpath(event,eventHome,eventAway),20);

        Actions actions = new Actions(driver);
        actions.moveToElement(getWebElement(event,eventHome,eventAway));
        actions.perform();

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,250)", "");
        try {
            Thread.sleep(2000);
        } catch (Exception ex){

        }
        getWebElement(event,eventHome,eventAway).click();
        return new BettingPageMobile(driver);
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

    public BettingPageMobile placeBet(String eventHome, String eventAway, double value)
    {
        getWebElement(betSlipToolBar).click();
        waitUntilWebElementPresent(getXpath(betSlipBet,eventHome,eventAway),20);
        getWebElement(betSlipBet,eventHome,eventAway).click();
        mobileKeyboard(Double.toString(value));
        return new BettingPageMobile(driver);
    }

    public String getBetID(WebElement betButton){
        return betButton.getAttribute("data-entityid");
    }

    public double getReturnValue(String betId) {
        String betReturnValue = getWebElement(returnValue,betId).getText();
        return Double.parseDouble(betReturnValue);
    }

}
