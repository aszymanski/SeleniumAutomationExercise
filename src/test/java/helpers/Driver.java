package helpers;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Driver {
    private static Driver instance = null;
    private WebDriver driver;

    private Driver() {
       setBrowser();
       setWindow();
    }

    public WebDriver openBrowser() {
        return driver;
    }

    public static Driver getInstance () {
        if(instance == null){
            instance = new Driver();
        }
        return instance;
    }

    public void clearInstance() {
        instance = null;
    }


    private void setBrowser() {
        if(System.getenv("HubUrl")!=null) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(CapabilityType.BROWSER_NAME,"chrome");
            String hubUrl = System.getenv("HubUrl");
            try{
                driver = new RemoteWebDriver(new URL(hubUrl), capabilities);
            } catch(MalformedURLException ex){
                System.out.print("Hub url seems to be incorrect.");
                System.out.print(ex);
            }
        } else {
            if(System.getenv("Mobile")!=null){

                Map<String, String> mobileEmulation = new HashMap<String, String>();
                mobileEmulation.put("deviceName",System.getenv("Mobile"));

                Map<String, Object> chromeOptions = new HashMap<String, Object>();
                chromeOptions.put("mobileEmulation", mobileEmulation);
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                ChromeDriverManager.getInstance().setup();
                driver = new ChromeDriver(capabilities);

            } else {
                String browser = System.getenv("BROWSER");
                if(browser == null) { browser = "Chrome"; }

                switch(browser){
                    case "Chrome":
                        ChromeDriverManager.getInstance().setup();
                        driver = new ChromeDriver();
                        break;
                    case "Firefox":
                        FirefoxDriverManager.getInstance().setup();
                        driver = new FirefoxDriver();
                        break;
                    case "IE":
                        InternetExplorerDriverManager.getInstance().setup();
                        driver = new InternetExplorerDriver();
                        break;
                    case "Edge":
                        EdgeDriverManager.getInstance().setup();
                        driver = new EdgeDriver();
                        break;
                    default:
                        ChromeDriverManager.getInstance().setup();
                        driver = new ChromeDriver();
                        break;
                }
            }



        }
    }

    private void setWindow() {
        String window = System.getenv("WINDOW");
        if(window == null) { window = "default"; }
        switch(window) {
            case "Maximize" :
                driver.manage().window().maximize();
                break;
        }
    }
}
