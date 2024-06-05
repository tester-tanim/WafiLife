package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BrowserSetup {
    public static String browserName = System.getProperty("browser", "Chrome");
    private static final ThreadLocal<WebDriver> LOCAL_BROWSER = new ThreadLocal<>();

    public static WebDriver getBrowser() {
        return LOCAL_BROWSER.get();
    }

    public static void setBrowser(WebDriver browser) {
        LOCAL_BROWSER.set(browser);
    }

    private WebDriver initializeBrowser(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                return new ChromeDriver();
            case "firefox":
                return new FirefoxDriver();
            case "edge":
                return new EdgeDriver();
            default:
                throw new RuntimeException("Browser is not available with the given name: " + browserName);
        }
    }

    @BeforeMethod
    public void startBrowser() {
        WebDriver browser;
        try {
            browser = initializeBrowser(browserName);
            browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            browser.manage().window().maximize();
            setBrowser(browser);
        } catch (Exception e) {
            throw new RuntimeException("Failed to start the browser: " + e.getMessage(), e);
        }
    }

    @AfterMethod
    public void quitBrowser() {
        try {
            WebDriver browser = getBrowser();
            if (browser != null) {
                browser.quit();
                LOCAL_BROWSER.remove();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to quit the browser: " + e.getMessage(), e);
        }
    }
}