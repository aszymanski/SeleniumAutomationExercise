package helpers;

import com.aventstack.extentreports.utils.FileUtil;
import cucumber.api.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;

public class CaptureScreenshot {
    static boolean clearDir = true;

    public static String capture(Scenario scenario) throws IOException{
        Driver webBrowser = Driver.getInstance();
        WebDriver driver = webBrowser.openBrowser();

        String dest ="";
        String reportDir =System.getProperty("user.dir")+"/output/";

        //Clear images in report dir (html file will be overwritten so no need to delete)
        if (clearDir){
            File rDir = new File(reportDir);
            for(File file : rDir.listFiles()){
                if(file.getName().endsWith(".png")){
                    file.delete();
                }
            }
            clearDir=false;
        }
        //This condition is intentional this is set for demo to be sure that screenshot will always be made)
        if (!scenario.isFailed()||scenario.isFailed()) {
            try {
                TakesScreenshot screenshot = ((TakesScreenshot) driver);
                File source = screenshot.getScreenshotAs(OutputType.FILE);
                //String screenshotName = scenario.getName().replaceAll("\\s","_");
                String screenshotName = scenario.toString();
                dest = reportDir+screenshotName+".png";
                File destination = new File(dest);
                FileUtils.copyFile(source,destination);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dest;
    }
}