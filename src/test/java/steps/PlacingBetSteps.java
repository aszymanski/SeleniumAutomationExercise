package steps;

import context.Utility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.Driver;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.IBettingPage;

import java.math.RoundingMode;
import java.text.DecimalFormat;


public class PlacingBetSteps {

    private Utility utility;
    public PlacingBetSteps(Utility context) {
        this.utility = context;
    }

    private Driver webBrowser = Driver.getInstance();
    private WebDriver driver = webBrowser.openBrowser();

    @When("^I select \"([^\"]*)\" from the Sports menu$")
    public void iSelectFromTheSportsMenu(String sportName) {
        IBettingPage page = utility.getBettingPage();
        page.selectSport(sportName);
    }

    @And("^I select event \"([^\"]*)\" v \"([^\"]*)\"$")
    public void iSelectEventV(String eventHome, String eventAway){
        utility.home = eventHome;
        utility.away = eventAway;
        IBettingPage page = utility.getBettingPage();
        page.selectEvent(eventHome,eventAway);
        utility.eventID = page.getEventID(eventHome,eventAway);
    }

    @When("^I place \"([^\"]*)\" on \"([^\"]*)\"$")
    public void iPlaceOn(double bet, String winner)  {
        IBettingPage page = utility.getBettingPage();
        WebElement selection = page.pickWinner(utility.eventID,winner);
        utility.betId = page.getBetID(selection).substring(5);
        utility.chanceA = page.getChanceA(selection);
        utility.chanceB = page.getChanceB(selection);
        utility.bet = bet;
        page.placeBet(utility.home, utility.away,bet);
        System.out.println(utility.betId.substring(5));
    }

    @Then("^Correct return value is calculated$")
    public void correctReturnValueIsCalculated() {
        IBettingPage page = utility.getBettingPage();
        Double returnVal = page.getReturnValue(utility.betId);
        Double calcReturnVal = utility.bet + (utility.bet * (utility.chanceA/ utility.chanceB));

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.DOWN);

        calcReturnVal = Double.parseDouble(df.format(calcReturnVal));

        System.out.println(calcReturnVal);
        Assert.assertEquals("Value from a page should equals calculated value based on read odds",returnVal,calcReturnVal);

    }
}
