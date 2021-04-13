import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.example.JSUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;

public class Run {
    private static WebDriver driver;
    private static ExtentReports extent;
    private static ExtentTest test;

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
    public void test_1() {
        driver.navigate().to("https://dgotlieb.github.io/Actions");
        TakeScreenShot.setTest(test);
        TakeScreenShot.setDriver(driver);
        TakeScreenShot.takeTheImage();

    }

    @Test
    public void test_2_dragAndDrop() {
        driver.navigate().to("https://dgotlieb.github.io/Actions/");
        WebElement firstElement = driver.findElement(By.id("drag1"));
        WebElement secondElement = driver.findElement(By.id("div1"));
        JSUtils.JavascriptDragAndDrop(driver, firstElement, secondElement);
        TakeScreenShot.setTest(test);
        TakeScreenShot.setDriver(driver);
        TakeScreenShot.takeTheImage();

    }
    @Test
    public void test_3_doubleClick() {
        driver.navigate().to("https://dgotlieb.github.io/Actions/");
        WebElement doubleClick = driver.findElement(By.cssSelector("p[ondblclick='doubleClickFunction()']"));
        Actions myAction = new Actions(driver);
        myAction.doubleClick(doubleClick);
        myAction.perform();
        TakeScreenShot.setTest(test);
        TakeScreenShot.setDriver(driver);
        TakeScreenShot.takeTheImage();
        String resultForAssert = "You double clicked";
        Assert.assertEquals(driver.findElement(By.id("demo")).getText(), resultForAssert);
        //removeFocus();
    }


    @Test
    public void test_4_mouseHover() {
        driver.navigate().to("https://dgotlieb.github.io/Actions/");
        WebElement buttonElement = driver.findElement(By.id("close"));
        Actions myAct = new Actions(driver);
        myAct.moveToElement(buttonElement);
        myAct.build().perform();
        TakeScreenShot.setTest(test);
        TakeScreenShot.setDriver(driver);
        TakeScreenShot.takeTheImage();

    }
        @Test
        public void test_5_selectMultiple(){
            driver.navigate().to("https://dgotlieb.github.io/Actions/");
            List<WebElement> elementsList = driver.findElements(By.name("kind"));
            Actions builder = new Actions(driver);
            builder.clickAndHold(elementsList.get(1)).clickAndHold(elementsList.get(2)).click();
            builder.build().perform();
            TakeScreenShot.setTest(test);
            TakeScreenShot.setDriver(driver);
            TakeScreenShot.takeTheImage();
        }

    @Test
    public void test06_uploadFile() {
        driver.navigate().to("https://dgotlieb.github.io/Actions/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(250,350)");
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys("C:\\Users\\Alex\\Desktop\\java\\Class10\\HomeWork11\\src\\test\\ScreenShot\\That-You-Need-In-Your-Life.jpg");
        TakeScreenShot.setTest(test);
        TakeScreenShot.setDriver(driver);
        TakeScreenShot.takeTheImage();
    }

    @Test
    public void test07_scrollToElement(){
        driver.navigate().to("https://dgotlieb.github.io/Actions/");
        WebElement element = driver.findElement(By.id("clickMe"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        TakeScreenShot.setTest(test);
        TakeScreenShot.setDriver(driver);
        TakeScreenShot.takeTheImage();
    }

    @Test
    public void test_8_scrollToLocation(){
        driver.navigate().to("https://dgotlieb.github.io/Actions/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(250,350)");
        TakeScreenShot.setTest(test);
        TakeScreenShot.setDriver(driver);
        TakeScreenShot.takeTheImage();
    }


    @Test
    public void test_9_XML(){
        driver.navigate().to(Xml.getData("URL"));
        TakeScreenShot.setTest(test);
        TakeScreenShot.setDriver(driver);
        TakeScreenShot.takeTheImage();
    }


    @Test
    public void test_10_Alert(){
        driver.navigate().to("https://dgotlieb.github.io/Navigation/Navigation.html");
        driver.findElement(By.id("MyAlert")).click();
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();
        TakeScreenShot.setTest(test);
        TakeScreenShot.setDriver(driver);
        TakeScreenShot.takeTheImage();
    }



    @Test
    public void test_11_prompt() throws InterruptedException {
        driver.navigate().to("https://dgotlieb.github.io/Navigation/Navigation.html");
        driver.findElement(By.id("MyPrompt")).click();
        Alert prompt = driver.switchTo().alert();
        prompt.sendKeys("Alex");
        prompt.accept();
        String assertName = "Alex";
        Assert.assertEquals(assertName,driver.findElement(By.id("output")).getText());
        TakeScreenShot.setTest(test);
        TakeScreenShot.setDriver(driver);
        TakeScreenShot.takeTheImage();

    }

    @Test
    public void test_12_confirmBox(){
        driver.navigate().to("https://dgotlieb.github.io/Navigation/Navigation.html");
        driver.findElement(By.id("MyConfirm")).click();
        Alert confirmBox = driver.switchTo().alert();
        confirmBox.accept();
        String pushDone = "Confirmed";
        Assert.assertEquals(pushDone,driver.findElement(By.id("output")).getText());
        driver.findElement(By.id("MyConfirm")).click();
        confirmBox.dismiss();
        String pushCancel = "canceled";
        Assert.assertEquals(pushCancel,driver.findElement(By.id("output")).getText());
        TakeScreenShot.setTest(test);
        TakeScreenShot.setDriver(driver);
        TakeScreenShot.takeTheImage();
    }



    @Test
    public void test_13_newTab(){
        driver.navigate().to("https://dgotlieb.github.io/Navigation/Navigation.html");
        driver.findElement(By.id("openNewTab")).click();
        driver.navigate().back();
        driver.navigate().to("https://dgotlieb.github.io/Navigation/Navigation.html");
        driver.findElement(withTagName("a").below(By.id("openNewTab"))).click();
        driver.navigate().back();
        TakeScreenShot.setTest(test);
        TakeScreenShot.setDriver(driver);
        TakeScreenShot.takeTheImage();
    }



    @AfterClass
    public static void afterClass() {
        test.log(Status.INFO, "@After test " + "After test methods");
        //driver.quit();
        extent.flush();
    }
}
