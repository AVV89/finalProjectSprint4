package tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import java.time.Duration;
import java.util.Objects;

public class BaseUITest {
    protected WebDriver driver;
    protected String browser;

    public BaseUITest() {
        this.browser = "chrome";
    }

    public BaseUITest(String browser) {
        this.browser = browser;
    }

    @Before
    public void startUp(){
        if (Objects.equals(browser, "Chrome")) {initChrome();} else {initSafari();}
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    public void initChrome(){
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
    }

    public void initSafari(){
        SafariOptions options = new SafariOptions();
        driver = new SafariDriver(options);
    }

}
