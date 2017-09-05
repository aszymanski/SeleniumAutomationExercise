package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helpers {


    static public boolean checkIfElementIfPresent(String xpath) {
        try {
            Driver webBrowser = Driver.getInstance();
            WebDriver driver = webBrowser.openBrowser();
            driver.findElement(By.xpath(xpath)).isDisplayed();

        } catch (NoSuchElementException ex) {
            return false;
        }
        return true;
    }

    static public void waitUntilWebElementAppear(String xpath, int seconds) {
        Driver webBrowser = Driver.getInstance();
        WebDriver driver = webBrowser.openBrowser();
        WebDriverWait wait = new WebDriverWait(driver,seconds);
        wait.until(ExpectedConditions.visibilityOf(getWebElement(xpath)));
    }

    static public void waitUntilWebElementDisappear(String xpath, int seconds) {
        Driver webBrowser = Driver.getInstance();
        WebDriver driver = webBrowser.openBrowser();
        WebDriverWait wait = new WebDriverWait(driver,seconds);
        wait.until(ExpectedConditions.invisibilityOf(getWebElement(xpath)));
    }

    static public void waitUntilWebElementPresent(String xpath, int seconds) {
        Driver webBrowser = Driver.getInstance();
        WebDriver driver = webBrowser.openBrowser();
        WebDriverWait wait = new WebDriverWait(driver,seconds);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    static public WebElement getWebElement(String xpath){
        Driver webBrowser = Driver.getInstance();
        WebDriver driver = webBrowser.openBrowser();
        return driver.findElement(By.xpath(String.format(xpath)));
    }
    static public WebElement getWebElement(String xpath, String par1){
        Driver webBrowser = Driver.getInstance();
        WebDriver driver = webBrowser.openBrowser();
        return driver.findElement(By.xpath(String.format(xpath,par1)));
    }
    static public WebElement getWebElement(String xpath, String par1, String par2){
        Driver webBrowser = Driver.getInstance();
        WebDriver driver = webBrowser.openBrowser();
        return driver.findElement(By.xpath(String.format(xpath,par1,par2)));
    }

    static public String getXpath(String xpath, String par1, String par2){
        return String.format(xpath,par1,par2);
    }
}
