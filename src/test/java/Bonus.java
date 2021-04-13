import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.gson.Gson;
import org.asynchttpclient.config.AsyncHttpClientConfigHelper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.JsonToWebElementConverter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.concurrent.TimeUnit;

public class Bonus {
    private static WebDriver driver;
    private static ExtentReports extent;
    private static ExtentTest test;
    private String jsonPath ="C:\\Users\\Alex\\Desktop\\java\\Class10\\HomeWork11\\src\\test\\java\\data.json";


    @BeforeClass
    public void runBefore(){
        String cwd = System.getProperty("user.dir");
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(cwd + "\\extent.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        test = extent.createTest("Buy-Me ", "Second Project");
        extent.setSystemInfo("Environment", "Production");
        extent.setSystemInfo("Tester", "Alex");
        test.log(Status.INFO, "@Before class");
        driver = Singleton.getDriverInstance();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }


    @Test
    public void test_1_bonus(){
        driver.navigate().to("https://www.google.com");
        String googlePage = driver.getWindowHandle();
        ((JavascriptExecutor)driver).executeScript("window.open('https://www.youtube.com','_blank');");
        String youTubePage = driver.getWindowHandle();
        ((JavascriptExecutor)driver).executeScript("window.open('https://translate.google.com','_blank');");
        driver.switchTo().window(googlePage);
        driver.switchTo().window(youTubePage);
        TakeScreenShot.setTest(test);
        TakeScreenShot.setDriver(driver);
        TakeScreenShot.takeTheImage();

    }

}
