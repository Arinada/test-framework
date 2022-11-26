package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import steps.SearchCarSalesSteps;

import java.io.File;

public abstract class BaseTest {
    private static WebDriver driver;
    protected SearchCarSalesSteps steps;

    public static WebDriver getDriver() {
        return driver;
    }

    public BaseTest() {
        setUp();
    }

    public void setUp() {
        File file = new File("src/test/resources/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver",
                file.getAbsolutePath());
        driver = new ChromeDriver();
        driver.navigate().to( "https://auto.drom.ru/");
        driver.manage().window().maximize();
        steps = new SearchCarSalesSteps();
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}
