package pages;

import org.openqa.selenium.WebElement;

import static helpers.Helpers.getWebElement;

//This is interface which is implemented by desktop and mobile page object classes
//Depending on environmental variable "Mobile" framework is creating correct instance of an object (mobile or desktop) - picocontainer
//This solution lets me to keep the same feature file and step definition code for mobile and desktop version of an  app.
//Only page objects have to be updated according to each platform.
//In this example code can be improved because some methods and locators can be shared by mobile and desktop

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
