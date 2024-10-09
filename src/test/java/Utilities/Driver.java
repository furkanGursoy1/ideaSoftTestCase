package Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {

    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    private Driver() {
    }

    public static WebDriver get() {

        if (driverPool.get() == null) {
            String browserParamFromEnv = System.getProperty("browser");
            String browser = browserParamFromEnv == null ? ConfigurationReader.get("browser") : browserParamFromEnv;
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions opt = new ChromeOptions();
                    opt.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                    opt.addArguments("--disable-extensions");
                    opt.addArguments("disable-notifications");
                    opt.addArguments("no-sandbox");
                    opt.addArguments("--remote-allow-origins=*");
                    opt.setAcceptInsecureCerts(true);
                    driverPool.set(new ChromeDriver(opt));
                    break;
                case "chrome-headless":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                    options.addArguments("--headless");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--window-size=1920,1080");
                    options.addArguments("--start-maximized");
                    options.addArguments("--disable-extensions");
                    options.addArguments("--disable-infobars");
                    options.addArguments("--disable-notifications");
                    options.addArguments("--headless");
                    options.addArguments("--remote-allow-origins=*");
                    options.addArguments("--window-size=1920,1080");
                    driverPool.set(new ChromeDriver(options));
                    break;
                case "firefox":
                    driverPool.set(new FirefoxDriver());
                    break;
                case "firefox_headless":
                    driverPool.set(new FirefoxDriver(new FirefoxOptions().addArguments("--headless=new")));
                    break;
                case "ie":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows")) {
                        throw new WebDriverException("Your OS doesn't support Internet Explorer");
                    }
                    driverPool.set(new InternetExplorerDriver());
                    break;
                case "edge":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows")) {
                        throw new WebDriverException("Your OS doesn't support Edge");
                    }
                    driverPool.set(new EdgeDriver());
                    break;
                case "safari":
                    if (!System.getProperty("os.name").toLowerCase().contains("mac")) {
                        throw new WebDriverException("Your OS doesn't support Safari");
                    }
                    driverPool.set(new SafariDriver());
                    break;

            }
        }

        return driverPool.get();
    }

    public static void closeDriver() {
        try {
            driverPool.get().quit();
            driverPool.remove();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driverPool.get() != null) {
                driverPool.get().quit();
                driverPool.remove();
            }
        }

    }
}