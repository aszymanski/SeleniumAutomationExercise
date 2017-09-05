package pages.mobile;

import org.openqa.selenium.WebDriver;

import static helpers.Helpers.getWebElement;
import static helpers.Helpers.waitUntilWebElementPresent;

//This is not a real abstract class
//In this example I want to have a possibility to create an instance of that class that's why I've made it to be Parent class
//methods that would be stored here are moved to Helpers an they are static (waiting, checking visibility, etc.)
// Such class is used in steps that are user across all pages we are automating

public class AbstractPageMobile {

    WebDriver driver;
    private String overlay = "//div[@class='wh-overlay wh-hide' and @id='wh-global-overlay']";
    private String mobileKeyBoardButton ="//*[@data-action='append' and @data-value='%s']";

    public AbstractPageMobile(WebDriver driver){
        this.driver = driver;
    }

    public void handleOverlay() {
        waitUntilWebElementPresent(overlay,30);
    }

    public void mobileKeyboard(String value){
        for(int i=0;i<value.length();i++){
            getWebElement(mobileKeyBoardButton,Character.toString(value.charAt(i))).click();
        }
    }
}
