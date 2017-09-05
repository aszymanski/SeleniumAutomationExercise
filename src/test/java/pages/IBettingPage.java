package pages;

import org.openqa.selenium.WebElement;

import static helpers.Helpers.getWebElement;

/**
 * Created by aszymanski on 9/4/2017.
 */
public interface IBettingPage {

    public IBettingPage selectSport(String sportName);

    public IBettingPage selectEvent(String eventHome, String eventAway);

    public String getEventID(String eventHome, String eventAway);

    public WebElement pickWinner(String eventId, String winner);

    public int getChanceA(WebElement betButton);

    public int getChanceB(WebElement betButton);

    public IBettingPage placeBet(String eventHome,String eventAway, double value);

    public String getBetID(WebElement betButton);

    public double getReturnValue(String betId);
}
